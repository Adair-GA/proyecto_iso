package view;

import modelo.Partida;
import modelo.ResourceManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Objects;

public class mainPanel extends JFrame {

	private JPanel contentPane;
	private Controler controler = null;
	private JLabel mainImage;
	private JPanel controlsPanel;
	private JLabel playersLabel;
	private JLabel npcsLabel;
	private JTextField playerCountTextField;
	private JTextField npcsTextField;
	private JLabel msRoundLabel;
	private JTextField msRoundTextField;
	private JLabel pokemonCountLabel;
	private JTextField pokemonCountTextField;
	private JButton btnReadme;
	private JButton btnStart;
	private Controlador controlador = null;
	private static mainPanel instance = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				mainPanel frame = new mainPanel();
				mainPanel.instance = frame;
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainPanel() {
		setForeground(new Color(255, 255, 255));
		setTitle("Pokemon battle simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getMainImage());
		contentPane.add(getControlsPanel(), BorderLayout.EAST);
	}
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
	private JLabel getMainImage() {
		if (mainImage == null) {
			mainImage = new JLabel("");
			mainImage.setIcon(ResourceManager.getInstance().getSprite("main"));
		}
		return mainImage;
	}
	private JPanel getControlsPanel() {
		if (controlsPanel == null) {
			controlsPanel = new JPanel();
			controlsPanel.setBackground(new Color(255, 255, 255));
			controlsPanel.setLayout(new GridLayout(0, 2, 0, 0));
			controlsPanel.add(getPlayersLabel());
			controlsPanel.add(getPlayerCountTextField());
			controlsPanel.add(getNpcsLabel());
			controlsPanel.add(getNpcsTextField());
			controlsPanel.add(getMsRoundLabel());
			controlsPanel.add(getMsRoundTextField());
			controlsPanel.add(getPokemonCountLabel());
			controlsPanel.add(getPokemonCountTextField());
			for (int i = 0; i < 5; i++) {
				controlsPanel.add(emptyLabel());
			}
			controlsPanel.add(getBtnReadme());
			controlsPanel.add(emptyLabel());
			controlsPanel.add(getBtnStart());
		}
		return controlsPanel;
	}
	private JLabel getPlayersLabel() {
		if (playersLabel == null) {
			playersLabel = new JLabel("players");
			playersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return playersLabel;
	}
	private JLabel getNpcsLabel() {
		if (npcsLabel == null) {
			npcsLabel = new JLabel("NPCs");
			npcsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return npcsLabel;
	}
	private JTextField getPlayerCountTextField() {
		if (playerCountTextField == null) {
			playerCountTextField = new JTextField();
			playerCountTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			playerCountTextField.setText("1");
			playerCountTextField.setHorizontalAlignment(SwingConstants.CENTER);
			playerCountTextField.setForeground(new Color(255, 153, 0));
			playerCountTextField.setBackground(new Color(204, 255, 255));
			playerCountTextField.setColumns(10);
		}
		return playerCountTextField;
	}
	private JTextField getNpcsTextField() {
		if (npcsTextField == null) {
			npcsTextField = new JTextField();
			npcsTextField.setText("2");
			npcsTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			npcsTextField.setHorizontalAlignment(SwingConstants.CENTER);
			npcsTextField.setForeground(new Color(255, 153, 0));
			npcsTextField.setBackground(new Color(204, 255, 255));
			npcsTextField.setColumns(10);
		}
		return npcsTextField;
	}
	private JLabel getMsRoundLabel() {
		if (msRoundLabel == null) {
			msRoundLabel = new JLabel("ms/round");
			msRoundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return msRoundLabel;
	}
	private JTextField getMsRoundTextField() {
		if (msRoundTextField == null) {
			msRoundTextField = new JTextField();
			msRoundTextField.setText("2000");
			msRoundTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			msRoundTextField.setHorizontalAlignment(SwingConstants.CENTER);
			msRoundTextField.setForeground(new Color(255, 153, 0));
			msRoundTextField.setBackground(new Color(204, 255, 255));
			msRoundTextField.setColumns(10);
		}
		return msRoundTextField;
	}
	private JLabel getPokemonCountLabel() {
		if (pokemonCountLabel == null) {
			pokemonCountLabel = new JLabel("PokéMons");
			pokemonCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return pokemonCountLabel;
	}
	private JTextField getPokemonCountTextField() {
		if (pokemonCountTextField == null) {
			pokemonCountTextField = new JTextField();
			pokemonCountTextField.setText("3");
			pokemonCountTextField.setHorizontalAlignment(SwingConstants.CENTER);
			pokemonCountTextField.setForeground(new Color(255, 153, 0));
			pokemonCountTextField.setBackground(new Color(204, 255, 255));
			pokemonCountTextField.setColumns(10);
		}
		return pokemonCountTextField;
	}

	private JLabel emptyLabel() {
		return new JLabel("");
	}
	private JButton getBtnReadme() {
		if (btnReadme == null) {
			btnReadme = new JButton("readme");
		}
		return btnReadme;
	}
	private JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton("start");
			btnStart.addActionListener(getControlador());
		}
		return btnStart;
	}

	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	private static void setInvisible() {
		mainPanel.instance.setVisible(false);
	}

	class Controlador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnStart) {
				Partida.getPartida().iniciar();
				//TODO no se puede crear el dialogo de batalla hasta que no se haya creado la partida pero da un IndexOutOfBounds

				new BattleDialog(0).setVisible(true);
				new BattleDialog(1).setVisible(true);

				Partida.getPartida().update();
				mainPanel.setInvisible();
			}
		}
	}

}
