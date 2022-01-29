package view.entity.student;

import controller.student.AddFailedSubjectController;
import controller.student.AddPassedSubjectController;
import controller.student.DeleteFailedSubjectController;
import controller.student.TakingExamController;
import model.database.DataModel;
import utils.Constants;
import view.Screen;
import view.tabs.StudentEditTab;
import view.entity.EditingScreen;
import view.entity.custom.EnterExitPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Vector;

public class StudentEditDialog extends EditingScreen {
    private StudentEditTab tab;

    public StudentEditDialog() {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle(Screen.getInstance().getResourceBundle().getString("editingStudentTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 3 / 4));
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        tab = new StudentEditTab(new AddPassedSubjectController(), new AddFailedSubjectController(), new DeleteFailedSubjectController(), new TakingExamController());
        
        tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
        tab.setBackgroundAt(1,new Color(32,136,203));
        tab.setForegroundAt(1,Color.white);
        tab.setBackgroundAt(2,new Color(32,136,203));
        tab.setForegroundAt(2,Color.white);

        tab.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                if (tab.getSelectedIndex() == 0) {
                	tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
                    tab.setBackgroundAt(1,new Color(32,136,203));
                    tab.setForegroundAt(1,Color.white);
                    tab.setBackgroundAt(2,new Color(32,136,203));
                    tab.setForegroundAt(2,Color.white);
                } else if (tab.getSelectedIndex() == 1) {
                	tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
                    tab.setBackgroundAt(0,new Color(32,136,203));
                    tab.setForegroundAt(0,Color.white);
                    tab.setBackgroundAt(2,new Color(32,136,203));
                    tab.setForegroundAt(2,Color.white);
                	
                    DataModel.getInstance()
                            .setEditTableObserver(tab.getToolbarEditStudentPassed().getPassedSubjectsTable());
                    DataModel.getInstance().notifyEditTable();
                    tab.getToolbarEditStudentPassed().setESPBAndAverage();
                } else if (tab.getSelectedIndex() == 2) {
                	tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
                    tab.setBackgroundAt(0,new Color(32,136,203));
                    tab.setForegroundAt(0,Color.white);
                    tab.setBackgroundAt(1,new Color(32,136,203));
                    tab.setForegroundAt(1,Color.white);
                     
                    DataModel.getInstance()
                            .setEditTableObserver(tab.getToolbarEditStudentFailed().getFailedSubjectsTable());
                    DataModel.getInstance().notifyEditTable();

                }
            }
        });
        addWindowListener(this);
        tab.getToolbarEditStudentPassed().setESPBAndAverage();
        add(tab);
        setVisible(false);

    }

    public StudentEditTab getEditStudentTab() {
        return tab;
    }

    public void setVisible() {
        setVisible(true);
    }

    @Override
    public JTextField getTextField(int index) {
        // TODO Auto-generated method stub
        return tab.getToolbarEditStudentInfo().getTextField(index);
    }

    @Override
    public JComboBox getComboBox(int index) {
        // TODO Auto-generated method stub
        return tab.getToolbarEditStudentInfo().getComboBox(index);
    }

    public Vector<JComponent> getFieldsReferences() {
        return tab.getToolbarEditStudentInfo().getFieldsReferences();
    }

    @Override
    public EnterExitPanel getEnterExit() {
        // TODO Auto-generated method stub
        return tab.getToolbarEditStudentInfo().getEnterExit();
    }
}
