package com.dm.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dm
 */
public class Category implements Serializable
{
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
