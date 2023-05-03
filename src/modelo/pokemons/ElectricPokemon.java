package modelo.pokemons;

import modelo.PokemonTypes;

public class ElectricPokemon extends Pokemon{
    public ElectricPokemon() {
        super();
        setType(PokemonTypes.ELECTRICO);
    }

    @Override
    protected int calculateReceivedDamage(Pokemon other) {
        int damage = super.calculateReceivedDamage(other);
        if (other.getType() == PokemonTypes.AGUA) {
            damage /= 2;
        }
        return damage;
    }
}
