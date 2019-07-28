package app.petclinic.Servicio;

import android.support.design.widget.BaseTransientBottomBar;

import java.util.List;

import app.petclinic.Modelos.Appointment;
import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Credenciales.Login;
import app.petclinic.Modelos.Credenciales.SuperUser;
import app.petclinic.Modelos.Credenciales.Usuario;
import app.petclinic.Modelos.Especialidades;
import app.petclinic.Modelos.Pet;
import app.petclinic.Modelos.Types;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @POST("cita_lista/")
    Call<Citas> addCita(@Header("Authorization") String token, @Body Citas cita);

    //Api rest citas

    @GET("loginApp/{user}")
    Call<String> userLogin(@Path("user") String gmail);

    @GET("citas/{id}")
    Call<List<Citas>> getCitas(@Path("id") int id);

    @DELETE("deleteCitas/{id}")
    Call<ResponseBody> deleteCita(@Path("id")int id);

    @FormUrlEncoded
    @POST("newCitas")
    Call<ResponseBody> AddAppoinment(
            @Field("owner_id")Integer owner_id,
            @Field("fecha")String fecha,
            @Field("hora")String  hora,
            @Field("mascota")Integer mascota,
            @Field("especialidad")Integer especialidad,
            @Field("confirmacion")Integer confirmacion
    );
    // aqui termina


}
