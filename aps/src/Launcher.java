import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        // Conectar ao banco de dados MySQL
        String jdbcUrl = "jdbc:mysql://localhost/dataset";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
        	String q = "SELECT * FROM dados limit 0, 5000";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            String[] data = new String[count];

            // Recuperar os dados da tabela e armazená-los em um array
            resultSet = statement.executeQuery(q);
            int index = 0;
            while (resultSet.next()) {
                data[index] = resultSet.getString("nome");
                index++;
            }

            // Agora você tem os dados da tabela no array 'data'
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            // Aplicar o algoritmo de ordenação e medir o tempo para cada um deles
            String[] dataCopy = Arrays.copyOf(data, data.length); // Copiar os dados para manter o original inalterado
            
            // Merge Sort
            long startTime = System.nanoTime();
            MergeSort.mergeSort(dataCopy, 0, dataCopy.length - 1);
            long mergeSortTime = (System.nanoTime() - startTime);
            
            dataset.addValue((mergeSortTime/1000000), "Merge Sort", String.valueOf(data.length));

            System.out.println("Merge Sort:");
            //MergeSort.printArray(dataCopy);
            System.out.println("Tempo de execução: " + mergeSortTime + " nanossegundos");

            dataCopy = Arrays.copyOf(data, data.length); // Restaurar os dados originais
            
            // Quick Sort
            startTime = System.nanoTime();
            QuickSort.quickSort(dataCopy, 0, dataCopy.length - 1);
            long quickSortTime = (System.nanoTime() - startTime);
            
            dataset.addValue((quickSortTime/1000000), "Quick Sort", String.valueOf(data.length));

            System.out.println("\nQuick Sort:");
            //QuickSort.printArray(dataCopy);
            System.out.println("Tempo de execução: " + quickSortTime + " nanossegundos");

            dataCopy = Arrays.copyOf(data, data.length); // Restaurar os dados originais
            
            // Bubble Sort
            startTime = System.nanoTime();
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.bubble_sort(dataCopy);
            long bubbleSortTime = (System.nanoTime() - startTime);
            
            dataset.addValue((bubbleSortTime/1000000), "Bubble Sort", String.valueOf(data.length));
            
            JFreeChart chart = ChartFactory.createBarChart(
                    "Tempo de Execução dos Algoritmos de Ordenação",
                    "Quantidade de Itens",
                    "Tempo (ms)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );
            
            JFrame frame = new JFrame("Gráfico de Ordenação");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ChartPanel(chart));
            frame.pack();
            frame.setVisible(true);

            System.out.println("\nBubble Sort:");
            //bubbleSort.printArray(dataCopy);
            System.out.println("Tempo de execução: " + bubbleSortTime + " nanossegundos");
            
            System.out.println("\nTempo aproximado em milissegundos:\nMergeSort:\t" + (mergeSortTime/1000000) + "\nQuisckSort:\t" + (quickSortTime/1000000) + "\nBubbleSort:\t" + (bubbleSortTime/1000000));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
