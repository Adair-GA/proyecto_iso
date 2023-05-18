package modelo.pokemons.state;

public class Evol3 implements IStatus{
    @Override
    public int atkBoost() {
        return 7;
    }

    @Override
    public int defBoost() {
        return 5;
    }

    @Override
    public int level() {
        return 2;
    }
}
