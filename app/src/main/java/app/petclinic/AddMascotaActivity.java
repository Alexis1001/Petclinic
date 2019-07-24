package app.petclinic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.petclinic.Modelos.Pet;
import app.petclinic.Servicio.Peticiones;

public class AddMascotaActivity extends AppCompatActivity {

    TextView dueno;
    EditText NombreMascota,FechaDeNacimento;
    Button ButtonMascotas,AgregarMascota;
    Spinner SpinnerMascotasTipo;
    int anio,mes,dia,anioI,mesI,diaI;
    static final int DATE_ID = 0;
    int user_id;
    String user_name;
    String token;
    String fecha1="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mascota);
        setTitle("Agregar Mascota ");

        dueno=findViewById(R.id.Dueno);
        NombreMascota=findViewById(R.id.ActualizarNombreMascota);
        FechaDeNacimento=findViewById(R.id.FechaDeNacimiento);
        ButtonMascotas=findViewById(R.id.ButtonMascotas);
        SpinnerMascotasTipo=findViewById(R.id.SpinnerMascotasTipo);
        AgregarMascota=findViewById(R.id.AgregarMascota);

        ArrayList<String> datos=getIntent().getStringArrayListExtra("SuperUser");
        System.out.println("datos obtenidos "+datos);
        user_id=Integer.parseInt(datos.get(0));
        user_name=datos.get(1);
        token=datos.get(2);

        dueno.setText(user_name);// agrego el dueño de la mascota

        System.out.println("user_id "+user_id);
        System.out.println("user name "+user_name);
        System.out.println("token "+token);


        List<String> TipoDeMascotas= new ArrayList<>();
        TipoDeMascotas.add("cat");
        TipoDeMascotas.add("dog");
        TipoDeMascotas.add("lizard");
        TipoDeMascotas.add("snake");
        TipoDeMascotas.add("bird");
        TipoDeMascotas.add("hamster");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,TipoDeMascotas);
        SpinnerMascotasTipo.setAdapter(adapter);


        FechaDeNacimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });

        AgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreMascota=NombreMascota.getText().toString();
                String fecha2=fecha1;
                String tipo=SpinnerMascotasTipo.getSelectedItem().toString();

                System.out.println("nombre de la mascota "+nombreMascota);
                System.out.println("fecha "+fecha2);
                System.out.println("tipo "+tipo);

                int type= getType(tipo);

                if(!nombreMascota.isEmpty()){
                    if(!fecha2.isEmpty()){
                        if(type>0){
                            Pet pet=new Pet(nombreMascota,fecha1,type,user_id,false);
                            Peticiones peticiones=new Peticiones();
                            peticiones.AgregarPet(AddMascotaActivity.this,user_id,user_name,token,pet,pet.getId());
                        }
                        else{
                            Toast.makeText(AddMascotaActivity.this, "campos vacios ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{

                        Toast.makeText(AddMascotaActivity.this, "campos vacios ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    Toast.makeText(AddMascotaActivity.this, "campos vacios ", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            anio = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            fecha1=(anio+1)+"-"+mes+"-"+dia;
            FechaDeNacimento.setText(fecha1);
        }

    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener,anioI,mesI,diaI);
        }
        return null;
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
