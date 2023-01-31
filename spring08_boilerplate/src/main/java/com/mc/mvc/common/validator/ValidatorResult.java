package com.mc.mvc.common.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ValidatorResult {
	
	private Map<String, FieldError> errors = new HashMap<>();
	
	public void addErrors(Errors errors) {
		for (FieldError error : errors.getFieldErrors()) {
			this.errors.put(error.getField(), error);
		}
	}

	public Map<String, FieldError> getErrors() {
		return errors;
	}

	public FieldError getError(String fieldName) {
		return errors.get(fieldName);
	}

}
