package modelo;

import modelo.pokemons.Pokemon;

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
        pokemon.receiveDamage(this.attacker);
        if (attacker.isCharged()){
            attacker.discharge();
        }
        this.attacker = null;
    }


    public void reset() {
        this.attacker = null;
    }
}
