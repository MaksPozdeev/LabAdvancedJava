package com.maksim.pozdeev.calculator.exceptions;

public class NonexistentOperationException extends RuntimeException{
    public NonexistentOperationException(String mess){
        super(mess);
    }
}
