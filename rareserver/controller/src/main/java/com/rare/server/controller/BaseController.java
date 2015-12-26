package com.rare.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.rare.server.apicaller.exception.ApiCallerException;
import com.rare.server.controller.error.ControllerError;
import com.rare.server.controller.error.Error;
import com.rare.server.controller.exception.ControllerException;
import com.rare.server.controller.response.ErrorResponse;

public abstract class BaseController {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		LOG.debug("JSON conversion exception caught", ex);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ControllerError errInfo = ControllerError.UNDEFINED_ERROR;
		Throwable t = ex.getCause();
		String exceptionName = t.getClass().getName();
		String extraMessage = t.getMessage();
		ErrorResponse er = new ErrorResponse();
		LOG.debug("extraMessage: " + extraMessage);
		if (t instanceof JsonMappingException) {
			LOG.debug("JSON field invalid");
			JsonMappingException ife = (JsonMappingException) t;
			List<Error> el = new ArrayList<Error>();
			Error ei = new Error();
			for (Reference r : ife.getPath()) {
				errInfo = ControllerError.valueOf(r.getFieldName().toUpperCase() + "_" + "INVALID_FORMAT");
				ei.setErrorCode(errInfo.getCode());
				ei.setMessage(errInfo.getDescription());
				el.add(ei);
			}
			if (el.size() == 0) {
				LOG.debug("Exception not because of path, but because of json invalid");
				errInfo = ControllerError.JSON_PARSE_ERROR;
				extraMessage = "";
				er = new ErrorResponse(errInfo.getCode(), errInfo.getDescription() + extraMessage);
			} else {
				er.setErrors(el);
			}
		} else if (t instanceof JsonParseException) {
			LOG.debug("JSON parse error");
			errInfo = ControllerError.JSON_PARSE_ERROR;
			extraMessage = "";
			er = new ErrorResponse(errInfo.getCode(), errInfo.getDescription() + extraMessage);
		} else {
			LOG.error("Unknown error", ex);
			errInfo = ControllerError.UNDEFINED_ERROR;
			exceptionName = "HttpMessageNotReadableException";
			extraMessage = ex.getMessage();
			extraMessage = ": Exception name: " + exceptionName + " Message: " + extraMessage;
			er = new ErrorResponse(errInfo.getCode(), errInfo.getDescription() + extraMessage);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ErrorResponse>(er, httpStatus);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		LOG.debug("Field validation exception caught", ex);
		BindingResult result = ex.getBindingResult();
		ControllerError errInfo = ControllerError.JSON_PARSE_ERROR;
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<Error> el = new ArrayList<Error>();
		for (Object error : result.getAllErrors()) {
			Error ei = new Error();
			if (error instanceof FieldError) {
				FieldError fieldError = (FieldError) error;
				String field = fieldError.getField();
				switch (fieldError.getCode()) {
				case "Future":
					errInfo = ControllerError.valueOf(field.toUpperCase() + "_" + "EXPIRED");
					break;
				case "Size":
				case "Max":
				case "Min":
				case "Pattern":
					errInfo = ControllerError.valueOf(field.toUpperCase() + "_" + "INVALID_FORMAT");
					break;
				case "NotBlank":
				case "NotNull":
					errInfo = ControllerError.valueOf(field.toUpperCase() + "_" + "MISSING");
					break;
				default:
					errInfo = ControllerError.UNDEFINED_ERROR;
				}
				ei.setErrorCode(errInfo.getCode());
				ei.setMessage(errInfo.getDescription());
				if (errInfo == ControllerError.UNDEFINED_ERROR) {
					LOG.error("Undefined error occurred: ", error);
					ei.setMessage(" [" + fieldError.getCode() + "] " + ex.getMessage());
					httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			} else {
				LOG.error("Undefined error occurred: ", error);
				ObjectError objectError = (ObjectError) error;
				ei.setMessage("[" + objectError.getCode() + "]" + objectError.getDefaultMessage());
				ei.setErrorCode(ControllerError.UNDEFINED_ERROR.getCode());
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			el.add(ei);
		}
		ErrorResponse er = new ErrorResponse();
		er.setErrors(el);
		return new ResponseEntity<ErrorResponse>(er, httpStatus);
	}

	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		LOG.debug(ex.getClass().getName() + " caught", ex);
		LOG.debug("parameterName = " + ex.getParameterName());
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ControllerError.valueOf(ex.getParameterName().toUpperCase() + "_REQUIRED_IN_URL")),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(ControllerException.class)
	public ResponseEntity<ErrorResponse> handleControllerException(ControllerException ex) {
		LOG.debug(ex.getClass().getName() + " caught", ex);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getControllerError()), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(ApiCallerException.class)
	public ResponseEntity<ErrorResponse> handleApiCallerException(ApiCallerException ex) {
		LOG.debug(ex.getClass().getName() + " caught", ex);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ControllerError.ERROR_IN_API_CALLER),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
