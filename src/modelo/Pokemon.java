package modelo;

import java.util.Observable;
import java.util.Random;

public class Pokemon extends Observable {
    private int hp;
    private final int maxHp;
    private int atk;
    private int atkbst = 0;
    private int def;
    private int defbst = 0;
    private int charge;
    private final int chargeMax;
    private boolean fainted;
    private final PokemonTypes type;
    private boolean hasAttacked;

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
        this.hasAttacked = false;
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
        update();
    }

    public void update(){
        setChanged();
        Object[] data = {hp,maxHp,atk,atkbst,def,defbst,charge,chargeMax,fainted,type.name(),hasAttacked};
        notifyObservers(data);
    }


    public int getAttack() {
        return this.atk + this.atkbst;
    }

    public int getDefense() {
        return this.def + this.defbst;
    }

    public boolean isFainted() {
        return this.fainted;
    }
    public void setHasAttacked() {
        this.hasAttacked = true;
    }
    public void resetHasAttacked() {
        this.hasAttacked = false;
    }
    public boolean hasAttacked() {
        return this.hasAttacked;
    }
}
