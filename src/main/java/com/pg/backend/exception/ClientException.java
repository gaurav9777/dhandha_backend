package com.pg.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientException extends RuntimeException{

    private String message;
    private Object response;

    public ClientException(String message){
        this.message=message;
    }
}
