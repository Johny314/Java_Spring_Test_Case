package com.greenData.JavaTestCase.exception;

public class ClientWithNameDoesntExistException extends Exception{
    public ClientWithNameDoesntExistException(String message) {
        super(message);
    }
}
