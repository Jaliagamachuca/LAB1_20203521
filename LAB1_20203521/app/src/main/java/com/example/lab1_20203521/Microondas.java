package com.example.lab1_20203521;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab1_20203521.Beans.MicroondasQuiz;

import java.util.Arrays;
import java.util.List;

public class Microondas extends AppCompatActivity {

    TextView txtTitulo, txtPreguntaNumero, txtPregunta, txtPuntajePregunta;
    RadioGroup radioGroup;
    RadioButton opcion1, opcion2, opcion3, opcion4;
    Button btnAnterior, btnSiguiente;

    int preguntaActual = 0;
    int puntajeTotal = 0;
    boolean finalizado = false;

    // Tus 7 preguntas (mezcladas desde MicroondasQuiz)
    List<List<String>> preguntas = MicroondasQuiz.getPreguntas();

    // Estado
    int[] respuestasUsuario;   // 1..4, -1 si sin responder
    int[] puntajesPregunta;    // puntaje obtenido en cada pregunta (con racha aplicada)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microondas);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtPreguntaNumero = findViewById(R.id.txtPreguntaNumero);
        txtPregunta = findViewById(R.id.txtPregunta);
        txtPuntajePregunta = findViewById(R.id.txtPuntajePregunta);
        radioGroup = findViewById(R.id.radioGroupOpciones);
        opcion1 = findViewById(R.id.opcion1);
        opcion2 = findViewById(R.id.opcion2);
        opcion3 = findViewById(R.id.opcion3);
        opcion4 = findViewById(R.id.opcion4);
        btnAnterior = findViewById(R.id.btnAnterior);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        respuestasUsuario = new int[preguntas.size()];
        Arrays.fill(respuestasUsuario, -1);
        puntajesPregunta = new int[preguntas.size()];

        mostrarPregunta();

        btnSiguiente.setOnClickListener(v -> {
            if (!finalizado) {
                registrarRespuestaSiSelecciono();
                if (preguntaActual < preguntas.size() - 1) {
                    preguntaActual++;
                    mostrarPregunta();
                } else {
                    finalizado = true;
                    mostrarResultadoFinal();
                }
            }
        });

