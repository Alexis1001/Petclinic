package app.petclinic.Conexion;
import app.petclinic.Modelos.Citas;
import app.petclinic.Servicio.CitasServicio;

public class Conexion {

    public static String API_URL = "http://192.168.0.6:8000/api/v1/";

    public static CitasServicio getServiceRemoteCitas(){

        return Cliente.getCliente(API_URL).create(CitasServicio.class);

    }

}
