package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double vnum1,vnum2,vresul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button soma=(Button) findViewById(R.id.soma);
        Button sub=(Button) findViewById(R.id.sub);
        Button multi=(Button) findViewById(R.id.multi);
        Button div=(Button) findViewById(R.id.div);
        Button clear=(Button) findViewById(R.id.clear);

        EditText valor1=(EditText) findViewById(R.id.valor1);
        EditText valor2=(EditText) findViewById(R.id.valor2);

        TextView resultado=(TextView) findViewById(R.id.resultado);

        // Rotina soma
        soma.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                vnum1=Double.parseDouble(valor1.getText().toString());
                vnum2=Double.parseDouble(valor2.getText().toString());
                vresul=vnum1 + vnum2;
                resultado.setText(String.valueOf(vresul));
            }
        });

        //Rotina Subtrair
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vnum1 = Double.parseDouble(valor1.getText().toString());
                vnum2 = Double.parseDouble(valor2.getText().toString());
                vresul = vnum1 - vnum2;
                resultado.setText(String.valueOf(vresul));
            }
        });

        //Rotina Multiplicar
        multi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vnum1 = Double.parseDouble(valor1.getText().toString());
                vnum2 = Double.parseDouble(valor2.getText().toString());
                vresul = vnum1 * vnum2;
                resultado.setText(String.valueOf(vresul));
            }
        });

        //Rotina Dividir
        div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vnum1 = Double.parseDouble(valor1.getText().toString());
                vnum2 = Double.parseDouble(valor2.getText().toString());
                vresul = vnum1 / vnum2;
                resultado.setText(String.valueOf(vresul));
            }
        });

        //Rotina Limpar
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                valor1.setText("0");
                valor2.setText("0");
                resultado.setText("");
            }
            });
        }

    }