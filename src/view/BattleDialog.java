package view;

import modelo.ResourceManager;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class BattleDialog extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel playerPane;
	private JButton endTurn;
	private JLabel trainerIcon;
	private JPanel pokePanelHolder;
	private ArrayList<PokemonPanel> pokePanels;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BattleDialog dialog = new BattleDialog();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BattleDialog() {
		setBounds(100, 100, 450, 300);
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
		JButton endTurn = new JButton("End turn");
		playerPane.add(endTurn, BorderLayout.NORTH);
		trainerIcon = new JLabel("");
		int trainerImage = new Random().nextInt(6);
		trainerIcon.setIcon(new ImageIcon(ResourceManager.getInstance().getSprite("trainer" + trainerImage)));
		playerPane.add(trainerIcon);
		pokePanelHolder = new JPanel();
		pokePanelHolder.setBackground(new Color(255, 255, 255));
		contentPanel.add(pokePanelHolder, BorderLayout.CENTER);

		pokePanels = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			//PokemonPanel pokePanel = new PokemonPanel();
			//pokePanels.add(pokePanel);
			//pokePanelHolder.add(pokePanel);
		}
	}

}
