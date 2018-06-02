package exigentech.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public final class MergeSort<T extends Comparable<T>> implements SortingStrategy<T> {

  @Override
  public T[] sort(T[] input) {
    if (input.length == 1) {
      return input;
    }

    final int pivot = input.length / 2;
    final T[] lhsSorted = sort(Arrays.copyOfRange(input, 0, pivot));
    final T[] rhsSorted = sort(Arrays.copyOfRange(input, pivot, input.length));

    if (lhsSorted.length == 1 && rhsSorted.length == 1) {
      final T[] sortedValues = (T[]) new Comparable[2];

      final T lhsValue = rhsSorted[0];
      final T rhsValue = rhsSorted[0];

      if (lhsValue.compareTo(rhsValue) <= 0) {
        sortedValues[0] = lhsValue;
        sortedValues[1] = rhsValue;
      } else {
        sortedValues[0] = rhsValue;
        sortedValues[1] = lhsValue;
      }

      return sortedValues;
    }

    return mergeArrays(lhsSorted, rhsSorted);
  }

  private T[] mergeArrays(final T[] lhs, final T[] rhs) {
    final T[] merged = Arrays.copyOf(lhs, lhs.length + rhs.length);
    for (int i = 1; i < rhs.length; i++) {
      merged[i + lhs.length] = rhs[i];
    }
    return merged;
  }
}
