package com.example.lab1_20203521.Beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CiberseguridadQuiz {
    public static List<List<String>> getPreguntas() {
        List<List<String>> preguntas = new ArrayList<>();

        // Formato: enunciado, alt1, alt2, alt3, alt4, correcta
        preguntas.add(List.of(
                "¿Cuál de los siguientes es un ataque de ingeniería social?",
                "Phishing", "DoS", "SQL Injection", "Sniffing",
                "1"
        ));

        preguntas.add(List.of(
                "¿Qué significa la sigla VPN?",
                "Virtual Private Network", "Variable Protection Node", "Verified Public Network", "Virtual Protocol Net",
                "1"
        ));

        preguntas.add(List.of(
                "¿Cuál es el propósito del cifrado?",
                "Aumentar la velocidad de la red", "Ocultar información a usuarios no autorizados", "Reducir el tamaño de archivos", "Corregir errores de transmisión",
                "2"
        ));

        preguntas.add(List.of(
                "¿Qué protocolo se usa para la navegación segura en la web?",
                "HTTP", "HTTPS", "FTP", "SMTP",
                "2"
        ));

        preguntas.add(List.of(
                "¿Qué tipo de malware se disfraza de software legítimo?",
                "Worm", "Trojan", "Spyware", "Ransomware",
                "2"
        ));

        preguntas.add(List.of(
                "¿Qué técnica permite verificar que un mensaje no fue alterado?",
                "Hashing", "Compression", "Fragmentation", "Encapsulation",
                "1"
        ));

        preguntas.add(List.of(
                "¿Qué herramienta se usa comúnmente para el análisis de tráfico de red?",
                "Wireshark", "Photoshop", "AutoCAD", "Excel",
                "1"
        ));

        // Mezclar preguntas
        Collections.shuffle(preguntas);

        return preguntas;
    }
}

