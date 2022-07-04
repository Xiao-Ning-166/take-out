package com.example.takeout.exception;

/**
 * @author xiaoning
 * @date 2022/07/04
 */
public class TakeOutException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TakeOutException(String message){
        super(message);
    }

    public TakeOutException(Throwable cause)
    {
        super(cause);
    }

    public TakeOutException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
