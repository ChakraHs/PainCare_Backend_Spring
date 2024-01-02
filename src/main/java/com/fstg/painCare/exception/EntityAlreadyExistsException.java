package com.fstg.painCare.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}
	public EntityAlreadyExistsException(String message) {
		super(message);
	}
}
