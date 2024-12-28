/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica_poryecto;

/**
 *
 * @author CltControl
 */
public class Practica_Poryecto {

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        Nodo_Tablero nodoRaiz = new Nodo_Tablero(tablero);
        Scanner scanner = new Scanner(System.in);

        // Turno inicial de la computadora
        System.out.println("La computadora comienza el juego...");
        Integer mejorMovimiento = Minimax.findBestMove(nodoRaiz);
        if (mejorMovimiento != null) {
            tablero.setCasilla(mejorMovimiento / 3, mejorMovimiento % 3, tablero.getTurno());
            tablero.cambiarTurno();
        }

        while (!tablero.verificarGanador() && !tablero.esEmpate()) {
            // Mostrar el tablero actual
            System.out.println("Estado actual del tablero:");
            tablero.mostrar();

            // Turno del jugador humano
            System.out.print("Ingrese su movimiento (0-8): ");
            int movimiento = scanner.nextInt();

            // Validar el movimiento
            if (movimiento < 0 || movimiento > 8 || tablero.getCasilla(movimiento / 3, movimiento % 3) != ' ') {
                System.out.println("Movimiento inválido. Intente de nuevo.");
                continue; // Volver a pedir el movimiento
            }

            // Realizar el movimiento del jugador
            tablero.setCasilla(movimiento / 3, movimiento % 3, tablero.getTurno());
            tablero.cambiarTurno();

            // Verificar si el jugador ha ganado o si hay un empate
            if (tablero.verificarGanador()) {
                System.out.println("¡El jugador " + tablero.getTurno() + " ha ganado!");
                break;
            }
            if (tablero.esEmpate()) {
                System.out.println("¡Es un empate!");
                break;
            }

            // Turno de la computadora
            System.out.println("Turno de la computadora...");
            mejorMovimiento = Minimax.findBestMove(nodoRaiz);
            if (mejorMovimiento != null) {
                tablero.setCasilla(mejorMovimiento / 3, mejorMovimiento % 3, tablero.getTurno());
                tablero.cambiarTurno();
            }

            // Verificar si la computadora ha ganado
            if (tablero.verificarGanador()) {
                System.out.println("¡La computadora ha ganado!");
                break;
            }
            if (tablero.esEmpate()) {
                System.out.println("¡Es un empate!");
                break;
            }

            // Actualizar el nodo raíz para el siguiente ciclo
            nodoRaiz.generarPosiblesEstados(); // Generar nuevos estados para el siguiente turno
        }

        // Mostrar el tablero final
        System.out.println("Estado final del tablero:");
        tablero.mostrar();
        scanner.close();
    }
}
