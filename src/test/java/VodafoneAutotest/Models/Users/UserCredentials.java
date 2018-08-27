package VodafoneAutotest.Models.Users;

public class UserCredentials {
    private String phoneNumber;
    private String password;

    public UserCredentials(String phoneNumber, String password) {
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
