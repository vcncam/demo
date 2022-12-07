package com.vcncam.testproject.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class GeneralResponse<K> {
    private GeneralStatusResponse status;
    private K data;
}
