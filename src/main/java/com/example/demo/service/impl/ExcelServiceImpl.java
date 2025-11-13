package com.example.demo.service.impl;

import com.example.demo.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.handler.exception.ErrorMessages.*;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {


  /**
   * Находит N-ое минимальное число из первого столбца указанного Excel-файла (.xlsx).
   *
   * @param filePath путь к локальному Excel-файлу (.xlsx)
   * @param n        порядковый номер минимального числа, которое нужно найти (начиная с 1)
   * @return N-ое минимальное число из файла
   * @throws IllegalArgumentException если файл не найден или n вне диапазона количества чисел
   * @throws IllegalStateException    если произошла ошибка при чтении или обработке Excel-файла
   */
  @Override
  public Integer findMinNumber(String filePath, int n) {
    List<Integer> numbers = new ArrayList<>();

    try (FileInputStream fis = new FileInputStream(filePath);
         Workbook workbook = new XSSFWorkbook(fis)) {

      // заполнение листа числами из файла .xlsx
      Sheet sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        Cell cell = row.getCell(0); // первый столбец
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
          numbers.add((int) cell.getNumericCellValue());
        }
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR_MESSAGE + filePath, e);
    } catch (IOException e) {
      throw new IllegalStateException(FILE_READ_ERROR_MESSAGE + filePath, e);
    } catch (Exception e) {
      throw new IllegalStateException(EXCEL_PROCESSING_ERROR_MESSAGE + e.getMessage(), e);
    }

    if (n < 1 || n > numbers.size()) {
      throw new IllegalArgumentException(N_OUT_OF_RANGE_ERROR_MESSAGE);
    }

    // поиск заданного минимального числа
    return quickSelect(numbers, 0, numbers.size() - 1, n - 1);
  }

  /**
   * Реализация алгоритма QuickSelect для поиска k-го минимального элемента без полной сортировки.
   * Модифицирует исходный список для разделения элементов относительно pivot.
   *
   * @param numbers список чисел для поиска
   * @param left    индекс левого края подмассива
   * @param right   индекс правого края подмассива
   * @param k       индекс искомого минимального элемента (0-based)
   * @return k-й минимальный элемент списка
   */
  private int quickSelect(List<Integer> numbers, int left, int right, int k) {
    // выбор последнего элемента как ключевого
    int pivot = numbers.get(right);
    // индекс, куда будут помещаться элементы меньше ключевого
    int partitionIndex = left;

    // проход по всем элементам, кроме последнего
    for (int i = left; i < right; i++) {
      // проверка, что элемент меньше ключевого
      if (numbers.get(i) < pivot) {
        // обмен текущего элемента с элементом на позиции partitionIndex
        int temp = numbers.get(i);
        numbers.set(i, numbers.get(partitionIndex));
        numbers.set(partitionIndex, temp);
        // смещение границы меньших элементов
        partitionIndex++;
      }
    }

    // установка ключевого элемента на своё место (между меньшими и большими)
    int temp = numbers.get(partitionIndex);
    numbers.set(partitionIndex, numbers.get(right));
    numbers.set(right, temp);

    // проверка: если найден нужный индекс, возвращается элемент
    if (partitionIndex == k) return numbers.get(partitionIndex);
      // рекурсивный поиск в левой части, если нужный элемент левее
    else if (partitionIndex > k) return quickSelect(numbers, left, partitionIndex - 1, k);
      // рекурсивный поиск в правой части, если нужный элемент правее
    else return quickSelect(numbers, partitionIndex + 1, right, k);
  }
}
