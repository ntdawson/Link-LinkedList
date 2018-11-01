package datastructures.linkedlist.link;

public class Node<E> {

	public E data;
	public Node<E> next;
	public Node<E> prev;

	public Node(E initData, Node<E> initNext, Node<E> initPrev) {
		data = initData;
		prev = initPrev;
		next = initNext;
	}

	public String toString() {
		while (data != null) {
			return data.toString();
		}
		return null;
	}

}
