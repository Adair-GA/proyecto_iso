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
            for (int i = 0; i < 3; i++) {
                Pokemon poke = getPokemon(i);
                if (!poke.isFainted()) {
                    BattleDirector.getInstance().setAttacker(1, i);
                    BattleDirector.getInstance().setReceiver(0, randGen.nextInt(3));
                    //sleep for half a second
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Partida.getPartida().endPlayingTurn(this.getId());
            endTurn();
        });

        t.start();
    }
}
