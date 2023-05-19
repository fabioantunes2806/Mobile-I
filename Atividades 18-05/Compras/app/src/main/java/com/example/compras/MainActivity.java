package com.example.compras;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.app.*;

public class MainActivity extends AppCompatActivity {

    CheckBox chkarroz, chkleite, chkcarne, chkfeijao;
    Button bttotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkarroz = (CheckBox) findViewById(R.id.chkarroz);
        chkleite = (CheckBox) findViewById(R.id.chkleite);
        chkcarne = (CheckBox) findViewById(R.id.chkcarne);
        chkfeijao = (CheckBox) findViewById(R.id.chkfeijao);

        Button bttotal = (Button) findViewById(R.id.bttotal);
        bttotal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                double total = 0;

                if (chkarroz.isChecked())
                    total += 2.69;

                if (chkleite.isChecked())
                    total += 5.00;

                if (chkcarne.isChecked())
                    total += 9.70;

                if (chkfeijao.isChecked())
                    total += 2.30;

                AlertDialog.Builder dialogo = new AlertDialog.Builder(
                        MainActivity.this);

                dialogo.setTitle("Aviso");
                dialogo.setMessage("Valor total da compra: " +
                        String.valueOf(total));

                dialogo.setNeutralButton("Ok", null);
                dialogo.show();

            }
        });
    }
}