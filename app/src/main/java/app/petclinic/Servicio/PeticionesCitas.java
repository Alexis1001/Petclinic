package app.petclinic.Servicio;

import app.petclinic.Conexion.Conexion;
import app.petclinic.Modelos.Citas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeticionesCitas {

    CitasServicio citasServicio;

    public void PostCitas(Citas cita){
       /* citasServicio= Conexion.getServiceRemoteCitas();
        Call<Citas> call=citasServicio.AddCitas(cita);

        call.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {
                if(response.isSuccessful()){
                    System.out.println("nueva cita "+response.body());
                }
                else{
                    System.out.println("repuesta fallida cita "+response.body());
                }
            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                System.out.println("Error "+t+" llamada "+call);
            }
        });*/



    }
}
