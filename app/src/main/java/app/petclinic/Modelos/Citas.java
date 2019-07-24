package app.petclinic.Modelos;

import java.sql.Time;

public class Citas {

    private int id;
    private int owner_id;
    String fecha;
    String hora;
    String mascota;
    String  especialidad;
    int confirmacion;


    public Citas(){

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

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(int confirmacion) {
        this.confirmacion = confirmacion;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "id=" + id +
                ", owner_id=" + owner_id +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", mascota='" + mascota + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", confirmacion=" + confirmacion +
                '}';
    }
}
