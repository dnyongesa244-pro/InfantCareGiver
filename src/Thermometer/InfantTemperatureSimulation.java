package Thermometer;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class InfantTemperatureSimulation {

    private static final int AVERAGE_WINDOW_SIZE = 60; // Number of seconds to calculate average over

    public static void main(String[] args) {
        simulateTemperatureReadings();
    }

    private static void simulateTemperatureReadings() {
        Random random = new Random();
        Queue<Double> temperatureQueue = new LinkedList<>();
        double currentTemperature = generateRealisticTemperature();

        while (true) {
            // Gradually change the temperature using a simple moving average
            double nextTemperature = currentTemperature + (random.nextDouble() - 0.5) * 0.5; // Larger random variation
            currentTemperature = (currentTemperature + nextTemperature) / 2.0;

            temperatureQueue.offer(currentTemperature);
            if (temperatureQueue.size() > AVERAGE_WINDOW_SIZE) {
                temperatureQueue.poll(); // Remove oldest temperature value
            }

            double averageTemperature = calculateAverage(temperatureQueue);
            displayTemperature(averageTemperature);

            try {
                // Adjust the sleeping time between temperature readings (e.g., 500 milliseconds)
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double generateRealisticTemperature() {
        // Simulate a realistic starting temperature for an infant (between 35.5 and 38.0 degrees Celsius)
        double minTemperature = 35.5;
        double maxTemperature = 38.0;

        Random random = new Random();
        return minTemperature + random.nextDouble() * (maxTemperature - minTemperature);
    }

    private static double calculateAverage(Queue<Double> temperatureQueue) {
        double sum = 0.0;
        for (Double temperature : temperatureQueue) {
            sum += temperature;
        }
        return sum / temperatureQueue.size();
    }

    private static void displayTemperature(double temperature) {
        // Format the temperature to display two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedTemperature = df.format(temperature);

        // Display the average temperature reading
        System.out.println("Average Infant's Temperature: " + formattedTemperature + " °C");
    }
}
