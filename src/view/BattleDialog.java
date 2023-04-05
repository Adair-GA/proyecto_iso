package view;

import modelo.Partida;
import modelo.PokemonFactory;
import modelo.ResourceManager;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class BattleDialog extends JFrame implements Observer {

	private int trainerID;
	private final JPanel contentPanel = new JPanel();
	private JPanel playerPane;
	private JButton endTurn;
	private JLabel trainerIcon;
	private JPanel pokePanelHolder;


	/**
	 * Create the dialog.
	 */
	public BattleDialog(int id) {
		trainerID=id;

		setBounds(100, 100, 650, 450);
		setDefaultCloseOperation(3);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		playerPane = new JPanel();
		playerPane.setBackground(new Color(255, 255, 255));
		contentPanel.add(playerPane, BorderLayout.WEST);
		playerPane.setLayout(new BorderLayout(0, 0));
		endTurn = new JButton("End turn");
		endTurn.addActionListener(new Controlador());
		playerPane.add(endTurn, BorderLayout.NORTH);
		trainerIcon = new JLabel("");
		trainerIcon.setIcon(ResourceManager.getInstance().getRandomTrainer());
		playerPane.add(trainerIcon);
		pokePanelHolder = new JPanel();
		pokePanelHolder.setBackground(new Color(255, 255, 255));
		contentPanel.add(pokePanelHolder, BorderLayout.CENTER);

		if (trainerID==0) {
			setTitle("Player");
		} else {
			setTitle("Bot");
			endTurn.setVisible(false);
		}

		Partida.getPartida().getPlayer(trainerID).addObserver(this);


		for (int i = 0; i < 3; i++) {
			PokemonPanel pokePanel = new PokemonPanel(trainerID,i);
			pokePanelHolder.add(pokePanel);
			Partida.getPartida().createAndAddPokemon().addObserver(pokePanel);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		boolean hasTurn = (boolean) arg;
		if (hasTurn) {
			endTurn.setEnabled(true);
		} else {
			endTurn.setEnabled(false);
		}
	}

	class Controlador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == endTurn) {
				Partida.getPartida().endPlayingTurn(trainerID);
			}
		}
	}
}
