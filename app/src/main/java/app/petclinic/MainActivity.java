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

            if(!TextUtils.isEmpty(user)){
                if(!TextUtils.isEmpty(pass)){
                    LoginUsuario(user);
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

    public void LoginUsuario(String gmail){
        citasServicio= Conexion.getServiceRemoteCitas();
        Call<String> call=citasServicio.userLogin(gmail);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    System.out.println("respuesta login "+response.body());
                    String id_usuario=response.body();
                    Intent intent=new Intent(MainActivity.this,MainCitas.class);
                    intent.putExtra("id_usuario",id_usuario);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login Exitoso ", Toast.LENGTH_SHORT).show();
                    UserName.setText("");
                    password.setText("");
                }
                else{
                    System.out.println("respuesta fallida login "+response.body());
                    Toast.makeText(MainActivity.this, "intentelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("respuesta fallida  error "+t+" llamada "+call);
                Toast.makeText(MainActivity.this, "Error en el servidor "+t, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
