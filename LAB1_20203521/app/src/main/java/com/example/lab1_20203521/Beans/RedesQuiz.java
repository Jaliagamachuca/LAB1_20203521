package com.example.lab1_20203521.Beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedesQuiz {
    public static List<List<String>> getPreguntas() {
        List<List<String>> preguntas = new ArrayList<>();

        // Formato: enunciado, alt1, alt2, alt3, alt4, correcta
        preguntas.add(List.of(
                "¿Qué significa la sigla IP en redes?",
                "Internet Protocol", "Internal Process", "Input Port", "Interface Path",
                "1" // correcta: Internet Protocol
        ));

        preguntas.add(List.of(
                "¿Qué capa del modelo OSI se encarga de la entrega de paquetes?",
                "Capa de Aplicación", "Capa de Red", "Capa de Enlace de Datos", "Capa de Transporte",
                "2"
        ));

        preguntas.add(List.of(
                "¿Qué dispositivo conecta diferentes redes entre sí?",
                "Switch", "Router", "Hub", "Access Point",
                "2"
        ));

        preguntas.add(List.of(
                "¿Cuál es la longitud en bits de una dirección IPv4?",
                "32 bits", "64 bits", "128 bits", "16 bits",
                "1"
        ));

        preguntas.add(List.of(
                "¿Qué protocolo se usa para traducir nombres de dominio a direcciones IP?",
                "HTTP", "FTP", "DNS", "SMTP",
                "3"
        ));

        preguntas.add(List.of(
                "¿Cuál es la velocidad típica de Fast Ethernet?",
                "10 Mbps", "100 Mbps", "1 Gbps", "10 Gbps",
                "2"
        ));

        preguntas.add(List.of(
                "¿Qué protocolo garantiza la entrega confiable de datos?",
                "UDP", "TCP", "ICMP", "ARP",
                "2"
        ));

        // Mezclar preguntas
        Collections.shuffle(preguntas);

        return preguntas;
    }
}
