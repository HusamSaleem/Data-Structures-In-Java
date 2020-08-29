package DataStructures;
//  Heap is typically represented as an array
// This one is currently just a min-heap implementation
public class Heap {
	int size;
	int capacity;

	Integer[] heapArr;

	public Heap(int capacity) {
		this.capacity = capacity;
		this.size = 0;

		heapArr = new Integer[capacity];
	}
	
	public void runHeapSort() {
		Integer[] sortedArr = new Integer[capacity];
		
		int i = 0;
		while (size > 0) {
			sortedArr[i] = pop();
			i++;
		}
		
		printArr(sortedArr);
	}
	
	private void swap(int index1, int index2) {
		Integer temp = this.heapArr[index1];
		this.heapArr[index1] = this.heapArr[index2];
		this.heapArr[index2] = temp;
	}
	
	public Integer peek() {
		if (size == 0)
			return null;
		return this.heapArr[0];
	}
	
	public Integer pop() {
		if (size == 0)
			return null;
		
		Integer data = this.heapArr[0];
		this.heapArr[0] = this.heapArr[size - 1];
		size--;
		heapifyDown();
		
		return data;
	}
	
	public void insert(Integer data) {
		if (size < capacity) {
			this.heapArr[size] = data;
			size++;
			heapifyUp();
		}
	}
	
	private void heapifyUp() {
		int index = size - 1;
		
		while (hasParent(index) && this.heapArr[getParentIndex(index)] > this.heapArr[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}
	
	private void heapifyDown() {
		int index = 0;
		
		while (hasLeftChild(index)) {
			int smallerChildIndex = getLeftChildIndex(index);
			
			if (hasRightChild(index) && this.heapArr[getRightChildIndex(index)] < this.heapArr[getLeftChildIndex(index)]) {
				smallerChildIndex = getRightChildIndex(index);
			}
			
			if (this.heapArr[index] < this.heapArr[smallerChildIndex]) {
				break;
			} else {
				swap(index, smallerChildIndex);
				index = smallerChildIndex;
			}
		}
	}
	
	public void printHeap() {
		for (Integer i : this.heapArr) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}
	
	public <T> void printArr(T[] arr) {
		for (T i : arr) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}

	private boolean hasLeftChild(int index) {
		return (getLeftChildIndex(index) < size);
	}
	
	private boolean hasRightChild(int index) {
		return (getRightChildIndex(index) < size);
	}
	
	private boolean hasParent(int index) {
		return (getParentIndex(index) < size);
	}

	private int getRightChildIndex(int parentIndex) {
		return (parentIndex * 2) + 2;
	}

	private int getLeftChildIndex(int parentIndex) {
		return (parentIndex * 2) + 1;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}
}