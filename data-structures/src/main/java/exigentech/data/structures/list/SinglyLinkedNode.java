package exigentech.data.structures.list;

final class SinglyLinkedNode<T> {

  SinglyLinkedNode<T> next = null;
  T value;

  SinglyLinkedNode(T value) {
    this.value = value;
  }

  void insertAfter(SinglyLinkedNode<T> newNode) {
    newNode.next = this.next;
    this.next = newNode;
  }

  void deleteNext() {
    // removes references to this.next, allowing it to be considered for garbage collection.
    next = next.next;
  }
}
