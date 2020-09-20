package edu.pucmm.isc581.josecl200;

import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellido;
    Spinner sexSpinner;
    RadioGroup programmer;
    Button mandar, limpiar;
    CheckBox c,csharp,java,python,golang,fortran;
    RadioButton yesProg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nameTextInput);
        apellido = (EditText) findViewById(R.id.lastNameTextInput);
        sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        programmer = (RadioGroup) findViewById(R.id.radioGroup);
        mandar = (Button) findViewById(R.id.sendButton);
        limpiar = (Button) findViewById(R.id.clearButton);
        c = (CheckBox) findViewById(R.id.cCheckBox);
        csharp = (CheckBox) findViewById(R.id.cSharpCheckBox);
        java = (CheckBox) findViewById(R.id.javaCheckBox);
        python = (CheckBox) findViewById(R.id.pythonCheckBox);
        golang = (CheckBox) findViewById(R.id.golangCheckBox);
        fortran = (CheckBox) findViewById(R.id.checkBox11);
        yesProg = (RadioButton) findViewById(R.id.siRadioButton);

        List<String> genders = new ArrayList<String>();
        genders.add("--Seleccione su sexo--");
        genders.add("Masculino");
        genders.add("Femenino");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(dataAdapter);
        programmer.check(R.id.siRadioButton);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre.setText("");
                apellido.setText("");
                sexSpinner.setSelection(0, true);
                programmer.check(-1);
                c.setChecked(false);
                csharp.setChecked(false);
                java.setChecked(false);
                python.setChecked(false);
                golang.setChecked(false);
                fortran.setChecked(false);
                disableEnableControls(false,findViewById(R.id.programmingLanguageLayout));
            }
        });

        programmer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId()==R.id.noRadioButton)
                    disableEnableControls(false,findViewById(R.id.programmingLanguageLayout));
                else if(radioGroup.getCheckedRadioButtonId()==R.id.siRadioButton)
                    disableEnableControls(true,findViewById(R.id.programmingLanguageLayout));
            }
        });

        mandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error=false;

                if(TextUtils.isEmpty(nombre.getText().toString().trim())) {
                    nombre.setError("Digite su nombre");
                    error=true;
                }

                if(TextUtils.isEmpty(apellido.getText().toString().trim())) {
                    apellido.setError("Digite su apellido");
                    error=true;
                }

                if(sexSpinner.getSelectedItemId()==0){
                    TextView errorText = (TextView)sexSpinner.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Seleccione su genero");
                    error=true;
                }

                if(yesProg.isChecked()){
                    if(!c.isChecked() && !csharp.isChecked() && !java.isChecked() && !golang.isChecked() && !python.isChecked() && !fortran.isChecked()){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                        alertDialogBuilder.setTitle("¿Seguro que le gusta programar?");
                        alertDialogBuilder.setMessage("¿Y en qué programa, mi estimado?");
                        alertDialogBuilder.setCancelable(true);
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        error=true;
                    }
                }

                if(!error)
                    Toast.makeText(view.getContext(),"Todo bien en ese punto",Toast.LENGTH_LONG).show();
                    //A la otra actividad
            }
        });
    }

    private void disableEnableControls(boolean enable, ViewGroup vg){
        for (int i = 0; i < vg.getChildCount(); i++){
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup){
                disableEnableControls(enable, (ViewGroup)child);
            }
        }
    }
}