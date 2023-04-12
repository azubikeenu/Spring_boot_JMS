package com.azubike.ellipsis.spring_jms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloWorldMessage  implements Serializable {
    private UUID id ;
    private String message;
}
