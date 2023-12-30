// HealthRecord class represents a health record entry
public class HealthRecord {
    // Fields to store user information
    private final String userName;
    private final String healthIssue;
    private final String state;

    // Constructor to initialize the health record with user information
    public HealthRecord(String userName, String healthIssue, String state) {
        this.userName = userName;
        this.healthIssue = healthIssue;
        this.state = state;
    }

    // Getter method to retrieve the user's name
    public String getUserName() {
        return userName;
    }

    // Getter method to retrieve the health issue description
    public String getHealthIssue() {
        return healthIssue;
    }

    // Getter method to retrieve the state information
    public String getState() {
        return state;
    }
}
