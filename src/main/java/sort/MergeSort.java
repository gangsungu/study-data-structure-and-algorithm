package sort;

public class MergeSort implements ISort {
    // 안정 정렬

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    // 분할
    private void mergeSort(int[] arr, int low, int high) {
        if (low >= high) { // low 와 high 의 인덱스가 동일하다는 것 -> 배열의 크기가 1이 되는 경우
            return;
        }
        // 실제로 arr 을 쪼개서 수많은 배열로 따로 만드는 게 아니라
        // arr 가 둘씩 분할되는 인덱스 위치를 찾음
        int mid = low + ((high - low) / 2); // 리스트의 중간 위치 인덱스. 중간 인덱스를 기준으로 분할
        mergeSort(arr, low, mid);           // 중간 인덱스를 기준으로 왼쪽 부분
        mergeSort(arr, mid + 1, high);  // 중간 인덱스를 기준으로 오른쪽 부분
        // low, mid, high 에는 분할된 인덱스의 값이 있음
        merge(arr, low, mid, high);         // merge 하면서 정렬
    }

    private void merge(int arr[], int low, int mid, int high) {
        int[] temp = new int[high - low + 1];   // 보조 배열
        int idx = 0;                            // 보조 배열의 인덱스

        int left = low;         // 분할된 왼쪽 리스트의 시작 인덱스
        int right = mid + 1;    // 분할된 오른쪽 리스트의 시작 인덱스
        while (left <= mid && right <= high) {
            // left 나 right 인덱스 둘중 하나라도
            // 리스트에 있는 값을 모두 꺼내게 되면 while 문이 종료
            if (arr[left] <= arr[right]) {   // 오름 차순 정렬해서 데이터를 쌓아야 하므로 작은 값부터
                temp[idx] = arr[left];
                left++;
            } else {
                temp[idx] = arr[right];
                right++;
            }
            idx++;
        }

        while (left <= mid) {   // 왼쪽 리스트에 아직 값이 남아 있는 경우
            temp[idx] = arr[left];
            idx++;
            left++;
        }

        while (right <= high) { // 오른쪽 리스트에 아직 값이 남아 있는 경우
            temp[idx] = arr[right];
            idx++;
            right++;
        }

        // 보조배열에 있는 값을 arr 로 복사
        for (int i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }
    }
}
