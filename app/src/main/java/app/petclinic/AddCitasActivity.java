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

public class AddCitasActivity extends AppCompatActivity {

    EditText horas,fecha;

    //variables de las fechas
    int anio,mes,dia,anioI,mesI,diaI;
    static final int DATE_ID = 0;
    Calendar Calendario = Calendar.getInstance();
    //fin variables de las fechas
    Spinner SpinnerEspecialidades,SpinnerMascotas;
    Button Especialidades,Mascota,Citas;

    //variables de las horas
    int hora ,minuto ;
    String date_time="";




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
        Citas=findViewById(R.id.Citas);

        anioI = Calendario.get(Calendar.MONTH);
        mesI  = Calendario.get(Calendar.DAY_OF_MONTH);
        diaI = Calendario.get(Calendar.YEAR);

        List<String> especialidades = new ArrayList<>();
        List<String> mascotas = new ArrayList<>();

        especialidades.add("especialidad 1");
        especialidades.add("especialidad 2");
        especialidades.add("especialidad 3");
        mascotas.add("mascota 1");
        mascotas.add("mascota 2");
        mascotas.add("mascota 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,especialidades);
        SpinnerEspecialidades.setAdapter(adapter);

        ArrayAdapter<String> AdapterMascotas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,mascotas);
        SpinnerMascotas.setAdapter(AdapterMascotas);



        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddCitasActivity.this, "soy la verga", Toast.LENGTH_SHORT).show();
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

        Citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    // inicio de selector de fechas

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            anio = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            String fecha1=(anio+1)+"-"+mes+"-"+dia;
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

                       horas.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                },hora,minuto, false);
        timePickerDialog.show();
    }

    // fin de selector de hora




}
