package com.dm.dscatalog.resources.exceptions;

import com.dm.dscatalog.services.exceptions.DatabaseException;
import com.dm.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler
{
    /**
     * entityNotFound
     *
     * @param e ResourceNotFoundException
     * @param request HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity<StandardError> entityNotFound( ResourceNotFoundException e, HttpServletRequest request )
    {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setTimestamp( Instant.now() );
        error.setStatus( status.value() );
        error.setError( "Resource not found" );
        error.setMessage( e.getMessage() );
        error.setPath( request.getRequestURI() );

        return ResponseEntity.status( status ).body( error );
    }

    /**
     * database
     *
     * @param e DatabaseException
     * @param request HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler( DatabaseException.class )
    public ResponseEntity<StandardError> database( DatabaseException e, HttpServletRequest request )
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp( Instant.now() );
        error.setStatus( status.value() );
        error.setError( "Database exception" );
        error.setMessage( e.getMessage() );
        error.setPath( request.getRequestURI() );

        return ResponseEntity.status( status ).body( error );
    }
}
