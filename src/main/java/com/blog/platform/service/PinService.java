package com.blog.platform.service;

import com.blog.platform.entity.Pin;
import com.blog.platform.exception.PinNotFoundException;
import com.blog.platform.mapper.PinMapper;
import com.blog.platform.payload.request.PinRequest;
import com.blog.platform.payload.response.PinResponse;
import com.blog.platform.repository.PinRepository;
import com.blog.platform.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinService {

    private PinRepository pinRepository;

    public ResponseEntity<HttpStatus> createPin(PinRequest pinRequest) {
        Pin pin = PinMapper.convertPinRequest(pinRequest);
        pinRepository.save(pin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updatePin(Long id, PinRequest pinRequest) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new PinNotFoundException(Constants.PIN_NOT_FOUND));
        PinMapper.updatePin(pin, pinRequest);
        pinRepository.save(pin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<PinResponse>> getAllPins() {
        List<PinResponse> pins = pinRepository.findAll()
                .stream()
                .map(PinMapper::convertPin)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pins, HttpStatus.OK);
    }

    public ResponseEntity<PinResponse> getPinById(Long id) {
        PinResponse pinResponse = pinRepository.findById(id)
                .map(PinMapper::convertPin)
                .orElseThrow(() -> new PinNotFoundException(Constants.PIN_NOT_FOUND));

        return new ResponseEntity<>(pinResponse, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteAllPins() {
        pinRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deletePinById(Long id) {
        pinRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
