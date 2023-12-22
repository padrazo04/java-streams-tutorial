/*
- Streams have two types of operations:
    - Intermediate: return Stream<T>. They allow chaining as a pipeline
    - Terminal: return a result of definite type. Executing a terminal operation makes a stream inaccessible.
 */

package example02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamIntermediateOperations {
    public static void main(String[] args) {
        // Distinct and Count
        // distinct() creates a new stream of unique elements of the previous stream
        // count() it is a terminal operation, which returns stream’s size.
        System.out.println("Distinct and Count: ");
        List<String> listOfStrings = new ArrayList<>(List.of("A", "B", "B", "C"));
        long count = listOfStrings.stream().distinct().count();
        System.out.println(count); // 3

        // Iterating
        // Stream API helps to substitute for, for-each and while loops
        // It allows concentrating on operation's logic, but not on the iteration
        // over the sequence of elements.
        System.out.println("\nIterating: ");
        listOfStrings.stream().forEach(System.out::println);
        listOfStrings.forEach(System.out::println);

        // Filtering
        // filter() allows us to pick a stream of elements that satisfy a predicate
        System.out.println("\nFiltering: ");
        listOfStrings.stream().filter(element -> element.contains("B")).forEach(System.out::println);

        // Map
        // map() transform elements from one value into another.
        // It transforms one element into a new element for every element of the stream.
        System.out.println("\nMap: ");
        List<String> result = listOfStrings.stream().map(element -> element + "Z").toList();
        result.forEach(System.out::println);

        // Skip and limit
        // These two are commonly used for pagination.
        // - Discard next N elements of a stream
        // - Keep next N elements of a stream
        System.out.println("\nSkip and Limit: ");
        List<Integer> someInts = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10));
        someInts.stream().skip(3).limit(5).forEach(System.out::println);

        // Sorted
        // Without argument it returns default order (elements of the stream
        // implementing the `Comparable<T>` interface).
        // It can take as well a `Comparator<T>` argument for a specific comparison.
        System.out.println("\nSorted: ");
        List<Integer> moreInts = new ArrayList<>(List.of(12,5,62,34,2,156,58,88,7,26));
        moreInts.stream().sorted().forEach(element -> System.out.print(element + ", "));

        System.out.println("\nSorted (with comparator): ");
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        moreInts.stream().sorted(reverseOrder).forEach(element -> System.out.print(element + ", "));

        // Flatmap
        // Advanced variant of map. It transforms elements from one value into zero, one or many values.
        // Instead of mapping one element from an input stream to one element in a resulting stream,
        // FlatMap performs an operation for each element that can result in zero, one or many elements.
        System.out.println("\n\nFlatmap: ");
        record Sentence(int numberOfWords, List<String> words) {}
        List<Sentence> sentences = new ArrayList<>(List.of(
           new Sentence(3, List.of("Hi", "i'm", "Carlos")),
           new Sentence(5, List.of("I", "like", "soda", "and", "pizza")),
           new Sentence(1, List.of("Goodbye"))
        ));
        // With map, this results in a List<List<String>>
        // map does an additional wrapping internally
        sentences.stream().map(Sentence::words).forEach(System.out::println);
        // With flatMap, this results in a List<String>
        sentences
            .stream()
            .flatMap(element -> element.words.stream())
            .forEach(element -> System.out.print(element + ", "));

        // Another example
        // map() works well optional - if the function returns the exact type we need
        Optional<String> s1 = Optional.of("test").map(String::toUpperCase);
        // Results in an Optional<String> with value TEST

        // However, in more complex cases we might be given a function that returns
        // an Optional too. In such cases using map() would lead to a nested structure,
        Optional.of("test").map(s2 -> Optional.of("test"));
        // We end up with the nested structure Optional<Optional<String>> with value TEST

        // Although it works, it’s pretty cumbersome to use and does not provide any
        // additional null safety, so it is better to keep a flat structure.

        // That’s exactly what flatMap() helps us to do:
        Optional.of("test").flatMap(s3 -> Optional.of("TEST"));
        // Results in an Optional<String> with value TEST

        // For streams of streams, it works similarly
        // The flatMap() method first flattens the input Stream of Streams to a Stream of Strings
    }
}
