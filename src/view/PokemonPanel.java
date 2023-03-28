package view;

import modelo.Pokemon;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
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
	private JProgressBar progressBar;
	private JProgressBar progressBar_1;


	/**
	 * Create the panel.
	 */
	public PokemonPanel(Pokemon pokemon) {
		this.pokemon = pokemon;
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
		
		progressBar = new JProgressBar();
		panel.add(progressBar);
		
		progressBar_1 = new JProgressBar();
		panel.add(progressBar_1);

	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
