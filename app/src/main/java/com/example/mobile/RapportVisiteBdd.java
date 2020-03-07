package com.example.mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class RapportVisiteBdd {

    private SQLiteDatabase db;
    public static final String CREATE_TABLE_RAPPORT_VISITE = "create table Rapport_Visite (rap_num int primary key, vis_matricule int not null," +
            "pra_num int not null, rap_bilan text, rap_motif text, rap_coefConf int, rap_statut int not null" +
            "rap_dateVisite date not null unique, rap_dateRapport date not null)";

    private GsbSQLiteOpenHelper gsbHelper;

    private String[] colonnes = {"rap_num", "vis_matricule", "pra_num", "rap_bilan", "rap_motif", "rap_coefConf", "rap_statut",
            "rap_dateVisite", "rap_dateRapport"};


    public RapportVisiteBdd(Context context) {

        gsbHelper = GsbSQLiteOpenHelper.getInstance(context);
        gsbHelper.checkDataBase();
    }

    public void open() throws SQLException {
        db = gsbHelper.getWritableDatabase();
    }

    public void close() {
        gsbHelper.close();
    }


    public void dateRapport(RapportVisite rapportVisite) {
        // db.query("rapport_Visite", "rap_dateRapport="+ rapportVisite.getRapDateRapport(),null);
    }

    public long insertRapportVisite(RapportVisite rapportVisite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("vis_matricule", rapportVisite.getRapMatricule());
        contentValues.put("rap_num", rapportVisite.getRapNum());
        contentValues.put("rap_bilan", rapportVisite.getRapBilan());
        contentValues.put("rap_coefConf", rapportVisite.getRapCoefConf());
        contentValues.put("rap_motif", rapportVisite.getRapMotif());
        contentValues.put("rap_statut", rapportVisite.getRapStatut());
        contentValues.put("pra_num", rapportVisite.getPraNum());
        contentValues.put("rap_dateRapport", rapportVisite.getRapDateRapport());
        contentValues.put("rap_dateVisite", rapportVisite.getRapDateVisite());

        return db.insert("Rapport_Visite", null, contentValues);
    }

    private RapportVisite cursorToRapportVisite(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst(); // Se placer sur le 1er élément
        RapportVisite rapportVisite = new RapportVisite();
        rapportVisite.setRapNum(cursor.getString(0));
        rapportVisite.setRapMatricule(cursor.getString(1));
        rapportVisite.setPraNum(cursor.getString(2));
        rapportVisite.setRapBilan(cursor.getString(3));
        rapportVisite.setRapMotif(cursor.getString(4));
        rapportVisite.setRapCoefConf(cursor.getString(5));
        rapportVisite.setRapStatut(cursor.getString(6));
        rapportVisite.setRapDateVisite(cursor.getString(7));
        rapportVisite.setRapDateRaport(cursor.getString(8));
        System.out.println(rapportVisite);
        return rapportVisite;

    }

    public List<RapportVisite> getAllRapportVisites() {
        List<RapportVisite> lesDates = new ArrayList<RapportVisite>();

        Cursor cursor = db.query("Rapport_Visite", new String[] {"vis_matricule", "rap_dateRapport"}, null,
                null,null, null, null);

        if (cursor.moveToFirst())
        {
            do {

                String vis_matricule = "";
                String rap_dateRapport = "";

                RapportVisite rapportVisite = new RapportVisite();
                rapportVisite.setRapMatricule(cursor.getString(0));
                rapportVisite.setRapDateRaport(cursor.getString(1));
                System.out.println(rapportVisite);
                lesDates.add(rapportVisite);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return lesDates;

    }

    public RapportVisite getDetailRapportVisite(String rap_num){
        String whereClause = "rap_num = ?";
        String[] whereArgs = new String[]{rap_num};

        Cursor cursor = db.query("Rapport_Visite", new String[]{"*"},
                whereClause,
                whereArgs,
                null, null, null);
        RapportVisite rapportVisite = cursorToRapportVisite(cursor);
        cursor.close();
        return rapportVisite;

    }

    public List<String> getdateRapport(String vis_matricule){
        List<String> date_rapports = new ArrayList<String>();
        String whereClause = "vis_matricule = ?";
        String date;
        String[] whereArgs = new String[]{vis_matricule};
        String ordre = "rap_dateRapport";
        Cursor cursor = db.query("Rapport_Visite", new String[] {"rap_dateRapport"},
                whereClause,
                whereArgs,"rap_dateRapport", null,
                ordre);

        if (cursor.moveToFirst())
        {
            do {
                date = cursor.getString(0);
                if(date != null)
                    date_rapports.add(date);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return date_rapports;


    }


    public List<String> getRapportVisiteMat(String vis_matricule){
        List<String> id_rapports = new ArrayList<String>();
        String whereClause = "vis_matricule = ?";
        String[] whereArgs = new String[]{vis_matricule};
        String ordre = "rap_num";
        Cursor cursor = db.query("Rapport_Visite", new String[] {"rap_num"},
                whereClause,
                whereArgs,null, null,
                ordre);

        if (cursor.moveToFirst())
        {
            do {
                id_rapports.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return id_rapports;


    }

    public List<String> getRapportVisiteMatDate(String vis_matricule, String date_rapport){
        List<String> id_rapports = new ArrayList<String>();
        String whereClause = "vis_matricule = ? and rap_dateRapport = ?";
        String[] whereArgs = new String[]{vis_matricule, date_rapport};
        String ordre = "rap_num";
        Cursor cursor = db.query("Rapport_Visite", new String[] {"rap_num"},
                whereClause,
                whereArgs,null, null,
                ordre);

        if (cursor.moveToFirst())
        {
            do {
                id_rapports.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return id_rapports;


    }



}



