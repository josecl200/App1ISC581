package edu.pucmm.isc581.josecl200;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
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
                if(radioGroup.getCheckedRadioButtonId()==R.id.siRadioButton)
                    disableEnableControls(true,findViewById(R.id.programmingLanguageLayout));
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