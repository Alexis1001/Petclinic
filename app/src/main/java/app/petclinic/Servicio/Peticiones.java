package app.petclinic.Servicio;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.petclinic.ActualizandoDatos;
import app.petclinic.ArrayAdapterListaPets;
import app.petclinic.Conexion.Conexion;
import app.petclinic.MainActivity;
import app.petclinic.MainCitas;
import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Pet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Peticiones {
    CitasServicio citasServicio;

    public Peticiones() {
    }


    public void AgregarPet(final Context contex, final int id_usuario, final String user_name, final String token, final Pet pet,
                           final int id_mascota){

        citasServicio = Conexion.getServiceRemoteCitas();

        System.out.println("perro id xd in actualizar "+id_mascota);

        Call<Pet> call = citasServicio.addPet(token,pet);
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()) {
                    System.out.println("mascota Agregada " + response.body());
                    ArrayList<String> datos = new ArrayList<String>();

                    System.out.println("user id " + id_usuario);
                    System.out.println("user name " + user_name);
                    System.out.println("token " + token);

                    datos.add(String.valueOf(id_usuario));
                    datos.add(user_name);
                    datos.add(token);

                    Intent intent=new Intent(contex,ActualizandoDatos.class);
                    intent.putExtra("SuperUser",datos);
                    contex.startActivity(intent);
                    Toast.makeText(contex, "Mascota Agregada", Toast.LENGTH_SHORT).show();

                } else {
                    System.out.println("Mascota no Agregada "+response.body());
                }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                System.out.println(" llamada "+call +" error "+t);
            }
        });

    }

    public void ActualizarDenuncia(final Context contex, final int id_usuario, final String user_name, final String token, final Pet pet,
                                   final int id_mascota) {
        citasServicio = Conexion.getServiceRemoteCitas();

        System.out.println("perro id xd in actualizar "+id_mascota);

        Call<Pet> call = citasServicio.updatePet(token,id_mascota, pet);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {

                if (response.isSuccessful()) {
                    System.out.println("mascota actualizada " + response.body());
                    ArrayList<String> datos = new ArrayList<String>();

                    System.out.println("user id " + id_usuario);
                    System.out.println("user name " + user_name);
                    System.out.println("token " + token);

                    datos.add(String.valueOf(id_usuario));
                    datos.add(user_name);
                    datos.add(token);

                    Intent intent=new Intent(contex, ActualizandoDatos.class);
                    intent.putExtra("SuperUser",datos);
                    contex.startActivity(intent);
                    Toast.makeText(contex,"Mascota Actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Mascota  no actualizada "+response.body());
                }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                System.out.println(" llamada "+call +" error "+t);
            }
        });


    }

    public void BorrarPet(final Context contex, final int id_usuario, final String user_name, final String token, final Pet pet,
                               final int id_mascota) {

        citasServicio = Conexion.getServiceRemoteCitas();
        System.out.println("perro id xd in borrar  "+id_mascota);
        Call<Pet> call = citasServicio.updatePet(token,id_mascota, pet);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()) {
                    System.out.println("pet borrado " + response.body());
                    ArrayList<String> datos = new ArrayList<String>();

                    System.out.println("user id " + id_usuario);
                    System.out.println("user name " + user_name);
                    System.out.println("token " + token);

                    datos.add(String.valueOf(id_usuario));
                    datos.add(user_name);
                    datos.add(token);

                    Intent intent=new Intent(contex,ActualizandoDatos.class);
                    intent.putExtra("SuperUser",datos);
                    contex.startActivity(intent);
                    Toast.makeText(contex,"Mascota Eliminada", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Mascota no eliminada "+response.body());
                }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                System.out.println(" llamada "+call +" error "+t);
            }

        });


    }

    public void AgregarCitas(final Context contex, final int id_usuario, final String user_name, final String token, final Citas cita){

        citasServicio = Conexion.getServiceRemoteCitas();

        System.out.println("agregar "+cita);

        Call<Citas> call = citasServicio.addCita(token,cita);

        call.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {

                if(response.isSuccessful()){
                    System.out.println("respuesta "+response.body());
                }
                else{
                    System.out.println("respuesta fallida "+response.body());
                }

            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                System.out.println("llamada "+call +"error "+t);

            }
        });

    }




}