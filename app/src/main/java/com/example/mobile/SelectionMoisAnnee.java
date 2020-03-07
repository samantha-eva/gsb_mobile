package com.example.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SelectionMoisAnnee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Intent intent;
    private  Spinner mois;
    String matricule;
    List<String> date_rapports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_mois_annee);

        Spinner mois = (Spinner) findViewById(R.id.mois);
        Button valider=(Button)findViewById(R.id.valider);
        RapportVisiteBdd rvb = new RapportVisiteBdd(this);
        rvb.open();
        intent = new Intent(this, Liste.class);

        matricule = (String)getIntent().getSerializableExtra("matricule");

        date_rapports  = rvb.getdateRapport(matricule);
        rvb.close();
        //System.out.print(date_rapports);
        //liste deroulante pour le mois

//        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this,R.array.mois,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1 , date_rapports);
        mois.setAdapter(adapter);
      //  mois.setOnItemSelectedListener(this);

    }

    public  void valider(View view){
            Spinner mois = (Spinner) findViewById(R.id.mois);
            Button valider=(Button)findViewById(R.id.valider);
            intent.putExtra("mois", mois.getSelectedItem().toString());
            intent.putExtra("matricule", matricule);
            startActivity(intent);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
