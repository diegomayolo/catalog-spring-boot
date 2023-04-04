package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.CategoryDTO;
import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.repositories.CategoryRepository;
import com.dm.dscatalog.services.exceptions.DatabaseException;
import com.dm.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dm
 */
@Service
public class CategoryService
{
   @Autowired
   private CategoryRepository categoryRepository;

   /**
    * findAllPaged
    *
    * @return Page<Category>
    */
   @Transactional( readOnly = true )
   public Page<CategoryDTO> findAllPaged( PageRequest pageRequest )
   {
      Page<Category> categories = categoryRepository.findAll( pageRequest );

      return categories.map( c -> new CategoryDTO( c ) );
   }

   /**
    * findById
    *
    * @param id
    * @return CategoryDTO
    */
   @Transactional( readOnly = true )
   public CategoryDTO findById( Long id )
   {
      return categoryRepository.findById(id )
                               .map(CategoryDTO::new )
                               .orElseThrow( () -> new ResourceNotFoundException( "Entity not found" ) );
   }

   /**
    * insert
    *
    * @param dto CategoryDTO
    * @return CategoryDTO
    */
   @Transactional
   public CategoryDTO insert( CategoryDTO dto )
   {
        Category category = new Category();
        category.setName( dto.getName() );

        category = categoryRepository.save( category );

        return new CategoryDTO( category );
   }

   /**
    * update
    *
    * @param id Long
    * @param dto CategoryDTO
    * @return CategoryDTO
    */
   @Transactional
   public CategoryDTO update( Long id, CategoryDTO dto )
   {
      try
      {
         Category category = categoryRepository.getOne( id );
         category.setName( dto.getName() );

         category = categoryRepository.save( category );
         return new CategoryDTO( category );
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
      // if (!categoryRepository.findById(id).isPresent()) {
      //    throw new ResourceNotFoundException("Id not found " + id);
      // }

      try
      {
         categoryRepository.deleteById(id);
      }

      catch ( DataIntegrityViolationException e )
      {
         throw new DatabaseException( "Integrity violation" );
      }
   }
}
