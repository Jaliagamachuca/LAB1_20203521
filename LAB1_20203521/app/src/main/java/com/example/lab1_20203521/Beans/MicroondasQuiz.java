package com.example.lab1_20203521.Beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MicroondasQuiz {

    // Método que retorna 7 listas con las preguntas y opciones
    public static List<List<String>> getPreguntas() {
        List<List<String>> preguntas = new ArrayList<>();

        // Definimos las preguntas (cada una con 6 elementos)
        preguntas.add(Arrays.asList(
                "¿En qué rango de frecuencias trabajan típicamente las microondas?",
                "300 MHz – 3 GHz",
                "3 GHz – 30 GHz",
                "30 GHz – 300 GHz",
                "30 MHz – 300 MHz",
                "2" // correcta: opción 2
        ));

        preguntas.add(Arrays.asList(
                "¿Cuál es la principal aplicación de los enlaces de microondas en telecomunicaciones?",
                "Transmisión satelital",
                "Redes de fibra óptica",
                "Enlaces punto a punto terrestres",
                "Telefonía fija",
                "3" // correcta: opción 3
        ));

        preguntas.add(Arrays.asList(
                "¿Qué tipo de antena es más común en sistemas de microondas?",
                "Dipolo",
                "Yagi-Uda",
                "Parabólica",
                "Logarítmica",
                "3" // correcta: opción 3
        ));

        preguntas.add(Arrays.asList(
                "¿Qué fenómeno afecta más a las microondas en propagación atmosférica?",
                "Difracción",
                "Reflexión",
                "Dispersión",
                "Atenuación por lluvia",
                "4" // correcta: opción 4
        ));

        preguntas.add(Arrays.asList(
                "¿Qué técnica se usa para aumentar la capacidad en enlaces de microondas?",
                "Multiplexación por división de tiempo (TDM)",
                "Modulación AM",
                "Ondas portadoras múltiples",
                "Señales analógicas",
                "1" // correcta: opción 1
        ));

        preguntas.add(Arrays.asList(
                "¿Qué ventaja principal tienen los sistemas de microondas frente a la fibra óptica?",
                "Mayor velocidad de transmisión",
                "Instalación más rápida y flexible",
                "Menor latencia",
                "Mayor ancho de banda",
                "2" // correcta: opción 2
        ));

        preguntas.add(Arrays.asList(
                "¿Cuál es la polarización más usada en antenas de microondas?",
                "Circular",
                "Vertical",
                "Horizontal",
                "Doble polarización",
                "4" // correcta: opción 4
        ));

        // Mezclamos el orden de las preguntas
        Collections.shuffle(preguntas);

        return preguntas;
    }

}
