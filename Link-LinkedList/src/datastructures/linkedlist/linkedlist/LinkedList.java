package datastructures.linkedlist.linkedlist;

import java.util.Collection;
import java.util.NoSuchElementException;
import datastructures.linkedlist.link.Node;

/*
 * Author: Nick Dawson
 * Date: 11/1/18
 * 
 * Method signature inspired by Java API, reverse engineered from there
 */

public class LinkedList<E> {

	/*
	 * we don't need to serialize derived information. Also, I don't like
	 * using @unnused annotations all the time
	 */
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

		// error handler
		checkPositionByIndex(index);

		Object[] a = c.toArray();
		int cLength = a.length;
		// DEBUG System.out.println(cLength);

		// no adding empty collections!
		if (cLength == 0) {
			return false;
		}

		// temp variables for determining where in the list we're adding to
		Node<E> before, after;

		// if we're adding to the end:
		if (index == size) {
			// appending to the end of the list means that the current last will be the new
			// last node's before link
			after = null;
			before = last;
		} else { // if adding anywhere BUT the end
			// the next node is the node currently at the given index and the previous node
			// is that node's current back-link
			after = node(index);
			before = after.prev;
		}

		/*
		 * basically a for-each loop through our object array (which is the collection
		 * we're adding).
		 */
		for (Object o : a) {

			@SuppressWarnings("unchecked")
			E e = (E) o; // WIZARDRY!. This is just casting our generic as whatever type the list is
			Node<E> newNode = new Node<E>(e, after, before);

			// if the node has no before, it's the first
			if (before == null)
				first = newNode;
			else
				// if not, set the before node's next field to this node we're creating
				before.next = newNode;
			/*
			 * / In any case, we need to keep track of where we're inserting the collection.
			 * As the loop continues, this will keep our place
			 */
			before = newNode;
		}

		// once we're done inserting, if we appended, set the list's last field to that
		// node
		if (after == null) {
			last = before;
		} else {
			/*
			 * If we didn't append, link the ends of our collection to the rest of the list
			 */
			before.next = after;
			after.prev = before;
		}

		// the linked list grows!
		size += cLength;

		// in case we need to verify success
		return true;

	}

	/*
	 * Append collection to this list
	 */
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}

	// add element at specific index
	public void add(int index, E element) {
		checkPositionByIndex(index);

		if (index == size) {
			linkLast(element);
		} else {
			linkBefore(element, node(index));
		}
	}

	// add element to end
	public void addLast(E e) {
		linkLast(e);
	}
	// add element to beginning
	public void addFirst(E e) {
		linkFirst(e);
	}


	// grab the data from a node by index
	public E get(int index) {
		checkPositionByIndex(index);
		return node(index).data;
	}

	// get the data of the first node
	public E getFirst() {
		final Node<E> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return f.data;
	}

	// get a node by index.
	Node<E> node(int index) {

		/*
		 * bitwise shifting in this case is dividing by 2 with no remainder. this allows
		 * us to determine on what 'side' of our list the index is on. Then, walk up or
		 * down the list as necessary to reach the object. Much faster than starting
		 * from first for every situation, especially with large lists.
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

	// link an element to the end of the list
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

	// link an element at the beginning of the list
	void linkFirst(E e) {
		final Node<E> f = first;
		final Node<E> newNode = new Node<>(e, f, null);
		first = newNode;
		if (f == null) {
			first = newNode;
		} else {
			f.prev = newNode;
		}
		size++;
	}

	// insert an element before a given element
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

	// remove a given element
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

	// remove the first instance of a given node
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

	// remove the last instance of a given node
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

	/// remove element by index
	public E remove(int index) {
		checkPositionByIndex(index);
		return unlink(node(index));
	}

	// remove first element
	public E removeFirst() {
		final Node<E> f = first;
		if (f == null) {
			throw new NoSuchElementException();
		}
		return unlinkFirst(f);
	}

	// remove last element
	public E removeLast() {
		final Node<E> l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return unlinkLast(l);
	}

	// void the whole array
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
