package com.dm.dscatalog.services.validation;

import com.dm.dscatalog.dto.UserInsertDTO;
import com.dm.dscatalog.entities.User;
import com.dm.dscatalog.repositories.UserRepository;
import com.dm.dscatalog.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @auhtor dm
 */
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO>
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid( UserInsertDTO dto, ConstraintValidatorContext context )
    {
        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail( dto.getEmail() );

        // testes de validação, acrescentando objetos FieldMessage à lista
        if ( user != null )
        {
            list.add( new FieldMessage( "email", "email already exists" ) );
        }

        for ( FieldMessage e : list )
        {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( e.getMessage() ).addPropertyNode( e.getFieldName() ).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
