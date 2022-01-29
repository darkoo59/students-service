package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.department.DeleteDepartmentController;
import controller.professor.AddProfessorController;
import controller.student.AddStudentController;
import controller.subject.AddSubjectController;
import controller.professor.DeleteProfessorController;
import controller.student.DeleteStudentController;
import controller.subject.DeleteSubjectController;
import controller.professor.EditingProfessorController;
import controller.student.EditingStudentController;
import controller.subject.EditingSubjectController;
import controller.department.AddDepartmentController;
import controller.department.EditDepartmentController;
import model.database.DataModel;
import utils.Constants;
import view.menubar.MenuBar;
import view.statusbar.StatusBar;
import view.tabs.MainTab;
import view.toolbar.Toolbar;

public class Screen extends JFrame {
    private int selectedTab = 0;
    private MainTab mainTab;
    private static Screen instance;
    private ResourceBundle resourceBundle;
    private Toolbar toolbar;
    private MenuBar menu;
    private StatusBar statusBar;
    private String tabName, activeTab;

    private Screen() {
        super();
        Locale.setDefault(new Locale("sr", "RS"));
        resourceBundle = ResourceBundle.getBundle("view.localization.language", Locale.getDefault());

        setSize(Constants.SCREEN_WIDTH * 3 / 4, Constants.SCREEN_HEIGHT * 3 / 4);
        setLocation(Constants.SCREEN_WIDTH / 8, Constants.SCREEN_HEIGHT / 8);

        // Screen body
        toolbar = new Toolbar();
        menu = new MenuBar();
        statusBar = new StatusBar(this);

        setJMenuBar(menu);

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.PAGE_START);
        add(statusBar, BorderLayout.SOUTH);
        activeTab = "Studenti";

        // Tables

        mainTab = new MainTab(new AddStudentController(), new EditingStudentController(), new DeleteStudentController());
        
        mainTab.setForegroundAt(mainTab.getSelectedIndex(),Color.black);
        mainTab.setBackgroundAt(1,new Color(32,136,203));
        mainTab.setForegroundAt(1,Color.white);
        mainTab.setBackgroundAt(2,new Color(32,136,203));
        mainTab.setForegroundAt(2,Color.white);
        mainTab.setBackgroundAt(3,new Color(32,136,203));
        mainTab.setForegroundAt(3,Color.white);

        mainTab.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                selectedTab = mainTab.getSelectedIndex();
                if (mainTab.getSelectedIndex() == 0) {
                    activeTab = "Studenti";
                    mainTab.setAddingController(new AddStudentController());
                    mainTab.setDeleteController(new DeleteStudentController());
                    mainTab.setEditingController(new EditingStudentController());
                    DataModel.getInstance().setTableObserver(mainTab.getStudentTable());
                    tabName = getTabName("Studenti");
                    statusBar.setTabName(tabName);
                    
                    mainTab.setForegroundAt(mainTab.getSelectedIndex(),Color.black);
                    mainTab.setBackgroundAt(1,new Color(32,136,203));
                    mainTab.setForegroundAt(1,Color.white);
                    mainTab.setBackgroundAt(2,new Color(32,136,203));
                    mainTab.setForegroundAt(2,Color.white);
                    mainTab.setBackgroundAt(3,new Color(32,136,203));
                    mainTab.setForegroundAt(3,Color.white);
                } else if (mainTab.getSelectedIndex() == 1) {
                    activeTab = "Profesori";
                    mainTab.setAddingController(new AddProfessorController());
                    mainTab.setDeleteController(new DeleteProfessorController());
                    mainTab.setEditingController(new EditingProfessorController());
                    DataModel.getInstance().setTableObserver(mainTab.getProfessorTable());
                    tabName = getTabName("Profesori");
                    statusBar.setTabName(tabName);
                    
                    mainTab.setForegroundAt(mainTab.getSelectedIndex(),Color.black);
                    mainTab.setBackgroundAt(0,new Color(32,136,203));
                    mainTab.setForegroundAt(0,Color.white);
                    mainTab.setBackgroundAt(2,new Color(32,136,203));
                    mainTab.setForegroundAt(2,Color.white);
                    mainTab.setBackgroundAt(3,new Color(32,136,203));
                    mainTab.setForegroundAt(3,Color.white);
                } else if(mainTab.getSelectedIndex() == 2) {
                    activeTab = "Predmeti";
                    mainTab.setAddingController(new AddSubjectController());
                    mainTab.setDeleteController(new DeleteSubjectController());
                    mainTab.setEditingController(new EditingSubjectController());
                    DataModel.getInstance().setTableObserver(mainTab.getSubjectTable());
                    tabName = getTabName("Predmeti");
                    statusBar.setTabName(tabName);
                    
                    mainTab.setForegroundAt(mainTab.getSelectedIndex(),Color.black);
                    mainTab.setBackgroundAt(1,new Color(32,136,203));
                    mainTab.setForegroundAt(1,Color.white);
                    mainTab.setBackgroundAt(0,new Color(32,136,203));
                    mainTab.setForegroundAt(0,Color.white);
                    mainTab.setBackgroundAt(3,new Color(32,136,203));
                    mainTab.setForegroundAt(3,Color.white);
                } else {
                    activeTab="Katedre";
                    mainTab.setAddingController(new AddDepartmentController());
                    mainTab.setDeleteController(new DeleteDepartmentController());
                    mainTab.setEditingController(new EditDepartmentController());
                    DataModel.getInstance().setTableObserver(mainTab.getDepartmentTable());
                    tabName = getTabName("Katedre");
                    statusBar.setTabName(tabName);
                    
                    mainTab.setForegroundAt(mainTab.getSelectedIndex(),Color.black);
                    mainTab.setBackgroundAt(0,new Color(32,136,203));
                    mainTab.setForegroundAt(0,Color.white);
                    mainTab.setBackgroundAt(1,new Color(32,136,203));
                    mainTab.setForegroundAt(1,Color.white);
                    mainTab.setBackgroundAt(2,new Color(32,136,203));
                    mainTab.setForegroundAt(2,Color.white);
                }
            }
        });
        add(mainTab);

        // Screen title and close operation
        setTitle("Studentska Sluzba");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void changeLanguage() {
        resourceBundle = ResourceBundle.getBundle("view.localization.language", Locale.getDefault());
        //statusBar.setTabName(getTabName(activeTab));
        setTitle(resourceBundle.getString("naslovAplikacije"));
        menu.changeLanguage();
        toolbar.changeLanguage();
        statusBar.changeLanguage(getTabName(activeTab));
        mainTab.changeLanguage();

        UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
        UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
        UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
        UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
    }

    public String getTabName(String name) {
        if (name.equals("Studenti")) {
        	return Screen.getInstance().getResourceBundle().getString("tabStudent");

        } else if (name.equals("Profesori")) {
        	return Screen.getInstance().getResourceBundle().getString("tabProfessor");

        } else if (name.equals("Predmeti")) {
        	return Screen.getInstance().getResourceBundle().getString("tabSubject");
        } else if(name.equals("Katedre")) {
        	return Screen.getInstance().getResourceBundle().getString("tabDepartment");
        }
        return "";
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public MainTab getMainTab() {
        return mainTab;
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
