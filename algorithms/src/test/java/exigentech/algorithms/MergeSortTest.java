package exigentech.algorithms;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public final class MergeSortTest {

  private final SortingStrategy<Integer> strategy = new MergeSort<>();

  @Test
  public void sortEmptyList() {
    assertThat(strategy.sortAscending(List.of()), is(empty()));
  }

  @Nested
  public final class SortAscending {

    @Test
    void sortAscendingEvenInput() {
      final List<Integer> values = List.of(5, 2, 99, 32);
      assertElementOrder(strategy.sortAscending(values), 2, 5, 32, 99);
    }

    @Test
    void sortAscendingOddLength() {
      final List<Integer> values = List.of(8, 2, 99, 32, 12);
      assertElementOrder(strategy.sortAscending(values), 2, 8, 12, 32, 99);
    }
  }

  private static <T> void assertElementOrder(final List<T> actual, final T... expectedValues) {
    assertThat(actual, equalTo(List.of(expectedValues)));
  }
}
