package view.about;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Constants;
import view.Screen;

public class AboutWindow extends JDialog {
	AboutTab tab;
	public AboutWindow() {
		super();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		setLayout(layout);
		setTitle(Screen.getInstance().getResourceBundle().getString("aboutUs"));
		setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 3 / 4));
		setLocationRelativeTo(null);
		tab = new AboutTab();
		tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
		tab.setBackgroundAt(1,new Color(32,136,203));
		tab.setForegroundAt(1,Color.white);
		tab.setBackgroundAt(2,new Color(32,136,203));
		tab.setForegroundAt(2,Color.white);

		tab.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(tab.getSelectedIndex() == 0) {
					tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
					tab.setBackgroundAt(1,new Color(32,136,203));
					tab.setForegroundAt(1,Color.white);
					tab.setBackgroundAt(2,new Color(32,136,203));
					tab.setForegroundAt(2,Color.white);
					
				}else if(tab.getSelectedIndex() == 1) {
					tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
					tab.setBackgroundAt(0,new Color(32,136,203));
					tab.setForegroundAt(0,Color.white);
					tab.setBackgroundAt(2,new Color(32,136,203));
					tab.setForegroundAt(2,Color.white);
				}else {
					tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
					tab.setBackgroundAt(0,new Color(32,136,203));
					tab.setForegroundAt(0,Color.white);
					tab.setBackgroundAt(1,new Color(32,136,203));
					tab.setForegroundAt(1,Color.white);
				}
			}
		});
		add(tab);
		setVisible(true);
	}
}
