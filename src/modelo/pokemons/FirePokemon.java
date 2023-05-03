package modelo.pokemons;

import modelo.PokemonTypes;

public class FirePokemon extends Pokemon{
    public FirePokemon() {
        super();
        setType(PokemonTypes.FUEGO);
    }

    @Override
    protected int calculateReceivedDamage(Pokemon other) {
        int damage = super.calculateReceivedDamage(other);
        if (other.getType() == PokemonTypes.PLANTA) {
            damage /= 2;
        } else if (other.getType() == PokemonTypes.AGUA) {
            damage *= 2;
        }
        return damage;
    }
}
