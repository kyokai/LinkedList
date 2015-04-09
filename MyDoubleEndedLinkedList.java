import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoubleEndedLinkedList<T extends Comparable<T>> implements
		Iterable<T> {
	// initialising Nodes including the two sentinal nodes

	private Node<T> head;
	private Node<T> tail;
	private Node<T> current;
	private int currentsize;

	MyDoubleEndedLinkedList() {
		head = new Node<T>();
		tail = new Node<T>();
		head.setNext(tail);
		tail.setPrevious(head);
		current = head;
		currentsize = 0;
	}

	public void listEvents() {
		resetCurrent();
		while (endList()) {
			((DistanceEvent) getCurrent()).display();
			nextCurrent();
		}
	}

	// Method to Insert DistanceEvent object into the list

	public void insertInFirstPosition(T newDistanceEvent) {

		T theNewLink = newDistanceEvent;

		if (isEmpty()) {
			Node<T> temp = new Node<T>(head, tail, theNewLink);
			head.setNext(temp);
			tail.setPrevious(temp);
		} else {
			Node<T> temp = new Node<T>(head, head.getNext(), theNewLink);
			head.getNext().setPrevious(temp);
			head.setNext(temp);
		}
	}

	public void insertInLastPosition(T newDistanceEvent) {

		T theNewLink = newDistanceEvent;

		if (isEmpty()) {
			Node<T> temp = new Node<T>(head, tail, theNewLink);
			head.setNext(temp);
			tail.setPrevious(temp);
			// isEmpty = false;
		} else {
			Node<T> temp = new Node<T>(tail.getPrevious(), tail, theNewLink);
			tail.getPrevious().setNext(temp);
			tail.setPrevious(temp);
		}
	}

	public void add(T newDistanceEvent) {
		T theNewLink = newDistanceEvent;
		if (current == head) {
			Node<T> temp = new Node<T>(head, tail, theNewLink);
			head.setNext(temp);
			tail.setPrevious(temp);
		}

		else if (current == tail) {
			Node<T> temp = new Node<T>(tail.getPrevious(), tail, theNewLink);
			tail.getPrevious().setNext(temp);
			tail.setPrevious(temp);
		}

		else {
			Node<T> temp = new Node<T>(current.getPrevious(), current,
					theNewLink);
			current.getPrevious().setNext(temp);
			current.setPrevious(temp);
		}

	}

	public void insert(T newDistanceEvent) {
		if (head == null) {
			insertInFirstPosition(newDistanceEvent);

		}

		resetCurrent();
		nextCurrent();
		T temp;

		temp = getCurrent();
		while (endList()) {
			if (newDistanceEvent.compareTo(temp) < 0) {
				break;
			}
			nextCurrent();
			temp = getCurrent();

		}
		add(newDistanceEvent);
	}

	// Methods used to help loop and iterate through the list
	public boolean isEmpty() {
		return (current == head && current == tail);
	}

	public boolean endList() {
		return (current != tail);
	}

	public void resetCurrent() {
		current = head;
	}

	public void nextCurrent() {
		current = current.getNext();
	}

	public T getCurrent() {
		return current.getData();
	}

	public int size() {
		return this.currentsize;
	}

	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator<T>();
	}

	// Node class for doublyLinkedList

	public class Node<E> {
		private Node<E> previous;
		private Node<E> next;
		private E data;

		Node() {
			previous = null;
			next = null;
			data = null;
		};

		Node(Node<E> newPrevious, Node<E> newNext, E newData) {
			previous = newPrevious;
			next = newNext;
			data = newData;
		}

		// set previous node
		public void setPrevious(Node<E> newPrevious) {
			previous = newPrevious;
		}

		// set Next node
		public void setNext(Node<E> newNext) {
			next = newNext;
		}

		public void setData(E newData) {
			data = newData;
		}

		public Node<E> getPrevious() {
			return previous;
		}

		public Node<E> getNext() {
			return next;

		}

		public E getData() {
			return data;
		}

	}

	class LinkedListIterator<E> implements Iterator<T> {
		private Node<T> current;
		private Node<T> previous;
		private Node<T> previous2;

		private boolean removeCalled;

		public LinkedListIterator() {
			current = head;
			previous = null;
			previous2 = null;
			removeCalled = false;
		}

		public boolean hasNext() {
			return (current != null);
		}

		public T next() {
			if (hasNext()) {

				T temp = current.getData();
				previous2 = previous;
				previous = current;
				current = current.getNext();
				removeCalled = false;
				return temp;
			}
			throw new NoSuchElementException();
		}

		public void remove() {

			if (previous == null || removeCalled) {
				throw new IllegalStateException();
			}
			if (previous2 == null) {
				head = current;
			} else {
				previous2.setNext(current);
				previous = previous2;
			}
			removeCalled = true;

			throw new UnsupportedOperationException();
		}

	}

}

