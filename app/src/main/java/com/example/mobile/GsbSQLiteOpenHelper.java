package com.example.mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Gère la création et la gestion de la BDD
// Méthodes à implémenter : onCreate(SQLiteDatabase), onUpgrade(SQLiteDatabase, int, int)
// option : onOpen(SQLiteDatabase)

public class GsbSQLiteOpenHelper extends SQLiteOpenHelper {
    private final Context context;
    private static GsbSQLiteOpenHelper gsbInstance = null;

    public static final String DB_NAME = "gsbcr.db";    // nom de la BDD
    private static final int DB_VERSION = 1;             // numéro de version
    private String DB_PATH;


    // Constructeur - pattern Singleton

    public static GsbSQLiteOpenHelper getInstance(Context context) {
        if (gsbInstance == null) {
            gsbInstance = new GsbSQLiteOpenHelper(context);
        }
        return gsbInstance;
    }

    private GsbSQLiteOpenHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;



        String repertoire = context.getFilesDir().getPath(); // retourne /data/data/<package>/files
        // Pour avoir /data/data/<package>/databases/
        DB_PATH = repertoire.substring(0, repertoire.lastIndexOf("/")) + "/databases/";

        // Si la BDD n'existe pas sur le terminal mobile
        // la copier du répertoire assets
        if (!checkDataBase()) {
            copyDataBase();
        }
    }

    public boolean checkDataBase() {

        File dbFile = new File(DB_PATH + DB_NAME);
        System.out.println(dbFile.exists() + " " + DB_PATH + DB_NAME);
        return dbFile.exists();
    }


    private void copyDataBase() {
        final String dstFileName = DB_PATH+DB_NAME;   // Fichier destination
        //String inFileName = this.context.getAssets()+DB_NAME;   // Fichier source de la Bdd dans assets
        InputStream inputStream;

        try {

            inputStream = this.context.getAssets().open(DB_NAME);




            // Dossier destination
            File cheminBdd = new File(DB_PATH);

            if (!(cheminBdd.exists())) {   // si le dossier de la BDD n'existe pas, il faut le créer
                if (!cheminBdd.mkdir()) {
                    Log.e("GsbSQLite","Création du répertoire impossible");
                    return;
                }
            }

            FileOutputStream dst = new FileOutputStream(dstFileName);
            // Copier la base de données dans le répertoire
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                dst.write(buffer, 0, length);
            }
            dst.flush();
            dst.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("GsbSQLite", "Erreur de copie de BDD"+e.getMessage());
        }
        try {
            SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                    SQLiteDatabase.OPEN_READWRITE).setVersion(DB_VERSION);
            Log.i("GsbSQLite", "Ouverture de BDD OK");
        } catch(SQLiteException e) {
            e.printStackTrace();
            Log.e("GsbSQLiteOpenHelper::bdd", "BDD n'existe pas"+e.getMessage());
        }
    }


    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            Log.w(GsbSQLiteOpenHelper.class.getName(), "upgrade GSB DB " + oldVersion + " to " + newVersion);
            // db.execSQL("DROP DATABASE IF EXISTS " + DB_NAME);
            this.context.deleteDatabase(DB_NAME);
            copyDataBase();

        }


    }
}
