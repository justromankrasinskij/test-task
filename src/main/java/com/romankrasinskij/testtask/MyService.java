package com.romankrasinskij.testtask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyAlgorithm algorithm;

    public MyService(MyAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getMinNumberFromFile(String path, int n) {
        List<Integer> numbers = readNumbersFromFile(path);

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("n выходит за границы массива!");
        }

        return algorithm.findSmallest(numbers, n - 1);
    }

    private List<Integer> readNumbersFromFile(String path) {
        List<Integer> data = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            throw new RuntimeException("Не найден файл '" + path + "'");
        }

        try (FileInputStream stream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(stream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);

                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    data.add((int) cell.getNumericCellValue());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        if (data.isEmpty()) {
            throw new RuntimeException("Нет числа в первой колонке!");
        }

        return data;
    }
}
