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

public class ResultadoMicroondas extends AppCompatActivity {

    TextView txtResultado;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_microondas);

        txtResultado = findViewById(R.id.txtResultado);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener puntaje desde el Intent
        int puntajeFinal = getIntent().getIntExtra("puntajeFinal", 0);

        // Mostrar el puntaje con color
        txtResultado.setText("Puntaje final: " + puntajeFinal);
        if (puntajeFinal > 0) {
            txtResultado.setTextColor(Color.GREEN);
        } else {
            txtResultado.setTextColor(Color.RED);
        }

        // Botón para volver a MainActivity2
        btnVolver.setOnClickListener(v -> {
            String texto = "Julio";
            Intent intent = new Intent(ResultadoMicroondas.this, MainActivity2.class);
            intent.putExtra("texto", texto);
            startActivity(intent);
            finish(); // cerrar resultado
        });
    }
}