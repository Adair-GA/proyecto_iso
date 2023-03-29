package view;

import modelo.Pokemon;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

public class PokemonPanel extends JPanel implements Observer {
	private final String info = """
			Name: %s
			attack: %d + %d
			defense: %d + %d
			health: %d + %d
			type: %s
			""";

	private Pokemon pokemon;
	private JTextArea infoTextArea;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JProgressBar life;
	private JProgressBar euphoricMeter;


	/**
	 * Create the panel.
	 */
	public PokemonPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(new GridLayout(0, 1, 0, 0));

		infoTextArea = new JTextArea();
		infoTextArea.setText(info);
		add(infoTextArea);

		lblNewLabel = new JLabel("");
		add(lblNewLabel);
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		life = new JProgressBar();
		panel.add(life);
		
		euphoricMeter = new JProgressBar();
		panel.add(euphoricMeter);

	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
