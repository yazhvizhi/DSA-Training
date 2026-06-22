public class Main {
    public static void main(String[] args) {
        exampleCallByValue();
        exampleCallByReference();
        exampleReverseString();
        exampleOddEvenCount();
        exampleFactorial();
        exampleDigitCount();
        exampleLargeElements();
        examplePrimeNumber();
        exampleFibonacciSeries();
    }

    private static void exampleCallByValue() {
        int x = 10;
        System.out.println("=== Call by value example ===");
        System.out.println("Before: " + x);
        modifyValue(x);
        System.out.println("After: " + x);
        System.out.println();
    }

    private static void modifyValue(int x) {
        x = x + 5;
        System.out.println("Inside modifyValue: " + x);
    }

    private static void exampleCallByReference() {
        int[] data = { 10 };
        System.out.println("=== Call by reference-like example ===");
        System.out.println("Before: " + data[0]);
        modifyArray(data);
        System.out.println("After: " + data[0]);
        System.out.println();
    }

    private static void modifyArray(int[] array) {
        array[0] = array[0] + 5;
        System.out.println("Inside modifyArray: " + array[0]);
    }

    private static void exampleReverseString() {
        String text = "hello";
        System.out.println("=== Reverse string example ===");
        System.out.println("Original: " + text);
        System.out.println("Reversed: " + reverseString(text));
        System.out.println();
    }

    private static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private static void exampleOddEvenCount() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7 };
        System.out.println("=== Odd/Even count example ===");
        System.out.println("Numbers: " + java.util.Arrays.toString(numbers));
        int[] counts = countOddEven(numbers);
        System.out.println("Odd count: " + counts[0]);
        System.out.println("Even count: " + counts[1]);
        System.out.println();
    }

    private static int[] countOddEven(int[] values) {
        int odd = 0;
        int even = 0;
        for (int value : values) {
            if (value % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return new int[] { odd, even };
    }

    private static void exampleFactorial() {
        int n = 5;
        System.out.println("=== Factorial example ===");
        System.out.println("Number: " + n);
        System.out.println("Factorial: " + factorial(n));
        System.out.println();
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static void exampleDigitCount() {
        int value = 12345;
        System.out.println("=== Digit count example ===");
        System.out.println("Number: " + value);
        System.out.println("Digits: " + countDigits(value));
        System.out.println();
    }

    private static int countDigits(int value) {
        value = Math.abs(value);
        if (value == 0) {
            return 1;
        }
        int count = 0;
        while (value > 0) {
            value /= 10;
            count++;
        }
        return count;
    }

    private static void exampleLargeElements() {
        int[] values = { 4, 9, 1, 7, 9, 3, 5 };
        System.out.println("=== Largest and second largest example ===");
        System.out.println("Values: " + java.util.Arrays.toString(values));
        int[] result = findLargestAndSecondLargest(values);
        System.out.println("Largest: " + result[0]);
        System.out.println("Second largest: " + result[1]);
        System.out.println();
    }

    private static int[] findLargestAndSecondLargest(int[] values) {
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > largest) {
                second = largest;
                largest = value;
            } else if (value > second && value < largest) {
                second = value;
            }
        }
        if (second == Integer.MIN_VALUE) {
            second = largest;
        }
        return new int[] { largest, second };
    }

    private static void examplePrimeNumber() {
        int n = 29;
        System.out.println("=== Prime number example ===");
        System.out.println("Number: " + n);
        System.out.println("Is prime? " + isPrime(n));
        System.out.println();
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    private static void exampleFibonacciSeries() {
        int count = 10;
        System.out.println("=== Fibonacci series example ===");
        System.out.println("Count: " + count);
        System.out.println("Series: " + java.util.Arrays.toString(fibonacciSeries(count)));
        System.out.println();
    }

    private static int[] fibonacciSeries(int count) {
        if (count <= 0) {
            return new int[0];
        }
        int[] series = new int[count];
        series[0] = 0;
        if (count > 1) {
            series[1] = 1;
        }
        for (int i = 2; i < count; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }
        return series;
    }
}