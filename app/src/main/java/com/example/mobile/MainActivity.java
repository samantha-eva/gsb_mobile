package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private VisiteurBdd visiteurBdd;
    private Visiteur visiteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connexion = (Button) findViewById(R.id.connecter);

        EditText login = (EditText) findViewById(R.id.LoginText);
        EditText mdp = (EditText) findViewById(R.id.mdpText);

        // Test BDD

        visiteurBdd = new VisiteurBdd(this);
        visiteurBdd.open();

    }

    public void Go(View view) {
        Toast.makeText(this, "coucou de sam!", Toast.LENGTH_LONG).show();
    }

    public void connexion(View view) {
        EditText login = (EditText) findViewById(R.id.LoginText);
        EditText mdp = (EditText) findViewById(R.id.mdpText);
        boolean con = visiteurBdd.getVisiteurByLoginAndMdp(login.getText().toString(), mdp.getText().toString());
        if (con == true) {
            Toast.makeText(this, "Vous êtes connecté!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Accueil.class);
            intent.putExtra("matricule",mdp.getText().toString());
            startActivity(intent);
            //visiteurBdd.close();
            RapportVisiteBdd rvb = new RapportVisiteBdd(this);
            rvb.open();

            RapportVisite rv = new RapportVisite();
            rv.setRapCoefConf("2");
            rv.setRapMotif("coronavirus");
            rv.setRapDateRaport("10/2020");
            rv.setRapMatricule("a17");
            rv.setRapBilan("très contagieux");
            //rvb.insertRapportVisite(rv);
            //System.out.println(rvb.getAllRapportVisites());
            rvb.getDetailRapportVisite("a17");
        } else {
            Toast.makeText(this, "erreur d'authentification", Toast.LENGTH_LONG).show();

        }

    }
}