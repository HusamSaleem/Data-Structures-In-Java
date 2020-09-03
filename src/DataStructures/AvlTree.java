package DataStructures;

public class AvlTree<T extends Comparable<T>> {

	public class Node {
		T data;
		int balanceFactor;
		int height;

		Node right, left, parent;

		public Node(T data) {
			this.data = data;
			this.height = 0;

			this.right = null;
			this.left = null;
		}
	}

	Node root;

	public AvlTree(T data) {
		root = new Node(data);
	}

	public static void main(String[] args) {
		AvlTree<Integer> tree = new AvlTree<Integer>(5);

		tree.insert(6);
		tree.insert(7);
		tree.insert(3);

		tree.printInOrder(tree.root);
		System.out.println("\nNew root is: " + tree.root.data);
	}

	private int nodeCount = 0;

	public int height() {
		if (root == null)
			return 0;
		return root.height;
	}

	public int size() {
		return nodeCount;
	}

	public boolean isEmpty() {
		return (nodeCount == 0);
	}

	public boolean search(T target) {
		return search(target, root);
	}

	public boolean search(T target, Node node) {
		Node curNode = node;

		while (curNode != null) {
			if (curNode.data.compareTo(target) == 0)
				return true;

			if (curNode.data.compareTo(target) > 0)
				curNode = curNode.left;
			else
				curNode = curNode.right;
		}

		return false;
	}

	public void insert(T data) {
		if (data == null)
			return;
		if (!search(data)) {
			root = insertItem(data, root);
			this.nodeCount++;
		}
	}

	private Node insertItem(T data, Node node) {
		if (node == null) {
			return new Node(data);
		}

		if (data.compareTo(node.data) < 0) {
			node.left = insertItem(data, node.left);
		} else {
			node.right = insertItem(data, node.right);
		}

		update(node);
		node = checkBalance(node);
		return node;
	}

	private Node checkBalance(Node node) {
		// Not balanced
		if (node != null && (node.balanceFactor > 1 || node.balanceFactor < -1)) {
			// Rotate

			// Balance factors
			// 2 -> left-heavy
			// -2 -> right-heavy

			// Left-Right rotation
			if (node.balanceFactor == 2) {

				// Rotate left first
				if (node.left.balanceFactor <= 0) {
					node = rotateLeftRight(node);
				} else {
					node = rotateRight(node);
				}
			}
			// Right-Heavy
			else if (node.balanceFactor == -2) {
				// Rotate right first
				if (node.right.balanceFactor >= 0) {
					node = rotateRightLeft(node);
				} else {
					node = rotateLeft(node);
				}
			}
		}

		return node;
	}

	private Node rotateLeft(Node node) {
		Node rightTemp = node.right;

		node.right = rightTemp.left;
		rightTemp.left = node;

		update(node);
		update(rightTemp);
		return rightTemp;
	}

	private Node rotateRight(Node node) {
		Node leftTemp = node.left;

		node.left = leftTemp.right;
		leftTemp.right = node;
		
		update(node);
		update(leftTemp);
		return leftTemp;
	}

	private Node rotateLeftRight(Node node) {
		node.left = rotateLeft(node.left);

		return rotateRight(node);
	}

	private Node rotateRightLeft(Node node) {
		node.right = rotateRight(node.right);

		return rotateLeft(node);
	}

	// Updates the balances and heights for the parent node
	private void update(Node node) {
		node.height = getHeight(node);
		node.balanceFactor = getBalance(node);

		//System.out.println("Balance factor for: " + node.data + " is " + node.balanceFactor);
		//System.out.println("Height for: " + node.data + " is " + node.height);

	}

	private int getHeight(Node node) {
		int height = 0;

		if (node.left != null && node.right != null) {
			height = Math.max(node.left.height, node.right.height) + 1;
		} else if (node.left != null) {
			height = Math.max(node.left.height, 0) + 1;
		} else if (node.right != null) { 
			height = Math.max(node.right.height, 0) + 1;
		} else {
			return -1;
		}

		return height;
	}

	private int getBalance(Node node) {
		int leftTreeH = (node.left != null) ? node.left.height : -1;
		int rightTreeH = (node.right != null) ? node.right.height : -1;

		return (leftTreeH - rightTreeH);
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

}