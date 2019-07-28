package app.petclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.petclinic.Modelos.Citas;
import app.petclinic.Modelos.Pet;

public class ArrayAdapterListaPets extends ArrayAdapter<Citas> {

    private Activity context;
    private List<Citas> citas=new ArrayList<>();


    ArrayAdapterListaPets(Activity context, ArrayList<Citas> citas){
        super(context, R.layout.activity_lista_mascotas,citas);
        this.context=context;
        this.citas=citas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_lista_mascotas, null, true);

        TextView fecha = (TextView) listViewItem.findViewById(R.id.titulo);


        Citas cita = citas.get(position);

        fecha.setText(cita.getFecha());


        return listViewItem;
    }



}







