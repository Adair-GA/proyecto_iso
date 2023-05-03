package view;

import modelo.BattleDirector;
import modelo.Partida;
import modelo.ResourceManager;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class PokemonPanel extends JPanel implements Observer {
	private final String info = """
			attack: %d + %d
			defense: %d + %d
			health: %d/%d
			type: %s
			""";

	private final int trainerID;
	private final int pokemonID;
	private JTextArea infoTextArea;
	private JLabel pokemonIcon;
	private final JPanel panel;
	private final JProgressBar life;
	private final JProgressBar euphoricMeter;
	private boolean hasAttacked = false;
	private int evol;


	/**
	 * Create the panel.
	 */
	public PokemonPanel(int trainerID, int pokeId) {
		this.trainerID = trainerID;
		pokemonID = pokeId;
		setBackground(new Color(255, 255, 255));
		setLayout(new GridLayout(0, 1, 0, 0));

		infoTextArea = new JTextArea();
		infoTextArea.setText(info);
		infoTextArea.setEditable(false);
		add(infoTextArea);

		pokemonIcon = new JLabel("");

		pokemonIcon.addMouseListener(new Controlador());
		add(pokemonIcon);

		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		life = new JProgressBar();
		life.setStringPainted(true);
		life.setString("LIFE");
		life.setForeground(new Color(0, 255, 0, 178));
		panel.add(life);

		euphoricMeter = new JProgressBar();
		euphoricMeter.setStringPainted(true);
		euphoricMeter.setPreferredSize(new Dimension(146, 5));
		euphoricMeter.setForeground(new Color(234, 0, 215, 178));
		euphoricMeter.setString("Euphoric");
		panel.add(euphoricMeter);

		this.evol = 1;
	}

	private class PokemonUpdateData{
		int hp;
		int maxHp;
		int atk;
		int atkbst;
		int def;
		int defbst;
		int charge;
		int chargeMax;
		boolean fainted;
		String type;
		boolean hasAttacked;
		boolean isCharged;
		int evolutionLevel;

		PokemonUpdateData(Object[] args) {
			hp = (int) args[0];
			maxHp = (int) args[1];
			atk = (int) args[2];
			atkbst = (int) args[3];
			def = (int) args[4];
			defbst = (int) args[5];
			charge = (int) args[6];
			chargeMax = (int) args[7];
			fainted = (boolean) args[8];
			type = (String) args[9];
			hasAttacked = (boolean) args[10];
			isCharged = (boolean) args[11];
			evolutionLevel = (int) args[12];
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		PokemonUpdateData data = new PokemonUpdateData((Object[]) arg);
		String infoFormatted = info.formatted(data.atk, data.atkbst, data.def, data.defbst, data.hp, data.maxHp, data.type);
		infoTextArea.setText(infoFormatted);
		life.setMaximum(data.maxHp);
		life.setValue(data.hp);
		euphoricMeter.setValue(data.charge);
		euphoricMeter.setMaximum(data.chargeMax);
		this.hasAttacked = data.hasAttacked;
		this.evol = data.evolutionLevel + 1;
		pokemonIcon.setIcon(ResourceManager.getInstance().getSprite(data.type, evol));
		if (data.fainted) {
			pokemonIcon.setIcon(ResourceManager.getInstance().getGrave());
		}
		pokemonIcon.setEnabled(!data.hasAttacked);
	}

	class Controlador implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (hasAttacked){return;}
			if (Partida.getPartida().hasTurn(trainerID)) {
				BattleDirector.getInstance().setAttacker(trainerID, pokemonID);
			}
			else {
				BattleDirector.getInstance().setReceiver(trainerID, pokemonID);
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
}
