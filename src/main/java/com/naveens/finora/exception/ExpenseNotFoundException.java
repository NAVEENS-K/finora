package com.naveens.finora.exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(String message){
        super(message);
    }
}
