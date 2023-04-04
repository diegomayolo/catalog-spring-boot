package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.ProductDTO;
import com.dm.dscatalog.entities.Product;
import com.dm.dscatalog.repositories.ProductRepository;
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
public class ProductService
{
   @Autowired
   private ProductRepository productRepository;

   /**
    * findAllPaged
    *
    * @return Page<Product>
    */
   @Transactional( readOnly = true )
   public Page<ProductDTO> findAllPaged( PageRequest pageRequest )
   {
      Page<Product> products = productRepository.findAll( pageRequest );

      return products.map( c -> new ProductDTO( c ) );
   }

   /**
    * findById
    *
    * @param id
    * @return ProductDTO
    */
   @Transactional( readOnly = true )
   public ProductDTO findById( Long id )
   {
      return productRepository.findById(id )
                              .map(product -> { return new ProductDTO( product, product.getCategories() ); } )
                              .orElseThrow( () -> new ResourceNotFoundException( "Entity not found" ) );
   }

   /**
    * insert
    *
    * @param dto ProductDTO
    * @return ProductDTO
    */
   @Transactional
   public ProductDTO insert( ProductDTO dto )
   {
        Product product = new Product();
//        product.setName( dto.getName() );

        product = productRepository.save( product );

        return new ProductDTO( product );
   }

   /**
    * update
    *
    * @param id Long
    * @param dto ProductDTO
    * @return ProductDTO
    */
   @Transactional
   public ProductDTO update( Long id, ProductDTO dto )
   {
      try
      {
         Product product = productRepository.getOne( id );
//         product.setName( dto.getName() );

         product = productRepository.save( product );
         return new ProductDTO( product );
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
         productRepository.deleteById(id);
      }

      catch ( DataIntegrityViolationException e )
      {
         throw new DatabaseException( "Integrity violation" );
      }
   }
}
