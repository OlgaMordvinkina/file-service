package com.example.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MinNumberRequest {

  @Schema(
    description = "Путь к локальному Excel-файлу в формате XLSX, содержащему столбик целых чисел",
    example = "/app/data/tableWithMinNumer.xlsx"
  )
  @NotBlank(message = "Путь к файлу не должен быть пустым")
  String filePath;

  @Schema(
    description = "Порядковый номер минимального числа, которое нужно найти",
    example = "3"
  )
  @NotNull(message = "Поле обязательно для заполнения")
  @Min(value = 1, message = "Значение должно быть больше 0")
  Integer n;
}
