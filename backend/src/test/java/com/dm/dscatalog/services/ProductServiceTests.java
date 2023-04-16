package com.dm.dscatalog.services;

import com.dm.dscatalog.dto.ProductDTO;
import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.entities.Product;
import com.dm.dscatalog.repositories.CategoryRepository;
import com.dm.dscatalog.repositories.ProductRepository;
import com.dm.dscatalog.services.exceptions.ResourceNotFoundException;
import factory.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

/**
 * The type Product service tests.
 *
 * @author dm
 */
@ExtendWith( SpringExtension.class )
public class ProductServiceTests
{
    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Product product;
    private ProductDTO productDTO;
    private Category category;
    private PageImpl<Product> page;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    void setUp() throws Exception
    {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        product = Factory.createProduct();
        productDTO = Factory.createProductDTO();
        category = Factory.createCategory();
        page = new PageImpl<>( List.of( product ) );

        // Simulate a pdocuctRepository call
        Mockito.when( productRepository.save( ArgumentMatchers.any() ) ).thenReturn( product );

        Mockito.when( productRepository.findAll( (Pageable)ArgumentMatchers.any() ) ).thenReturn( page );

        Mockito.when( productRepository.findById( existingId ) ).thenReturn( Optional.of( product ) );
        Mockito.when( productRepository.findById( nonExistingId ) ).thenReturn( Optional.empty() );

        Mockito.doNothing().when( productRepository ).deleteById( existingId );
        Mockito.doThrow( EmptyResultDataAccessException.class ).when( productRepository ).deleteById( nonExistingId );
        Mockito.doThrow( DataIntegrityViolationException.class ).when( productRepository ).deleteById( dependentId );

        Mockito.when( productRepository.getOne( existingId ) ).thenReturn( product );
        Mockito.when( productRepository.getOne( nonExistingId ) ).thenThrow( EntityNotFoundException.class );

        // Simulate a categoryRepository call
        Mockito.when( categoryRepository.getOne( existingId ) ).thenReturn( category );
        Mockito.when( categoryRepository.getOne( existingId ) ).thenThrow( EntityNotFoundException.class );
    }

    /**
     * Find all paged should return page.
     */
    @Test
    public void findAllPagedShouldReturnPage()
    {
        Pageable pageable = PageRequest.of( 0, 10 );
        Page<ProductDTO> result = service.findAllPaged( pageable );

        Assertions.assertNotNull( result );
        Mockito.verify( productRepository, Mockito.times( 1 ) ).findAll( pageable );
    }

    /**
     * Find by id should return product dto when id exists.
     */
    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists()
    {
        ProductDTO result = service.findById( existingId );

        Assertions.assertNotNull( result );
    }

    /**
     * Find by id should throw resource not found exception when id does not exists.
     */
    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists()
    {
        Assertions.assertThrows( ResourceNotFoundException.class, () -> {
            service.findById( nonExistingId );
        } );
    }

    /**
     * Update should return product dto when id exists.
     */
    @Test
    public void updateShouldReturnProductDTOWhenIdExists()
    {
        ProductDTO result = service.update( existingId, productDTO );

        Assertions.assertNotNull( result );
    }

    /**
     * Update should throw resource not found exception when id does not exists.
     */
    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists()
    {
        Assertions.assertThrows( ResourceNotFoundException.class, () -> {
            service.update( nonExistingId, productDTO );
        } );
    }

    /**
     * Delete should throw empty result data access exception when id does not exists.
     */
    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists()
    {
        Assertions.assertThrows( EmptyResultDataAccessException.class, () ->
        {
            service.delete( nonExistingId );
        } );

        Mockito.verify( productRepository, Mockito.times( 1 ) ).deleteById( nonExistingId );
    }

    /**
     * Delete should do nothing when id exists.
     */
    @Test
    public void deleteShouldDoNothingWhenIdExists()
    {
        Assertions.assertDoesNotThrow( () -> {
            service.delete( existingId );
        } );

        Mockito.verify( productRepository, Mockito.times( 1 ) ).deleteById( existingId );
    }
}
