package com.dm.dscatalog.dto;

import com.dm.dscatalog.services.validation.UserUpdateValid;

import java.io.Serial;

/**
 *
 * @author dm
 */
@UserUpdateValid
public class UserUpdateDTO extends UserDTO
{
    @Serial
    private static final long serialVersionUID = 1L;

}
