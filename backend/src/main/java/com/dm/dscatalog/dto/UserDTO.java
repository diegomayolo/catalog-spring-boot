package com.dm.dscatalog.dto;

import com.dm.dscatalog.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "can't be empty")
    private String firstName;

    @NotBlank(message = "can't be empty")
    private String lastName;

    @Email(message = "invalid email")
    private String email;

    Set<RoleDTO> roles = new HashSet<>();

    /**
     * UserDTO
     */
    public UserDTO()
    {
    }

    /**
     * UserDTO
     *
     * @param id Long
     * @param firstName String
     * @param lastName String
     * @param email String
     */
    public UserDTO( Long id, String firstName, String lastName, String email )
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * UserDTO
     *
     * @param entity User
     */
    public UserDTO( User entity )
    {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();

        entity.getRoles().forEach( role -> this.roles.add( new RoleDTO( role ) ) );
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
     * getFirstName
     *
     * @return String
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * setFirstName
     *
     * @param firstName String
     */
    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    /**
     * getLastName
     *
     * @return String
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * setLastName
     *
     * @param lastName String
     */
    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    /**
     * getEmail
     *
     * @return String
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * setEmail
     *
     * @param email String
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * getRoles
     *
     * @return Set<RoleDTO>
     */
    public Set<RoleDTO> getRoles()
    {
        return roles;
    }
}
