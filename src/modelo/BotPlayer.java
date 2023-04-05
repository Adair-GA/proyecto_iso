package modelo;

import java.util.Random;

public class BotPlayer extends Jugador {
    public BotPlayer(int id) {
        super(id);
    }

    @Override
    public void startTurn() {
        super.startTurn();

        //create a thread
        Thread t = new Thread(() -> {
            Random randGen = new Random();
            for (int attacker = 0; attacker < 3; attacker++) {
                Pokemon poke = getPokemon(attacker);
                int receiverIndex;
                do{
                    receiverIndex = randGen.nextInt(3);
                }while (Partida.getPartida().getPokemon(0,receiverIndex).isFainted());
                if (!poke.isFainted()) {
                    BattleDirector.getInstance().setAttacker(1, attacker);
                    BattleDirector.getInstance().setReceiver(0, receiverIndex);
                    //sleep for half a second
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Partida.getPartida().endPlayingTurn(this.getId());
        });

        t.start();
    }
}
