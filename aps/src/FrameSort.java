import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FrameSort extends javax.swing.JFrame {

    public FrameSort() {
    	setTitle("Sorts");
        setPreferredSize(new Dimension(700, 530));
        setResizable(false);
        initComponents();
    }
                      
    private void initComponents() {

        elementosScrollPane = new javax.swing.JScrollPane();
        elementosList = new javax.swing.JList<>();
        imagemLabel = new javax.swing.JLabel();
        nomeTextLabel = new javax.swing.JLabel();
        tempoLabel = new javax.swing.JLabel();
        nomeLabel = new javax.swing.JLabel();
        tempoTextLabel = new javax.swing.JLabel();
        qtdTextLabel = new javax.swing.JLabel();
        qtdField = new javax.swing.JTextField();
        qtdSlider = new javax.swing.JSlider();
        algoritmoTextLabel = new javax.swing.JLabel();
        algoritmoBox = new javax.swing.JComboBox<>();
        medidaTextLabel = new javax.swing.JLabel();
        medidaBox = new javax.swing.JComboBox<>();
        bubbleSortPane = new javax.swing.JPanel();
        bubbleSortTextLabel = new javax.swing.JLabel();
        tempoBubbleSort = new javax.swing.JLabel();
        mergeSortPane = new javax.swing.JPanel();
        mergeSortTextLabel = new javax.swing.JLabel();
        tempoMergeSort = new javax.swing.JLabel();
        quickSortPane = new javax.swing.JPanel();
        quickSortTextLabel = new javax.swing.JLabel();
        tempoQuickSort = new javax.swing.JLabel();
        runButton = new javax.swing.JButton();
        listModel = new DefaultListModel<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        elementosList.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        elementosScrollPane.setViewportView(elementosList);

        imagemLabel.setToolTipText("");
        imagemLabel.setPreferredSize(new Dimension(200, 200));
        imagemLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nomeTextLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nomeTextLabel.setText("Nome:");

        tempoLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tempoLabel.setText("tipotempo");

        nomeLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nomeLabel.setText("nomearquivo");

        tempoTextLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tempoTextLabel.setText("Tempo:");

        qtdTextLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        qtdTextLabel.setText("Quantidade de elementos:");

        qtdField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        qtdField.setText("1");
        
        qtdSlider.setMinimum(1);
        qtdSlider.setMaximum(79863);
        qtdSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                qtdSliderMouseDragged(evt);
            }
        });

        algoritmoTextLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        algoritmoTextLabel.setText("Algoritmo:");

        algoritmoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bubble Sort", "Merge Sort", "Quick Sort", "Todos" }));
        algoritmoBox.setSelectedIndex(3);

        medidaTextLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        medidaTextLabel.setText("Medida de tempo:");

        medidaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nanosegundos", "Milisegundos", "Segundos" }));
        medidaBox.setSelectedIndex(1);

        bubbleSortTextLabel.setText("Bubble sort:");

        tempoBubbleSort.setText("Tempo:");

        javax.swing.GroupLayout bubbleSortPaneLayout = new javax.swing.GroupLayout(bubbleSortPane);
        bubbleSortPane.setLayout(bubbleSortPaneLayout);
        bubbleSortPaneLayout.setHorizontalGroup(
            bubbleSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bubbleSortPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bubbleSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempoBubbleSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bubbleSortTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        bubbleSortPaneLayout.setVerticalGroup(
            bubbleSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bubbleSortPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bubbleSortTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempoBubbleSort, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mergeSortTextLabel.setText("Merge Sort:");

        tempoMergeSort.setText("Tempo:");

        javax.swing.GroupLayout mergeSortPaneLayout = new javax.swing.GroupLayout(mergeSortPane);
        mergeSortPane.setLayout(mergeSortPaneLayout);
        mergeSortPaneLayout.setHorizontalGroup(
            mergeSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mergeSortPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mergeSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempoMergeSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mergeSortTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mergeSortPaneLayout.setVerticalGroup(
            mergeSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mergeSortPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mergeSortTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempoMergeSort, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        quickSortTextLabel.setText("Quick Sort:");

        tempoQuickSort.setText("Tempo:");

        javax.swing.GroupLayout quickSortPaneLayout = new javax.swing.GroupLayout(quickSortPane);
        quickSortPane.setLayout(quickSortPaneLayout);
        quickSortPaneLayout.setHorizontalGroup(
            quickSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quickSortPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quickSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempoQuickSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quickSortTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        quickSortPaneLayout.setVerticalGroup(
            quickSortPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quickSortPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quickSortTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempoQuickSort)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        runButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        runButton.setText("Rodar análise");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tempoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tempoTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(elementosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(algoritmoTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quickSortPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bubbleSortPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(qtdField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(qtdTextLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(algoritmoBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(medidaTextLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(medidaBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mergeSortPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(qtdTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(qtdField)
                            .addComponent(qtdSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(18, 18, 18)
                        .addComponent(algoritmoTextLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(algoritmoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(medidaTextLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(medidaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(runButton))
                    .addComponent(elementosScrollPane))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(imagemLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bubbleSortPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mergeSortPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(quickSortPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nomeTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tempoTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tempoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        elementosList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    try {
						displayInfos();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
        });
        
        imagemLabel.setVisible(false);
        tempoTextLabel.setVisible(false);
        tempoLabel.setVisible(false);
        nomeTextLabel.setVisible(false);
        nomeLabel.setVisible(false);
        
        bubbleSortPane.setVisible(false);
        mergeSortPane.setVisible(false);
        quickSortPane.setVisible(false);
        

        pack();
        setVisible(true);
    }// </editor-fold>                                                                 

    private void qtdSliderMouseDragged(java.awt.event.MouseEvent evt) {                                       
        qtdField.setText(Integer.toString(qtdSlider.getValue()));
    }                                                                               

    private boolean runButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	
    	int qtd = Integer.parseInt(qtdField.getText());
        if (qtd <= 0) {
        	JOptionPane.showMessageDialog(this, "O número tem que ser maior que zero");
        	return false;
        }
        if (qtd > 79863) {
        	JOptionPane.showMessageDialog(this, "O número precisa ser menor que 79863");
        	return false;
        }
    	
    	String limit = qtdField.getText();
    	Launcher lc = new Launcher(this, limit);
    	
    	if(medidaBox.getSelectedIndex() == 0) {
			lc.medida = 1;
			lc.nomeMedida = "nanosegundos";
    	}
    	else if(medidaBox.getSelectedIndex() == 1) {
    		lc.medida = Math.pow(10, 4);
    		lc.nomeMedida = "milisegundos";
    	}
    	else {
			lc.medida = Math.pow(10, 9);
			lc.nomeMedida = "segundos";
    	}	
    	
    	if(algoritmoBox.getSelectedIndex() == 0) {
    		lc.runBubbleSort();
    	}
    	else if(algoritmoBox.getSelectedIndex() == 1) {
    		lc.runMergeSort();
    	}
    	else if(algoritmoBox.getSelectedIndex() == 2) {
    		lc.runQuickSort();
    	}
    	else {
			lc.runBubbleSort();
			lc.runMergeSort();
			lc.runQuickSort();
    	}
    	
    	lc.displayChart();
    	return true;
    }      
    
    private void displayInfos() throws SQLException {
    	String jdbcUrl = "jdbc:mysql://localhost/dataset";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
        	String q = "SELECT * FROM dados WHERE nome = '" + elementosList.getSelectedValue() + "'";
        	System.out.println(q);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            
            while(resultSet.next()) {
            	nomeTextLabel.setVisible(true);
            	tempoTextLabel.setVisible(true);
            	
            	nomeLabel.setText(resultSet.getString("nome"));
            	nomeLabel.setVisible(true);
            	
            	tempoLabel.setText(resultSet.getString("tempo"));
            	tempoLabel.setVisible(true);
            	
            	ImageIcon iconImg = new ImageIcon(resultSet.getString("imagem"));
        	    Image imageImg = iconImg.getImage();
        	    Image finalImg = imageImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        	    ImageIcon capa = new ImageIcon(finalImg);
        	    imagemLabel.setIcon(capa);
        	    imagemLabel.setVisible(true);
            	
            }
           
        }
    }
                   
    private javax.swing.JComboBox<String> algoritmoBox;
    private javax.swing.JLabel algoritmoTextLabel;
    public javax.swing.JPanel bubbleSortPane;
    public javax.swing.JLabel bubbleSortTextLabel;
    public javax.swing.JList<String> elementosList;
    public javax.swing.JScrollPane elementosScrollPane;
    private javax.swing.JLabel imagemLabel;
    private javax.swing.JComboBox<String> medidaBox;
    private javax.swing.JLabel medidaTextLabel;
    public javax.swing.JPanel mergeSortPane;
    public javax.swing.JLabel mergeSortTextLabel;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JLabel nomeTextLabel;
    private javax.swing.JTextField qtdField;
    private javax.swing.JSlider qtdSlider;
    private javax.swing.JLabel qtdTextLabel;
    public javax.swing.JPanel quickSortPane;
    public javax.swing.JLabel quickSortTextLabel;
    private javax.swing.JButton runButton;
    public javax.swing.JLabel tempoBubbleSort;
    private javax.swing.JLabel tempoLabel;
    public javax.swing.JLabel tempoMergeSort;
    public javax.swing.JLabel tempoQuickSort;
    public javax.swing.JLabel tempoTextLabel;   
    public javax.swing.DefaultListModel<String> listModel;
}

