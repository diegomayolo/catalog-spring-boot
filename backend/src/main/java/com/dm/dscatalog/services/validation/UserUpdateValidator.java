package com.dm.dscatalog.services.validation;

import com.dm.dscatalog.dto.UserUpdateDTO;
import com.dm.dscatalog.entities.User;
import com.dm.dscatalog.repositories.UserRepository;
import com.dm.dscatalog.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @auhtor dm
 */
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO>
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid( UserUpdateDTO dto, ConstraintValidatorContext context )
    {
        Map<String, String> uriVars = ( Map<String, String> ) request.getAttribute( HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE );
        long userId = Long.parseLong( uriVars.get( "id" ) );

        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail( dto.getEmail() );

        // testes de validação, acrescentando objetos FieldMessage à lista
        if ( user != null && userId != user.getId() )
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
