package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.ProductDTO;
import com.dm.dscatalog.entities.Product;
import com.dm.dscatalog.repositories.CategoryRepository;
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

   @Autowired
   private CategoryRepository categoryRepository;

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
      // return product with categories
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
        buildProduct( product, dto );

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
         buildProduct( product, dto );

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


   /**
    * buildProduct
    *
    * @param product Product
    * @param productDTO ProductDTO
    */
   private void buildProduct( Product product, ProductDTO productDTO )
   {
      product.setName( productDTO.getName() );
      product.setDescription( productDTO.getDescription() );
      product.setDate( productDTO.getDate() );
      product.setImgUrl( productDTO.getImgUrl() );
      product.setPrice( productDTO.getPrice() );

      product.getCategories().clear();

      // Add categories in product
      productDTO.getCategories().forEach( categoryDTO ->
      {
         product.getCategories().add( categoryRepository.getOne( categoryDTO.getId() ) );
      } );
   }
}
