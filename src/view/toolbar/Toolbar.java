package view.toolbar;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


import utils.Constants;

import view.ListenerHandler;
import view.Screen;
import view.entity.custom.CustomSeparator;

public class Toolbar extends JToolBar {

	private ToolbarIconButton btnNew;
	private ToolbarIconButton btnEdit;
	private ToolbarIconButton btnDelete;
	private ToolbarIconButton btnSearch;
	private ToolbarSearch searchField;
	

	public Toolbar() {
		super(SwingConstants.HORIZONTAL);

		setPreferredSize(new Dimension(Constants.SCREEN_WIDTH * 3 / 4, 30));
		setFloatable(false);

		btnNew = new ToolbarIconButton("Icons/NewMenuItem.png", "Dodaj entitet", 'N');
		btnEdit = new ToolbarIconButton("Icons/EditMenuItem.png", "Izmeni entitet", 'E');
		btnDelete = new ToolbarIconButton("Icons/DeleteMenuitem.png", "Izbrisi entitet", 'D');
		btnSearch = new ToolbarIconButton("Icons/SearchIconItem.png", "Pretrazi", 'F');

		btnSearch.addActionListener(ListenerHandler.searchTables());

		searchField = new ToolbarSearch();
		btnNew.addActionListener(ListenerHandler.openWindowListener());
		btnEdit.addActionListener(ListenerHandler.openEditDialogListener());
		btnDelete.addActionListener(ListenerHandler.getButtonDeleteListener());
		// Left side of toolbar


		add(new CustomSeparator());
		add(btnNew);
		add(new CustomSeparator());
		add(btnEdit);
		add(new CustomSeparator());
		add(btnDelete);

		// Middle
		add(Box.createHorizontalGlue());

		// Right side of toolbar
		add(searchField);
		add(new CustomSeparator());
		add(btnSearch);
		add(new CustomSeparator());
		

	}
	
	public void changeLanguage() {
		btnNew.setToolTipText(Screen.getInstance().getResourceBundle().getString("tipNew"));
		btnEdit.setToolTipText(Screen.getInstance().getResourceBundle().getString("tipEdit"));
		btnDelete.setToolTipText(Screen.getInstance().getResourceBundle().getString("tipDelete"));
		btnSearch.setToolTipText(Screen.getInstance().getResourceBundle().getString("tipSearch"));
		searchField.setToolTipText(Screen.getInstance().getResourceBundle().getString("tipSearch"));
	}

	public ToolbarIconButton getBtnNew() {
		return btnNew;
	}

	public ToolbarIconButton getBtnEdit() {
		return btnEdit;
	}

	public ToolbarIconButton getBtnDelete() {
		return btnDelete;
	}

	public ToolbarIconButton getBtnSearch() {
		return btnSearch;
	}

	public ToolbarSearch getSearchField() {
		return searchField;
	}
	

}
