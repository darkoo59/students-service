package view.entity.custom;

import javax.swing.*;
import java.awt.*;

public class PanelFieldError extends JPanel {
    public PanelFieldError(JTextField field, JLabel label) {
        this.setPreferredSize(new Dimension(field.getPreferredSize().width,
                field.getPreferredSize().height + label.getPreferredSize().height));
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.add(field);
        this.add(Box.createVerticalStrut(2));
        this.add(label);
    }
}
