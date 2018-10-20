package datastructures.linkedlist.link;

public class Node<E> {
	
	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getLink() {
		return link;
	}

	public void setLink(Node<E> link) {
		this.link = link;
	}

	private E data;
	private Node<E> link;
	
	public Node(E initData, Node<E> initLink){
		data = initData;
		link = initLink;
	}
	
	public String toString() {
		while(data!=null) {
			return data.toString();
		}
		return null;
	}
		
}
