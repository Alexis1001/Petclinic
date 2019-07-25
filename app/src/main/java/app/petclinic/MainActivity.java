package app.petclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.petclinic.Conexion.Conexion;
import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Credenciales.Login;
import app.petclinic.Modelos.Credenciales.SuperUser;
import app.petclinic.Modelos.Credenciales.Usuario;
import app.petclinic.Servicio.CitasServicio;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CitasServicio citasServicio;
    Button login;
    EditText UserName,password;
    Citas cita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login);
        UserName=findViewById(R.id.UserName);
        password=findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String user=UserName.getText().toString().trim();
            String pass=password.getText().toString().trim();


                //LocalTime justoAhora = LocalTime.now();
                //justoAhora.

                //System.out.printf("En este momento son las %d horas con %d minutos y %d segundos\n", justoAhora.getHour(),
                //        justoAhora.getMinute(), justoAhora.getSecond());

                Calendar calendario = Calendar.getInstance();

                int hora, minutos, segundos;

                hora =calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);

                Date date = new Date();
                DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");




               // Citas cita =new Citas(3,2010-7-23,"2:35:00",10,1,0);
            // Citas(int owner_id, String fecha, String hora, int mascota, int especialidad, int confirmacion) {

               Citas citas=new  Citas(3,"2019-01-12","13:12:12",10, 1,0);



            if(!TextUtils.isEmpty(user)){
                if(!TextUtils.isEmpty(pass)){
                    UserName.setText("");
                    password.setText("");



                    //Login(user); // le paso el correo
                   // ObtenrCitas();
                    Citas citax=new  Citas(3,"2019-01-12","13:12:12",10, 1,0);
                    PostCitas(citax);

                }
                else{
                 password.setError("campo requerido");
                }
            }
            else{
                UserName.setText("");
                password.setText("");
                Toast.makeText(MainActivity.this, "Asegurese de llenar los campos ", Toast.LENGTH_SHORT).show();
            }


            }

        });
    }

    public void ObtenerToken(Usuario usuario){
        citasServicio= Conexion.getServiceRemoteCitas();
        Call<SuperUser> call=citasServicio.login(usuario);
        System.out.println("pasa por la llamada call ");

        call.enqueue(new Callback<SuperUser>() {
            @Override
            public void onResponse(Call<SuperUser> call, Response<SuperUser> response) {

                ArrayList<String > datos=new ArrayList<String>();

                String  user_id=String.valueOf(response.body().getUser_id());
                String  UserName=response.body().getUsername();
                String  token ="Token "+response.body().getToken();

                System.out.println("user id "+user_id);
                System.out.println("user name "+UserName);
                System.out.println("token "+token);

                datos.add(user_id);
                datos.add(UserName);
                datos.add(token);

                Intent intent=new Intent(MainActivity.this,MainCitas.class);
                intent.putExtra("SuperUser",datos);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "LOGEADO", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SuperUser> call, Throwable t) {
                System.out.println("error "+t+" call "+call);
                Toast.makeText(MainActivity.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Login(String gmail){
        citasServicio= Conexion.getServiceRemoteCitas();
        Call<Login> call=citasServicio.userLogin(gmail);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if(response.isSuccessful()){

                    System.out.println("respuesta perro "+response.body().getOwner_id());
                }
                else{
                    System.out.println("no respuesta "+response.body());
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                System.out.println("respuesta fallida "+t+" llamada "+call);
            }
        });


    }

    public void ObtenrCitas(){
        citasServicio= Conexion.getServiceRemoteCitas();
        Call<List<Citas>> call =citasServicio.getCitas(4);

        call.enqueue(new Callback<List<Citas>>() {
            @Override
            public void onResponse(Call<List<Citas>> call, Response<List<Citas>> response) {

                if(response.isSuccessful()){
                    System.out.println("respuesta correcta "+response.body());
                }
                else{
                    System.out.println("respuesta fallida "+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Citas>> call, Throwable t) {

                System.out.println("llamada "+call +"Error "+t);
            }

        });
    }

    public void PostCitas(Citas cita){
        citasServicio= Conexion.getServiceRemoteCitas();
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
        });



    }

}
