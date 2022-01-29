package view.entity.custom;

import javax.swing.*;
import java.awt.*;

public class CustomSeparator extends JSeparator {
    int width;

    public CustomSeparator(int width) {
        super();
        setPreferredSize(new Dimension(width, 0));
        setBorder(BorderFactory.createEmptyBorder());
    }

    public CustomSeparator() {
        setPreferredSize(new Dimension(20, 0));
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Dimension getMaximumSize() {
        // TODO Auto-generated method stub
        return new Dimension(100, 0);
    }


}
