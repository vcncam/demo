package com.vcncam.testproject.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude (JsonInclude.Include.NON_NULL)
public class GeneralStatusResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}
