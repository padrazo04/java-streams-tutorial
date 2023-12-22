/*
- Java streams (java.util.stream) were introduced in Java 8
- java.util.stream contains classes for processing sequences of elements
- The central API class is Stream<T>
- Stream operations act as a pipeline. The output of one operation is the input of the next one.
- In most of the code samples shown in this article, we left the streams unconsumed
  (we didn’t apply the close() method or a terminal operation).
  In a real app, don’t leave an instantiated stream unconsumed, as that will lead to memory leaks.
 */
package example01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreatingStreams {
    /*
    - Streams can be created from different element sources e.g.
      collections or arrays with methods stream() and of().
    - They can be created using the builder pattern too.
     */
    public static void main(String[] args) {
        record Employee(int id, String name, double salary) {}
        Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos", 100000.0),
            new Employee(2, "Bill Gates", 200000.0),
            new Employee(3, "Mark Zuckerberg", 300000.0)
        };

        // Stream from array
        Stream.of(arrayOfEmps);
        Stream.of(Arrays.asList("A", "B", "C"));

        // Stream from List
        List<String> listOfStrings = new ArrayList<>(List.of("A", "B", "C"));
        listOfStrings.stream();

        // Stream from builder
        Stream<String> streamBuilder = Stream.<String>builder().add("D").add("E").add("F").build();
        streamBuilder.forEach(System.out::println);
    }
}
