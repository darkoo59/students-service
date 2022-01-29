package view.entity.student;

import controller.student.TakingExamController;
import model.database.DataModel;
import model.Subject;
import utils.Constants;
import view.Screen;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class StudentEnteringMark extends JDialog {

    private String[] labelNames = Constants.getTakingExamLabelNames();
    private String[] markValue = Constants.marksLabels;
    private String[] subjectData;
    private ArrayList<CustomLabel> labelReferences;
    private Vector<JComponent> fieldsReferences;
    private EnterExitPanel enterExit;
    private StudentEditFailedPanel failedSubjectsPanel;

    public TakingExamController getExamController() {
        return failedSubjectsPanel.getExamController();

    }


    public StudentEnteringMark(StudentEditFailedPanel failedPanel) {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle(Screen.getInstance().getResourceBundle().getString("takingExamTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 8, Constants.SCREEN_HEIGHT * 3 / 6));
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        fieldsReferences = new Vector<>();
        labelReferences = new ArrayList<>();
        failedSubjectsPanel = failedPanel;
        String failedSubjectIndex = failedSubjectsPanel.getSelectedSubjectId();
        Subject subject = DataModel.getInstance().getSubjectById(failedSubjectIndex);
        String subjectData[] = TakingExamController.findSubjectDataForFields(subject);
        for (int i = 0; i < 4; i++) {
            String labelName = labelNames[i];
            if (i < 2) {
                add(createOneItem(labelName, "ProtectedTextField", subjectData[i]));
            } else if (i == 2)
                add(createOneItem(labelName, "ComboBox", ""));
            else
                add(createOneItem(labelName, "Date", ""));
        }

        enterExit = new EnterExitPanel();
        add(enterExit);
        setVisible(true);

    }

    public JPanel createOneItem(String labelName, String itemType, String subjectData) {
        JPanel itemPanel = new JPanel();
        CustomLabel label = new CustomLabel(labelName);
        labelReferences.add(label);
        itemPanel.add(label);

        if (itemType.equals("ProtectedTextField")) {
            CustomTxtField field = new CustomTxtField(labelName);
            field.setText(subjectData);
            field.setEdit(false);
            PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
            itemPanel.add(errPanel);
            fieldsReferences.add(field);
        } else if (itemType.equals("ComboBox")) {
            fieldsReferences.add(new CustomCombo(markValue));
            itemPanel.add(fieldsReferences.get(fieldsReferences.size() - 1));
        } else if (itemType.equals("Date")) {
            CustomTxtField field = new CustomTxtField(labelName);
            field.setText("");
            PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
            itemPanel.add(errPanel);
            fieldsReferences.add(field);
        }
        return itemPanel;
    }

    public JTextField getTextField(int index) {
        // TODO Auto-generated method stub
        return (JTextField) fieldsReferences.get(index);
    }

    public JComboBox getComboBox(int index) {
        // TODO Auto-generated method stub
        return (JComboBox) fieldsReferences.get(index);
    }

    public Vector<JComponent> getFieldsReferences() {
        // TODO Auto-generated method stub
        return fieldsReferences;
    }

    public ArrayList<CustomLabel> getLabelReferences() {
        return labelReferences;
    }

    public EnterExitPanel getEnterExit() {
        // TODO Auto-generated method stub
        return enterExit;
    }

}
