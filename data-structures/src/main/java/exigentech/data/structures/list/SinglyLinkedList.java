package exigentech.data.structures.list;

import java.util.Arrays;

/**
 * Naive, simple implementation of a singly-linked list. Intended to behave similarly to the list
 * implementation in "Cracking the Coding Interview."
 *
 * @param <T> The generic type for values in the list.
 */
public final class SinglyLinkedList<T> {

  private SinglyLinkedNode<T> listHead;
  private int length;

  public SinglyLinkedList() {
    listHead = null;
    length = 0;
  }

  public int getLength() {
    return length;
  }

  public T valueAt(int index) {
    checkIndex(index);
    return nodeAt(index).value;
  }

  public void insertHead(T value) {
    insertAt(0, value);
  }

  public void insertTail(T value) {
    insertAt(length, value);
  }

  public void insertAt(int index, T value) {
    checkIndex(index);
    final SinglyLinkedNode<T> node = new SinglyLinkedNode(value);

    if (index == 0) {
      node.next = listHead;
      listHead = node;
    } else {
      nodeAt(index - 1).insertAfter(node);
    }

    length++;
  }

  public void deleteAt(int index) {
    checkIndex(index);
    nodeAt(index - 1).deleteNext();
    length--;
  }

  private void checkIndex(final int index) {
    if (index < 0) {
      throw new IllegalArgumentException();
    }
    if (index >= length) {
      throw new IndexOutOfBoundsException();
    }
  }

  private SinglyLinkedNode<T> nodeAt(final int index) {
    SinglyLinkedNode<T> node = listHead;
    for (int i = 0; i < length; i++) {
      if (i == index) {
        break;
      }
      node = node.next;
    }
    return node;
  }

}
