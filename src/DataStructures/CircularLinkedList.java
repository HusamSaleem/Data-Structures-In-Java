package DataStructures;

import DataStructuresInterfaces.LinkedListInterface;

@SuppressWarnings("unchecked")
public class CircularLinkedList<T> implements LinkedListInterface {

	int size;
	Node<T> root;
	Node<T> tail;

	public CircularLinkedList() {
		this.root = null;
		this.size = 0;
	}

	@SuppressWarnings("hiding")
	public class Node<T> {
		T data;
		Node<T> next;

		public Node() {
			this.data = null;
			this.next = null;
		}

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	@Override
	public <E> boolean insertAtEnd(E data) {
		if (tail == null) {
			tail = new Node<T>((T) data);
		} else {
			// The tail's next will become the new node
			// The new node's next will become the tails..
			Node<T> newNode = new Node<T>((T) data);

			newNode.next = root;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public <E> boolean insertAtHead(E data) {
		if (size == 0) {
			root = new Node<T>((T) data);
		} else {
			Node<T> newNode = new Node<T>((T) data);

			newNode.next = root;
			root = newNode;
			tail.next = root;
		}

		size++;
		return true;
	}

	@Override
	public <E> E peek() {
		if (size != 0)
			return (E) root.data;

		return null;
	}

	@Override
	public <E> E removeEnd() {
		if (size != 0) {
			Node<T> curNode = root;
			Node<T> prev = null;

			while (curNode != null) {
				prev = curNode;
				curNode = curNode.next;
			}

			prev.next = root;
			tail = prev;
			size--;

		}
		return null;
	}

	@Override
	public <E> E removeHead() {
		if (root != null) {
			Node<T> curRoot = root;
			root = root.next;
			tail.next = root;

			size--;
			return (E) curRoot;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public String toString() {
		Node<T> curNode = root;
		String s = "";

		while (curNode != null) {
			s += curNode.data + ", ";
			curNode = curNode.next;
		}

		return s;
	}
}