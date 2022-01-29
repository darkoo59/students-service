package utils;

import javax.swing.*;
import java.awt.*;

public class PropertyFactory {
	public static void addBlackBorder(JComponent comp, int thickness) {
		comp.setBorder(BorderFactory.createLineBorder(Color.black, thickness));
	}
}
