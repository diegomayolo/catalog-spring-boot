package com.dm.dscatalog.entities;

import jakarta.persistence.*;

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
@Table( name = "tb_category" )
public class Category implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;

    @Column( columnDefinition = "TIMESTAMP WITHOUT TIME ZONE" )
    private Instant createdAt;

    @Column( columnDefinition = "TIMESTAMP WITHOUT TIME ZONE" )
    private Instant updatedAt;

    @ManyToMany( mappedBy = "categories" )
    private Set<Product> products = new HashSet<>();

    /**
     * Category
     *
     */
    public Category() {}

    /**
     * Category
     *
     * @param id
     * @param name
     */
    public Category( Long id, String name )
    {
        this.id = id;
        this.name = name;
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
     * getCreatedAt
     *
     * @return Instant
     */
    public Instant getCreatedAt()
    {
        return createdAt;
    }

    /**
     * getUpdatedAt
     *
     * @return Instant
     */
    public Instant getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * prePersist
     *
     */
    @PrePersist
    public void prePersist()
    {
        createdAt = Instant.now();
    }

    /**
     * preUpdate
     *
     */
    @PreUpdate
    public void preUpdate()
    {
        updatedAt = Instant.now();
    }

     /**
     * getProducts
     *
     * @return Set<Product>
     */
    public Set<Product> getProducts()
    {
        return products;
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
        Category category = ( Category ) o;
        return Objects.equals( id, category.id ) && Objects.equals( name, category.name );
    }

    /**
     * hashCode
     *
     * @return int
     */
    @Override
    public int hashCode()
    {
        return Objects.hash( id, name );
    }
}
