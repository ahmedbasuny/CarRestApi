package com.evolvice.task.evolvicecar.car.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarDataNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CarDataNotValidException() {
		super("Car data is not valid. Please make sure all required data are provided.");
	}
}
