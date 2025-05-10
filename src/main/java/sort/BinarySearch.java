package sort;

public class BinarySearch {

    public int search(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;

        int m;
        while (l <= r) {
            m = l + ((r - l) / 2);  // overflow exception

            if (arr[m] == target) {
                return m;
            }

            if (arr[m] < target) {  // target 값이 더 큰 경우
                l = m + 1;
            } else {    // target 값이 더 작은 경우
                r = m - 1;
            }
        }
        return -1;
    }
}
