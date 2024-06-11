import java.util.*;

/**
 * @author Raika Roy Choudhury, APCS Peterson P2
 * Main program (utilizing classes) with main method
 * Chapter 13 Programming Project
 * <p>
 * Tests each algorithm with arrays of varying sizes (1 to 4096, in powers of two), arrays in reverse order, 
 * and pre-sorted arrays
 * <p>
 * Separated into two parts, one with different length arrays, 
 * and one with fixed length arrays
 * Prints data from SortStats object of each test
 *
 */

public class Program {
	/**
	 * Main method, tests sorters
	 * @param args     Main method
	 */
	public static void main(String[] args) {
		ISorter[] sorters = {new BubbleSort(), new SelectionSort(), new InsertionSort(), new MergeSort()};

		// First set of tests for arrays of various lengths between 1 and 4096 inclusive, 
		// by powers of 2 (12 arrays total)
		System.out.println("Test 1: Sorting arrays of varying length");
		for (int i = 1; i <= 4096; i *= 2) {
			int[] a = new int[i];
			for (int j = 0; j < i; j++) {
				a[j] = (int) (Math.random() * 100);
			}


			for (int j = 0; j < sorters.length; j++) {
				int[] b = Arrays.copyOf(a, a.length);
				ISortStats stats = sorters[j].sort(b);
				System.out.println("\nSorted: " + Check.isInOrder(b));
				System.out.println(stats.toString());
			}
		}

		// Second set of tests, reverse order and already ordered arrays of size 4096
		System.out.println("\nTest 2: Sorting reverse order and already sorted arrays");

		int[] a = new int[4096];
		int bigNum = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			bigNum -= (int)(Math.random() * 4096 + 1);
			a[i] = bigNum;
		}

		System.out.println("\nSorting reverse ordered array:");

		int[] sorted = new int[4096];
		for (int j = 0; j < sorters.length; j++) {
			int[] b = Arrays.copyOf(a, a.length);
			ISortStats stats1 = sorters[j].sort(b);
			System.out.println("\nSorted: " + Check.isInOrder(b));
			System.out.println(stats1.toString());

			System.out.println();
			sorted = Arrays.copyOf(b, b.length);

		}
		
		System.out.println("Sorting already sorted array:");
		
		for (int j = 0; j < sorters.length; j++) {
			ISortStats stats2 = sorters[j].sort(sorted);
			System.out.println("\nSorted: " + Check.isInOrder(sorted));
			System.out.println(stats2.toString());
			
			System.out.println();

		}

	}
}
