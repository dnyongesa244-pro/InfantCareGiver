package BreatrateMonitor;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BreathingSimulation {

    private static final int AVERAGE_WINDOW_SIZE = 60; // Number of seconds to calculate average over
    private static final int MIN_BREATHING_RATE = 20; // Minimum breaths per minute
    private static final int MAX_BREATHING_RATE = 80; // Maximum breaths per minute

    public static void main(String[] args) {
        simulateBreathing();
    }

    private static void simulateBreathing() {
        Random random = new Random();
        Queue<Integer> breathingRateQueue = new LinkedList<>();
        int currentBreathingRate = generateRealisticBreathingRate();

        while (true) {
            // Gradually change the breathing rate using a simple moving average
            int nextVariation = random.nextInt(5) - 2; // Random variation between -2 and 2
            int nextBreathingRate = currentBreathingRate + nextVariation;
            currentBreathingRate = (currentBreathingRate + nextBreathingRate) / 2;

            // Ensure the breathing rate remains within the specified range
            currentBreathingRate = Math.max(MIN_BREATHING_RATE, Math.min(MAX_BREATHING_RATE, currentBreathingRate));

            breathingRateQueue.offer(currentBreathingRate);
            if (breathingRateQueue.size() > AVERAGE_WINDOW_SIZE) {
                breathingRateQueue.poll(); // Remove oldest breathing rate value
            }

            int averageBreathingRate = calculateAverage(breathingRateQueue);
            displayBreathingRate(averageBreathingRate);

            try {
                // Adjust the sleeping time between breathing rate readings (e.g., 1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int generateRealisticBreathingRate() {
        // Simulate a realistic starting breathing rate for a person (between 20 and 80 breaths per minute)
        return MIN_BREATHING_RATE + new Random().nextInt(MAX_BREATHING_RATE - MIN_BREATHING_RATE + 1);
    }

    private static int calculateAverage(Queue<Integer> breathingRateQueue) {
        int sum = 0;
        for (Integer breathingRate : breathingRateQueue) {
            sum += breathingRate;
        }
        return sum / breathingRateQueue.size();
    }

    private static void displayBreathingRate(int breathingRate) {
        // Display the average breathing rate
        System.out.println("Average Breathing Rate: " + breathingRate + " breaths per minute");
    }
}
