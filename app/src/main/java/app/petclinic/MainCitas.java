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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import app.petclinic.Conexion.Conexion;
import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Pet;
import app.petclinic.Servicio.CitasServicio;
import app.petclinic.Servicio.Peticiones;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCitas extends AppCompatActivity {

    Button Salir,AgregarNuevaCita;
    ListView ListaCitas;
    CitasServicio citasServicio;
    ArrayList<Citas> citas=new ArrayList<>();
    ArrayAdapterListaPets  AdapterListaCitas;
   String id_usuarioS="";
   int id_usuario=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_citas);
        MainCitas.this.setTitle("Citas");

        Salir =findViewById(R.id.ButtonSalir);
        AgregarNuevaCita=findViewById(R.id.ButtonNuevaCita);
        ListaCitas=findViewById(R.id.ListaCitas);


        id_usuarioS=getIntent().getStringExtra("id_usuario");
        id_usuario=Integer.parseInt(id_usuarioS);

        System.out.println("id_usuario "+id_usuario);
        ListaCitasPerros(id_usuario);


        ListaCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Citas cita =citas.get(i);
                System.out.println("perro "+i);
                System.out.println("cita  "+cita);

                LlamarActualizarYBorrarDialogo(cita);
            }
        });

        }

        public void onClick(View view){

            if(view.getId()==Salir.getId()){
                Intent intent=new Intent(MainCitas.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(MainCitas.this, "Usted a salido ", Toast.LENGTH_SHORT).show();
            }

            if(view.getId()==AgregarNuevaCita.getId()){
                Intent intent=new Intent(MainCitas.this,AddCitasActivity.class);
                System.out.println("id usuario add new cita ");
                intent.putExtra("id_usuario",id_usuarioS);
                startActivity(intent);
                Toast.makeText(MainCitas.this, "Agregar nueva cita", Toast.LENGTH_SHORT).show();
            }


        }

    public void ListaCitasPerros(int id_usuario){
        citasServicio= Conexion.getServiceRemoteCitas();
        Call<List<Citas>> call =citasServicio.getCitas(id_usuario);

        call.enqueue(new Callback<List<Citas>>() {
            @Override
            public void onResponse(Call<List<Citas>> call, Response<List<Citas>> response) {

                if(response.isSuccessful()){
                    System.out.println("respuesta correcta lista citas "+response.body());
                     //ArrayList<Citas> citas= (ArrayList<Citas>) response.body();
                    citas= (ArrayList<Citas>) response.body();
                    AdapterListaCitas=new ArrayAdapterListaPets(MainCitas.this,citas);
                    ListaCitas.setAdapter(AdapterListaCitas);
                }
                else{
                    System.out.println("respuesta fallida citas "+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Citas>> call, Throwable t) {

                System.out.println("llamada "+call +"Error "+t);
            }

        });
    }

    public void LlamarActualizarYBorrarDialogo(final Citas cita){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.acivity_update_dialog, null);
        dialogBuilder.setView(dialogView);


        final EditText ActualizarNombreMascota = (EditText) dialogView.findViewById(R.id.NombreMascota);
        final EditText ActualizarFechaCita=(EditText)dialogView.findViewById(R.id.FechaCita);
        final EditText ActualizarHoraCita= (EditText) dialogView.findViewById(R.id.HoraCita);
        final EditText ActualizarTipoMascota=(EditText)dialogView.findViewById(R.id.TipoMascota);
        final EditText Confirmacion=(EditText)dialogView.findViewById(R.id.Confirmacion);

        ActualizarNombreMascota.setText(cita.getMascota());
        ActualizarFechaCita.setText(cita.getFecha());
        ActualizarHoraCita.setText(cita.getHora());
        String TipoMascota=getTipoMascota(cita);
        ActualizarTipoMascota.setText(TipoMascota);
        String confirmation=getConfirmacio(cita.getConfirmacion());
        Confirmacion.setText(confirmation);

        final Button BotonACtualizar = (Button) dialogView.findViewById(R.id.BotonActualizarPets);
        final Button BotonEliminar = (Button) dialogView.findViewById(R.id.BotonBorrarPets);

        dialogBuilder.setTitle("cita");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        BotonACtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NombreMascota =ActualizarNombreMascota.getText().toString().trim();
                String Horacita=ActualizarHoraCita.getText().toString().trim();
                String Type=ActualizarTipoMascota.getText().toString().trim();

                if (!TextUtils.isEmpty(NombreMascota)) {
                    if (!TextUtils.isEmpty(Horacita)) {
                        if (!TextUtils.isEmpty(Type)) {
                                Toast.makeText(MainCitas.this, "acualizacion ", Toast.LENGTH_SHORT).show();
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

        });

        BotonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCitas(cita);
                b.dismiss();
            }
        });


    }

    public String getConfirmacio(String confirmacion){
        if(confirmacion.equals("0")){
            return "no confirmada";
        }
        if(confirmacion.equals(1)){
            return "Confirmada";
        }
        return "null";
    }


    public String getTipoMascota(Citas cita ){
       int tipo=cita.getEspecialidad();
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



    public void DeleteCitas(Citas cita){
        int id=cita.getId();
        System.out.println("id suario de cita perro  "+id);
        System.out.println("id_usuarioS "+id_usuarioS);
        citasServicio= Conexion.getServiceRemoteCitas();
        Call<ResponseBody> call=citasServicio.deleteCita(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println(" cita eliminada  "+response.body());

                    Intent intent=new Intent(MainCitas.this,ActualizandoDatos.class);
                    intent.putExtra("id_usuario",id_usuarioS);
                    startActivity(intent);
                    Toast.makeText(MainCitas.this, "Cita Eliminada", Toast.LENGTH_SHORT).show();
                }
                else{
                    System.out.println("repuesta fallida cita eliminada "+response.body());
                    Toast.makeText(MainCitas.this, "Cita No eliminada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error cita eliminada  "+t+" llamada cita eliminada  "+call);
                Toast.makeText(MainCitas.this, "erro en el servidor ", Toast.LENGTH_SHORT).show();
            }
        });


    }


}

