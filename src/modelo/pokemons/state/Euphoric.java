package modelo.pokemons.state;

public class Euphoric implements IStatus{
    @Override
    public int atkBoost() {
        return 100;
    }

    @Override
    public int defBoost() {
        return 100;
    }

    @Override
    public int level() {
        return 0;
    }
}
