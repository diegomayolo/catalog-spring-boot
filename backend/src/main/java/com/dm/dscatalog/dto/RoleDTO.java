package com.dm.dscatalog.dto;

import com.dm.dscatalog.entities.Role;

import java.io.Serializable;

/**
 *
 * @author dm
 */
public class RoleDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    /**
     * RoleDTO
     */
    public RoleDTO()
    {
    }

    /**
     * RoleDTO
     *
     * @param id Long
     * @param authority String
     */
    public RoleDTO( Long id, String authority )
    {
        this.id = id;
        this.authority = authority;
    }

    /**
     * RoleDTO
     *
     * @param role Role
     */
    public RoleDTO( Role role )
    {
        id = role.getId();
        authority = role.getAuthority();
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
