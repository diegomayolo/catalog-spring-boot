package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.CategoryDTO;
import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.repositories.CategoryRepository;
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
}
