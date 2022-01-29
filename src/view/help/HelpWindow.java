package view.help;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.Constants;

import java.awt.Color;
import java.awt.Dimension;

import view.Screen;

public class HelpWindow extends JDialog	{

	public HelpWindow() {
		super();
		setTitle(Screen.getInstance().getResourceBundle().getString("itemHelp"));
		setSize(new Dimension(Constants.SCREEN_WIDTH * 4 / 5, Constants.SCREEN_HEIGHT * 3 / 4));
		JTextArea txtArea = new JTextArea(30,100);
        txtArea.setAutoscrolls(true);
        txtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtArea.setFont(new Font("Helvetica", Font.BOLD, 14));
        txtArea.setLineWrap(true);
        txtArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane (txtArea, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(txtArea);
        scrollPane.setAutoscrolls(true);

		String text = Screen.getInstance().getResourceBundle().getString("helpText");

		txtArea.setText(text);

		add(scrollPane);
	    setLocationRelativeTo(null);
		setVisible(true);
	}
}
