package view.entity.subject;

import utils.Constants;
import view.Screen;
import view.entity.AddingScreen;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SubjectNewDialog extends AddingScreen {
    private String[] labelNames = Constants.getSubjectLabelNames();
    private String[] semesterValues = Constants.semesterValues;
    private ArrayList<CustomLabel> labelsReferences;
    private EnterExitPanel tenex;

    public SubjectNewDialog() {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);
        setTitle(Screen.getInstance().getResourceBundle().getString("addingSubjectTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 1 / 2));
        setLocationRelativeTo(null);
        fieldsReferences = new ArrayList<>();
        labelsReferences = new ArrayList<>();

        for (int i = 0; i < labelNames.length; i++) {
            String labelName = labelNames[i];
            add(Box.createVerticalStrut(5));
            if (i == 2) {
                add(createRow(labelName, "Semester"));
                add(Box.createVerticalStrut(20));
                continue;
            }
            add(createRow(labelName, ""));
        }
        tenex = new EnterExitPanel();
        add(tenex);
        setVisible(true);
    }

    private JPanel createRow(String labelName, String compType) {

        CustomLabel label = new CustomLabel(labelName);
        CustomRowPanel row = new CustomRowPanel(label);

        labelsReferences.add(label);


        if (compType.equals("Semester")) {
            fieldsReferences.add(new CustomCombo(semesterValues));
            row.add(fieldsReferences.get(fieldsReferences.size() - 1));
        } else {
            CustomTxtField field = new CustomTxtField(labelName);
            PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
            row.add(errPanel);
            fieldsReferences.add(field);
        }


        return row;
    }

    public JTextField getTextField(int index) {
        return (JTextField) fieldsReferences.get(index);
    }

    public JComboBox getComboBox(int index) {
        return (JComboBox) fieldsReferences.get(index);
    }

    public ArrayList<JComponent> getFieldsReferences() {
        return fieldsReferences;
    }

    @Override
    public EnterExitPanel getTenex() {
        return tenex;
    }

    @Override
    public ArrayList<CustomLabel> getLabelReferences() {
        return labelsReferences;
    }


}
