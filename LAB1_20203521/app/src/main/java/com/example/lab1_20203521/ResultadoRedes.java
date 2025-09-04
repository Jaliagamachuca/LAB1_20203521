package com.example.lab1_20203521;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoRedes extends AppCompatActivity {

    TextView txtResultado;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_redes);

        txtResultado = findViewById(R.id.txtResultado);
        btnVolver = findViewById(R.id.btnVolver);

        int puntaje = getIntent().getIntExtra("puntajeFinal", 0);
        txtResultado.setText("Puntaje Final: " + puntaje);

        if (puntaje >= 0) {
            txtResultado.setTextColor(Color.GREEN);
        } else {
            txtResultado.setTextColor(Color.RED);
        }

        btnVolver.setOnClickListener(v -> {
            String texto = "Julio";
            Intent intent = new Intent(ResultadoRedes.this, MainActivity2.class);
            intent.putExtra("texto", texto);
            startActivity(intent);
            finish(); // cerrar resultado
        });
    }
}