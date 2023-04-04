package com.dm.dscatalog.dto;

import com.dm.dscatalog.entities.Category;
import com.dm.dscatalog.entities.Product;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;
    private List<CategoryDTO> categories = new ArrayList<>();

    /**
     * ProductDTO
     *
     */
    public ProductDTO() {}

    /**
     * ProductDTO
     *
     * @param id Long
     * @param name String
     * @param description String
     * @param price Double
     * @param imgUrl String
     * @param date Instant
     */
    public ProductDTO( Long id, String name, String description, Double price, String imgUrl, Instant date )
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    /**
     * ProductDTO
     *
     * @param product Product
     */
    public ProductDTO( Product product )
    {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        this.date = product.getDate();
    }

    /**
     * ProductDTO
     *
     * @param product Product
     * @param categories Set<Category>
     */
    public ProductDTO( Product product, Set<Category> categories )
    {
        this( product );
        categories.forEach( cat -> this.categories.add( new CategoryDTO( cat ) ) );
    }

    /**
     * getId
     *
     * @return Long
     */
    public Long getId()
    {
        return id;
    }

    /**
     * setId
     *
     * @param id Long
     */
    public void setId( Long id )
    {
        this.id = id;
    }

    /**
     * getName
     *
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * setName
     *
     * @param name String
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * getDescription
     *
     * @return String
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * setDescription
     *
     * @param description String
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * getPrice
     *
     * @return Double
     */
    public Double getPrice()
    {
        return price;
    }

    /**
     * setPrice
     *
     * @param price Double
     */
    public void setPrice( Double price )
    {
        this.price = price;
    }

    /**
     * getImgUrl
     *
     * @return String
     */
    public String getImgUrl()
    {
        return imgUrl;
    }

    /**
     * setImgUrl
     *
     * @param imgUrl String
     */
    public void setImgUrl( String imgUrl )
    {
        this.imgUrl = imgUrl;
    }

    /**
     * getDate
     *
     * @return Instant
     */
    public Instant getDate()
    {
        return date;
    }

    /**
     * setDate
     *
     * @param date Instant
     */
    public void setDate( Instant date )
    {
        this.date = date;
    }

    /**
     * getCategories
     *
     * @return List<CategoryDTO>
     */
    public List<CategoryDTO> getCategories()
    {
        return categories;
    }

    /**
     * setCategories
     *
     * @param categories List<CategoryDTO>
     */
    public void setCategories( List<CategoryDTO> categories )
    {
        this.categories = categories;
    }
}
