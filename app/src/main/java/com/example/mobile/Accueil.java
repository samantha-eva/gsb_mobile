package com.example.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Accueil extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_accueil);


        Button consulter=(Button)findViewById(R.id.consulter);
        Button saisir=(Button)findViewById(R.id. saisir);

    }
    public void consulter(View view){
        //Toast.makeText(this, "coucou de sam!", Toast.LENGTH_LONG).show();
        intent = new Intent(this, SelectionMoisAnnee.class);
        intent.putExtra("matricule", (String) getIntent().getSerializableExtra("matricule"));
        startActivity(intent);


    }
}

