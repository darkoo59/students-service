package view.about;

import javax.swing.JTabbedPane;

import view.Screen;

public class AboutTab extends JTabbedPane {
	private AboutSrdjan aboutSrdjanTab;
	private AboutDarko aboutDarkoTab;
	private AboutApp aboutApp;
	public AboutTab() {
		aboutSrdjanTab = new AboutSrdjan();
		aboutDarkoTab = new AboutDarko();
		aboutApp = new AboutApp();
		add(Screen.getInstance().getResourceBundle().getString("aboutAppTabTitle"), aboutApp);
		add("Srdjan", aboutSrdjanTab);
		add("Darko", aboutDarkoTab);
	}
}
