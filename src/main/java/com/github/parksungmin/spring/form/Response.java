package com.github.parksungmin.spring.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response<T> {
    private final T data;
    private final Map<String, List<String>> errors;

    public Response(T data) {
        this.data = data;
        errors = new HashMap<>();
    }

    public Response(Map<String, List<String>> errors) {
        data = null;
        this.errors = errors;
    }

    public Response(T data, Map<String, List<String>> errors) {
        this.data = data;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
