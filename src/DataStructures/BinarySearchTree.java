package DataStructures;
public class BinarySearchTree<T extends Comparable<T>> {
	public class Node {
		T data;
		Node right, left;

		public Node(T data) {
			this.data = data;
			this.right = null;
			this.left = null;
		}
	}

	Node root;
	int size = 0;
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(3);
		bst.insert(1);
		bst.insert(6);
		bst.insert(0);
		bst.insert(2);
		bst.insert(6);
		bst.insert(5);
		bst.insert(8);
		
		bst.levelOrderTraversal(bst.root);
	}

	public BinarySearchTree(T data) {
		root = new Node(data);
	}

	public void insert(T data) {
		insertItem(data, root);
		size++;
	}

	public void insertItem(T data, Node node) {
		if (node == null) {
			node = new Node(data);
			return;
		}

		if (node.data.compareTo(data) > 0) {
			if (node.left != null)
				insertItem(data, node.left);
			else
				node.left = new Node(data);
		} else if (node.data.compareTo(data) < 0) {
			if (node.right != null)
				insertItem(data, node.right);
			else
				node.right = new Node(data);
		}
	}

	public boolean search(T target) {
		return search(target, root);
	}

	public boolean search(T target, Node node) {
		if (node.data.compareTo(target) == 0)
			return true;

		if (node.left == null && node.right == null)
			return false;

		if (node.data.compareTo(target) > 0)
			return search(target, node.left);
		else if (node.data.compareTo(target) < 0)
			return search(target, node.right);

		return false;
	}

	public void printPostOrder(Node node) {
		if (node != null) {
			printInOrder(node.left);
			printInOrder(node.right);
			System.out.print(node.data + " ");
		}
	}

	public void printPreOrder(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			printInOrder(node.left);
			printInOrder(node.right);
		}

	}

	public void printInOrder(Node node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(node.data + " ");
			printInOrder(node.right);
		}
	}
	
	public void levelOrderTraversal(Node node) {
		Queue<Node> queue = new Queue<Node>(this.size);
		
		queue.enQueue(node);
		
		while (!queue.isEmpty()) {
			Node curNode = queue.deQueue();
			System.out.printf("%d ", curNode.data);

			if (curNode.left != null) {
				queue.enQueue(curNode.left);
			}
			if (curNode.right != null) {
				queue.enQueue(curNode.right);
			}
		}
	}
}