package datastructures.linkedlist.link;

public class Node<E> {

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getPrev() {
		return prev;
	}

	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}

	private E data;
	private Node<E> next;
	private Node<E> prev;

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
