package exigentech.algorithms;

@FunctionalInterface
public interface SortingStrategy<T extends Comparable<T>> {

   T[] sort(T[] input);
}
