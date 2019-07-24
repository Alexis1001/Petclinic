package app.petclinic.Modelos.Credenciales;

public class SuperUser {

    private int user_id;
    private String username;
    private String token;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "id=" + user_id +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
