package com.example.uidesign;

public class MinimumEightCharactersRequiredException extends PasswordException {
    public MinimumEightCharactersRequiredException(String errorMessage) {
        super(errorMessage);
    }
}

