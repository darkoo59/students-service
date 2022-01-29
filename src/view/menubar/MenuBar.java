package view.menubar;

import utils.ImageUtils;
import view.ListenerHandler;
import view.Screen;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
	private MenuBar context = this;
	private JMenu fileMenuButton,editMenuButton,helpMenuButton,changeLanguageButton;
	private MenuItems newMenuItem,saveMenuItem,closeMenuItem,editMenuItem,deleteMenuItem,helpMenuItem,aboutMenuItem;
	private MenuItems studentsItem,subjectsItem,professorsItem,departmentsItem;
	private MenuItems serbianItem,englishItem;
	private JMenu openMenuButton;

	public MenuBar() {
		super();
		UIManager.put("MenuItem.selectionBackground", new Color(232,57,95));
		setBackground(new Color(32,136,203));
		fileMenuButton = new JMenu("Fajl");
		fileMenuButton.setForeground(Color.white);
		editMenuButton = new JMenu("Izmena");
		editMenuButton.setForeground(Color.white);
		helpMenuButton = new JMenu("Pomoc");
		helpMenuButton.setForeground(Color.white);
		changeLanguageButton = new JMenu("Jezik");
		changeLanguageButton.setForeground(Color.white);

		fileMenuButton.setMnemonic('F');
		editMenuButton.setMnemonic('E');
		helpMenuButton.setMnemonic('H');
		changeLanguageButton.setMnemonic('L');

		newMenuItem = new MenuItems("Novo", "Icons/NewMenuItem.png", "CTRL + N", 'N');
		newMenuItem.addActionListener(ListenerHandler.openWindowListener());
		saveMenuItem = new MenuItems("Sacuvaj", "Icons/SaveMenuItem.png", "CTRL + S", 'S');
		saveMenuItem.addActionListener(ListenerHandler.saveFilesListener());
		closeMenuItem = new MenuItems("Zatvori", "Icons/CloseMenuItem.png", "CTRL + C", 'C');
		closeMenuItem.addActionListener(ListenerHandler.closeAppListener());

		editMenuItem = new MenuItems("Izmena", "Icons/EditMenuItem.png", "CTRL + E", 'E');
		editMenuItem.addActionListener(ListenerHandler.openEditDialogListener());
		deleteMenuItem = new MenuItems("Izbrisi", "Icons/DeleteMenuItem.png", "CTRL + D", 'D');
		deleteMenuItem.addActionListener(ListenerHandler.getButtonDeleteListener());

		helpMenuItem = new MenuItems("Pomoc", "Icons/HelpMenuItem.png", "CTRL + H", 'H');
		helpMenuItem.addActionListener(ListenerHandler.openHelpFrame());
		aboutMenuItem = new MenuItems("O nama", "Icons/AboutMenuItem.png", "CTRL + A", 'A');
		aboutMenuItem.addActionListener(ListenerHandler.openAboutFrame());

		openMenuButton = new JMenu("Otvori");
		openMenuButton.setOpaque(true);
		openMenuButton.setBackground(new Color(32,136,203));
		openMenuButton.setForeground(Color.white);

		ImageIcon imageIcon = ImageUtils.readImageIcon("Icons/OpenMenuItem.png");
		Image image = imageIcon.getImage().getScaledInstance(13, 13, Image.SCALE_SMOOTH);
		openMenuButton.setIcon(new ImageIcon(image));

		studentsItem = new MenuItems("Studenti", "Icons/StudentMenuItem.png", "CTRL + T", 'T');
		studentsItem.addActionListener(ListenerHandler.getOpenStudentTabListener());
		subjectsItem = new MenuItems("Predmeti", "Icons/SubjectMenuItem.png", "CTRL + P", 'P');
		subjectsItem.addActionListener(ListenerHandler.getOpenSubjectTabListener());
		professorsItem = new MenuItems("Profesori", "Icons/ProfessorMenuItems.png", "CTRL + R", 'R');
		professorsItem.addActionListener(ListenerHandler.getOpenProfessorTabListener());
		departmentsItem = new MenuItems("Katedre", "Icons/DepartmentMenuItem.png", "CTRL + K", 'K');
		departmentsItem.addActionListener(ListenerHandler.getOpenDepartmentTabListener());
		
		serbianItem = new MenuItems("Srpski","Icons/Serbia.png","CTRL + Q",'Q');
		serbianItem.addActionListener(ListenerHandler.getChangeToSerbianListener());
		englishItem = new MenuItems("Engleski","Icons/US.png","CTRL + W",'W');
		englishItem.addActionListener(ListenerHandler.getChangeToUsListener());

		openMenuButton.add(studentsItem);
		openMenuButton.add(subjectsItem);
		openMenuButton.add(professorsItem);
		openMenuButton.add(departmentsItem);
		openMenuButton.setMnemonic('O');

		fileMenuButton.add(newMenuItem);
		fileMenuButton.add(saveMenuItem);
		fileMenuButton.add(openMenuButton);
		fileMenuButton.add(closeMenuItem);

		editMenuButton.add(editMenuItem);
		editMenuButton.add(deleteMenuItem);

		helpMenuButton.add(helpMenuItem);
		helpMenuButton.add(aboutMenuItem);
		
		changeLanguageButton.add(serbianItem);
		changeLanguageButton.add(englishItem);

		this.add(fileMenuButton);
		this.add(editMenuButton);
		this.add(helpMenuButton);
		this.add(changeLanguageButton);

	}
	/*
	 * 	private MenuItems newMenuItem,saveMenuItem,closeMenuItem,editMenuItem,deleteMenuItem,helpMenuItem,aboutMenuItem;
	private MenuItems studentsItem,subjectsItem,professorsItem,departmentsItem;
	private MenuItems serbianItem,englishItem;
	 */
	public void changeLanguage() {
		fileMenuButton.setText(Screen.getInstance().getResourceBundle().getString("btnFile"));
		editMenuButton.setText(Screen.getInstance().getResourceBundle().getString("btnEdit"));
		helpMenuButton.setText(Screen.getInstance().getResourceBundle().getString("btnHelp"));
		changeLanguageButton.setText(Screen.getInstance().getResourceBundle().getString("btnLanguage"));
		newMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemNew"));
		saveMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemSave"));
		closeMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemClose"));
		editMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemEdit"));
		deleteMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemDelete"));
		helpMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemHelp"));
		aboutMenuItem.setText(Screen.getInstance().getResourceBundle().getString("itemAbout"));
		serbianItem.setText(Screen.getInstance().getResourceBundle().getString("itemSerbian"));
		englishItem.setText(Screen.getInstance().getResourceBundle().getString("itemEnglish"));
		openMenuButton.setText(Screen.getInstance().getResourceBundle().getString("btnOpen"));
		studentsItem.setText(Screen.getInstance().getResourceBundle().getString("itemStudents"));
		subjectsItem.setText(Screen.getInstance().getResourceBundle().getString("itemSubjects"));
		professorsItem.setText(Screen.getInstance().getResourceBundle().getString("itemProfessors"));
		departmentsItem.setText(Screen.getInstance().getResourceBundle().getString("itemDepartments"));
		
	}
}
