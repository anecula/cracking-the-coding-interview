package exigentech.data.structures;

import java.util.Arrays;

public final class SinglyLinkedList<T> {

  private Node<T> listHead;
  private int length;

  public <T> SinglyLinkedList() {
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

  public void insertAt(int index, T value) {
    checkIndex(index);
    final Node<T> node = new Node(value);

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

    final Node<T> node = nodeAt(index - 1);
    if (node == null) {
      throw new IndexOutOfBoundsException();
    }

    node.deleteNext();
    length--;
  }

  public Object[] toArray() {
    if (length == 0) {
      return new Object[]{};
    }

    Node[] array = new Node[32];
    array[0] = listHead;
    int length = 1;

    Node<T> current = listHead;

    while (current.next != null) {
      if (length == array.length) {
        array = Arrays.copyOf(array, array.length * 2);
      }

      array[length++] = current;
      current = current.next;
    }
    return array;
  }

  private void checkIndex(final int index) {
    if (index < 0 || index > length) {
      throw new IndexOutOfBoundsException();
    }
  }

  private Node<T> nodeAt(final int index) {
    Node<T> node = listHead;
    for (int i = 0; i < length; i++) {
      if (i == index) {
        break;
      }
      node = node.next;
    }
    return node;
  }

  private class Node<T> {

    Node<T> next = null;
    T value;

    Node(T value) {
      this.value = value;
    }

    void insertAfter(Node<T> newNode) {
      newNode.next = this.next;
      this.next = newNode;
    }

    void deleteNext() {
      // removes references to this.next, allowing it to be considered for garbage collection.
      next = next.next;
    }
  }
}
