package com.info.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.info.product.model.Message;
import com.info.product.model.RestResponse;
import com.info.product.util.RestHelper;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ ApplicationException.class })
	@ResponseBody
	public ResponseEntity<RestResponse> handleEmptyListException(final Exception ex) {
		Message msg = resolveError(ex);
		return RestHelper.responseError(msg.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	private static Message resolveError(final Exception e) {
		return new Message(e.getMessage());
	}

}
