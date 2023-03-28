package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;

public class BattleDialog extends JFrame {

	private final JPanel contentPanel = new JPanel();

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
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel playerPane = new JPanel();
			playerPane.setBackground(new Color(255, 255, 255));
			contentPanel.add(playerPane, BorderLayout.WEST);
			playerPane.setLayout(new BorderLayout(0, 0));
			{
				JButton btnNewButton = new JButton("End turn");
				playerPane.add(btnNewButton, BorderLayout.NORTH);
			}
			{
				JLabel lblNewLabel = new JLabel("New label");
				playerPane.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
		}
	}

}
