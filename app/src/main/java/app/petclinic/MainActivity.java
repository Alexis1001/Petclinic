package app.petclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import app.petclinic.Conexion.Conexion;
import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Credenciales.SuperUser;
import app.petclinic.Modelos.Credenciales.Usuario;
import app.petclinic.Servicio.CitasServicio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CitasServicio citasServicio;
    Button login;
    EditText UserName,password;
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
                    UserName.setText("");
                    password.setText("");

                    Usuario usuario= new Usuario(user,pass);
                    ObtenerToken(usuario);
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

}
