package messages;

import messages.client.Listable;

import java.util.List;

public class UserListMessage extends AbstractMessage implements Listable {

    private String username;
    private String password;
    private boolean admin;
    private boolean newUser;
    private int userID;

    public UserListMessage() {
        super(7);
    }

    public UserListMessage(int id, String username, String password, boolean admin, boolean newUser) {
        super(7);
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.newUser = newUser;
        this.userID = id;
    }

    public UserListMessage(int id, String username){
        super(7);
        this.username = username;
        this.userID = id;
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

    public int getUserID() {
        return userID;
    }

    @Override
    public String getNameListable() {
        return username;
    }

    @Override
    public String getCategoryListable() {
        return admin ? "Admin User" : "User";
    }

    @Override
    public int getIDListable() {
        return userID;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}
