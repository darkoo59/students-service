package view.entity.professor;

import utils.Constants;
import view.ListenerHandler;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProfessorEditInfoPanel extends JPanel {
    String[] labelNames = Constants.getProfessorLabelNames();
    String[] finansingWay = Constants.finansingWayLabels;
    String[] yearOfStudy = Constants.yearsLabels;

    Vector<JComponent> fieldsReferences;
    ArrayList<CustomLabel> labelReferences;
    EnterExitPanel enterExit;

    public ProfessorEditInfoPanel() {
        super();
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        fieldsReferences = new Vector<JComponent>();
        labelReferences = new ArrayList<>();
        add(Box.createVerticalStrut(5));
        for (int i = 0; i < 10; i++) {
            String labelName = labelNames[i];
            add(createOneItem(labelName));
        }
        enterExit = new EnterExitPanel();
        enterExit.getButtonConfirm().setEnabled(true);
        add(enterExit);
        setVisible(true);
    }

    public JPanel createOneItem(String labelName) {
        CustomLabel label = new CustomLabel(labelName);
        CustomRowPanel itemPanel = new CustomRowPanel(label);
        labelReferences.add(label);

        CustomTxtField field = new CustomTxtField(labelName);
        PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
        itemPanel.add(errPanel);
        fieldsReferences.add(field);

        if (fieldsReferences.size() == 4) {
            fieldsReferences.get(3).addFocusListener(ListenerHandler.getAdressScreenListener());
        }
        if (fieldsReferences.size() == 7)
            fieldsReferences.get(6).addFocusListener(ListenerHandler.getAdressScreenListener());
        return itemPanel;
    }

    public EnterExitPanel getEnterExit() {
        return enterExit;
    }

    public JTextField getTextField(int index) {
        return (JTextField) fieldsReferences.get(index);
    }

    public void setTextField(int index, String value) {
        JTextField field = (JTextField) fieldsReferences.get(index);
        field.setText(value);
    }

    public Vector<JComponent> getFieldsReferences() {
        return fieldsReferences;
    }

}