        btnAnterior.setOnClickListener(v -> {
            registrarRespuestaSiSelecciono(); // guarda cambios antes de retroceder
            if (preguntaActual > 0) {
                preguntaActual--;
                mostrarPregunta();
            }
        });
    }

    private void mostrarPregunta() {
        radioGroup.clearCheck();

        List<String> q = preguntas.get(preguntaActual);

        txtPreguntaNumero.setText("Pregunta " + (preguntaActual + 1));
        txtPregunta.setText(q.get(0));
        opcion1.setText(q.get(1));
        opcion2.setText(q.get(2));
        opcion3.setText(q.get(3));
        opcion4.setText(q.get(4));

        // Si ya respondió antes → re-marcar y mostrar puntaje/colores
        if (respuestasUsuario[preguntaActual] != -1) {
            int opcion = respuestasUsuario[preguntaActual];
            if (opcion == 1) opcion1.setChecked(true);
            if (opcion == 2) opcion2.setChecked(true);
            if (opcion == 3) opcion3.setChecked(true);
            if (opcion == 4) opcion4.setChecked(true);
        }

        // Mostrar el puntaje de esta pregunta y color (si ya existe)
        actualizarUIdePuntajePregunta();
    }

    private void registrarRespuestaSiSelecciono() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) return; // no seleccionó nada

        int respuestaUsuario = -1;
        if (selectedId == opcion1.getId()) respuestaUsuario = 1;
        else if (selectedId == opcion2.getId()) respuestaUsuario = 2;
        else if (selectedId == opcion3.getId()) respuestaUsuario = 3;
        else if (selectedId == opcion4.getId()) respuestaUsuario = 4;

        // Guardar la respuesta del usuario para esta pregunta
        respuestasUsuario[preguntaActual] = respuestaUsuario;

        // Recalcular TODOS los puntajes con la regla de rachas (orden 1→7)
        recalcularPuntajesYTotal();

        // Refrescar UI para esta pregunta
        actualizarUIdePuntajePregunta();
    }

    /**
     * Regla de rachas:
     * - Correcta: +2, siguiente correcta +4, luego +8, ...
     * - Incorrecta: -3, siguiente incorrecta -6, luego -12, ...
     * - Cambiar de tipo reinicia a +2 o -3.
     * - Una pregunta sin responder corta la racha.
     */
    private void recalcularPuntajesYTotal() {
        puntajeTotal = 0;

        int rachaTipo = 0; // 0 ninguna, 1 correctas, -1 incorrectas
        int factorCorrecta = 1;   // multiplica a +2
        int factorIncorrecta = 1; // multiplica a -3

        for (int i = 0; i < preguntas.size(); i++) {
            int resp = respuestasUsuario[i];

            if (resp == -1) {
                // Sin responder: corta racha y puntaje 0
                puntajesPregunta[i] = 0;
                rachaTipo = 0;
                factorCorrecta = 1;
                factorIncorrecta = 1;
                continue;
            }

            int correcta = Integer.parseInt(preguntas.get(i).get(5));
            boolean esCorrecta = (resp == correcta);

            if (rachaTipo == 0) {
                // Primera de una racha
                if (esCorrecta) {
                    puntajesPregunta[i] = 2 * factorCorrecta; // 2
                    rachaTipo = 1;
                    factorCorrecta *= 2;      // siguiente correcta será 4
                    factorIncorrecta = 1;     // reinicia incorrecta
                } else {
                    puntajesPregunta[i] = -3 * factorIncorrecta; // -3
                    rachaTipo = -1;
                    factorIncorrecta *= 2;    // siguiente incorrecta será -6
                    factorCorrecta = 1;       // reinicia correcta
                }
            } else if (rachaTipo == 1) {
                if (esCorrecta) {
                    puntajesPregunta[i] = 2 * factorCorrecta; // 4, 8, 16, ...
                    factorCorrecta *= 2;
                } else {
                    // cambio de tipo: reinicia a -3
                    factorIncorrecta = 1;
                    puntajesPregunta[i] = -3 * factorIncorrecta; // -3
                    rachaTipo = -1;
                    factorIncorrecta *= 2; // próxima incorrecta -6
                    factorCorrecta = 1;    // reinicia correcta
                }
            } else { // rachaTipo == -1
                if (esCorrecta) {
                    // cambio de tipo: reinicia a +2
                    factorCorrecta = 1;
                    puntajesPregunta[i] = 2 * factorCorrecta; // +2
                    rachaTipo = 1;
                    factorCorrecta *= 2;  // próxima correcta +4
                    factorIncorrecta = 1; // reinicia incorrecta
                } else {
                    puntajesPregunta[i] = -3 * factorIncorrecta; // -6, -12, ...
                    factorIncorrecta *= 2;
                }
            }

            puntajeTotal += puntajesPregunta[i];
        }
    }

    private void actualizarUIdePuntajePregunta() {
        int p = puntajesPregunta[preguntaActual];
        txtPuntajePregunta.setText("Puntaje: " + p);
        if (p > 0) {
            txtPuntajePregunta.setTextColor(Color.GREEN);
        } else if (p < 0) {
            txtPuntajePregunta.setTextColor(Color.RED);
        } else {
            txtPuntajePregunta.setTextColor(Color.BLACK);
        }
    }

    private void mostrarResultadoFinal() {
        // Por si el usuario terminó sin marcar la última, recalculamos
        recalcularPuntajesYTotal();

        Intent intent = new Intent(Microondas.this, ResultadoMicroondas.class);
        intent.putExtra("puntajeFinal", puntajeTotal);
        startActivity(intent);
        finish(); // opcional: para que no se pueda volver atrás
    }
}




