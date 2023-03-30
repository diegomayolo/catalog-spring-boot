package com.dm.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.repositories.CategoryRepository;

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
   public List<Category> findAll()
   {
      return categoryRepository.findAll();
   }   
}
