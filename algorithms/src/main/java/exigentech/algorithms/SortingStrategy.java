package exigentech.algorithms;

import java.util.List;

@FunctionalInterface
public interface SortingStrategy<T extends Comparable<T>> {

   /**
    * Sorts an ordered List of elements according to their Comparable properties.
    *
    * @param input List of elements to sortAscending.
    * @return Unmodifiable copy of the input List sorted in ascending order.
    */
   List<T> sortAscending(List<T> input);
}
