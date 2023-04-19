package com.dm.dscatalog.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author dm
 */
@Entity
@Table( name = "tb_user" )
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable( name = "tb_user_role",
            joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private Set<Role> roles = new HashSet<>();

    /**
     * User
     */
    public User() {}

    /**
     * User
     *
     * @param id Long
     * @param firstName String
     * @param lastName String
     * @param email String
     * @param password String
     */
    public User( Long id, String firstName, String lastName, String email, String password )
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
     * getPassword
     *
     * @return String
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * setPassword
     *
     * @param password String
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * getRoles
     *
     * @return Set<Role>
     */
    public Set<Role> getRoles()
    {
        return roles;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        User user = ( User ) o;
        return Objects.equals( id, user.id );
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
