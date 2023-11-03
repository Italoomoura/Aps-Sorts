package sorts;

public class BubbleSort {

	public void bubble_sort(int lista[]) {
		int i, j, aux, tamanho = lista.length;
		for(i=tamanho-1; i>0; i--) {
			for (j=0; j<i; j++) {
				if(lista[j] > lista[j+1]) {
					aux = lista[j];
					lista[j] = lista[j+1];
					lista[j+1] = aux;
				}
			}
		}
	}
	
}
