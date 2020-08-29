package Algorithms;


import java.util.Random;

public class SortingAlgorithms {

	public static void main(String[] args) {
		SortingAlgorithms sort = new SortingAlgorithms();

		int[] arr = new int[1000];

		arr = addRandomElements(arr);
		arr = sort.quickSort(arr, 0, arr.length - 1);
		// arr = sort.mergeSort(arr, 0, arr.length-1);

		// arr = sort.insertionSort(arr);
		// arr = sort.selectionSort(arr);
		// arr = sort.bubbleSort(arr);

		sort.printArray(arr);
	}

	// Bubble Sort, O(N^2)
	// Stable
	// In place
	public <E extends Comparable<E>> E[] bubbleSort(E[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].compareTo(arr[j]) > 0) {
					swap(arr, i, j);
				}
			}
		}

		return arr;
	}

	// Selection Sort, O(N^2)
	// Not stable
	// In place
	// Finds the minimum element in the array starting from index 0, then swaps it
	// with it
	public <E extends Comparable<E>> E[] selectionSort(E[] arr) {
		int minIndex = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minIndex].compareTo(arr[j]) > 0) {
					minIndex = j;
				}
			}

			swap(arr, i, minIndex);
		}

		return arr;
	}

	// Insertion Sort, O(N^2)
	// Stable
	// In Place
	// Chooses an element starting at 1, then checks to see whether or not the left
	// side of the element is bigger or smaller than it, and then shifts the key
	// until it is in place
	public <E extends Comparable<E>> E[] insertionSort(E[] arr) {
		int markerIndex;
		int j = 0;

		for (int i = 1; i < arr.length; i++) {
			markerIndex = i;
			j = i - 1;

			while (j >= 0 && arr[markerIndex].compareTo(arr[j]) < 0) {
				// Swap the left element from the current key with the current "key" until it is
				// in place
				// Basically shifting the key to the left
				swap(arr, j, markerIndex);
				markerIndex = j;

				j--;
			}
		}

		return arr;
	}

	// Main function that sorts arr[l..r] using
	// merge()
	public int[] mergeSort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int mid = (l + r) / 2;

			// Sort first and second halves
			mergeSort(arr, l, mid);
			mergeSort(arr, mid + 1, r);

			// Merge the sorted halves
			return merge(arr, l, mid, r);
		}
		return arr;
	}

	public int[] merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}

		return arr;
	}

	// Will find the pivot point in the array and place the smaller elements to the
	// left, and the bigger ones on the right
	// Then will sort the left most array first, and then the right...
	// Average case: O(nlogn)
	// In place
	// Not stable (Can be though)
	public int[] quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int partitionIndex = partition(arr, start, end);

			quickSort(arr, start, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, end);
		}

		return arr;
	}

	public int partition(int[] arr, int start, int end) {
		// Wil choose the last element as the pivot
		Random rand = new Random();
		int randIndex = start + rand.nextInt(end - start);
		int pivot = arr[randIndex];
		int pivotIndex = start;

		// This is needed if we use a random pivot because the pivot should be moved to
		// the end of the array...
		swap(arr, randIndex, end);

		// Keep smaller elements of the pivot to the left, the bigger ones to the right
		// Basically swaps the smaller elements with the bigger elements so it is sorted
		// in a way
		for (int i = start; i < end; i++) {
			if (pivot > arr[i]) {
				swap(arr, i, pivotIndex);
				pivotIndex++;
			}
		}

		// So the pivot is in the right place
		swap(arr, end, pivotIndex);

		return pivotIndex;
	}

	private void swap(int[] arr, int ind1, int ind2) {
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;

	}

	public <E> void printArray(E[] arr) {
		for (E e : arr) {
			System.out.print(e + " ");
		}

		System.out.println();
	}

	public void printArray(int[] arr) {
		for (int e : arr) {
			System.out.print(e + " ");
		}

		System.out.println();
	}

	public static int[] addRandomElements(int[] arr) {
		Random rand = new Random();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(1000);
		}

		return arr;
	}

	public <E> void swap(E[] arr, int ind1, int ind2) {
		E temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
}
