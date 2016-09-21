package com.augmentum.oes.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {

    private static final long serialVersionUID = 5464564904251049282L;

    public ParameterException() {
        super();
    }

    private Map<String, String> errorFields = new HashMap<String, String>();

    public void setErrorFields (Map<String, String> errorFields) {
        this.errorFields = errorFields;
    }

    public Map<String, String> getErrorFields() {
        return errorFields;
    }

    public void addErrorFields (String fieldName, String message) {
        errorFields.put(fieldName, message);
    }

    public boolean isErrorField() {
        return errorFields.isEmpty();
    }
}
