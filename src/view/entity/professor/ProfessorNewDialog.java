package view.entity.professor;

import utils.Constants;
import view.ListenerHandler;
import view.Screen;
import view.entity.AddingScreen;
import view.entity.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfessorNewDialog extends AddingScreen {
    private String[] labelNames = Constants.getProfessorLabelNames();
    private ArrayList<CustomLabel> labelReferences;
    private EnterExitPanel tenex;

    public ProfessorNewDialog() {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);
        setTitle(Screen.getInstance().getResourceBundle().getString("addingProfessorTitle"));
        setSize(new Dimension(Constants.SCREEN_WIDTH * 2 / 5, Constants.SCREEN_HEIGHT * 3 / 4));
        setLocationRelativeTo(null);

        fieldsReferences = new ArrayList<JComponent>();
        labelReferences = new ArrayList<CustomLabel>();

        for (int i = 0; i < labelNames.length; i++) {
            add(Box.createVerticalStrut(5));
            String name = labelNames[i];
            add(createRow(name));
        }
        tenex = new EnterExitPanel();
        add(tenex);
        setVisible(true);

    }

    private JPanel createRow(String name) {

        CustomLabel lbl = new CustomLabel(name);
        CustomRowPanel row = new CustomRowPanel(lbl);
        labelReferences.add(lbl);

        CustomTxtField field = new CustomTxtField(name);
        PanelFieldError errPanel = new PanelFieldError(field, new ErrorMessageLabel("", field.getPreferredSize().width, 20));
        fieldsReferences.add(field);

        if (fieldsReferences.size() == 4) {
            fieldsReferences.get(3).addFocusListener(ListenerHandler.getAdressScreenListener());
        }
        if (fieldsReferences.size() == 7) {
            fieldsReferences.get(6).addFocusListener(ListenerHandler.getAdressScreenListener());
        }
        row.add(errPanel);
        //row.add(fieldsReferences.get(fieldsReferences.size() - 1));
        return row;
    }


    public ArrayList<JComponent> getFieldsReferences() {
        return fieldsReferences;
    }

    @Override
    public EnterExitPanel getTenex() {
        return tenex;
    }

    public ArrayList<CustomLabel> getLabelReferences() {
        return labelReferences;
    }

    public JTextField getTextField(int index) {
        return (JTextField) fieldsReferences.get(index);
    }

    public JComboBox getComboBox(int index) {
        return (JComboBox) fieldsReferences.get(index);
    }

}
