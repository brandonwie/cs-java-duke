class ListNode<T> {
  private ListNode<T> prevNode;
  private ListNode<T> nextNode;
  T data;

  ListNode(T data) {
    this.prevNode = null;
    this.nextNode = null;
    this.data = data;
  }

  public void setPrevNode(ListNode<T> node) {
    this.prevNode = node;
  }

  public void setNextNode(ListNode<T> node) {
    this.nextNode = node;
  }
}