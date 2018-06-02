package exigentech.data.structures;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class SinglyLinkedListTest {

  @Test
  public void construction() {
    final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    assertThat(list.getLength(), is(0));
  }

  @Nested
  public final class Insertion {
    private final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

    @Test
    public void addToEmptyList() {
      list.insertAt(0, 1);
      assertThat(list.getLength(), is(1));
      assertThat(list.valueAt(0), is(1));
    }

    @Test
    public void insertHead() {
      list.insertAt(0, 1);
      list.insertAt(0, 2);

      assertThat(list.getLength(), is(2));
      assertThat(list.valueAt(0), is(2));
      assertThat(list.valueAt(1), is(1));
    }

    @Test
    public void insertTail() {
      list.insertAt(1, 2);

      assertThat(list.getLength(), is(2));
      assertThat(list.valueAt(0), is(1));
      assertThat(list.valueAt(1), is(2));
    }
  }
}