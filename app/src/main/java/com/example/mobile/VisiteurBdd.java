package com.example.mobile;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



// Gestion de la table Visiteur dans la BDD SQLite 3

public class VisiteurBdd {
    private SQLiteDatabase db;
    public static final String CREATE_TABLE_VISITEUR = "create table Visiteur (vis_matricule text primary key, vis_nom text not null," +
            "vis_prenom text not null, vis_adresse text, vis_cp text, vis_ville text, " +
            "vis_login text not null unique, vis_mdp text not null)";

    private GsbSQLiteOpenHelper gsbHelper;

    private String[] colonnes = {"vis_matricule", "vis_nom", "vis_prenom", "vis_adresse", "vis_cp", "vis_ville",
            "vis_login"};


    public VisiteurBdd(Context context) {

        gsbHelper = GsbSQLiteOpenHelper.getInstance(context);
        gsbHelper.checkDataBase();
    }

    public void open() throws SQLException {
        db = gsbHelper.getReadableDatabase();
    }

    public void close() {
        gsbHelper.close();
    }

    public long insertVisiteur(Visiteur visiteur) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("vis_matricule", visiteur.getVisMatricule());
        contentValues.put("vis_nom", visiteur.getVisNom());
        contentValues.put("vis_prenom", visiteur.getVisPrenom());
        contentValues.put("vis_adresse", visiteur.getVisAdresse());
        contentValues.put("vis_cp", visiteur.getVisCp());
        contentValues.put("vis_ville", visiteur.getVisVille());
        contentValues.put("vis_login", visiteur.getVisLogin());
        contentValues.put("vis_mdp", visiteur.getVisMdp());

        return db.insert("Visiteur", null, contentValues);
    }


    public int deleteVisiteurByMatricule(String matricule) {
        String whereClause = "vis_matricule = ?";
        String[] argsClause = {matricule};

        return db.delete("Visiteur", whereClause, argsClause);
    }

    public void deleteVisiteur(Visiteur visiteur) {
        db.delete("Visiteur", "vis_matricule = " + visiteur.getVisMatricule(), null);
    }

    public int updateVisiteur(String matricule, Visiteur visiteur) {
        ContentValues contentValues = new ContentValues();

        // contentValues.put("vis_matricule", visiteur.getVisMatricule());
        contentValues.put("vis_nom", visiteur.getVisNom());
        contentValues.put("vis_prenom", visiteur.getVisPrenom());
        contentValues.put("vis_adresse", visiteur.getVisAdresse());
        contentValues.put("vis_cp", visiteur.getVisCp());
        contentValues.put("vis_ville", visiteur.getVisVille());
        contentValues.put("vis_login", visiteur.getVisLogin());
        contentValues.put("vis_mdp", visiteur.getVisMdp());

        return db.update("Visiteur", contentValues, "vis_matricule = " + matricule,
                null);

    }

    //permet au visiteur de se connecter  a l'aide de son mot de passe et de son login
    public boolean getVisiteurByLoginAndMdp(String login, String mdp) {
        String whereClause = "vis_login = ? AND vis_mdp = ?";
        String[] whereArgs = new String[]{login,mdp};

        Cursor cursor = db.query("Visiteur", new String[]{"vis_matricule", "vis_nom", "vis_prenom", "vis_adresse", "vis_cp",
                        "vis_ville"},
                whereClause,
                whereArgs,
                null, null, null);
        Visiteur visiteur = cursorToVisiteur(cursor);
        System.out.println("visiteurToBdd : " + visiteur);
        if (visiteur == null)
                return false;
        else
                return true;
    }


    private Visiteur cursorToVisiteur(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst(); // Se placer sur le 1er élément
        Visiteur visiteur = new Visiteur();
        visiteur.setVisMatricule(cursor.getString(0));
        visiteur.setVisNom(cursor.getString(1));
        visiteur.setVisPrenom(cursor.getString(2));
        visiteur.setVisAdresse(cursor.getString(3));
        visiteur.setVisCp(cursor.getString(4));
        visiteur.setVisVille(cursor.getString(5));
        cursor.close();
        return visiteur;


    }

    public List<Visiteur> getAllVisiteurs() {
        List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();

        Cursor cursor = db.query("Visiteur", new String[] {"vis_matricule", "vis_nom", "vis_prenom"}, null,
                null,null, null, null);

        if (cursor.moveToFirst())
        { int  i=0;
            do {

                String vis_nom = "";
                String vis_prenom = "";
                try {
                    vis_nom = new String(cursor.getBlob(1), "UTF-8");
                    vis_prenom = new String(cursor.getBlob(2), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Visiteur visiteur = new Visiteur();
                visiteur.setVisNom(vis_nom);
                visiteur.setVisPrenom(vis_prenom);
                lesVisiteurs.add(visiteur);

            }
            while (cursor.moveToNext());
        }
        cursor.close(); // fermeture du curseur
        return lesVisiteurs;
    }
}
