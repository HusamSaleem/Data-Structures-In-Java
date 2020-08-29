package DataStructures;
// Double Linked List implementation
public class LinkedList<E> {

	Node head;
	Node tail;
	int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	public class Node {
		public E val;
		public Node next;
		public Node prev;

		public Node(E val) {
			this.val = val;
			this.next = null;
			this.prev = null;
		}
	}

	public boolean insert(E val) {
		if (size == 0) {
			// O(1)
			this.head = new Node(val);

			size++;
			return true;
		} else {

			if (this.tail == null) {
				this.tail = new Node(val);
				this.tail.prev = this.head;
				this.head.next = this.tail;
				size++;
				return true;
			}

			Node newNode = new Node(val);

			this.tail.next = newNode;
			newNode.prev = this.tail;
			this.tail = newNode;

			size++;

			return true;
		}
	}

	<E extends Comparable<E>> E removeSmallest() {
		if (size == 0) {
			return null;
		}
		E smallVal = (E) this.head.val;

		Node cur = head;
		int pos = 0;
		int count = 0;

		while (cur != null) {
			if (smallVal.compareTo((E) cur.val) > 0) {
				smallVal = (E) cur.val;
				pos = count;
			}

			cur = cur.next;

			count++;
		}

		cur = head;
		Node prev = null;

		count = 0;

		while (count < pos) {
			prev = cur;
			cur = cur.next;
			count++;
		}

		prev.next = cur.next;
		cur = null;

		return smallVal;

	}

	public boolean push(E val) {
		if (size == 0) {
			this.head = new Node(val);
			return true;
		} else {
			Node node = new Node(val);
			node.next = this.head;

			this.head.prev = node;

			this.head = node;
			return true;
		}
	}

	public boolean insertAt(E val, int pos) {
		if (size == 0 || pos > size - 1) {
			return false;
		} else if (pos == 0) {
			push(val);
			return true;
		} else {

			int i = 0;

			Node cur = head;
			Node prev = null;

			while (i < pos && cur.next != null) {
				prev = cur;
				cur = cur.next;
				i++;
			}

			if (i == pos) {
				Node newNode = new Node(val);
				newNode.next = cur;

				newNode.prev = prev;
				cur.prev = newNode;

				prev.next = newNode;

				return true;
			}

			return false;

		}
	}

	// O(n)
	public void printFromHead() {
		Node temp = head;

		while (temp != null) {
			System.out.print(temp.val + " -> ");
			temp = temp.next;
		}
	}

	public void printFromTail() {
		Node temp = tail;

		while (temp != null) {
			System.out.print(temp.val + " -> ");
			temp = temp.prev;
		}
	}

}
