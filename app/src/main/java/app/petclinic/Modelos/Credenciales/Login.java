package app.petclinic.Modelos.Credenciales;

public class Login {

    int owner_id;

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Login{" +
                "owner_id=" + owner_id +
                '}';
    }
}
