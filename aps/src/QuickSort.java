public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
        	// Encontra o pivô
            int pivotIndex = partition(arr, low, high);
            
            // Ordena a metade esquerda
            quickSort(arr, low, pivotIndex - 1);
            
            // Ordena a metade direita
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
    	
    	// Escolhe o elemento mais à direita como pivô
        int pivot = arr[high];
        
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                
                // Troca elementos menores que o pivô para a esquerda
                swap(arr, i, j);
            }
        }
        
        // Coloca o pivô em sua posição final
        swap(arr, i + 1, high);
        
        return i + 1;
    }
    
    // Função para trocar elementos no array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
