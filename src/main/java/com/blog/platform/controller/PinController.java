package com.blog.platform.controller;

import com.blog.platform.payload.request.PinRequest;
import com.blog.platform.payload.response.PinResponse;
import com.blog.platform.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PinController {

    @Autowired
    private final PinService pinService;

    @PostMapping("/pins")
    public ResponseEntity<HttpStatus> createPin(@RequestBody PinRequest pinRequest) {
        return pinService.createPin(pinRequest);
    }

    @PutMapping("/pins/{id}")
    public ResponseEntity<HttpStatus> updatePin(@PathVariable Long id, @RequestBody PinRequest pinRequest) {
        return pinService.updatePin(id, pinRequest);
    }

    @GetMapping("/pins")
    public ResponseEntity<List<PinResponse>> getAllPins() {
        return pinService.getAllPins();
    }

    @GetMapping("pins/{id}")
    public ResponseEntity<PinResponse> getPinById(@PathVariable Long id) {
        return pinService.getPinById(id);
    }

    @DeleteMapping("pins/{id}")
    public ResponseEntity<HttpStatus> deletePinById(@PathVariable Long id) {
        return pinService.deletePinById(id);
    }

    public ResponseEntity<HttpStatus> deleteAllPins() {
        return pinService.deleteAllPins();
    }
}
