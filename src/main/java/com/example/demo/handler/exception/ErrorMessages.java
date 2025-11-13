package com.example.demo.handler.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
  public static final String N_OUT_OF_RANGE_ERROR_MESSAGE = "N вне диапазона количества чисел в файле";
  public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "Файл не найден по пути: ";
  public static final String FILE_READ_ERROR_MESSAGE = "Ошибка чтения файла: ";
  public static final String EXCEL_PROCESSING_ERROR_MESSAGE = "Ошибка обработки файла Excel: ";
}
