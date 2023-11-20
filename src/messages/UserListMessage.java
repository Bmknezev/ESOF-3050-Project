package messages;

import java.util.List;

public class UserListMessage extends AbstractMessage{

    private String username;
    private String password;
    private boolean admin;
    private boolean newUser;

    public UserListMessage() {
        super(7);
    }

    public UserListMessage(String username, String password, boolean admin, boolean newUser) {
        super(7);
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.newUser = newUser;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAdmin(boolean admin){
        this.admin = admin;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean getAdmin(){
        return admin;
    }

    public boolean getNewUser() {
        return newUser;
    }

}
