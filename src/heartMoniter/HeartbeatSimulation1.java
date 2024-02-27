package heartMoniter;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class HeartbeatSimulation1 {

    private static final int AVERAGE_WINDOW_SIZE = 60; // Number of seconds to calculate average over
    private static final int MIN_HEARTBEAT = 80;
    private static final int MAX_HEARTBEAT = 180;

    public static void main(String[] args) {
        simulateHeartbeat();
    }

    private static void simulateHeartbeat() {
        Random random = new Random();
        Queue<Integer> heartbeatQueue = new LinkedList<>();
        int currentHeartbeat = generateRealisticHeartbeat();

        while (true) {
            // Gradually change the heartbeat using a simple moving average
            int nextVariation = random.nextInt(21) - 10; // Random variation between -10 and 10
            int nextHeartbeat = currentHeartbeat + nextVariation;
            currentHeartbeat = (currentHeartbeat + nextHeartbeat) / 2;

            // Ensure the heartbeat remains within the specified range
            currentHeartbeat = Math.max(MIN_HEARTBEAT, Math.min(MAX_HEARTBEAT, currentHeartbeat));

            heartbeatQueue.offer(currentHeartbeat);
            if (heartbeatQueue.size() > AVERAGE_WINDOW_SIZE) {
                heartbeatQueue.poll(); // Remove oldest heartbeat value
            }

            int averageHeartbeat = calculateAverage(heartbeatQueue);
            displayHeartbeat(averageHeartbeat);

            try {
                // Adjust the sleeping time between heartbeat readings (e.g., 500 milliseconds)
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int generateRealisticHeartbeat() {
        // Simulate a realistic starting heartbeat rate for a person (between 60 and 100 beats per minute)
        return MIN_HEARTBEAT + new Random().nextInt(MAX_HEARTBEAT - MIN_HEARTBEAT + 1);
    }

    private static int calculateAverage(Queue<Integer> heartbeatQueue) {
        int sum = 0;
        for (Integer heartbeat : heartbeatQueue) {
            sum += heartbeat;
        }
        return sum / heartbeatQueue.size();
    }

    private static void displayHeartbeat(int heartbeat) {
        // Display the average heartbeat rate
        System.out.println("Average Heartbeat Rate: " + heartbeat + " BPM");
    }
}
