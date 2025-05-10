package sort;

public class InsertionSort implements ISort {
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];   // 삽입 위치를 찾아줄 데이터
            int j = i - 1;  // 0-j 정렬된 서브 리스트
            while (j >= 0 && arr[j] > key) {
                // key 값보다 정렬된 배열에 있는 값이 크면
                arr[j + 1] = arr[j];    // 데이터를 한칸씩 오른 쪽으로 이동
                j = j - 1; // 역순으로 값 비교
            }
            arr[j + 1] = key;
        }
    }
}
