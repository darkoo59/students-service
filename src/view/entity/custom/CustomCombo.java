package view.entity.custom;

import javax.swing.*;
import java.awt.*;

public class CustomCombo extends JComboBox {


    public CustomCombo(String[] dataList) {
        super(dataList);
        setPreferredSize(new Dimension(150, 30));


    }

}
