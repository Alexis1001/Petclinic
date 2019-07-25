package app.petclinic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Pet;
import app.petclinic.Servicio.Peticiones;

public class AddCitasActivity extends AppCompatActivity {

    EditText horas,fecha;

    //variables de las fechas
    int anio,mes,dia,anioI,mesI,diaI;
    static final int DATE_ID = 0;
    Calendar Calendario = Calendar.getInstance();
    //fin variables de las fechas
    Spinner SpinnerEspecialidades,SpinnerMascotas;
    Button Especialidades,Mascota,Citasxx;

    //variables de las horas
    int hora ,minuto ;
    String date_time="";

    int user_id;
    String user_name;
    String token;

    String Hora_minutos;
    String fecha1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_citas);
        setTitle("Agregar Citas");
        horas=findViewById(R.id.horas);
        fecha=findViewById(R.id.fecha);

        SpinnerEspecialidades = findViewById(R.id.spinner);
        SpinnerMascotas=findViewById(R.id.spinner1);
        Especialidades=findViewById(R.id.Especialidades);
        Mascota=findViewById(R.id.Mascota);
        Citasxx=findViewById(R.id.Citas);

        anioI = Calendario.get(Calendar.MONTH);
        mesI  = Calendario.get(Calendar.DAY_OF_MONTH);
        diaI = Calendario.get(Calendar.YEAR);

        final List<String> especialidades = new ArrayList<>();
        List<String> mascotas = new ArrayList<>();

        especialidades.add("cardiologia ");
        especialidades.add("dermatologia ");
        especialidades.add("fisoterapia");

        mascotas.add("cat");
        mascotas.add("dog");
        mascotas.add("lizard");
        mascotas.add("snake");
        mascotas.add("bird");
        mascotas.add("hamster");

        ArrayList<String> datos=getIntent().getStringArrayListExtra("SuperUser");

        user_id=Integer.parseInt(datos.get(0));
        user_name=datos.get(1);
        token=datos.get(2);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,especialidades);
        SpinnerEspecialidades.setAdapter(adapter);

        ArrayAdapter<String> AdapterMascotas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,mascotas);
        SpinnerMascotas.setAdapter(AdapterMascotas);



        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddCitasActivity.this, "soy la verga", Toast.LENGTH_SHORT).show();
                showDialog(DATE_ID);

            }
        });

        Especialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selecion=SpinnerEspecialidades.getSelectedItem().toString();
                System.out.println("selecionado "+selecion);
            }
        });

        Mascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selecion=SpinnerMascotas.getSelectedItem().toString();
                System.out.println("selecionado "+selecion);
            }
        });

        horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePicker();
            }
        });

        Citasxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TipoMascota=SpinnerMascotas.getSelectedItem().toString();
                String TipoEspecialidad=SpinnerEspecialidades.getSelectedItem().toString();

                int pet=getType(TipoMascota);
                int especialidadx=getEspecialidad(TipoEspecialidad);

                System.out.println("tipo mascota "+TipoMascota);
                System.out.println("tipo especialidad "+TipoEspecialidad);

                System.out.println("owner_id "+user_id);
                System.out.println("fecha "+fecha1);
                System.out.println("hora "+Hora_minutos);
                System.out.println("tipo mascota "+pet);
                System.out.println("especialidad "+especialidadx);
                System.out.println("boolean "+false);

                //Citas(int owner_id, String fecha, String hora, int mascota, int especialidad, boolean delete)
                //Citas cita=new Citas(user_id,fecha1,Hora_minutos,pet,especialidadx,false);
                //Peticiones peticiones=new Peticiones();
                //peticiones.AgregarCitas(AddCitasActivity.this,user_id,user_name,token,cita);



            }
        });


    }


    // inicio de selector de fechas

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            anio = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            fecha1=(anio+1)+"-"+mes+"-"+dia;
            fecha.setText(fecha1);
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
    // fin de selector de fechas


    // incio de selector de hora

    private void TimePicker(){
         hora = Calendario.get(Calendar.HOUR_OF_DAY);
         minuto = Calendario.get(Calendar.MINUTE);
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora=hourOfDay;
                        minuto=minute;

                        Hora_minutos=date_time+" "+hourOfDay + ":"+ minute; //aqui agrego nuevo
                        horas.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                },hora,minuto, false);
        timePickerDialog.show();
    }

    // fin de selector de hora

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

    public int getEspecialidad(String type){
        int numerox=0;

        if(type.equals("cardiologia")){
            numerox=1;
        }
        if(type.equals("dermatologia")){
            numerox=2;
        }
        if(type.equals("fisoterapia")){
            numerox=3;
        }

        return numerox;
    }





}
