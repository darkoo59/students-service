package view.entity.professor;

import model.database.DataModel;
import utils.Constants;
import view.Screen;
import view.tabs.ProfessorEditTab;
import view.entity.EditingScreen;
import view.entity.custom.EnterExitPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Vector;

public class ProfessorEditDialog extends EditingScreen {
    private ProfessorEditTab tab;

    public ProfessorEditDialog() {
        // TODO Auto-generated constructor stub
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle(Screen.getInstance().getResourceBundle().getString("editingProfessorTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 3 / 4));
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        
        tab = new ProfessorEditTab();
        tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
        tab.setBackgroundAt(1,new Color(32,136,203));
        tab.setForegroundAt(1,Color.white);

        tab.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                if (tab.getSelectedIndex() == 0) {
                   tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
                   tab.setBackgroundAt(1,new Color(32,136,203));
                   tab.setForegroundAt(1,Color.white);

                } else if (tab.getSelectedIndex() == 1) {
                    tab.setForegroundAt(tab.getSelectedIndex(),Color.black);
                    tab.setBackgroundAt(0,new Color(32,136,203));
                    tab.setForegroundAt(0,Color.white);
                    
                    DataModel.getInstance().setEditTableObserver(tab.getEditSubjects().getProfessorSubjectsTable());
                }

            }
        });
        add(tab);
        setVisible(false);
    }

    public ProfessorEditTab getEditProfessorTab() {
        return tab;
    }

    public void setVisible() {
        setVisible(true);
    }

    @Override
    public JTextField getTextField(int index) {
        // TODO Auto-generated method stub
        return tab.getToolbarEditProfessorInfo().getTextField(index);
    }

    @Override
    public JComboBox getComboBox(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector<JComponent> getFieldsReferences() {
        // TODO Auto-generated method stub
        return tab.getToolbarEditProfessorInfo().getFieldsReferences();
    }


    @Override
    public EnterExitPanel getEnterExit() {
        // TODO Auto-generated method stub
        return tab.getToolbarEditProfessorInfo().getEnterExit();
    }

}
