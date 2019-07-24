package app.petclinic.Modelos;

public class Types {

    //@SerializedName("id")
    //@Expose
    private int id;

    //@SerializedName("name")
    //@Expose
    private String name;

    public Types(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Types{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
