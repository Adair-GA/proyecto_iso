package modelo;

import view.BattleDialog;

import java.util.ArrayList;

public class Partida {
    private static Partida partida = null;
    private ArrayList<Jugador> jugadores;

    private Partida() {
        jugadores = new ArrayList<>();
    }
    public static Partida getPartida() {
        if (partida == null) {
            partida = new Partida();
        }
        return partida;
    }

    public void iniciar(){
        Jugador j1 = new Jugador("Jugador 1");
        Jugador j2 = new Jugador("Jugador 2");
        jugadores.add(j1);
        jugadores.add(j2);


        new BattleDialog().setVisible(true);
        new BattleDialog().setVisible(true);
    }


}
