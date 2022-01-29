package view.entity.department;

import controller.department.EditDepartmentController;
import utils.Constants;
import view.entity.custom.edit_custom.ErrorAddDelPanel;
import view.Screen;
import view.entity.EditingScreen;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class DepartmentEditDialog extends EditingScreen {
    private Vector<JComponent> fieldsReferences;
    private EnterExitPanel tenex;
    private String[] departmentLabelNames = Constants.getDepartmentLabelNames();
    private EditDepartmentController editDepartmentController;
    private ErrorAddDelPanel addRemovePanel;
    private String choosenProfessor = "";

    public DepartmentEditDialog(EditDepartmentController editDepartmentController) {
        this.editDepartmentController = editDepartmentController;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle(Screen.getInstance().getResourceBundle().getString("editingStudentTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 1 / 4 + 70));
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        fieldsReferences = new Vector<>();

        for (int i = 0; i < departmentLabelNames.length; i++) {
            JPanel panel = createRow(departmentLabelNames[i]);
            add(Box.createVerticalStrut(10));
            add(panel);
        }
        tenex = new EnterExitPanel();
        add(tenex);
        editDepartmentController.setFieldsValues(this);
        switchAddDeleteButtons();
        setVisible(true);
    }

    public JPanel createRow(String labelName) {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setMaximumSize(new Dimension(500, 30));

        CustomLabel label = new CustomLabel(labelName);

        JPanel left = new JPanel(new BorderLayout());
        left.add(label, BorderLayout.NORTH);

        CustomTxtField txtField = new CustomTxtField(labelName);
        txtField.setName(labelName);

        panel.add(left);

        if (labelName.equals(Screen.getInstance().getResourceBundle().getString("lblDepartmentName2"))) {
            txtField.setEnabled(false);
            addRemovePanel = new ErrorAddDelPanel(txtField, new ErrorMessageLabel("", txtField.getPreferredSize().width, 20));
            panel.add(addRemovePanel);
        }else if(labelName.equals(Screen.getInstance().getResourceBundle().getString("lblDepartmentName3"))) {
            DepSetterButton depSetterButton = new DepSetterButton(editDepartmentController, this);
            JPanel setterWrapper = new JPanel();
        	setterWrapper.add(depSetterButton);
            panel.add(setterWrapper);
        } else {
            PanelFieldError errPanel = new PanelFieldError(txtField, new ErrorMessageLabel("", txtField.getPreferredSize().width, 20));
            panel.add(errPanel);
        }
        fieldsReferences.add(txtField);
        return panel;
    }

    public void showList() {
        editDepartmentController.addDepartmentToField(this);
    }

    public void setChoosenProfessor(String professorId) {
        this.choosenProfessor = professorId;
    }

    public void switchProfessorNameWithId() {
        getTextField(2).setText(choosenProfessor);
    }

    public void switchAddDeleteButtons() {
        JTextField depTxt = (JTextField) fieldsReferences.get(2);
        if (depTxt.getText().trim().equals("")) {
            addRemovePanel.setEmptyFieldCase();
        } else {
            addRemovePanel.setFullFieldCase();
        }

    }

    @Override
    public JTextField getTextField(int index) {
        return (JTextField) fieldsReferences.get(index);
    }

    @Override
    public JComboBox getComboBox(int index) {
        return (JComboBox) fieldsReferences.get(index);
    }

    @Override
    public Vector<JComponent> getFieldsReferences() {
        return fieldsReferences;
    }

    @Override
    public EnterExitPanel getEnterExit() {
        return tenex;
    }
}
