import java.util.Arrays;

public class MyMergeSort<T extends Comparable> {
    public void mergeSort(T[] a, int l, int r, T[] tmp) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(a, l, mid, tmp);
        mergeSort(a, mid + 1, r, tmp);

        /* or: System.arraycopy(a, l, tmp, l, r + 1 - l); */
        for (int k = l; k <= r; k++) {
            tmp[k] = a[k];
        }

        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
            if (tmp[i].compareTo(tmp[j]) <= 0) {
                a[k++] = tmp[i++];
            } else {
                a[k++] = tmp[j++];
            }
        }
        while (i <= mid) {
            a[k++] = tmp[i++];
        }
        while (j <= r) {
            a[k++] = tmp[j++];
        }
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3, 5, 1, 7, 6, 2, 4, 0};

        Integer[] tmp = new Integer[a.length];
        MyMergeSort<Integer> mms = new MyMergeSort<>();

        System.out.println(Arrays.toString(a));
        mms.mergeSort(a, 0, a.length - 1, tmp);
        System.out.println(Arrays.toString(a));
    }

}
