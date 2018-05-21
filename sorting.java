// Sorting Algorithm


private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
}

private static void swap(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
}

// Big O Cheatsheet: http://bigocheatsheet.com/

// Time O(n^2), Space O(1)

public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            swap(a, i, min);
        }
    }
}


public class Bubble {
    public static void sort(Comparable[] a) {
        int N = a.length;
        boolean hasSorted = false;
        for (int i = 0; i < N && !hasSorted; i++) {
            hasSorted = true;
            for (int j = 0; j < N - i - 1; j++) {
                if (less(a[j + 1], a[j])) {
                    hasSorted = false;
                    swap(a, j, j + 1);
                }
            }
        }
    }
}

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                swap(a, j, j - 1);
    }
}
/* Example:
12, 11, 13, 5, 6 i=1
11, 12, 13, 5, 6 i=1
11, 12, 13, 5, 6 i=2
5, 11, 12, 13, 6 i=3
5, 6, 11, 12, 13 i=4
*/

// 希尔排序使用插入排序对间隔 h 的序列进行排序, 通过不断减小 h，最后令 h=1
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1; // 1, 4, 13, 40, ...

        while (h >= 1) {
            for (int i = h; i < N; i++)
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    swap(a, j, j - h);
            h = h / 3;
        }
    }
}
