/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.tablero;

import java.util.ArrayList;

/**
 * Representacion de una columna del tablero de juego.
 * @author Mario Codes Sánchez
 * @since 12/12/2016
 */
public class Columna {
    //private Casilla[] casillas = new Casilla[9];
    private ArrayList<Integer> numerosDisponiblesColumna = new ArrayList<Integer>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
        add(8);
        add(9);
    }};
    
//    /**
//     * Constructor a utilizar por defecto para rellenar.
//     * @param casillas Casilla[] que formara la columna.
//     */
//    public Columna(Casilla[] casillas) {
//        //this.casillas = casillas;
//    }
    
    public Columna() {
        //this.casillas = new Casilla[9];
    }

    /**
     * @param numerosDisponiblesColumna the numerosDisponiblesColumna to set
     */
    public void setNumerosDisponiblesColumna(ArrayList<Integer> numerosDisponiblesColumna) {
        this.numerosDisponiblesColumna = numerosDisponiblesColumna;
    }

    /**
     * @return the numerosDisponiblesColumna
     */
    public ArrayList<Integer> getNumerosDisponiblesColumna() {
        return numerosDisponiblesColumna;
    }
}
