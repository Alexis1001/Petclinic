package app.petclinic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import app.petclinic.Conexion.Conexion;
import app.petclinic.Modelos.Appointment;
import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Pet;
import app.petclinic.Servicio.CitasServicio;
import app.petclinic.Servicio.Peticiones;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    int hora ,minuto,segundos ;
    String date_time="";


    String id_usuarioS="";
    int id_usuario=0;

    String Hora_minutos;
    String fecha1;
    CitasServicio citasServicio;

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

        especialidades.add("cardiologia");
        especialidades.add("dermatologia");
        especialidades.add("fisoterapia");

        mascotas.add("cat");
        mascotas.add("dog");
        mascotas.add("lizard");
        mascotas.add("snake");
        mascotas.add("bird");
        mascotas.add("hamster");


        id_usuarioS=getIntent().getStringExtra("id_usuario");
        id_usuario=Integer.parseInt(id_usuarioS);

        System.out.println("id_usuarioS "+id_usuarioS);
        System.out.println("id_usuario "+id_usuario);


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
                int pet=getTipo(TipoMascota);
                int especialidadx=getEspecialidad(TipoEspecialidad);

                if(fecha1!=null){
                    if(Hora_minutos!=null){

                        Appointment cita=new Appointment(id_usuario,fecha1,Hora_minutos,pet,especialidadx,0); //asi no se envia la fecha
                        PostCitas(cita);// esta metodo si funciona

                   }
                    else{
                        Toast.makeText(AddCitasActivity.this, "Campos vacios ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(AddCitasActivity.this, "Campos vacios ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            anio = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            fecha1=anio+"-"+(mes+1)+"-"+dia;
            fecha.setText(fecha1);
            //anio lequite uno (anio+1)
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

    private void TimePicker(){
         hora = Calendario.get(Calendar.HOUR_OF_DAY);
         minuto = Calendario.get(Calendar.MINUTE);
         segundos=Calendario.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora=hourOfDay;
                        minuto=minute;
                        System.out.println("segundos perro "+segundos);


                        Hora_minutos=hourOfDay+":"+minute+":"+00;//aqui agrego nuevo
                        horas.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                },hora,minuto, false);
        timePickerDialog.show();
    }

    public int getTipo(String type){
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

    public int getEspecialidad(String especialidad){
        int numerox=0;
        System.out.println("especialidad metodo get especialidad "+especialidad);
        if(especialidad.equals("cardiologia")){
            numerox=1;
        }
        if(especialidad.equals("dermatologia")){
            numerox=2;
        }
        if(especialidad.equals("fisoterapia")){
            numerox=3;
        }

        return numerox;
    }


    public void PostCitas(Appointment cita){
        citasServicio= Conexion.getServiceRemoteCitas();
        Integer owner_id=cita.getOwner_id();
        String fecha=cita.getFecha();
        String hora=cita.getHora();
        Integer mascota=cita.getMascota();
        Integer especialidad=cita.getEspecialidad();
        Integer confirmacion=cita.getConfirmacion();
        //Call<ResponseBody> call=  citasServicio.AddAppoinment(4,"2015-07-27","15:12:00",4,2,0); si funciona de esta manera
        Call<ResponseBody> call= citasServicio.AddAppoinment(owner_id,fecha,hora,mascota,especialidad,confirmacion);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println("nueva cita agregada  "+response.body());
                    Intent intent=new Intent(AddCitasActivity.this,ActualizandoDatos.class);
                    intent.putExtra("id_usuario",id_usuarioS);
                    startActivity(intent);
                    Toast.makeText(AddCitasActivity.this, "Nueva cita ", Toast.LENGTH_SHORT).show();
                }
                else{
                    System.out.println("repuesta fallida cita "+response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error cita "+t+" llamada "+call);
            }
        });

    }

}
