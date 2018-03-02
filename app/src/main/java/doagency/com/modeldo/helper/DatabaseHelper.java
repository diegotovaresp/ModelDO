package doagency.com.modeldo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import doagency.com.modeldo.objetos.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Diego on 24/01/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "model_intranet";

    // Table Names
    private static final String TABLE_MODELO = "ev_Modelo";
    private static final String TABLE_PAIS = "ev_Pais";

    // Common column names
    private static final String KEY_ID = "id";

    // NOTES Table - column nmaes
    private static final String KEY_IDMODELO = "idModelo";
    private static final String KEY_IDUSUARIO = "idUsuario";
    private static final String KEY_NOMBRE = "Nombre";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_CELULAR = "Celular";
    private static final String KEY_OJO = "Ojo";
    private static final String KEY_PELO = "Pelo";
    private static final String KEY_PIEL = "Piel";
    private static final String KEY_PAIS = "Pais";
    private static final String KEY_PAISACTUAL = "PaisActual";
    private static final String KEY_ESTATURA = "Estatura";
    private static final String KEY_CINTURA = "Cintura";
    private static final String KEY_BUSTO = "Busto";
    private static final String KEY_CADERA = "Cadera";
    private static final String KEY_ZAPATOS = "Zapato";
    private static final String KEY_IDOJO = "idOjo";
    private static final String KEY_IDPELO = "idPelo";
    private static final String KEY_IDPIEL = "idPiel";

    // TABLE PAIS

    private static  final String KEY_IDPAIS = "idPais";
    private static final String KEY_NOMBREPAIS= "Pais";
    private static final String KEY_IMPORTANCIA = "Importancia";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_MODELO = "CREATE TABLE "
            + TABLE_MODELO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IDUSUARIO
            + " INTEGER," + KEY_NOMBRE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_CELULAR
            + " TEXT," + KEY_OJO  + " TEXT," + KEY_PELO  + " TEXT," + KEY_PIEL+" TEXT,"
            + KEY_PAIS+" TEXT,"+KEY_PAISACTUAL+" TEXT,"+KEY_ESTATURA+" INTEGER,"+KEY_CINTURA
            + " INTEGER,"+KEY_BUSTO+" INTEGER,"+KEY_CADERA+" INTEGER,"+KEY_ZAPATOS
            + " INTEGER,"+KEY_IDOJO+" INTEGER,"+KEY_IDPELO+" INTEGER,"+ KEY_IDPIEL+" INTEGER" +")";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_MODELO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODELO);

        // create new tables
        onCreate(db);
    }

    /*
     * Creating a NombresListasCM
     */
    public long createModelo(Usuario listasCM) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDUSUARIO, listasCM.getId());
        values.put(KEY_NOMBRE, listasCM.getNombre());
        values.put(KEY_IDMODELO, listasCM.getIdModelo());
        values.put(KEY_CELULAR, listasCM.getCelular());
        values.put(KEY_EMAIL, listasCM.getEmail());
        values.put(KEY_OJO, listasCM.getOjo());
        values.put(KEY_PELO, listasCM.getPelo());
        values.put(KEY_PIEL, listasCM.getPiel());
        values.put(KEY_PAIS, listasCM.getPais());
        values.put(KEY_PAISACTUAL, listasCM.getPaisActual());
        values.put(KEY_ESTATURA, listasCM.getEstatura());
        values.put(KEY_CINTURA, listasCM.getCintura());
        values.put(KEY_BUSTO, listasCM.getBusto());
        values.put(KEY_CADERA, listasCM.getCadera());
        values.put(KEY_ZAPATOS, listasCM.getZapatos());
        values.put(KEY_IDOJO, listasCM.getIdOjo());
        values.put(KEY_IDPELO, listasCM.getIdPelo());
        values.put(KEY_IDPIEL, listasCM.getIdPiel());
        // insert row




        return db.insert(TABLE_MODELO, null, values);
    }

    /*
 * get single todo
 */
    public Usuario getModelo(long Modelo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MODELO + " WHERE "
                + KEY_IDMODELO + " = " + Modelo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Usuario td = new Usuario();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setIdUsuario((c.getInt(c.getColumnIndex(KEY_IDUSUARIO))));
        td.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));
        td.setIdModelo((c.getInt(c.getColumnIndex(KEY_IDMODELO))));
        td.setCelular(c.getString(c.getColumnIndex(KEY_CELULAR)));
        td.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        td.setOjo(c.getString(c.getColumnIndex(KEY_OJO)));
        td.setPelo(c.getString(c.getColumnIndex(KEY_PELO)));
        td.setPiel((c.getString(c.getColumnIndex(KEY_PIEL))));
        td.setPais(c.getString(c.getColumnIndex(KEY_PAIS)));
        td.setPaisActual(c.getString(c.getColumnIndex(KEY_PAISACTUAL)));
        td.setEstatura(c.getInt(c.getColumnIndex(KEY_ESTATURA)));
        td.setCintura(c.getInt(c.getColumnIndex(KEY_CINTURA)));
        td.setBusto(c.getInt(c.getColumnIndex(KEY_BUSTO)));
        td.setCadera(c.getInt(c.getColumnIndex(KEY_CADERA)));
        td.setZapatos(c.getInt(c.getColumnIndex(KEY_ZAPATOS)));
        td.setIdOjo(c.getInt(c.getColumnIndex(KEY_IDOJO)));
        td.setIdPelo(c.getInt(c.getColumnIndex(KEY_IDPELO)));
        td.setIdPiel(c.getInt(c.getColumnIndex(KEY_IDPIEL)));
        return td;
    }

    /*
     * getting all todos
     * */
    public List<Usuario> getAllModelos() {
        List<Usuario> todos = new ArrayList<Usuario>();
        String selectQuery = "SELECT  * FROM " + TABLE_MODELO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Usuario td = new Usuario();
                td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                td.setIdUsuario((c.getInt(c.getColumnIndex(KEY_IDUSUARIO))));
                td.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));
                td.setIdModelo((c.getInt(c.getColumnIndex(KEY_IDMODELO))));
                td.setCelular(c.getString(c.getColumnIndex(KEY_CELULAR)));
                td.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                td.setOjo(c.getString(c.getColumnIndex(KEY_OJO)));
                td.setPelo(c.getString(c.getColumnIndex(KEY_PELO)));
                td.setPiel((c.getString(c.getColumnIndex(KEY_PIEL))));
                td.setPais(c.getString(c.getColumnIndex(KEY_PAIS)));
                td.setPaisActual(c.getString(c.getColumnIndex(KEY_PAISACTUAL)));
                td.setEstatura(c.getInt(c.getColumnIndex(KEY_ESTATURA)));
                td.setCintura(c.getInt(c.getColumnIndex(KEY_CINTURA)));
                td.setBusto(c.getInt(c.getColumnIndex(KEY_BUSTO)));
                td.setCadera(c.getInt(c.getColumnIndex(KEY_CADERA)));
                td.setZapatos(c.getInt(c.getColumnIndex(KEY_ZAPATOS)));
                td.setIdOjo(c.getInt(c.getColumnIndex(KEY_IDOJO)));
                td.setIdPelo(c.getInt(c.getColumnIndex(KEY_IDPELO)));
                td.setIdPiel(c.getInt(c.getColumnIndex(KEY_IDPIEL)));
                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        c.close();

        return todos;
    }

    /*
 * Updating a todo
 */
    public int updateModelo(Usuario todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDMODELO, todo.getIdModelo());
        values.put(KEY_NOMBRE, todo.getNombre());
        values.put(KEY_IDUSUARIO, todo.getIdUsuario());
        values.put(KEY_EMAIL, todo.getEmail());
        values.put(KEY_CELULAR, todo.getCelular());
        values.put(KEY_OJO, todo.getOjo());
        values.put(KEY_PELO, todo.getPelo());
        values.put(KEY_PIEL, todo.getPiel());
        values.put(KEY_PAIS, todo.getPais());
        values.put(KEY_PAISACTUAL, todo.getPaisActual());
        values.put(KEY_ESTATURA, todo.getEstatura());
        values.put(KEY_CINTURA, todo.getCintura());
        values.put(KEY_BUSTO, todo.getBusto());
        values.put(KEY_CADERA, todo.getCadera());
        values.put(KEY_ZAPATOS, todo.getZapatos());
        values.put(KEY_IDOJO, todo.getIdOjo());
        values.put(KEY_IDPIEL, todo.getIdPiel());
        values.put(KEY_IDPELO, todo.getIdPelo());
        // updating row
        return db.update(TABLE_MODELO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    /*
 * Deleting a todo
 */
    public void deleteModelo(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MODELO, KEY_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get INTEGER
     * */
    private String getINTEGER() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
