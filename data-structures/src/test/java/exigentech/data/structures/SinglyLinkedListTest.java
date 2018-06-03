package exigentech.data.structures;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exigentech.data.structures.list.SinglyLinkedList;
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
    public void insertIntoEmptyList() {
      list.insertAt(0, 1);
      assertThat(list.getLength(), is(1));
      assertThat(list.valueAt(0), is(1));
    }

    @Test
    public void insertHead() {
      list.insertHead(1);
      list.insertHead(2);

      assertThat(list.getLength(), is(2));
      assertThat(list.valueAt(0), is(2));
      assertThat(list.valueAt(1), is(1));
    }

    @Test
    public void insertTail() {
      list.insertTail(1);
      list.insertTail(2);

      assertThat(list.getLength(), is(2));
      assertThat(list.valueAt(0), is(1));
      assertThat(list.valueAt(1), is(2));
    }

    @Test
    public void insertMiddle() {
      list.insertHead(1);
      list.insertTail(3);
      list.insertAt(1, 2);

      assertThat(list.getLength(), is(3));
      assertThat(list.valueAt(0), is(1));
      assertThat(list.valueAt(1), is(2));
      assertThat(list.valueAt(2), is(3));
    }
  }

  @Nested
  public final class Deletion {

    private final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

    @Test
    public void passIndexExceedingListSize() {
      assertThrows(IndexOutOfBoundsException.class, () -> list.deleteAt(1));
      assertThrows(IndexOutOfBoundsException.class, () -> list.valueAt(1));
    }

    @Test
    public void passNegativeIndex() {
      assertThrows(IllegalArgumentException.class, () -> list.deleteAt(-1));
      assertThrows(IllegalArgumentException.class, () -> list.valueAt(-1));
    }

    @Test
    public void deleteFromEmptyList() {
      assertThrows(IndexOutOfBoundsException.class, () -> list.deleteAt(0));
    }
  }
}