package datastructures.linkedlist.linkedlist;

import java.util.Collection;

import datastructures.linkedlist.link.Node;

public class LinkedList<E> {

	transient int size;
	transient Node<E> first;
	transient Node<E> last;
	
	public LinkedList() {
	}
	
	public LinkedList(Collection<? extends E> c) {
		return addAll(c);
	}

	/*
	 * Insert collection into this list
	 */
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size,c);
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
	private boolean addAll(int index, Collection<? extends E> c) {
		checkPositionByIndex(index);
		
		
	}
	
	
}
