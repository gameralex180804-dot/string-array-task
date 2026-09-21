package com.example;

public class SortedStringArray {
    private final String[] elements;
    private int size;

    /**
     * Создаёт массив с ёмкостью по умолчанию — 100 элементов.
     */
    public SortedStringArray() {
        this(100);
    }

    /**
     * Создаёт массив с заданной ёмкостью.
     * Больше capacity элементов добавить не получится.
     */
    public SortedStringArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.elements = new String[capacity];
        this.size = 0;
    }

    /**
     * Добавляет строку, сохраняя массив отсортированным по возрастанию длины.
     * Если места нет — бросает IllegalStateException.
     */
    public void add(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
        if (size == elements.length) {
            throw new IllegalStateException("Array is full");
        }
        int insertIndex = findInsertIndex(value.length());
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

    public int capacity() {
        return elements.length;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }
}