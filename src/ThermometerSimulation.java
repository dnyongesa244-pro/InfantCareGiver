
import java.text.DecimalFormat;
import java.util.Random;

public class ThermometerSimulation {

    public static void main(String[] args) {
        simulateTemperatureReadings();
    }

    private static void simulateTemperatureReadings() {
        // Simulate temperature readings continuously
        while (true) {
            double temperature = generateRandomTemperature();
            displayTemperature(temperature);

            // Sleep for a short interval (e.g., 1 second) before the next reading
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double generateRandomTemperature() {
        // Simulate a temperature range, e.g., between 36.0 and 38.0 degrees Celsius
        double minTemperature = 36.0;
        double maxTemperature = 38.0;

        // Use the Random class to generate a random temperature within the specified range
        Random random = new Random();
        return minTemperature + (maxTemperature - minTemperature) * random.nextDouble();
    }

    private static void displayTemperature(double temperature) {
        // Format the temperature to display two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedTemperature = df.format(temperature);

        // Display the temperature reading
        System.out.println("Infant's Temperature: " + formattedTemperature + " °C");
    }
}
