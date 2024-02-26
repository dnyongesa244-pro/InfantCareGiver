package Thermometer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;

public class InfantTemperatureSimulation {

    private static final int WINDOW_SIZE = 20; // Number of data points to display in the chart

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Infant Temperature Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        XYSeries temperatureSeries = new XYSeries("Infant Temperature");
        XYSeriesCollection dataset = new XYSeriesCollection(temperatureSeries);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Infant Temperature Simulation", "Time", "Temperature (°C)",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        simulateTemperatureReadings(temperatureSeries);
    }

    private static void simulateTemperatureReadings(XYSeries temperatureSeries) {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            double temperature = generateRealisticTemperature();
            temperatureSeries.add(i, temperature);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double generateRealisticTemperature() {
        // Simulate a realistic temperature range for an infant
        double minTemperature = 36.0;
        double maxTemperature = 37.5;

        // Add some variation to the temperature readings
        Random random = new Random();
        double variation = random.nextDouble() * 0.5; // Maximum variation of 0.5 degrees
        double temperature = minTemperature + variation;

        return temperature;
    }
}
