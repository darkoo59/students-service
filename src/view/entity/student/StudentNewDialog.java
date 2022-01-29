package view.entity.student;

import utils.Constants;
import view.ListenerHandler;
import view.Screen;
import view.entity.AddingScreen;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentNewDialog extends AddingScreen {
    private String[] labelNames = Constants.getStudentLabelNames();
    private String[] finansingWay = Constants.finansingWayLabels;
    private String[] years = Constants.yearsLabels;
    private ArrayList<CustomLabel> labelReferences;
    private EnterExitPanel tenex;

    public StudentNewDialog() {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);
        setTitle(Screen.getInstance().getResourceBundle().getString("addingStudentTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 3 / 4));
        setLocationRelativeTo(null);
        fieldsReferences = new ArrayList<>();
        labelReferences = new ArrayList<>();

        for (int i = 0; i < labelNames.length; i++) {
            String name = labelNames[i];
            add(Box.createVerticalStrut(5));
            if (i >= 9) {
                add(Box.createVerticalStrut(20));
                add(createRow(name, "ComboFinancing"));
            } else if (i >= 8)
                add(createRow(name, "ComboYears"));
            else
                add(createRow(name, "Text"));
        }
        tenex = new EnterExitPanel();
        add(tenex);
        setVisible(true);

    }


    private JPanel createRow(String name, String fieldType) {

        CustomLabel lbl = new CustomLabel(name);
        CustomRowPanel row = new CustomRowPanel(lbl);

        labelReferences.add(lbl);

        if (fieldType.equals("Text")) {
            CustomTxtField field = new CustomTxtField(name);
            PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
            row.add(errPanel);
            fieldsReferences.add(field);
        } else if (fieldType.equals("ComboYears")) {
            fieldsReferences.add(new CustomCombo(years));
            row.add(fieldsReferences.get(fieldsReferences.size() - 1));
        } else if (fieldType.equals("ComboFinancing")) {
            fieldsReferences.add(new CustomCombo(finansingWay));
            row.add(fieldsReferences.get(fieldsReferences.size() - 1));
        }


        if (fieldsReferences.size() == 4) {
            fieldsReferences.get(3).addFocusListener(ListenerHandler.getAdressScreenListener());
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
        return labelReferences;
    }


}
