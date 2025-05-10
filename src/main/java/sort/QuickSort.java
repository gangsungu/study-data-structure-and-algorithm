package sort;

public class QuickSort implements ISort {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        // 분할된 배열의 크기가 1이 될 때까지
        if (low >= high) {
            return;
        }
        // pivot 인덱스
        int pivot = low + ((high - low) / 2);
        // pivot 인덱스에 위치한 값
        int pivotValue = arr[pivot];

        int left = low;
        int right = high;


        // 피봇값을 기준으로 피봇값의 왼쪽에는 피봇값보다 작은 값
        // 오른쪽엔 피봇값보다 큰 값
        while (left <= right) {
            // 왼쪽 값이 피봇값보다 작으면
            // 위치를 바꿀 필요가 없기 때문에 그대로 두고 왼쪽 인덱스를 증가
            while (arr[left] < pivotValue) {
                left++;
            }
            // 오른쪽 값이 피봇값보다 크면
            // 위치를 바꿀 필요가 없디 때문에 계속 오른쪽 인덱스를 감소
            while (arr[right] > pivotValue) {
                right--;
            }

            // 왼쪽 인덱스와 오른쪽 인덱스가 교차 하지 않았으면 왼쪽 값과 오른쪽 값 교환
            if (left <= right) {
                int tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                left++;
                right--;
            }
        }

        quickSort(arr, low, right);
        quickSort(arr, left, high);
    }
}
