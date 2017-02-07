/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokernetwork;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import juego.Carta;
import juego.Juego;

/**
 * Proyecto Online juego Oscar -> Poker!.
 * @author Mario Codes Sánchez
 * @since 06/02/2017
 */
public class Servidor {
    private static Juego juego;
    private static int numeroJugadores = 0;
    private static int identificadorJugadorActualRonda = 1;
    
    private static final int PUERTO = 8143;
    private static Socket socket = null;
    
    private static InputStream in = null;
    private static OutputStream out = null;
        
    private static ObjectInputStream ois = null;
    private static ObjectOutputStream oos = null;
    
//    private static void cerrarCabecerasConexion() {
//        try {
//            if(oos != null) oos.close();
//            if(ois != null) ois.close();
//            if(out != null) out.close();
//            if(in != null) in.close();
//            if(socket != null) socket.close();
//        }catch(IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    private static void aperturaCabecerasConexion() {
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            oos = new ObjectOutputStream(out);
            ois = new ObjectInputStream(in);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void repartirCartasJugadores() {
        boolean jugadorRepartido = false;
        int jugadoresRepartidosCorrectamente = 0;
        
        while(jugadoresRepartidosCorrectamente <= numeroJugadores) {
            while(!jugadorRepartido) {
                jugadorRepartido = repartirCartasJugador(identificadorJugadorActualRonda);
                identificadorJugadorActualRonda++;
            }
            
            jugadorRepartido = false;
            jugadoresRepartidosCorrectamente++;
        }
        identificadorJugadorActualRonda = 1;
    }
    
    private static boolean repartirCartasJugador(int identificadorJugadorActual) {
        ArrayList<Carta> cartas = juego.repartoManoJugador();
        
        try {
            oos.writeInt(identificadorJugadorActual);
            oos.flush();

            boolean jugadorAdecuado = ois.readBoolean();

            if(!jugadorAdecuado) return false;
            else {
                String carta1 = cartas.get(0).toString();
                String valor1 = carta1.substring(0, 1);
                String palo1 = carta1.substring(2);
                
                oos.writeObject(valor1);
                oos.flush();
                
                oos.writeObject(palo1);
                oos.flush();
                
                String carta2 = cartas.get(1).toString();
                String valor2 = carta2.substring(0, 1);
                String palo2 = carta2.substring(2);
                
                oos.writeObject(valor2);
                oos.flush();
                
                oos.writeObject(palo2);
                oos.flush();
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        
        return true;
    }
    
    private static void gestionAcciones() {
        try {
            aperturaCabecerasConexion();
            
            byte opcion = (byte) ois.readInt();
            System.out.println(opcion);
            switch(opcion) {
                case 0: //Join de un jugador.
                    oos.writeInt(++numeroJugadores);
                    oos.flush();
                    break;
                case 1: //Join del ultimo jugador.
                    oos.writeInt(++numeroJugadores);
                    oos.flush();
                    
                    juego = new Juego(numeroJugadores);
                    juego.comienzoJuego();
                    break;
                case 2: //Reparto cartas cada Jugador.
                    repartirCartasJugadores();
                    break;
                default:
                    System.out.println("Comprobar selector de Acciones.");
                    break;
            }
            
//            cerrarCabecerasConexion();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Ejecucion de la accion del Server.
     */
    public static void ejecucionServidor() {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO); //Espera y escucha la llegada de los clientes. Una vez establecida, devuelve el Socket.; 
        
            while(true) {
                socket = serverSocket.accept(); /* El ServerSocket me da el Socket.
                                                        Bloquea el programa en esta linea y solo avanza cuando un cliente se conecta.*/
                new Thread(() -> gestionAcciones()).start(); //Comienzo de la faena en un Hilo aparte.
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ejecucionServidor();
    }
}
