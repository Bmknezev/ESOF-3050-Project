//-----------------------------------------------------------------
// LoginMessage.java
// Group 2
// Description: Represents a message used for user login.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - username: String - Represents the user's username.
//   - password: String - Represents the user's password.
//   - loginStatus: boolean - Indicates the login status (whether login is successful or not).
//   - admin: boolean - Indicates whether the user has administrative privileges.
//
//-----------------------------------------------------------------

package messages;

public class LoginMessage extends AbstractMessage{
    private String username;
    private String password;
    private boolean loginStatus;

    private boolean admin;

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

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getAdmin() {
        return admin;
    }
}
