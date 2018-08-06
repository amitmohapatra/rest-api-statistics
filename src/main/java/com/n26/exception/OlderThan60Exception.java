package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "The transaction is older than 60 seconds")
public class OlderThan60Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
