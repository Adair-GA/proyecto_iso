package modelo;

import modelo.pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Observable;

public class Jugador extends Observable {
    private ArrayList<Pokemon> pokemons;
    private int id;
    private boolean hasTurn;

    public Jugador(int trainerID) {
        this.id  = trainerID;
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public int pokemonCount(){
        return pokemons.size();
    }

    public void updateAllPokemons(){
        pokemons.forEach(Pokemon::update);
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    public boolean allFainted() {
        return pokemons.stream().allMatch(Pokemon::isFainted);
    }

    public void startTurn(){
        hasTurn = true;
    }
    public void endTurn(){
        hasTurn = false;
    }
    public boolean hasTurn(){
        return hasTurn;
    }

    public void update() {
        setChanged();
        boolean[] data = {hasTurn, false, false};
        notifyObservers(data);
    }
    public int getId() {
        return id;
    }

    public void win(){
        setChanged();
        boolean[] data = {false, true, true};
        notifyObservers(data);
    }

    public void lose(){
        setChanged();
        boolean[] data = {false, true, false};
        notifyObservers(data);
    }

    public void resetAttackOfAll(){
        pokemons.forEach(Pokemon::resetHasAttacked);
        updateAllPokemons();
    }

}
