package modelo;

import java.util.ArrayList;
import java.util.Observable;

public class Jugador extends Observable {
    private ArrayList<Pokemon> pokemons;
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }



}
