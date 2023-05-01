package com.dm.dscatalog.resources.exceptions;

import java.io.Serializable;

/**
 *
 * @author dm
 */
public class FieldMessage implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    /**
     *
     * FieldMessage
     */
    public FieldMessage() {}

    /**
     *
     * FieldMessage
     *
     * @param fieldName String
     * @param message String
     */
    public FieldMessage( String fieldName, String message )
    {
        this.fieldName = fieldName;
        this.message   = message;
    }

    /**
     * getFieldName
     *
     * @return String
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * setFieldName
     *
     * @param fieldName String
     */
    public void setFieldName( String fieldName )
    {
        this.fieldName = fieldName;
    }

    /**
     * getMessage
     *
     * @return String
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * setMessage
     *
     * @param message String
     */
    public void setMessage( String message )
    {
        this.message = message;
    }
}
