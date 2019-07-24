package app.petclinic.Servicio;

import java.util.List;

import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Credenciales.SuperUser;
import app.petclinic.Modelos.Credenciales.Usuario;
import app.petclinic.Modelos.Especialidades;
import app.petclinic.Modelos.Pet;
import app.petclinic.Modelos.Types;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CitasServicio {

    @POST("login/")
    Call<SuperUser> login(@Body Usuario usuario );

    @GET("especialidad_lista/")
    Call<List<Especialidades>> GetEspecialidades(@Header("Authorization") String token);

    @GET("type_lista/")
    Call<List<Types>> GetTipoMascotas(@Header("Authorization") String token);


    @GET("mascota_lista/")
    Call<List<Pet>> getPets(@Header("Authorization") String token);

    @POST("mascota_lista/")
    Call<Pet> addPet(@Header("Authorization") String token, @Body Pet pet);

    @GET("mascota_detalles/{id}")
    Call<Pet> getByIdPet(@Header("Authorization") String token,@Path("id") int id);

    @PUT("mascota_detalles/{id}")
    Call<Pet> updatePet(@Header("Authorization") String token,@Path("id") int id, @Body Pet pet);


}
