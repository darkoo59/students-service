package view.about;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constants;
import utils.ImageUtils;
import view.entity.custom.CustomLabel;

public class AboutApp extends JPanel{
	private String[] labelNames = Constants.getAppLabels();
	private String[] appInfo = Constants.getAppInfo();
	
	public AboutApp() {
		super();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);

		for(int i=0;i<5;i++) {
			String labelName = labelNames[i];
			add(createInfo(labelName,appInfo[i]));
		}
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("icons/FTN.png"));
		image = ImageUtils.scaleImage(image, Constants.SCREEN_WIDTH * 1 / 7, Constants.SCREEN_HEIGHT * 1 / 5);
		add(new JLabel(image),Component.CENTER_ALIGNMENT);
		setVisible(true);
	}
	
	private JPanel createInfo(String labelName,String info) {
		JPanel panel = new JPanel();
		CustomLabel lbl1 = new CustomLabel(labelName);
		panel.add(lbl1);
		CustomLabel lbl2 = new CustomLabel(info);
		lbl2.setForeground(Color.GRAY);
		panel.add(lbl2);
		return panel;
	}
}
