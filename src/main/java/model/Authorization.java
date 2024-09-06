package model;

public class Authorization {
    private String username;
    private String password;
    
    public Authorization(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public boolean isUsernameValid(){
        if(username == "") return false;
        return true;
    } 

    public boolean isPasswordValid(){
        if(password == "") return false;
        if(password.length() < 6) return false;

        return true;
    }
}
