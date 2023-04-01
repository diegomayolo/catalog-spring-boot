package com.dm.dscatalog.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    /**
     *
     * StandardError
     */
    public StandardError() {}

    /**
     * getTimestamp
     *
     * @return Instant
     */
    public Instant getTimestamp()
    {
        return timestamp;
    }

    /**
     * setTimestamp
     *
     * @param timestamp Instant
     */
    public void setTimestamp( Instant timestamp )
    {
        this.timestamp = timestamp;
    }

    /**
     * getStatus
     *
     * @return Integer
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * setStatus
     *
     * @param status Integer
     */
    public void setStatus( Integer status )
    {
        this.status = status;
    }

    /**
     * getError
     *
     * @return String
     */
    public String getError()
    {
        return error;
    }

    /**
     * setError
     *
     * @param error String
     */
    public void setError( String error )
    {
        this.error = error;
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

    /**
     * getPath
     *
     * @return String
     */
    public String getPath()
    {
        return path;
    }

    /**
     * setPath
     *
     * @param path String
     */
    public void setPath( String path )
    {
        this.path = path;
    }
}
