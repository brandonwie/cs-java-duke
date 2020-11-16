package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException(
					"MyLinkedList cannot store null pointers");
		}
		// initialize new LLNode with data
		LLNode<E> n = new LLNode<>(element);
		// if it's empty list,
		if (size == 0) {
			n.prev = head;
			n.next = tail;
			head.next = n;
			tail.prev = n;
		} else {
			n.prev = tail.prev;
			n.next = tail;
			tail.prev.next = n;
			tail.prev = n;
		}
		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		LLNode<E> n = this.head;
		for (int i = 0; i < index + 1; i++) {
			n = n.next;
		}
		return n.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method
		if (index < 0 || (size > 0 && index >= size)
				|| (size == 0 && index > size)) {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		if (element == null) {
			throw new NullPointerException("Null cannot be inserted");
		}

		if (size == 0 && index == 0) {
			size++;
			this.add(element);
		} else {
			LLNode<E> currNode = getNode(index);
			LLNode<E> newNode = new LLNode<E>(element);

			// new node
			newNode.next = currNode; // | k | a | b(index) |
			newNode.prev = currNode.prev;
			// other nodes (only k's next and b's prev change)
			currNode.prev.next = newNode; // k's next = n (prev doesn't matter)
			currNode.prev = newNode;
			size++;
		}
	}
	/**
	 * PRIVATE CODE
	 * 
	 * @category helper method to add right above
	 * 
	 */
	private LLNode<E> getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		LLNode<E> n = this.head;
		for (int i = 0; i < index + 1; i++) {
			n = n.next;
		}
		return n;
	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return this.size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		// TODO: Implement this method
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		LLNode<E> n = getNode(index);
		// a | n | b
		E k = n.data;
		n.prev.next = n.next;
		n.next.prev = n.prev;
		// decrease size
		size--;
		return k;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method
		E k = null;
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		if (element == null) {
			throw new NullPointerException("Null cannot be inserted");
		}
		LLNode<E> n = getNode(index);
		k = n.data;
		n.data = element;

		return k;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
