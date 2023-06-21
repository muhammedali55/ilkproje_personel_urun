package com.muhammet.ilkproje.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Component
public class ErrorMessage {
    String message;
    int code;
    List<String> fields;
}
