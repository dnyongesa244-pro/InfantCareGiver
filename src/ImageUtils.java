import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageUtils {

    public static ImageIcon getScaledImageIcon(URL imageURL, int maxWidth, int maxHeight) {
        ImageIcon originalIcon = new ImageIcon(imageURL);

        // Get the original image dimensions
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        // Calculate the scaling factors
        double widthScale = (double) maxWidth / originalWidth;
        double heightScale = (double) maxHeight / originalHeight;

        // Choose the minimum scale factor to ensure the entire image fits within the specified dimensions
        double scale = Math.min(widthScale, heightScale);

        // Calculate the scaled dimensions
        int scaledWidth = (int) (originalWidth * scale);
        int scaledHeight = (int) (originalHeight * scale);

        // Create a scaled ImageIcon
        Image scaledImage = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Resizing Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel jLabel = new JLabel();
            URL imageURL = ImageUtils.class.getResource("/Icons/Infant.jpg");

            if (imageURL != null) {
                ImageIcon scaledIcon = ImageUtils.getScaledImageIcon(imageURL, 100, 100);
                jLabel.setIcon(scaledIcon);
            } else {
                System.err.println("Couldn't find image resource");
            }

            frame.getContentPane().add(jLabel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
