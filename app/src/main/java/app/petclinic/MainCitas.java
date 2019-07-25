package app.petclinic;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.petclinic.Conexion.Conexion;
import app.petclinic.Modelos.Pet;
import app.petclinic.Servicio.CitasServicio;
import app.petclinic.Servicio.Peticiones;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCitas extends AppCompatActivity {

    Button Salir ,AgregarNuevaMascota,AgregarNuevaCita;
    ListView ListaPerros;
    CitasServicio citasServicio;
    List<Pet> mascotas=new ArrayList<>();

    ArrayAdapterListaPets adapterListaPets;
   int user_id;
   String user_name;
   String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_citas);
        MainCitas.this.setTitle("Mascotas");

            ArrayList<String> datos=getIntent().getStringArrayListExtra("SuperUser");
            System.out.println("datos obtenidos "+datos);
            Salir =findViewById(R.id.ButtonSalir);
            AgregarNuevaCita=findViewById(R.id.ButtonNuevaCita);
            AgregarNuevaMascota=findViewById(R.id.ButtonNuevaMascota);
            ListaPerros=findViewById(R.id.ListaPerros);

            user_id=Integer.parseInt(datos.get(0));
            user_name=datos.get(1);
            token=datos.get(2);

            ListaPets(token ,user_id);

        ListaPerros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pet mascota =mascotas.get(i);
                System.out.println("eligio el numero de la lista "+i);
                System.out.println("probando objeto mascota "+mascota);
                LlamarActualizarYBorrarDialogo(user_id,user_name,token,mascota,mascota.getId());
            }
        });

        }

        public void onClick(View view){

            if(view.getId()==Salir.getId()){
              System.out.println("salir perro ");

            }

            if(view.getId()==AgregarNuevaMascota.getId()){

                ArrayList<String> datos = new ArrayList<String>();

                System.out.println("user id " +user_id);
                System.out.println("user name " +user_name);
                System.out.println("token " + token);

                datos.add(String.valueOf(user_id));
                datos.add(user_name);
                datos.add(token);

                Intent intent=new Intent(MainCitas.this,AddMascotaActivity.class);
                intent.putExtra("SuperUser",datos);
                startActivity(intent);
                Toast.makeText(MainCitas.this, "Agregar nueva mascota", Toast.LENGTH_SHORT).show();

            }

            if(view.getId()==AgregarNuevaCita.getId()){

                ArrayList<String> datos = new ArrayList<String>();

                System.out.println("user id " +user_id);
                System.out.println("user name " +user_name);
                System.out.println("token " + token);

                datos.add(String.valueOf(user_id));
                datos.add(user_name);
                datos.add(token);

                Intent intent=new Intent(MainCitas.this,AddCitasActivity.class);
                intent.putExtra("SuperUser",datos);
                startActivity(intent);
                Toast.makeText(MainCitas.this, "Agregar nueva cita", Toast.LENGTH_SHORT).show();

            }


        }

    public void ListaPets(String token , final int id_user){
        citasServicio=Conexion.getServiceRemoteCitas();
        Call<List<Pet>> call=citasServicio.getPets(token);
        System.out.println("primero lista reportes ");


        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if(response.isSuccessful()){
                    System.out.println("repsuesta perro "+response.body());
                    ArrayList<Pet> ListaMascotas= (ArrayList<Pet>) response.body();
                    for(Pet lista: ListaMascotas){
                        if(id_user==lista.getOwner_id()){
                            mascotas.add(lista);
                        }
                    }
                    System.out.println("mascotas relacionadad del "+id_user +" sus mascotas "+mascotas);

                    adapterListaPets=new ArrayAdapterListaPets(MainCitas.this,mascotas);
                    ListaPerros.setAdapter(adapterListaPets);

                }

                else{
                    System.out.println("no se enviaron los datos correcta mente "+response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                System.out.println("respues fallida "+call +" erro en el server "+t);
            }
        });



    }


    public void LlamarActualizarYBorrarDialogo(final int user_id, final String user_name, final String token, final Pet mascota, final int id_mascota){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.acivity_update_dialog, null);
        dialogBuilder.setView(dialogView);


        final EditText ActualizarNombreMascota = (EditText) dialogView.findViewById(R.id.ActualizarNombreMascota);
        final EditText ActualizarFecha= (EditText) dialogView.findViewById(R.id.ActualizarFecha);
        final EditText ActualizarType=(EditText)dialogView.findViewById(R.id.ActualizarType);


        String NombreM=getMascota(mascota);

        ActualizarNombreMascota.setText(mascota.getName());
        ActualizarFecha.setText(mascota.getBirth_date());
        ActualizarType.setText(NombreM);


        final Button BotonACtualizar = (Button) dialogView.findViewById(R.id.BotonActualizarPets);
        final Button BotonEliminar = (Button) dialogView.findViewById(R.id.BotonBorrarPets);

        dialogBuilder.setTitle(user_name);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        BotonACtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NombreMascota =ActualizarNombreMascota.getText().toString().trim();
                String FechaDeNacimiento =ActualizarFecha.getText().toString().trim();
                String Type=ActualizarType.getText().toString().trim();
                System.out.println("id mazcota cccccccccccc xxx "+id_mascota);
                int TipoMascota=getType(Type);
                System.out.println("tipo mascota "+TipoMascota);
                if(TipoMascota<1){
                    Toast.makeText(MainCitas.this, "Tipo de mascota Inexistente", Toast.LENGTH_SHORT).show();
                }
                if(TipoMascota>0){
                    System.out.println("mayor A cero XXXXXXXXX ");
                    if (!TextUtils.isEmpty(NombreMascota)) {
                        if (!TextUtils.isEmpty(FechaDeNacimiento)) {
                            if (!TextUtils.isEmpty(Type)) {
                                Pet pet = new Pet(NombreMascota, FechaDeNacimiento, TipoMascota, user_id, false);
                                System.out.println("pet actualizado mentira " + pet);
                                Peticiones peticiones=new Peticiones();
                                peticiones.ActualizarDenuncia(MainCitas.this,user_id,user_name,token,pet,id_mascota);
                                b.dismiss();
                            } else {
                                Toast.makeText(MainCitas.this, "campos vacios ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainCitas.this, "campos vacios ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        Toast.makeText(MainCitas.this, "campos vacios ", Toast.LENGTH_SHORT).show();
                    }

                }


            }

        });

        BotonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pet pet=new Pet(mascota.getName(),mascota.getBirth_date(),mascota.getType_id(),user_id,true);
                System.out.println("pet eliminado mentira "+pet);
                Peticiones peticiones=new Peticiones();
                peticiones.BorrarPet(MainCitas.this,user_id,user_name,token,pet,id_mascota);
                b.dismiss();
            }
        });


    }


    public String getMascota(Pet mascota){
       int tipo= mascota.getType_id();
       String NameMascota="";

       if(tipo==1){
           NameMascota="cat";
       }
       if(tipo==2){
           NameMascota="dog";
       }
       if(tipo==3){
            NameMascota="lizard";
       }
       if(tipo==4){
            NameMascota="snake";
       }
       if(tipo==5){
            NameMascota="bird";
       }
        if(tipo==6){
            NameMascota="hamster";
        }
        return NameMascota;
    }

    public int getType(String type){
        int numero=0;

        if(type.equals("cat")){
            numero=1;
        }
        if(type.equals("dog")){
            numero=2;
        }
        if(type.equals("lizard")){
            numero=3;
        }
        if(type.equals("snake")){
            numero=4;
        }
        if(type.equals("bird")){
            numero=5;
        }
        if(type.equals("hamster")){
            numero=6;
        }

        return numero;
    }


}

