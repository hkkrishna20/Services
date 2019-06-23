package com.task.taskmanager.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcption extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundExcption() {
        super();
    }

    public ResourceNotFoundExcption(String message) {
        super(message);
    }

    public ResourceNotFoundExcption(String message, Throwable cause) {
        super(message, cause);
    }
}