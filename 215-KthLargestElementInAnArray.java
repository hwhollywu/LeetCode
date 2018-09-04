// 215. Kth Largest Element in an Array 
// https://leetcode.com/problems/kth-largest-element-in-an-array/description/


// Big O Cheatsheet: http://bigocheatsheet.com/

// default sorting algorithm
// Time O(NlogN), space O(1)

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

// the following sorting algorithm has time complexity of O(NlogN)

// 归并排序 merge sort 
// Time O(nlogn) Space O(n)

// 堆排序 heap sort
// space O(1)

public int findKthLargest_HeapSort(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(); 
    for (int val : nums) {
        pq.add(val);
        if (pq.size() > k)
            pq.poll();  // remove the head
    }
    return pq.peek();  // retrieve the head
}

// 快速排序 quick sort
// space O(logn)

public int findKthLargest_QuickSort(int[] nums, int k) {
    k = nums.length - k;
    int l = 0, h = nums.length - 1;
    while (l < h) {
        int j = partition(nums, l, h);
        if (j == k)
            break;
        else if (j < k)
            l = j + 1;
        else
            h = j - 1;
    }
    return nums[k];
}

private int partition(int[] a, int l, int h) {
    int i = l, j = h + 1;
    while (true) {
        while (a[++i] < a[l] && i < h) ;
        while (a[--j] > a[l] && j > l) ;
        if (i >= j)
            break;
        swap(a, i, j);
    }
    swap(a, l, j);
    return j;
}

private void swap(int[] a, int i, int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
}
