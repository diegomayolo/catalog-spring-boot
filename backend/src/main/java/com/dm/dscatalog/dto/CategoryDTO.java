package com.dm.dscatalog.dto;

import com.dm.dscatalog.entities.Category;

import java.io.Serializable;

/**
 *
 * @author dm
 */
public class CategoryDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    /**
     * CategoryDTO
     */
    public CategoryDTO()
    {
    }

    /**
     * CategoryDTO
     *
     * @param id Long
     * @param name String
     */
    public CategoryDTO( Long id, String name )
    {
        this.id = id;
        this.name = name;
    }

    /**
     * CategoryDTO
     *
     * @param entity Category
     */
    public CategoryDTO( Category entity )
    {
        this.id = entity.getId();
        this.name = entity.getName();
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
}
