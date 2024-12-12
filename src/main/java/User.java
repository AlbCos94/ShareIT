import java.sql.Date;

public class User {
    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private java.lang.String dateOfBirth;
    private String phoneNumber;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    private java.time.LocalDateTime lastLogin;
    private boolean isActive;
    private boolean isVerified;

    // Constructor
    public User(String username, String email, String passwordHash, String firstName, String lastName,
    java.lang.String dateOfBirth, String phoneNumber, boolean isActive, boolean isVerified) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.isVerified = isVerified;
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.lastLogin = null; // Can be set later
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public java.lang.String getDateOfBirth() { return dateOfBirth; }

    /* 
    public String getDateOfBirthAsString() { 
        String dateToString = new String();
        dateToString = String.valueOf(dateOfBirth.getYear()) + "-" + String.valueOf(dateOfBirth.getMonthValue() + 
        "-" +String.valueOf(dateOfBirth.getDayOfWeek().ordinal()) ) ;
        return dateToString; 
    }*/

    public void setDateOfBirth(java.lang.String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public java.time.LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(java.time.LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
}