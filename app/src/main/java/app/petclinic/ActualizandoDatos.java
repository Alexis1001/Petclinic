package app.petclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ActualizandoDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizando_datos);


        ArrayList<String> datos1=getIntent().getStringArrayListExtra("SuperUser");

        int id_usuario=Integer.parseInt(datos1.get(0));
        String user_name=datos1.get(1);
        String token=datos1.get(2);


        ArrayList<String> datos = new ArrayList<String>();

        System.out.println("user id " + id_usuario);
        System.out.println("user name " + user_name);
        System.out.println("token " + token);

        datos.add(String.valueOf(id_usuario));
        datos.add(user_name);
        datos.add(token);

        Intent intent=new Intent(ActualizandoDatos.this,MainCitas.class);
        intent.putExtra("SuperUser",datos);
        startActivity(intent);


    }
}
