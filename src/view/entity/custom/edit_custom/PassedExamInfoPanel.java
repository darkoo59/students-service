package view.entity.custom.edit_custom;

import view.Screen;

import javax.swing.*;
import java.awt.*;

public class PassedExamInfoPanel extends JPanel {
    private JLabel averageField;
    private JLabel espbField;

    public PassedExamInfoPanel() {
        super();
        setLayoutForComponent(this, BoxLayout.Y_AXIS);
        JPanel panelAverage = new JPanel();
        JLabel avgText = new JLabel(Screen.getInstance().getResourceBundle().getString("averageMark"));
        averageField = new JLabel("");
        panelAverage.add(avgText, BorderLayout.CENTER);
        panelAverage.add(averageField, BorderLayout.EAST);

        JPanel panelEspb = new JPanel();
        JLabel espbText = new JLabel(Screen.getInstance().getResourceBundle().getString("espbNum"));
        espbField = new JLabel("");
        panelEspb.add(espbText);
        panelEspb.add(espbField);

        add(panelAverage);
        add(panelEspb);
        setVisible(true);
    }

    private void setLayoutForComponent(JComponent comp, int axis) {
        BoxLayout layout = new BoxLayout(comp, axis);
        comp.setLayout(layout);
    }

    public JLabel getAverageField() {
        return averageField;
    }

    public JLabel getEspbField() {
        return espbField;
    }
}
