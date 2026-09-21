package com.example;

public class SortedStringArrayTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAddKeepsSortedOrder();
        testGetMax();
        testGetAverageLength();
        testEmptyGetMaxThrows();
        testEmptyAverageThrows();
        testGrowthBeyondInitialCapacity();

        System.out.println("\nPassed: " + passed + ", Failed: " + failed);
        if (failed > 0) System.exit(1);
    }

    private static void assertEquals(Object expected, Object actual, String name) {
        if (expected.equals(actual)) {
            passed++;
            System.out.println("[OK] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name + " expected=" + expected + " actual=" + actual);
        }
    }

    private static void testAddKeepsSortedOrder() {
        SortedStringArray arr = new SortedStringArray();
        arr.add("java");
        arr.add("c");
        arr.add("python");
        arr.add("js");

        assertEquals("c", arr.get(0), "sorted[0] = c");
        assertEquals("js", arr.get(1), "sorted[1] = js");
        assertEquals("java", arr.get(2), "sorted[2] = java");
        assertEquals("python", arr.get(3), "sorted[3] = python");
    }

    private static void testGetMax() {
        SortedStringArray arr = new SortedStringArray();
        arr.add("a");
        arr.add("abcdef");
        arr.add("abc");
        assertEquals("abcdef", arr.getMax(), "getMax returns longest");
    }

    private static void testGetAverageLength() {
        SortedStringArray arr = new SortedStringArray();
        arr.add("a");      // 1
        arr.add("abc");    // 3
        arr.add("abcde");  // 5
        assertEquals(3.0, arr.getAverageLength(), "average = 3.0");
    }

    private static void testEmptyGetMaxThrows() {
        SortedStringArray arr = new SortedStringArray();
        try {
            arr.getMax();
            failed++;
            System.out.println("[FAIL] empty getMax should throw");
        } catch (IllegalStateException e) {
            passed++;
            System.out.println("[OK] empty getMax throws");
        }
    }

    private static void testEmptyAverageThrows() {
        SortedStringArray arr = new SortedStringArray();
        try {
            arr.getAverageLength();
            failed++;
            System.out.println("[FAIL] empty average should throw");
        } catch (IllegalStateException e) {
            passed++;
            System.out.println("[OK] empty average throws");
        }
    }

    private static void testGrowthBeyondInitialCapacity() {
        SortedStringArray arr = new SortedStringArray(2);
        arr.add("aa");
        arr.add("b");
        arr.add("cccc");
        arr.add("ddd");
        assertEquals(4, arr.size(), "size after growth");
        assertEquals("cccc", arr.getMax(), "max after growth");
    }
}