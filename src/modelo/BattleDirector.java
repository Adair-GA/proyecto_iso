package modelo;

public class BattleDirector {
    private static BattleDirector instance = null;
    private Pokemon attacker;
    private BattleDirector() {
    }
    public static BattleDirector getInstance() {
        if (instance == null) {
            instance = new BattleDirector();
        }
        return instance;
    }

    public void setAttacker(int tID, int pID) {
        Pokemon pokemon = Partida.getPartida().getPokemon(tID, pID);
        if (!Partida.getPartida().hasTurn(tID)){return;}
        this.attacker = pokemon;
        pokemon.setHasAttacked();
        pokemon.update();
        System.out.println("Atacante: " + tID + " " + pID);
    }

    public void setReceiver(int tID, int pID) {

        if (this.attacker == null) {
            System.out.println("No hay atacante");
            return;
        }

        Pokemon pokemon = Partida.getPartida().getPokemon(tID, pID);
        System.out.println("Defensor: " + tID + " " + pID);
        pokemon.receiveDamage(calculateDamage(this.attacker, pokemon));
        this.attacker = null;
    }

    private int calculateDamage(Pokemon attacker, Pokemon defender) {
        //TODO hacerlas bien
        return attacker.getAttack() - defender.getDefense();
    }

    public void reset() {
        this.attacker = null;
    }
}
