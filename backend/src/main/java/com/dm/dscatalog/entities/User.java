package com.dm.dscatalog.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author dm
 */
@Entity
@Table( name = "tb_user" )
public class User implements UserDetails, Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String lastName;

    @Column( unique = true )
    private String email;
    private String password;

    @ManyToMany( fetch = FetchType.EAGER )
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
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return roles.stream().map( role -> new SimpleGrantedAuthority( role.getAuthority() ) ).collect( Collectors.toList() );
    }

    /**
     * @return
     */
    @Override
    public String getUsername()
    {
        return email;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled()
    {
        return true;
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
