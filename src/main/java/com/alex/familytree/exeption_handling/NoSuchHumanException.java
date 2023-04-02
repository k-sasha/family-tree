package com.alex.familytree.exeption_handling;

public class NoSuchHumanException extends RuntimeException{
    public NoSuchHumanException(String message) {
        super(message);
    }
}
