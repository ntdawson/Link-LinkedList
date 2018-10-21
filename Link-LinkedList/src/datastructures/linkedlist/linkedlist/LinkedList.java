package datastructures.linkedlist.linkedlist;

import java.util.Collection;
import java.util.NoSuchElementException;

import datastructures.linkedlist.link.Node;

public class LinkedList<E> {

	transient int size = 0;
	transient Node<E> first;
	transient Node<E> last;

	public LinkedList() {
	}

	public LinkedList(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/*
	 * Insert collection into this list
	 */
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}

	private void checkPositionByIndex(int index) {
		if (!positionExists(index)) {
			throw new IndexOutOfBoundsException("That index refers to nothing in this list");
		}
	}

	private boolean positionExists(int index) {
		return index >= 0 && index <= size;
	}

	/*
	 * Insert collection into list at specific index
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		checkPositionByIndex(index);

		Object[] a = c.toArray();
		int cLength = a.length;
		System.out.println(cLength);
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
		
		if(after == null) {
			last = before;
		}else {
			before.next = after;
			after.prev = before;
		}

		size += cLength;
		return true;

	}

	public E get(int index) {
		checkPositionByIndex(index);
		return node(index).getData();
	}

	Node<E> node(int index) {
		/*
		 * "size >> 1" is a cute way to handle 0 and 1 size lists bitwise shifting in
		 * this instance is basically dividing size by 2
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
	
	public E getFirst() {
		final Node<E> f = first;
		if(f==null)
			throw new NoSuchElementException();
		return f.data;
	}

}
