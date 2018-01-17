import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // get path to data-file from user
        JFrame popupFrame = new JFrame();
        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.requestFocus();
        textArea.requestFocusInWindow();
        scrollPane.setPreferredSize(new Dimension(200, 30));
        JOptionPane.showMessageDialog(popupFrame, scrollPane, "Enter Reminders data-file path", JOptionPane.PLAIN_MESSAGE);
        popupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String filePath = textArea.getText();

        // if the path to the data-file is empty
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Cannot run without data-file. Exiting...");
            System.exit(1);
        }
        // the path is OK, run the GUI
        else {
            System.out.println("Starting GUI with file: " + filePath);
            Gui gui = new Gui(filePath);
            gui.init();
        }
    }
}