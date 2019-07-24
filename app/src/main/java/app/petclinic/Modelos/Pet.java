package app.petclinic.Modelos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pet {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("birth_date")
    @Expose
    private String birth_date;

    @SerializedName("type_id")
    @Expose
    private int type_id;

    @SerializedName("owner_id")
    @Expose
    private int owner_id;

    @SerializedName("delete")
    @Expose
    private boolean delete;

    public Pet(){

    }

    public Pet(String name, String birth_date, int type_id, int owner_id, boolean delete) {
        this.name = name;
        this.birth_date = birth_date;
        this.type_id = type_id;
        this.owner_id = owner_id;
        this.delete = delete;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", type_id=" + type_id +
                ", owner_id=" + owner_id +
                ", delete=" + delete +
                '}';
    }
}
