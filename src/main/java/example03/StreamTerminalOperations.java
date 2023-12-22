/*
- Streams have two types of operations:
    - Intermediate: return Stream<T>. They allow chaining as a pipeline
    - Terminal: return a result of definite type. Executing a terminal operation makes a stream inaccessible.
 */

package example03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamTerminalOperations {
    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>(List.of("A1", "B1", "B1", "C1"));

        System.out.println("Operations that create containers from the elements of the Stream");
        List<String> result1 = listOfStrings.stream().toList(); // Returns a List<String>
        Object[] result2 = listOfStrings.stream().toArray(); // Returns an Object[] array
        String[] result3 = listOfStrings.stream().toArray(String[]::new); // Returns an Object[] array

        System.out.println("Match operations: Take a predicate and return a boolean");
        // Returns true if one element of the stream matches the predicate.
        listOfStrings.stream().anyMatch(e -> e.contains("C"));
        // Returns true if all the elements of the stream matches the predicate.
        listOfStrings.stream().allMatch(e -> e.contains("1"));
        // Returns true if none element of the stream matches the predicate.
        listOfStrings.stream().noneMatch(e -> e.contains("2"));

        System.out.println("Max/Min operations given a sort order");
        int[] arrayOfInts = new int[] {2, 5, 4, 1};
        // Stream.of(arrayOfInts).max(); Doesn't work
        Arrays.stream(arrayOfInts).max(); // Returns 5
        Arrays.stream(arrayOfInts).min(); // Returns 1

        // For each element (printing it, saving it to a database, updating a value from it)
        // (it takes a `Consumer<T> where T is the element of the stream).
        System.out.println("Side effecting action");
        Arrays.stream(arrayOfInts).forEach(System.out::println);

        // Get the first or any element of a stream that matches a predicate
        System.out.println("FindFirst (or FindAny)");
        // Both return an Optional<String>
        listOfStrings.stream().filter(e -> e.contains("B")).findFirst();
        // findAny() is explicitly nondeterministic; it is free to select any element in the stream.
        // This is to allow for maximal performance in parallel operations;
        listOfStrings.stream().filter(e -> e.contains("B")).findAny();

        // Count number of elements in a stream
        System.out.println("Count");
        listOfStrings.stream().filter(e -> e.contains("B")).count(); // Return 2

        // Reduce Combine elements together using a combining function
        System.out.println("Reduce");
        List<Integer> integers = Arrays.asList(1,1,1);
        int res1 = integers.stream().reduce(23, (acc, next) -> acc + next); // Returns 26
        int res2 = integers.stream().reduce(23, Integer::sum); // Returns 26
    }
}
