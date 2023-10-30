public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
        	// Encontra o ponto médio do array
            int middle = (left + right) / 2;
            
            // Classifica a metade esquerda
            mergeSort(arr, left, middle);
            
            // Classifica a metade direita
            mergeSort(arr, middle + 1, right);
            
            // Combina as duas metades classificadas
            merge(arr, left, middle, right);
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        
        // Cria 2 arrays temporários para as duas metades
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = arr[middle + 1 + i];
        }

        int i = 0, j = 0, k = left;
        
        // Combina os dois arrays temporários em um único array ordenado
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copia quaisquer elementos restantes da metade esquerda
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copia quaisquer elementos restantes da metade direita
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
