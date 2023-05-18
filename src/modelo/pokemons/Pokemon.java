package modelo.pokemons;

import modelo.PokemonTypes;
import modelo.pokemons.state.*;

import java.util.Observable;
import java.util.Random;

public abstract class Pokemon extends Observable {
    private int hp;
    private final int maxHp;
    private final int atk;
    private final int def;
    private int charge;
    private final int chargeMax;
    private boolean fainted;
    private PokemonTypes type;
    private boolean hasAttacked;
    private IStatus isCharged;
    private IStatus evolutionLevel;

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
        this.isCharged = new NotEuphoric();
        this.evolutionLevel = new Evol1();
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
        int atkbst = this.evolutionLevel.atkBoost() + this.isCharged.atkBoost();
        int defbst = this.evolutionLevel.defBoost() + this.isCharged.defBoost();
        boolean chargeForUpdate = this.isCharged instanceof Euphoric;
        int evol = this.evolutionLevel.level();
        Object[] data = {hp,maxHp,atk,atkbst,def,defbst,charge,chargeMax,fainted,type.name(),hasAttacked,chargeForUpdate,evol};
        notifyObservers(data);
    }


    private void updateStatus(){
        if (isCharged instanceof NotEuphoric) {
            charge++;
            if (charge == chargeMax) {
                charged();
            }
        }
        evolve();
    }

    private void evolve() {
        if (this.evolutionLevel instanceof Evol1 && this.hp < this.maxHp * 0.5) {
            this.evolutionLevel = new Evol2();
        } else if (!(this.evolutionLevel instanceof Evol3) && this.hp < this.maxHp * 0.25) {
            this.evolutionLevel = new Evol3();
        }
    }

    public void discharge() {
        this.charge = 0;
        this.isCharged = new NotEuphoric();
    }

    private void charged() {
        this.isCharged = new Euphoric();
    }

    public int getAttack() {
        return this.atk + this.evolutionLevel.atkBoost() + this.isCharged.atkBoost();
    }

    public int getDefense() {
        return this.def + this.evolutionLevel.defBoost() + this.isCharged.defBoost();
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
        return this.isCharged instanceof Euphoric;
    }
}
