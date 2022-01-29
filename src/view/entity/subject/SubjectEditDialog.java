package view.entity.subject;

import controller.subject.EditingSubjectController;
import model.Professor;
import model.database.DataModel;
import model.Subject;
import utils.Constants;
import view.entity.custom.edit_custom.ErrorAddDelPanel;
import view.Screen;
import view.entity.EditingScreen;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class SubjectEditDialog extends EditingScreen {
    private String[] labelNames = Constants.getSubjectLabelNames();
    private String[] years = Constants.yearsLabels;
    private String[] semester = Constants.semesterValues;
    private ArrayList<CustomLabel> labelReferences;
    private Vector<JComponent> fieldsReferences;
    private EnterExitPanel enterExit;
    private ErrorAddDelPanel addRemovePanel;
    private String choosenProfessor = "";

    public SubjectEditDialog() {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle(Screen.getInstance().getResourceBundle().getString("editingSubjectTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 1 / 2));
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        setVisible(false);
        fieldsReferences = new Vector<>();
        labelReferences = new ArrayList<>();
        String subjectIndex = Screen.getInstance().getMainTab().getSelectedSubjectId();
        DataModel model = DataModel.getInstance();
        Subject subject = model.getSubjectById(subjectIndex);
        String subjectData[] = EditingSubjectController.findSubjectDataForFields(subject, this);


        for (int i = 0; i < labelNames.length; i++) {
            String name = labelNames[i];
            add(Box.createVerticalStrut(5));
            if (i <= 1 || i == 4)
                add(createOneItem(name, "Text", subjectData[i]));
            else if (i == 2) {
                add(createOneItem(name, "ComboSemester", subjectData[i]));
                add(Box.createVerticalStrut(20));
            }
            else if (i == 5) {
                add(createOneItem(name, "ComboYears", subjectData[i]));
                add(Box.createVerticalStrut(20));
            } else
                add(createOneItem(name, "Professor", subjectData[i]));
        }
        enterExit = new EnterExitPanel();
        enterExit.getButtonConfirm().setEnabled(true);
        add(enterExit);
        switchAddDeleteButtons();
        setVisible(true);
    }



    public JPanel createOneItem(String labelName, String itemType, String subjectData) {
        CustomLabel lbl = new CustomLabel(labelName);
        CustomRowPanel panel = new CustomRowPanel(lbl);
        labelReferences.add(lbl);

        if (itemType.equals("Text")) {
            CustomTxtField field = new CustomTxtField(labelName);
            JTextField fieldTxt = (JTextField) field;
            fieldTxt.setText(subjectData);
            PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
            panel.add(errPanel);
            fieldsReferences.add(field);
        } else if (itemType.equals("ComboSemester")) {
            CustomCombo combo = new CustomCombo(semester);
            combo.setSelectedItem(subjectData);
            fieldsReferences.add(combo);
            panel.add(fieldsReferences.get(fieldsReferences.size() - 1));
        } else if (itemType.equals("ComboYears")) {
            CustomCombo combo = new CustomCombo(years);
            combo.setSelectedItem(subjectData);
            fieldsReferences.add(combo);
            panel.add(fieldsReferences.get(fieldsReferences.size() - 1));
        } else if (itemType.equals("Professor")) {
            CustomTxtField field = new CustomTxtField(labelName);
            JTextField fieldTxt = (JTextField) field;
            field.setEnabled(false);
            fieldTxt.setText(subjectData);
            addRemovePanel = new ErrorAddDelPanel(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
            panel.add(addRemovePanel);
            fieldsReferences.add(field);
        }
        return panel;
    }

    public void setChoosenProfessor(String professorId) {
        this.choosenProfessor = professorId;
    }

    public void switchProfessorNameWithId() {
        getTextField(3).setText(choosenProfessor);
    }

    public void setLabelNames(String[] labelNames) {
        this.labelNames = labelNames;
    }

    public void setYears(String[] years) {
        this.years = years;
    }

    public void setSemester(String[] semester) {
        this.semester = semester;
    }

    public void setLabelReferences(ArrayList<CustomLabel> labelReferences) {
        this.labelReferences = labelReferences;
    }

    public void setFieldsReferences(Vector<JComponent> fieldsReferences) {
        this.fieldsReferences = fieldsReferences;
    }

    public void setVisible() {
        setVisible();
    }

    public void switchAddDeleteButtons() {
        JTextField profTxt = (JTextField) fieldsReferences.get(3);
        if (profTxt.getText().trim().equals("")) {
            addRemovePanel.setEmptyFieldCase();
        } else {
            addRemovePanel.setFullFieldCase();
        }
    }

    public void setTextField(int index, String text) {
        JTextField field = (JTextField) fieldsReferences.get(index);
        field.setText(text);
    }

    @Override
    public JTextField getTextField(int index) {
        // TODO Auto-generated method stub
        return (JTextField) fieldsReferences.get(index);
    }

    @Override
    public JComboBox getComboBox(int index) {
        // TODO Auto-generated method stub
        return (JComboBox) fieldsReferences.get(index);
    }

    @Override
    public Vector<JComponent> getFieldsReferences() {
        // TODO Auto-generated method stub
        return fieldsReferences;
    }

    @Override
    public EnterExitPanel getEnterExit() {
        // TODO Auto-generated method stub
        return enterExit;
    }

    public ArrayList<CustomLabel> getLabelReferences() {
        return labelReferences;
    }

}
