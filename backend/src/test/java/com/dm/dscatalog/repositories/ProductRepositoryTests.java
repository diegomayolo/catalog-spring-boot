package com.dm.dscatalog.repositories;

import com.dm.dscatalog.entities.Product;
import factory.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

/**
 * The type Product repository tests.
 *
 * @author dm
 */
@DataJpaTest
public class ProductRepositoryTests
{
    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    void setUp() throws Exception
    {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    /**
     * Find should find object when id exists.
     */
    @Test
    public void findShouldFindObjectWhenIdExists()
    {
        Optional<Product> optional = repository.findById( existingId );

        Assertions.assertTrue( optional.isPresent() );
    }

    /**
     * Find should find object when id non exists.
     */
    @Test
    public void findShouldFindObjectWhenIdNonExists()
    {
        Optional<Product> optional = repository.findById( nonExistingId );

        Assertions.assertFalse( optional.isPresent() );
    }

    /**
     * Save should persist with autoincrement when id is null.
     */
    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull()
    {
        // Arrange
        Product product = Factory.createProduct();
        product.setId( null );

        // Act
        product = repository.save( product );

        // Assert
        Assertions.assertNotNull( product.getId() );
        Assertions.assertEquals( countTotalProducts + 1, product.getId() );
    }

    /**
     * Delete should delete object when id exists.
     */
    @Test
    public void deleteShouldDeleteObjectWhenIdExists()
    {
        // Act
        repository.deleteById( existingId );

        // Assert
        Optional<Product> optional = repository.findById( existingId );
        Assertions.assertFalse( optional.isPresent() );
    }
}
