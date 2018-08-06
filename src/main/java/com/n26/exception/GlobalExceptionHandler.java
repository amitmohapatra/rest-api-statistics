package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.n26.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidJsonException.class)
	public ResponseEntity<?> handleException(InvalidJsonException exception) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<?> handleException(JsonParseException exception) {
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(OlderThan60Exception.class)
	public ResponseEntity<?> handleException(OlderThan60Exception exception) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleException(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception exception) {
		return new ErrorResponse(500, exception.getMessage().toString());
	}
}