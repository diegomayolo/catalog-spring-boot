package com.dm.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author dm
 */
@Entity
@Table( name = "tb_category" )
public class Category implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;

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
