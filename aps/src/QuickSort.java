public class QuickSort {
    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
        	// Encontra o pivô
        	int[] pivotIndexes = partition(arr, low, high);
            
            // Ordena a metade esquerda
        	quickSort(arr, low, pivotIndexes[0] - 1);
            
            // Ordena a metade direita
        	quickSort(arr, pivotIndexes[1] + 1, high);
        }
    }

    public static int[] partition(String[] arr, int low, int high) {
    	
    	// Escolhe o elemento mais à direita como pivô
    	String pivot = arr[low];  // Escolha o primeiro elemento como pivô
        int left = low;
        int right = high;
        int i = low;
        
        while (i <= right) {
            int cmp = arr[i].compareTo(pivot);
            if (cmp < 0) {
                swap(arr, left, i);
                left++;
                i++;
            } else if (cmp > 0) {
                swap(arr, i, right);
                right--;
            } else {
                i++;
            }
        }

        // Coloque o pivô na posição correta
        int[] pivotIndexes = {left, right};
        return pivotIndexes;
    }
    
    // Função para trocar elementos no array
    public static void swap(String[] arr, int i, int j) {
    	String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
