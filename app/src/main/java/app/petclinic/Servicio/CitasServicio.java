package app.petclinic.Servicio;

import java.util.List;

import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Credenciales.Login;
import app.petclinic.Modelos.Credenciales.SuperUser;
import app.petclinic.Modelos.Credenciales.Usuario;
import app.petclinic.Modelos.Especialidades;
import app.petclinic.Modelos.Pet;
import app.petclinic.Modelos.Types;

import okhttp3.ResponseBody;
import retrofit2.Call;
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

    //@POST("cita_lista/")
    //Call<Citas> addCita(@Header("Authorization") String token, @Body Citas cita);

    ///////////////////////////////////////////

    @GET("loginApp/{user}")
    Call<Login> userLogin(@Path("user") String gmail);

    @GET("citas/{id}")
    Call<List<Citas>> getCitas(@Path("id") int id);

    @POST("newCitas")
    Call<Citas> AddCitas(@Body Citas cita);

    /////////////////////////////////////////

/*
    @FormUrlEncoded
    @POST("newCitas")
    Call<Citas> updateDenuncias(
            @Field("key") String key,
            @Field("id") int id,
            @Field("title") String title,
            @Field("description") String description,
            @Field("date_now") String date_now,
            @Field("image") String image);
     */








}
