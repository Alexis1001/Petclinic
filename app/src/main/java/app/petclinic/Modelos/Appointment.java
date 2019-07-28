package app.petclinic.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("owner_id")
    @Expose
    private Integer owner_id;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("hora")
    @Expose
    private String hora;

    @SerializedName("mascota")
    @Expose
    private Integer mascota;

    @SerializedName("especialidad")
    @Expose
    private Integer especialidad;

    @SerializedName("confirmacion")
    @Expose
    private Integer confirmacion;



    public Appointment(Integer owner_id, String fecha, String hora, Integer mascota, Integer especialidad, Integer confirmacion) {
        this.owner_id = owner_id;
        this.fecha = fecha;
        this.hora = hora;
        this.mascota = mascota;
        this.especialidad = especialidad;
        this.confirmacion = confirmacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public  String getFecha() {
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

    public Integer getMascota() {
        return mascota;
    }

    public void setMascota(Integer mascota) {
        this.mascota = mascota;
    }

    public Integer getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Integer especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Integer confirmacion) {
        this.confirmacion = confirmacion;
    }
}
