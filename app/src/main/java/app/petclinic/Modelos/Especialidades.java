package app.petclinic.Modelos;

public class Especialidades {

    private int id;
    String nombre ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Especialidades{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
