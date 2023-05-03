package modelo;

import modelo.pokemons.*;

import java.util.Random;

public class PokemonFactory {
    public static Pokemon createPokemon(){
        PokemonTypes type = PokemonTypes.values()[new Random().nextInt(PokemonTypes.values().length)];
        return switch (type) {
            case FUEGO -> new FirePokemon();
            case PLANTA -> new GrassPokemon();
            case ELECTRICO -> new ElectricPokemon();
            case AGUA -> new WaterPokemon();
        };
    }
}
