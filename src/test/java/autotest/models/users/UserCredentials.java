package autotest.models.users;

public class UserCredentials {
    private String phoneNumber;
    private String password;

    UserCredentials(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
