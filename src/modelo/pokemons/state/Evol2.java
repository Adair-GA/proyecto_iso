package modelo.pokemons.state;

public class Evol2 implements IStatus{
    @Override
    public int atkBoost() {
        return 5;
    }

    @Override
    public int defBoost() {
        return 3;
    }

    @Override
    public int level() {
        return 1;
    }
}
