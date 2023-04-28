package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInicio = findViewById(R.id.btnCadastrar);
        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abreSegundaTela();

            }
        });

    }

    private void abreSegundaTela(){
        Intent it = new Intent(this, Cadastro.class);

        startActivityForResult(it,1);

    }
}