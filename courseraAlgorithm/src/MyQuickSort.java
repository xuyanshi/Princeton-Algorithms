import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


public class MyQuickSort<T extends Comparable<T>> {


    public void quickSort(T[] a) {
        StdRandom.shuffle(a); // Shuffle the array firstly
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivotIdx = partition(a, lo, hi);
        quickSort(a, lo, pivotIdx - 1); // quick sort the left side
        quickSort(a, pivotIdx + 1, hi); // quick sort the right side
    }

    private int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // two pointers
        T pivot = a[lo];
        while (i < j) {
            while (a[++i].compareTo(pivot) < 0) {
                if (i == hi) {
                    break;
                }
            }
            while (a[--j].compareTo(pivot) > 0) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j); // Move the pivot to the correct location.
        return j; // j is the index of pivot. i.e. a[lo ... j-1] <= a[j] <= a[j+1 ... hi]
    }

    private void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3, 5, 1, 7, 6, 2, 4, 0};
        MyQuickSort<Integer> mqs = new MyQuickSort<>();

        System.out.println(Arrays.toString(a));
        mqs.quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}
