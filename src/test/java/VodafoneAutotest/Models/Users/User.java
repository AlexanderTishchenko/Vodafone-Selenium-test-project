package VodafoneAutotest.Models.Users;

public class User {
    private String name;
    private UserCredentials credentials;

    User(String name, UserCredentials credentials) {
        this.name = name;
        this.credentials = credentials;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return getCredentials().getPhoneNumber();
    }
}
