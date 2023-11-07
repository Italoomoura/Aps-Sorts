public class BubbleSort {

	public void bubble_sort(String lista[]) {
		int i, j, tamanho = lista.length;
		String aux;
		
		for(i=tamanho-1; i>0; i--) {
			for (j=0; j<i; j++) {
				if(lista[j].compareTo(lista[j + 1]) > 0) {
					aux = lista[j];
					lista[j] = lista[j+1];
					lista[j+1] = aux;
				}
			}
		}
	}
	
	public void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
	
}
