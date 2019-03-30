package com.example.windows10.noteaplikasi;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "mydatabase" ;
    private static final String TAG = "";
    SQLiteDatabase database ;
    EditText judul, isi, tanggal ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        judul = (EditText) findViewById(R.id.Judul);
        isi = (EditText) findViewById(R.id.isi);
        tanggal = (EditText) findViewById(R.id.tanggal);

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Log.e(TAG, "onCreate: ",rt(createTable()));

        findViewById(R.id.fab).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);

    }

    private Throwable rt( void table) {
    }

    private void createTable(){


        String sql = "CREATE TABLE IF NOT EXISTS note (" +
                "    id INTEGER NOT NULL CONSTRAINT note_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    judul varchar(200) NOT NULL," +
                "    isi varchar(200) NOT NULL," +
                "    joiningdate datetime NOT NULL," +
                "    salary double NOT NULL" +
                ");" ;

        database.execSQL(sql);

    }

    private void addNote(){
        String judulNote = judul.getText().toString().trim();
        String isiNote = isi.getText().toString().trim();

        if(judulNote.isEmpty()) {
            judul.setError("harus diisi");
            judul.requestFocus();
            return;
        }

        if(isiNote.isEmpty()) {
            isi.setError("harus diisi");
            isi.requestFocus();
            return;
        }


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        String insertsql = "INSERT INTO note " +
                "(judul, isi, joiningdate)" +
                "VALUES " +
                "(?, ?, ?)" ;

        database.execSQL(insertsql, new String[]{isiNote, judulNote, joiningDate}) ;
        Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.fab :

               addNote() ;

           break;

           case R.id.button :
           break ;
       }
    }
}
