package com.example;

public class SortedStringArray {
    private String[] elements;
    private int size;

    public SortedStringArray() {
        this.elements = new String[10]; // начальная ёмкость
        this.size = 0;
    }

    public SortedStringArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.elements = new String[initialCapacity];
        this.size = 0;
    }

    /**
     * Добавляет строку, сохраняя массив отсортированным по возрастанию длины.
     * При равенстве длин новый элемент вставляется после существующих.
     */
    public void add(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
        ensureCapacity();
        int insertIndex = findInsertIndex(value.length());
        // сдвигаем элементы вправо
        System.arraycopy(elements, insertIndex, elements, insertIndex + 1, size - insertIndex);
        elements[insertIndex] = value;
        size++;
    }

    private int findInsertIndex(int length) {
        int i = 0;
        while (i < size && elements[i].length() <= length) {
            i++;
        }
        return i;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            String[] newArr = new String[elements.length * 2];
            System.arraycopy(elements, 0, newArr, 0, size);
            elements = newArr;
        }
    }

    /**
     * Возвращает максимальный по длине элемент (последний в отсортированном массиве).
     */
    public String getMax() {
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        return elements[size - 1];
    }

    /**
     * Возвращает среднюю длину всех элементов.
     */
    public double getAverageLength() {
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += elements[i].length();
        }
        return (double) total / size;
    }

    public int size() {
        return size;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }
}