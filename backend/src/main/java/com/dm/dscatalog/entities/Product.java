package com.dm.dscatalog.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 *
 * @author dm
 */
@Entity
@Table( name = "tb_product" )
public class Product implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;

    @Column( columnDefinition = "TEXT" )
    private String description;
    private Double price;
    private String imgUrl;

    @Column( columnDefinition = "TIMESTAMP WITHOUT TIME ZONE" )
    private Instant date;

    @ManyToMany
    @JoinTable( name = "tb_product_category",
                joinColumns = @JoinColumn( name = "product_id" ),
                inverseJoinColumns = @JoinColumn( name = "category_id" ) )
    Set<Category> categories = new HashSet<>();

    /**
     * Product
     *
     */
    public Product() {}

    /**
     * Product
     *
     * @param id Long
     * @param name String
     * @param description String
     * @param price Double
     * @param imgUrl String
     * @param date Instant
     */
    public Product( Long id, String name, String description, Double price, String imgUrl, Instant date )
    {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.price       = price;
        this.imgUrl      = imgUrl;
        this.date        = date;
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
     * @return Set<Category>
     */
    public Set<Category> getCategories()
    {
        return categories;
    }

    /**
     * equals
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Product product = ( Product ) o;
        return Objects.equals( id, product.id );
    }

    /**
     * hashCode
     *
     * @return int
     */
    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
