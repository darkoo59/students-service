package view.entity.custom;

import javax.swing.*;
import java.awt.*;

public class ErrorMessageLabel extends JLabel {
    public ErrorMessageLabel(String text, int width, int height) {
        super(text);
        this.setFont(new Font("serif", Font.BOLD, 9));
        this.setForeground(Color.red);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
    }
}
