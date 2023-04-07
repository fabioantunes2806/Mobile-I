package com.example.agendaordorealitas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicio = findViewById(R.id.btn_cadastro);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abreSegundaTela();
            }
        });
    }

    private void abreSegundaTela(){

        Intent it = new Intent(this, Missao.class);
        startActivityForResult(it, 1);
    }
}
