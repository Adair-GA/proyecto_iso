package modelo;

import modelo.pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

public class Partida extends Observable {
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
        Jugador j1 = new Jugador(0);
        Jugador j2 = new BotPlayer(1);
        jugadores.add(j1);
        jugadores.add(j2);
        for (Jugador j: jugadores) {
            for (int i = 0; i < 3; i++) {
                j.addPokemon(PokemonFactory.createPokemon());
            }
        }
        jugadores.forEach(Jugador::updateAllPokemons);
        Random randGen = new Random();
        int randomPlayer = randGen.nextInt(2);
        jugadores.get(randomPlayer).startTurn();
        this.setChanged();
        this.notifyObservers();
    }

    public Pokemon createAndAddPokemon(){
        Pokemon poke = PokemonFactory.createPokemon();
        Iterator<Jugador> it = getIterador();
        Jugador j;
        while (it.hasNext()){
            j = it.next();
            if (j.pokemonCount()<3){
                j.addPokemon(poke);
                return poke;
            }
        }
        return null;
    }

    public void endPlayingTurn(int trainerID){
        //A turn has ended, so we should give turn to a random player
        BattleDirector.getInstance().reset();
        jugadores.forEach(Jugador::resetAttackOfAll);
        jugadores.get(trainerID).endTurn();

        Iterator<Jugador> it = getIterador();
        while (it.hasNext()){
            if (it.next().allFainted()){
                //todo
                System.out.println("Game Over");
                System.exit(0);
            }
        }
        Random randGen = new Random();
        int randomPlayer = randGen.nextInt(2);
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

    public boolean hasTurn(int id){
        return jugadores.get(id).hasTurn();
    }

    public void update(){
        jugadores.forEach(Jugador::update);
        jugadores.forEach(Jugador::updateAllPokemons);
    }

}
