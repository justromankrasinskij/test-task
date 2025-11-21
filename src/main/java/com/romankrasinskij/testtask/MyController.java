package com.romankrasinskij.testtask;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @Operation()
    @GetMapping("/api/excel")
    public ResponseEntity getMinNumber(
            @Parameter()
            @RequestParam String path,
            @Parameter()
            @RequestParam int n) {
        try {
            int minNumber = service.getMinNumberFromFile(path, n);
            return ResponseEntity.ok(minNumber);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
