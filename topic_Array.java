// Array


// Left Rotation: 
// https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
// Input: an array of integers a, size n, number of left rotations k 
// Output: array after rotation.
// Example: 5 4 
//		1 2 3 4 5
// - >  5 1 2 3 4

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        int[] newArray = new int[n];
        for (int i=0;i<n;i++){
            newArray[(i+n-k)%n]=a[i];
        }
        return newArray;
      
    }

// New Year Chaos
// https://www.hackerrank.com/challenges/new-year-chaos/forum
/* 1. understand bubble sort, 
   2. understand "chaotic"case, 
   3. reduce O(n*2) to O(n) / not exactly bubble sort
   4. optimized bubble sort, add is_sorted boolean
 */


    static void minimumBribes(int[] q) {
        int n = q.length;
        int result = 0;
        
         // check chaotic case: any person moves more than 2 positions
        for (int i=0; i<n; i++){
            if ((q[i] - (i+1)) > 2){
                System.out.println("Too chaotic");
                return;
            }
        }
        // else: count swaps in bubble sort
        for (int i=0; i<n-1; i++){
            // optimized bubble sort
            boolean is_sorted = true;
            
            for(int j=0; j<n-i-1;j++){
                if (q[j] > q[j+1]){
                    //swap
                    int temp = q[j];
                    q[j] = q[j+1];
                    q[j+1] = temp;
                    is_sorted = false;
                    result++;
                }
            }
            // check if its already ordered
            if (is_sorted) break;
        }
        System.out.println(result);
    }

// 2D Array
// https://www.hackerrank.com/challenges/2d-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
/*
 1. (0,0)(1,0)(2,0) i=x, j=y in 2D array
         (1,1)
    (0,2)(1,2)(2,2)
 2. optimize  (not passing test 3 & 7) not initialize sum to zero, because negative values would not overwrite it.
    the sum might be <= 0.  
*/

     static int hourglassSum(int[][] arr) {
        int x = arr[0].length;
        int y = arr[1].length;
        int sum = Integer.MIN_VALUE;
        for (int i=0; i < x-2; i++){
            for (int j=0; j < y-2; j++){
                int temp = arr[i][j]+arr[i][j+1]+arr[i][j+2]+arr[i+1][j+1]+arr[i+2][j]+arr[i+2][j+1]+arr[i+2][j+2];
                if (temp > sum){
                    sum = temp;
                }
            }
        }
        return sum;
    }




