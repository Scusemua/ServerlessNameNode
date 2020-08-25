package com.gmail.benrcarver.Fission;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import io.fission.Function;
import io.fission.Context;

// Handler value: example.Handler
public class Handler implements Function {

    @Override
    public ResponseEntity<?> call(RequestEntity req, Context context) {
        return ResponseEntity.ok("Hello World!");
    }
}