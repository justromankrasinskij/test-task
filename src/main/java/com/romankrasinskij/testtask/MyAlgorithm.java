package com.romankrasinskij.testtask;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MyAlgorithm {

    private final Random random = new Random();

    public int findSmallest(List<Integer> numbers, int n) {
        if (numbers == null || numbers.isEmpty() || n < 0 || n >= numbers.size()) {
            throw new IllegalArgumentException("Неправильный аргумент.");
        }

        return selectSmallest(numbers, 0, numbers.size() - 1, n);
    }

    private int selectSmallest(List<Integer> number, int left, int right, int n) {
        if (left == right) {
            return number.get(left);
        }

        int currentElement = left + random.nextInt(right - left + 1);
        currentElement = partitionArray(number, left, right, currentElement);

        if (n == currentElement) {
            return number.get(n);
        } else if (n < currentElement) {
            return selectSmallest(number, left, currentElement - 1, n);
        } else {
            return selectSmallest(number, currentElement + 1, right, n);
        }
    }

    private int partitionArray(List<Integer> numbers, int left, int right, int index) {
        int value = numbers.get(index);
        swapElements(numbers, index, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (numbers.get(i) < value) {
                swapElements(numbers, storeIndex, i);
                storeIndex++;
            }
        }

        swapElements(numbers, storeIndex, right);

        return storeIndex;
    }

    private void swapElements(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
}
