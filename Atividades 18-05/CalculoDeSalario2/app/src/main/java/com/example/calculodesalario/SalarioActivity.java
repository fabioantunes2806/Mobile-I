package com.example.calculodesalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
import android.view.*;
import android.app.*;

public class SalarioActivity extends AppCompatActivity {

    RadioGroup rgopcoes;
    Button btcalcular;

    EditText edsalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salario);

        edsalario = (EditText) findViewById(R.id.edSalario);
        rgopcoes = (RadioGroup) findViewById(R.id.rgopcoes);
        btcalcular = (Button) findViewById(R.id.btcalcular);

        btcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                double salario = Double.parseDouble(edsalario.getText().toString());

                int op = rgopcoes.getCheckedRadioButtonId();

                double novo_salario = 0;

                if (op ==  R.id.rb40){
                    novo_salario = salario + (salario * 0.4);
                }else if (op ==  R.id.rb45){
                    novo_salario = salario + (salario * 0.45);
                }else{
                    novo_salario = salario + (salario * 0.50);
                }

                AlertDialog.Builder dialogo = new AlertDialog.Builder(SalarioActivity.this);

                dialogo.setTitle("Novo salário");
                dialogo.setMessage("Seu salário novo é de: R$" + String.valueOf(novo_salario));

                dialogo.setNeutralButton("OK", null);
                dialogo.show();
            }
        });


    }
}