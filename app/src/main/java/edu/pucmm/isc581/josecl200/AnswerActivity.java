package edu.pucmm.isc581.josecl200;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AnswerActivity extends AppCompatActivity {

    TextView nombre,info,programacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent intent = getIntent();
        nombre=(TextView) findViewById(R.id.textView);
        info=(TextView) findViewById(R.id.textView2);
        programacion=(TextView) findViewById(R.id.textView3);
        nombre.setText(intent.getExtras().getString("nameString"));
        info.setText(intent.getExtras().getString("sexAndBirthString"));
        programacion.setText(intent.getExtras().getString("programmingString"));


    }
}