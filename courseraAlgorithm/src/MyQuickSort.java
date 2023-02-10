import java.util.Arrays;

public class MyQuickSort<T extends Comparable<T>> {
    public void mergeSort(T[] a, int l, int r) {
        if (l >= r) {
            return;
        }

    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3, 5, 1, 7, 6, 2, 4, 0};

        MyQuickSort<Integer> mqs = new MyQuickSort<>();

        System.out.println(Arrays.toString(a));
        mqs.mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
