package com.sample.feedapi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super(String.format("No entry found with id: <%s>", id));
    }
}
