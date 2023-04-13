package com.dm.dscatalog.repositories.factory;

import com.dm.dscatalog.dto.ProductDTO;
import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.entities.Product;

import java.time.Instant;

/**
 *
 * @author dm
 */
public class Factory
{
    /**
     * createProduct
     *
     * @return Product
     */
    public static Product createProduct()
    {
        Product product = new Product( 1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse( "2023-06-06T03:00:00Z" ) );
        product.getCategories().add( Factory.createCategory() );

        return product;
    }

    /**
     * createProductDTO
     *
     * @return ProductDTO
     */
    public static ProductDTO createProductDTO()
    {
        Product product = Factory.createProduct();
        return new ProductDTO( product, product.getCategories() );
    }

    /**
     * createCategory
     *
     * @return Category
     */
    public static Category createCategory()
    {
        return new Category( 2L, "Electronics" );
    }
}
