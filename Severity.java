public enum Severity {
    // Enum constants with associated descriptions
    HEAD("Severe Head Issue"),
    HEART("Serious Heart Problem"),
    CHEST("Critical Chest Condition"),
    ARMS("Severe Arm Problem"),
    LOWER_BODY("Lower Body Discomfort");

    // Field to store the description associated with each severity level
    private final String description;

    // Constructor to initialize the severity level with a description
    Severity(String description) {
        this.description = description;
    }

    // Getter method to retrieve the description of the severity level
    public String getDescription() {
        return description;
    }
}
