import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// Main GUI class
public class HealthSystemGUI extends JFrame {
    private List<HealthRecord> healthRecords;
    private JTextArea severityTextArea;
    private JComboBox<Severity> healthIssueComboBox;

    // Constructor
    public HealthSystemGUI() {
        healthRecords = new ArrayList<>();

        // GUI initialization
        setTitle("Health System Framework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Buttons and input fields
        JButton enterButton = new JButton("Enter");
        JButton displayButton = new JButton("Display List");
        JTextField userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(200, 25));
        healthIssueComboBox = new JComboBox<>(Severity.values());
        severityTextArea = new JTextArea();
        severityTextArea.setLineWrap(true);
        severityTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(severityTextArea);

        // Date, State, and Time boxes
        JLabel dateLabel = new JLabel("Date: " + getCurrentDate());
        JLabel stateLabel = new JLabel("State: ");
        JTextField stateTextField = new JTextField();
        stateTextField.setPreferredSize(new Dimension(100, 25));
        JLabel timeLabel = new JLabel("Time: " + getCurrentTime());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(userNameField);
        inputPanel.add(new JLabel("Health Issue: "));
        inputPanel.add(healthIssueComboBox);
        inputPanel.add(new JLabel("Date: "));
        inputPanel.add(dateLabel);
        inputPanel.add(stateLabel);
        inputPanel.add(stateTextField);
        inputPanel.add(timeLabel);
        inputPanel.add(enterButton);
        inputPanel.add(displayButton);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event listeners
        enterButton.addActionListener(e -> {
            processUserInput(userNameField.getText(), (Severity) healthIssueComboBox.getSelectedItem(),
                    stateTextField.getText());
            userNameField.setText("");
            stateTextField.setText("");
        });

        displayButton.addActionListener(e -> displayHealthRecords());

        // Frame properties
        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to get the current date
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    // Helper method to get the current time
    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(new Date());
    }

    // Process user input
    private void processUserInput(String userName, Severity selectedSeverity, String state) {
        healthRecords.add(new HealthRecord(userName, selectedSeverity.getDescription(), state));

        healthRecords.sort((r1, r2) -> {
            Severity severity1 = getSeverityByDescription(r1.getHealthIssue());
            Severity severity2 = getSeverityByDescription(r2.getHealthIssue());
            return Integer.compare(severity1.ordinal(), severity2.ordinal());
        });
    }

    // Helper method to get severity by description
    private Severity getSeverityByDescription(String description) {
        for (Severity severity : Severity.values()) {
            if (Objects.equals(description, severity.getDescription())) {
                return severity;
            }
        }
        throw new IllegalArgumentException("Invalid severity description: " + description);
    }

    // Display health records in the text area
    private void displayHealthRecords() {
        severityTextArea.setText("");
        for (HealthRecord record : healthRecords) {
            severityTextArea.append(record.getUserName() + ": " + record.getHealthIssue() +
                    " (State: " + record.getState() + ")\n");
        }
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HealthSystemGUI());
    }
}
