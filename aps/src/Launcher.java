import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class Launcher {
	
	private String[] data;
	private String[] dataCopy;
	private DefaultCategoryDataset dataset;
	public double medida;
	public String nomeMedida;
	private String limit;
	private String coluna;
	private DecimalFormat df = new DecimalFormat("#.#####");
	private FrameSort frame;

    public Launcher(FrameSort frame, String limit, String coluna) {
    	this.frame = frame;
    	this.limit = limit;
    	this.coluna = coluna;
    	
    	try {
			sql();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        // Agora você tem os dados da tabela no array 'data'
        dataset = new DefaultCategoryDataset();

        // Aplicar o algoritmo de ordenação e medir o tempo para cada um deles
        dataCopy = Arrays.copyOf(data, data.length); // Copiar os dados para manter o original inalterado
    }
    
    private void sql() throws SQLException {
    	// Conectar ao banco de dados MySQL
        String jdbcUrl = "jdbc:mysql://localhost/dataset";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
        	String q = "SELECT * FROM dados LIMIT " + limit;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            
            int count = 0;
            frame.listModel.clear();
            while (resultSet.next()) {
                count++;
                frame.listModel.addElement(resultSet.getString("nome"));
            }
            
            frame.elementosList.setModel(frame.listModel);
            frame.elementosScrollPane.setViewportView(frame.elementosList);
            frame.elementosScrollPane.invalidate();
            frame.elementosScrollPane.revalidate();
            frame.elementosScrollPane.repaint();
            
            data = new String[count];

            // Recuperar os dados da tabela e armazená-los em um array
            resultSet = statement.executeQuery(q);
            int index = 0;
            while (resultSet.next()) {
                data[index] = resultSet.getString(coluna);
                index++;
            }
        }
    }
    
    public void attLista(String[] dataList) {
    	int count = 0;
        frame.listModel.clear();
        while (count < dataList.length) {
        	//System.out.println(dataList[count]);
            frame.listModel.addElement(dataList[count]);
            count++;
        }
        
        frame.elementosList.setModel(frame.listModel);
        frame.elementosScrollPane.setViewportView(frame.elementosList);
        frame.elementosScrollPane.invalidate();
        frame.elementosScrollPane.revalidate();
        frame.elementosScrollPane.repaint();
    }
    
    public void runBubbleSort() {
    	// Bubble Sort
        long startTime = System.nanoTime();
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubble_sort(dataCopy);
        long bubbleSortTime = (System.nanoTime() - startTime);
        attLista(dataCopy);
        
        dataset.addValue((bubbleSortTime/medida), "Bubble Sort", String.valueOf(data.length));
        
        dataCopy = Arrays.copyOf(data, data.length); // Restaurar os dados originais
        
        frame.bubbleSortPane.setVisible(true);
        frame.tempoBubbleSort.setText("Tempo em " + nomeMedida + ": " + df.format(bubbleSortTime/medida));
    }
    
    public void runMergeSort() {
    	// Merge Sort
        long startTime = System.nanoTime();
        MergeSort.mergeSort(dataCopy, 0, dataCopy.length - 1);
        long mergeSortTime = (System.nanoTime() - startTime);
        attLista(dataCopy);
        
        dataset.addValue((mergeSortTime/medida), "Merge Sort", String.valueOf(data.length));

        dataCopy = Arrays.copyOf(data, data.length); // Restaurar os dados originais
        
        frame.mergeSortPane.setVisible(true);
        frame.tempoMergeSort.setText("Tempo em " + nomeMedida + ": " + df.format(mergeSortTime/medida));
    }
    
    public void runQuickSort() {
    	// Quick Sort
        long startTime = System.nanoTime();
        QuickSort.quickSort(dataCopy, 0, dataCopy.length - 1);
        long quickSortTime = (System.nanoTime() - startTime);
        attLista(dataCopy);
        
        dataset.addValue((quickSortTime/medida), "Quick Sort", String.valueOf(data.length));

        dataCopy = Arrays.copyOf(data, data.length); // Restaurar os dados originais
        
        frame.quickSortPane.setVisible(true);
        frame.tempoQuickSort.setText("Tempo em " + nomeMedida + ": " + df.format(quickSortTime/medida));
    }
    
    public void displayChart() {
    	JFreeChart chart = ChartFactory.createBarChart(
                "Tempo de Execução dos Algoritmos de Ordenação",
                "Quantidade de Itens",
                "Tempo (" + nomeMedida + ")",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        JFrame frame = new JFrame("Gráfico de Ordenação");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
    
}
