package com.dm.dscatalog.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 *
 * @author dm
 */
@Entity
@Table( name = "tb_role" )
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String authority;

    /**
     * Role
     */
    public Role()
    {
    }

    /**
     * Role
     *
     * @param id Long
     * @param authority String
     */
    public Role( Long id, String authority )
    {
        this.id = id;
        this.authority = authority;
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
     * getAuthority
     *
     * @return String
     */
    public String getAuthority()
    {
        return authority;
    }

    /**
     * setAuthority
     *
     * @param authority String
     */
    public void setAuthority( String authority )
    {
        this.authority = authority;
    }
}
