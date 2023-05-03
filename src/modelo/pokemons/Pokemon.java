package modelo.pokemons;

import modelo.PokemonTypes;

import java.util.Observable;
import java.util.Random;

public abstract class Pokemon extends Observable {
    private int hp;
    private final int maxHp;
    private int atk;
    private int atkbst = 0;
    private int def;
    private int defbst = 0;
    private int charge;
    private final int chargeMax;
    private boolean fainted;
    private PokemonTypes type;
    private boolean hasAttacked;
    private boolean isCharged;
    private int evolutionLevel;

    public Pokemon() {
        Random randGen = new Random();

        this.atk = 11 + randGen.nextInt(7) + 1;
        this.def = 3 + randGen.nextInt(3) + 1;
        this.hp = 200 + randGen.nextInt(21) + 1;
        this.maxHp = this.hp;
        this.chargeMax = randGen.nextInt(3) + 4;
        this.charge = 0;
        this.fainted = false;
        this.hasAttacked = false;
        this.isCharged = false;
        this.evolutionLevel = 0;
    }

    protected void setType(PokemonTypes type) {
        this.type = type;
    }

    public void receiveDamage(Pokemon other) {
        int damage = calculateReceivedDamage(other);
        this.hp -= damage;
        if (this.hp <= 0) {
            this.fainted = true;
        }
        updateStatus();
        update();
    }

    protected int calculateReceivedDamage(Pokemon other){
        int damage = other.getAttack() - this.getDefense();
        if (damage < 0) {
            damage = 0;
        }
        return damage;
    }

    public void update(){
        setChanged();
        Object[] data = {hp,maxHp,atk,atkbst,def,defbst,charge,chargeMax,fainted,type.name(),hasAttacked,isCharged,evolutionLevel};
        notifyObservers(data);
    }


    private void updateStatus(){
        if (!isCharged) {
            charge++;
            if (charge == chargeMax) {
                charged();
            }
        }
        evolve();
    }

    private void evolve() {
        if (this.evolutionLevel == 0 && this.hp < this.maxHp * 0.5) {
            evolve1();
        } else if (this.evolutionLevel != 2 && this.hp < this.maxHp * 0.25) {
            evolve2();
        }
    }

    private void evolve2() {
        this.evolutionLevel = 2;
        this.atkbst += 2;
        this.defbst += 2;
    }

    private void evolve1() {
        this.evolutionLevel = 1;
        this.atkbst += 5;
        this.defbst += 3;
    }

    public void discharge() {
        this.charge = 0;
        this.atkbst -= 100;
        this.defbst -= 100;
        this.isCharged = false;
    }

    private void charged() {
        this.isCharged = true;
        this.atkbst += 100;
        this.defbst += 100;
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
    public PokemonTypes getType() {
        return this.type;
    }

    public boolean isCharged() {
        return this.isCharged;
    }
}
