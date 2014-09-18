package com.thales.services.dt.codingdojo.computerunner;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
public class MainView {

   private static void initWindow() {
        //Create and set up the window.
        JFrame frame = new JFrame("Compute Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container mainPanel=frame.getContentPane();
         
        
        // Create command row with main actions button
        JPanel commandPanel=new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
       JButton loadDataButton = new JButton("Load input data");
        commandPanel.add(loadDataButton);
        JButton computeButton = new JButton("Compute !");
        commandPanel.add(computeButton);
        mainPanel.add(commandPanel, BorderLayout.NORTH);
        
        
        //JPanel mainPanel=new JPanel();
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        
         JTable dataTable = new JTable();          
         ComputeTableModel dataModel = new ComputeTableModel();
         JTable table = new JTable(dataModel);
         JScrollPane scrollpane = new JScrollPane(table);
         mainPanel.add(scrollpane,BorderLayout.CENTER);
        
         
       
        //add a graph
         JFreeChart chart =ChartFactory.createXYLineChart("Computing visualization", "X", "Y", null);
        		 
         XYPlot plot=chart.getXYPlot();
    
         ChartPanel cp = new ChartPanel(chart);
         
         loadDataButton.addActionListener(new DataLoaderActionListener(dataModel,frame));
         computeButton.addActionListener(new ComputeActionListener(dataModel, plot));

        mainPanel.add(cp,BorderLayout.SOUTH);
 
        
        
        
        //Display the view
        
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}
