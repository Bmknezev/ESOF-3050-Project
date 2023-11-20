package messages;

import java.util.List;

public class UserListMessage extends AbstractMessage{

    List<String> usernames = new java.util.ArrayList<>();
    List<String> passwords = new java.util.ArrayList<>();
    List<Boolean> admin = new java.util.ArrayList<>();

    public UserListMessage() {
        super(7);
    }

    public void setPasswords(List<String> passwords){
        this.passwords = passwords;
    }

    public void setUsernames(List<String> usernames){
        this.usernames = usernames;
    }

    public void setAdmin(List<Boolean> admin){
        this.admin = admin;
    }

    public List<String> getUsernames(){
        return usernames;
    }

    public List<String> getPasswords(){
        return passwords;
    }

    public List<Boolean> getAdmin(){
        return admin;
    }
}
