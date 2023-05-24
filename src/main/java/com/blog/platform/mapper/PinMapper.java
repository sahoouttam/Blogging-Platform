package com.blog.platform.mapper;

import com.blog.platform.entity.Pin;
import com.blog.platform.payload.request.PinRequest;
import com.blog.platform.payload.response.PinResponse;

public class PinMapper {

    public static PinResponse convertPin(Pin pin) {
        return PinResponse.builder()
                .id(pin.getId())
                .title(pin.getTitle())
                .url(pin.getUrl())
                .build();
    }

    public static Pin convertPinRequest(PinRequest pinRequest) {
        return Pin.builder()
                .title(pinRequest.getTitle())
                .url(pinRequest.getUrl())
                .board(pinRequest.getBoard())
                .build();
    }

    public static void updatePin(Pin pin, PinRequest pinRequest) {
        pin.setTitle(pinRequest.getTitle());
        pin.setUrl(pinRequest.getUrl());
        pin.setBoard(pinRequest.getBoard());
    }
}
