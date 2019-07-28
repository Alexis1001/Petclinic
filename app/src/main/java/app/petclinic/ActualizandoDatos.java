package app.petclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ActualizandoDatos extends AppCompatActivity {

    String id_usuarioS="";
    int id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizando_datos);

        id_usuarioS=getIntent().getStringExtra("id_usuario");
        System.out.println("perrro ........"+id_usuarioS);
        id_usuario=Integer.parseInt(id_usuarioS);

        Intent intent=new Intent(ActualizandoDatos.this,MainCitas.class);
        intent.putExtra("id_usuario",id_usuarioS);
        startActivity(intent);
    }
}
