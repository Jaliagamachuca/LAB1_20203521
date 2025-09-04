package com.example.lab1_20203521;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent1 = getIntent();
        String texto = intent1.getStringExtra("texto");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(texto);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this,Redes.class);
            startActivity(intent);
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this,Ciberseguridad.class);
            startActivity(intent);
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this,Microondas.class);
            startActivity(intent);
        });
    }
}