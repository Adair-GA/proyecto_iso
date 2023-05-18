package modelo;

import modelo.pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Partida extends Observable {
    private static Partida partida = null;
    private ArrayList<Jugador> jugadores;
    private int cantPokemon;

    private Partida() {
        jugadores = new ArrayList<>();
    }
    public static Partida getPartida() {
        if (partida == null) {
            partida = new Partida();
        }
        return partida;
    }

    public void iniciar(int playerCount, int botCount, int pokemonCount, int botSleepTime){
        Jugador nuevo;
        for (int i = 0; i < playerCount; i++) {
            nuevo = new Jugador(i);
            jugadores.add(nuevo);
        }
        for (int i = 0; i < botCount; i++) {
            nuevo = new BotPlayer(i + playerCount, botSleepTime);
            jugadores.add(nuevo);
        }
        for (Jugador j: jugadores) {
            for (int i = 0; i < pokemonCount; i++) {
                j.addPokemon(PokemonFactory.createPokemon());
            }
        }
        jugadores.forEach(Jugador::updateAllPokemons);
        Random randGen = new Random();
        int randomPlayer = randGen.nextInt(jugadores.size());
        jugadores.get(randomPlayer).startTurn();
        this.setChanged();
        this.notifyObservers();
    }

    public void endPlayingTurn(int trainerID){
        BattleDirector.getInstance().reset();
        jugadores.forEach(Jugador::resetAttackOfAll);
        jugadores.get(trainerID).endTurn();

        Iterator<Jugador> it = getIterador();
        int loser = 0;
        while (it.hasNext()){
            Jugador j = it.next();
            if (j.allFainted()) {
                loser++;
            }
            }
        if (loser == getPlayerCount() - 1){
            jugadores.forEach(jugador -> {
                if (!jugador.allFainted()){
                    jugador.win();
                }
                else {
                    jugador.lose();
                }
            });
            System.exit(0);
        }
        Random randGen = new Random();
        int randomPlayer = randGen.nextInt(getPlayerCount());
        jugadores.get(randomPlayer).startTurn();
        jugadores.forEach(Jugador::update);
    }

    private Iterator<Jugador> getIterador(){
        return jugadores.iterator();
    }

    public Pokemon getPokemon(int trainerID, int pokemonID){
        return jugadores.get(trainerID).getPokemon(pokemonID);
    }

    public Jugador getPlayer(int id) {
        return jugadores.get(id);
    }

    public boolean isDefeated(int id){
        return jugadores.get(id).allFainted();
    }

    public boolean hasTurn(int id){
        return jugadores.get(id).hasTurn();
    }

    public void update(){
        jugadores.forEach(Jugador::update);
        jugadores.forEach(Jugador::updateAllPokemons);
    }

    public int getPlayerCount(){
        return jugadores.size();
    }
}
