package app.petclinic.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Time;

public class Citas {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("owner_id")
    @Expose
    private int owner_id;

    @SerializedName("fecha")
    @Expose
    String fecha;

    @SerializedName("hora")
    @Expose
    String hora;

    @SerializedName("mascota")
    @Expose
    int  mascota;

    @SerializedName("especialidad")
    @Expose
    int  especialidad;

    @SerializedName("confirmacion")
    @Expose
    int  confirmacion;

    public Citas(int owner_id, String fecha, String hora, int mascota, int especialidad, int confirmacion) {
        this.owner_id = owner_id;
        this.fecha = fecha;
        this.hora = hora;
        this.mascota = mascota;
        this.especialidad = especialidad;
        this.confirmacion = confirmacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getMascota() {
        return mascota;
    }

    public void setMascota(int mascota) {
        this.mascota = mascota;
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    public int getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(int confirmacion) {
        this.confirmacion = confirmacion;
    }
}
