package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.UserDTO;
import com.dm.dscatalog.dto.UserInsertDTO;
import com.dm.dscatalog.entities.User;
import com.dm.dscatalog.repositories.RoleRepository;
import com.dm.dscatalog.repositories.UserRepository;
import com.dm.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dm
 */
@Service
public class UserService
{
   @Autowired
    private BCryptPasswordEncoder passwordEncoder;

   @Autowired
   private UserRepository userRepository;

   @Autowired
    private RoleRepository roleRepository;

   /**
    * findAllPaged
    *
    * @return Page<User>
    */
   @Transactional( readOnly = true )
   public Page<UserDTO> findAllPaged( Pageable pageable )
   {
      Page<User> users = userRepository.findAll( pageable );

      return users.map( c -> new UserDTO( c ) );
   }

   /**
    * findById
    *
    * @param id Long
    * @return UserDTO
    */
   @Transactional( readOnly = true )
   public UserDTO findById( Long id )
   {
      return userRepository.findById(id )
                              .map( UserDTO::new )
                              .orElseThrow( () -> new ResourceNotFoundException( "Entity not found" ) );
   }

   /**
    * insert
    *
    * @param dto UserDTO
    * @return UserDTO
    */
   @Transactional
   public UserDTO insert( UserInsertDTO dto )
   {
        User user = new User();
        buildUser( user, dto );
        user.setPassword( passwordEncoder.encode( dto.getPassword() ) );

        user = userRepository.save( user );

        return new UserDTO( user );
   }

   /**
    * update
    *
    * @param id Long
    * @param dto UserDTO
    * @return UserDTO
    */
   @Transactional
   public UserDTO update( Long id, UserDTO dto )
   {
      try
      {
         User user = userRepository.getReferenceById( id );
         buildUser( user, dto );

         user = userRepository.save( user );
         return new UserDTO( user );
      }

      catch ( EntityNotFoundException e )
      {
          throw new ResourceNotFoundException( "Id not found " + id );
      }
   }

   /**
    * delete
    *
    * @param id Long
    */
   public void delete( Long id )
   {
      try
      {
         userRepository.deleteById(id);
      }

      catch ( DataIntegrityViolationException e )
      {
         throw new ResourceNotFoundException( "Integrity violation" );
      }
   }


   /**
    * buildUser
    *
    * @param user User
    * @param userDTO UserDTO
    */
   private void buildUser( User user, UserDTO userDTO )
   {
      user.setFirstName( userDTO.getFirstName() );
      user.setLastName( userDTO.getLastName() );
      user.setEmail( userDTO.getEmail() );

      user.getRoles().clear();

      // Add roles in user
      userDTO.getRoles().forEach( roleDTO ->
      {
         user.getRoles().add( roleRepository.getReferenceById( roleDTO.getId() ) );
      } );
   }
}
