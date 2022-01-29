package view.toolbar;

import utils.Constants;
import utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class ToolbarIconButton extends JButton {

    public ToolbarIconButton(String path, String tooltip, char accelerator) {
        super(new ToolbarAbstractAction(accelerator));
        ImageIcon icon = ImageUtils.scaleImage(ImageUtils.readImageIcon(path), Constants.ICON_BUTTON_WIDTH, Constants.ICON_BUTTON_HEIGHT);
        this.setIcon(icon);
        setSize(20, 20);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setToolTipText(tooltip);

    }

}
