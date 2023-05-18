package modelo;

import modelo.pokemons.Pokemon;

import javax.sound.midi.Receiver;
import java.util.Random;

public class BotPlayer extends Jugador {
    public BotPlayer(int id) {
        super(id);
    }

    @Override
    public void startTurn() {
        super.startTurn();

        Thread t = new Thread(() -> {
            Random randGen = new Random();
            for (int attacker = 0; attacker < this.pokemonCount(); attacker++) {
                Pokemon poke = getPokemon(attacker);
                int receiverIndex;
                receiverIndex = randGen.nextInt(pokemonCount());
                int receiverTrainer = randGen.nextInt(Partida.getPartida().getPlayerCount());
                while (receiverTrainer == getId() || Partida.getPartida().getPlayer(receiverTrainer).allFainted()) {
                    receiverTrainer = randGen.nextInt(Partida.getPartida().getPlayerCount());
                }
                int i = 0;
                while (Partida.getPartida().getPlayer(receiverTrainer).getPokemon(receiverIndex).isFainted() && i < pokemonCount()) {
                    receiverIndex = receiverIndex + 1 % (pokemonCount() - 1);
                }

                if (!poke.isFainted()) {
                    BattleDirector.getInstance().setAttacker(this.getId(), attacker);
                    BattleDirector.getInstance().setReceiver(receiverTrainer, receiverIndex);
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
