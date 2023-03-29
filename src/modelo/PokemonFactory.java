package modelo;

import java.util.Random;

public class PokemonFactory {
    private static PokemonFactory pokemonFactory = null;

    public static PokemonFactory getPokemonFactory() {
        if (pokemonFactory == null) {
            pokemonFactory = new PokemonFactory();
        }
        return pokemonFactory;
    }

    public static Pokemon createPokemon(){
        PokemonTypes type = PokemonTypes.values()[new Random().nextInt(PokemonTypes.values().length)];
        return new Pokemon(type);
    }
}
