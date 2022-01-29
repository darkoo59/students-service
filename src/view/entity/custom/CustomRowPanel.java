package view.entity.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustomRowPanel extends JPanel {
    public CustomRowPanel(JLabel label) {
        super();
        setLayout(new GridLayout(1, 2));
        setMaximumSize(new Dimension(500, 30));

        JPanel left = new JPanel(new BorderLayout());
        left.add(label, BorderLayout.NORTH);

        add(left);


    }
}
