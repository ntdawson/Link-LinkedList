package datastructures.linkedlist.link;

public class Node<E> {
	
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
