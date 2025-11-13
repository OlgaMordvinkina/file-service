package com.example.demo.web.controller;

import com.example.demo.service.ExcelService;
import com.example.demo.web.dto.MinNumberRequest;
import com.example.demo.web.dto.MinNumberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Validated
@RestController
@RequestMapping("/file/excel")
@RequiredArgsConstructor
@Tag(name = "Операции с Excel-файлами", description = "API для работы с Excel-файлами")
public class ExcelController {
  private final ExcelService service;

  @PostMapping("/min-number")
  @Operation(
    summary = "Получить N-ое минимальное число из XLSX-файла",
    description = "Метод принимает путь к локальному *.xlsx* файлу, считывает числа из столбца и возвращает N-ое минимальное значение. "
      + "Если файл не найден или данные некорректны, возвращает ошибку."
  )
  public MinNumberResponse getMinimumNumber(@Valid @RequestBody MinNumberRequest request) {
    HttpServletRequest requestInfo = getRequestAttributes().getRequest();
    log.debug("Запроса: {}, URL: {}", requestInfo.getMethod(), requestInfo.getPathInfo());

    Integer minNumber = service.findMinNumber(request.getFilePath(), request.getN());
    return new MinNumberResponse(minNumber);
  }

  private static ServletRequestAttributes getRequestAttributes() {
    return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
  }
}

