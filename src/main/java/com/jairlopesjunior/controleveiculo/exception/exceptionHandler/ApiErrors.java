package com.jairlopesjunior.controleveiculo.exception.exceptionHandler;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String errors) {
        this.errors = Arrays.asList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
