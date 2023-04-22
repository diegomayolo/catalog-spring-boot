package com.dm.dscatalog.dto;

import java.io.Serial;

/**
 *
 * @author dm
 */
public class UserInsertDTO extends UserDTO
{
    @Serial
    private static final long serialVersionUID = 1L;

    private String password;

    /**
     * UserInsertDTO
     */
    public UserInsertDTO()
    {
        super();
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
}
