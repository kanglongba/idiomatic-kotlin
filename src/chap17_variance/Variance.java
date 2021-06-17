package chap17_variance;

public class Variance {

    public static void main (String... args) {
        varianceTest();
    }

    public static void varianceTest() {
        Number[] numbers = new Number[12];
        arrayVariance(numbers);

        Integer[] integers = new Integer[12];
        //This will produce a run-time exception because you are trying to insert a Long inside an Integer array
        arrayVariance(integers);
    }

    public static void arrayVariance(Number[] numbers) {
        numbers[0] = new Long(12);
    }
}
