package com.example.mobile;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import static android.widget.Toast.makeText;


public class Liste  extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_rapport);

        ListView listView=(ListView) findViewById(R.id.liste_rapports);
        String mois = (String) getIntent().getSerializableExtra("mois");
        String annee = (String) getIntent().getSerializableExtra("annee");
        String matricule = (String) getIntent().getSerializableExtra("matricule");
        makeText(this, mois + "/" + annee + "de " + matricule ,Toast.LENGTH_SHORT).show();
        RapportVisiteBdd rvb = new RapportVisiteBdd(this);
        rvb.open();
        final List<String> rapports = rvb.getRapportVisiteMat(matricule);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , rapports);
        listView.setAdapter(arrayAdapter);
        listView.getSelectedView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RapportVisiteBdd rvb = new RapportVisiteBdd(Liste.this);
                rvb.open();
                Toast.makeText(Liste.this, rvb.getDetailRapportVisite(Integer.toString(position+1)).toString(), Toast.LENGTH_LONG).show();

            }
        });

    }


}
