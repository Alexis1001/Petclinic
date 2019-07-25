package app.petclinic.Conexion;
import app.petclinic.Modelos.Citas;
import app.petclinic.Servicio.CitasServicio;

public class Conexion {

    public static String API_URL = "http://192.168.49.200:8080/api/";

    public static CitasServicio getServiceRemoteCitas(){

        return Cliente.getCliente(API_URL).create(CitasServicio.class);

    }

}
