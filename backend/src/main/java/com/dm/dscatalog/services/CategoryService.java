package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.CategoryDTO;
import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.repositories.CategoryRepository;
import com.dm.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    * findAll
    * 
    * @return List<Category>
    */
   @Transactional( readOnly = true )
   public List<CategoryDTO> findAll()
   {
      List<Category> categories = categoryRepository.findAll();

      return categories.stream().map( c -> new CategoryDTO( c ) ).collect( Collectors.toList() );
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
                               .orElseThrow( () -> new EntityNotFoundException( "Entity not found" ) );
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
}
