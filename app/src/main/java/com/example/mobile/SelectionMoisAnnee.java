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

public class SelectionMoisAnnee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Intent intent;
    private  Spinner mois;
    private Spinner annee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_mois_annee);


        Spinner mois = (Spinner) findViewById(R.id.mois);
        Spinner annee = (Spinner) findViewById(R.id.annee);
        Button valider=(Button)findViewById(R.id.valider);

        //liste deroulante pour le mois

        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this,R.array.number,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mois.setAdapter(adapter);
      //  mois.setOnItemSelectedListener(this);

        //liste deroulante pour l'annee

        adapter = ArrayAdapter.createFromResource(this, R.array.annee, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        annee.setAdapter(adapter);
       // annee.setOnItemSelectedListener(this);
    }

    public  void valider(View view){
            Spinner mois = (Spinner) findViewById(R.id.mois);
            Spinner annee = (Spinner) findViewById(R.id.annee);
            Button valider=(Button)findViewById(R.id.valider);

            Intent intent = new Intent(this, Liste.class);
            intent.putExtra("annee", annee.getSelectedItem().toString());
            intent.putExtra("mois", mois.getSelectedItem().toString());
            intent.putExtra("matricule", (String)getIntent().getSerializableExtra("matricule"));
            startActivity(intent);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
