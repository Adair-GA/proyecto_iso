package modelo.pokemons;

import modelo.PokemonTypes;

public class GrassPokemon extends Pokemon{
    public GrassPokemon() {
        super();
        setType(PokemonTypes.PLANTA);
    }

    @Override
    protected int calculateReceivedDamage(Pokemon other) {
        int damage = super.calculateReceivedDamage(other);
        if (other.getType() == PokemonTypes.AGUA) {
            damage /= 2;
        } else if (other.getType() == PokemonTypes.FUEGO) {
            damage *= 2;
        }
        return damage;
    }
}
