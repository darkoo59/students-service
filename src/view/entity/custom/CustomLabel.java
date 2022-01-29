package view.entity.custom;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {


    public CustomLabel(String text) {
        super(text);
        setPreferredSize(new Dimension(200, 30));
//		setFocusable(true);
    }

}
