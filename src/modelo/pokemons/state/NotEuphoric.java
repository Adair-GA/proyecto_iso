package modelo.pokemons.state;

public class NotEuphoric implements IStatus{
    @Override
    public int atkBoost() {
        return 0;
    }

    @Override
    public int defBoost() {
        return 0;
    }

    @Override
    public int level() {
        return 0;
    }
}
