package datastructures.linkedlist.linkedlist;

import java.util.Collection;
import java.util.NoSuchElementException;

import datastructures.linkedlist.link.Node;

public class LinkedList<E> {

	transient int size = 0;
	transient Node<E> first;
	transient Node<E> last;

	// blank constructor
	public LinkedList() {
	}

	// constructor that starts with a collection to populate
	public LinkedList(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/*
	 * Insert collection into list at specific index
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		checkPositionByIndex(index);

		Object[] a = c.toArray();
		int cLength = a.length;
		// DEBUG System.out.println(cLength);

		if (cLength == 0) {
			return false;
		}

		Node<E> before, after;

		if (index == size) {
			after = null;
			before = last;
		} else {
			after = node(index);
			before = after.prev;
		}

		for (Object o : a) {
			@SuppressWarnings("unchecked")
			E e = (E) o;
			Node<E> newNode = new Node<E>(e, after, before);
			if (before == null)
				first = newNode;
			else
				before.next = newNode;
			before = newNode;
		}

		if (after == null) {
			last = before;
		} else {
			before.next = after;
			after.prev = before;
		}

		size += cLength;
		return true;

	}

	/*
	 * Insert collection into this list
	 */
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}

	public void add(int index, E element) {
		checkPositionByIndex(index);

		if (index == size) {
			linkLast(element);
		} else {
			linkBefore(element, node(index));
		}
	}

	public E get(int index) {
		checkPositionByIndex(index);
		return node(index).getData();
	}

	public E getFirst() {
		final Node<E> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return f.data;
	}

	Node<E> node(int index) {
		/*
		 * "size >> 1" is a cute way to handle 0 and 1 size lists bitwise shifting in
		 * this instance is basically dividing by 2 with no remainder
		 */
		if (index < (size >> 1)) {
			Node<E> x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	void linkLast(E e) {
		final Node<E> l = last;
		final Node<E> newNode = new Node<>(e, null, l);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}

	void linkFirst(E e) {
		final Node<E> f = first;
		final Node<E> newNode = new Node<>(e, null, f);
		first = newNode;
		if (f == null) {
			first = newNode;
		} else {
			f.prev = newNode;
		}
		size++;
	}

	void linkBefore(E e, Node<E> after) {
		final Node<E> before = after.prev;
		final Node<E> newNode = new Node<>(e, after, before);
		after.prev = newNode;
		if (before == null) {
			first = newNode;
		} else {
			before.next = newNode;
		}
		size++;
	}

	E unlink(Node<E> x) {
		final E data = x.data;
		final Node<E> next = x.next;
		final Node<E> prev = x.prev;

		if (prev == null) {
			first = next;
		} else {
			prev.next = first;
			x.prev = null;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}

		x.data = null;
		size--;
		return data;
	}

	public E unlinkFirst(Node<E> f) {
		final E data = f.data;
		final Node<E> next = f.next;
		f.data = null;
		f.next = null;
		first = next;
		if (next == null)
			last = null;
		else
			next.prev = null;

		size--;
		return data;

	}

	public E unlinkLast(Node<E> l) {
		final E data = l.data;
		final Node<E> prev = l.prev;
		l.data = null;
		l.prev = null;
		last = prev;
		if (last == null)
			first = null;
		else
			prev.next = null;
		size--;
		return data;

	}

	public E remove(int index) {
		checkPositionByIndex(index);
		return unlink(node(index));
	}

	public E removeFirst() {
		final Node<E> f = first;
		if (f == null) {
			throw new NoSuchElementException();
		}
		return unlinkFirst(f);
	}

	public E removeLast() {
		final Node<E> l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return unlinkLast(l);
	}

	public void clear() {
		for (Node<E> x = first; x != null;) {
			Node<E> next = x.next;
			x.data = null;
			x.prev = null;
			x.next = null;
			x = next;
		}
		first = last = null;
		size = 0;
	}

	/*
	 * Error checking methods
	 */
	private void checkPositionByIndex(int index) {
		if (!positionExists(index)) {
			throw new IndexOutOfBoundsException("That index refers to nothing in this list");
		}
	}

	private boolean positionExists(int index) {
		return index >= 0 && index <= size;
	}
	/*
	 * 
	 */
}
