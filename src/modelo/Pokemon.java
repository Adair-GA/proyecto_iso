package modelo;

import java.util.Observable;
import java.util.Random;

public class Pokemon extends Observable {
    private int hp;
    private final int maxHp;
    private int atk;
    private int def;
    private int charge;
    private final int chargeMax;
    private boolean fainted;
    private final PokemonTypes type;

    public Pokemon(PokemonTypes pType) {
        Random randGen = new Random();

        this.atk = 11 + randGen.nextInt(7) + 1;
        this.def = 3 + randGen.nextInt(3) + 1;
        this.hp = 200 + randGen.nextInt(21) + 1;
        this.maxHp = this.hp;
        this.chargeMax = randGen.nextInt(3) + 4;
        this.charge = 0;
        this.fainted = false;
        this.type = pType;
    }

    public void receiveDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.fainted = true;
        }
        if (this.charge < this.chargeMax) {
            this.charge++;
        } else {
            this.charge = this.chargeMax;
        }
        setChanged();
        notifyObservers();
    }



}
