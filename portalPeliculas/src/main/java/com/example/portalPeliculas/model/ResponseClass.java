package com.example.portalPeliculas.model;

public class ResponseClass {

    private String message;

    public ResponseClass(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
