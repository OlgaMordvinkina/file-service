package com.example.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MinNumberResponse {

  @Schema(
    description = "Минимальное найденное число",
    example = "3"
  )
  Integer minNumber;
}
