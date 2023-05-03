package modelo.pokemons;

import modelo.PokemonTypes;

public class WaterPokemon extends Pokemon{
    public WaterPokemon() {
        super();
        setType(PokemonTypes.AGUA);
    }

    @Override
    protected int calculateReceivedDamage(Pokemon other) {
        int damage = super.calculateReceivedDamage(other);
        if (other.getType() == PokemonTypes.FUEGO) {
            damage /= 2;
        } else if (other.getType() == PokemonTypes.PLANTA) {
            damage *= 2;
        }
        return damage;
    }
}
