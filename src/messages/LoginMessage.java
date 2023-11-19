package messages;

public class LoginMessage extends AbstractMessage{
    private String username;
    private String password;
    private boolean loginStatus;

    public LoginMessage(String username, String password) {
        super(5);
        this.username = username;
        this.password = password;
        loginStatus = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
