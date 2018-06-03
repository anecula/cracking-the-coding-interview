package exigentech.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MergeSort<T extends Comparable<T>> implements SortingStrategy<T> {

  @Override
  public List<T> sortAscending(List<T> input) {
    if (input.size() == 1) {
      return input;
    }

    final int pivot = input.size() / 2;
    final List<T> lhsSorted = sortAscending(input.subList(0, pivot));
    final List<T> rhsSorted = sortAscending(input.subList(pivot, input.size()));

    final T lhsValue = lhsSorted.get(0);
    final T rhsValue = rhsSorted.get(0);

    final List<T> mergedSorted = new ArrayList<>(lhsSorted.size() + rhsSorted.size());

    if (lhsSorted.size() == 1 || rhsSorted.size() == 1 && lhsValue.compareTo(rhsValue) > 0) {
      mergedSorted.addAll(rhsSorted);
      mergedSorted.addAll(lhsSorted);
    } else {
      mergedSorted.addAll(lhsSorted);
      mergedSorted.addAll(rhsSorted);
    }

    return Collections.unmodifiableList(mergedSorted);
  }
}
