package view.about;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import utils.Constants;
import view.entity.custom.CustomLabel;

public class AboutDarko extends JPanel{
	private String[] labelNames = Constants.getCreatorsLabels();
	private String[] darkoInfo = Constants.getDarkoInfo();
	
	public AboutDarko() {
		super();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);

		for(int i=0;i<8;i++) {
			String labelName = labelNames[i];
			add(createInfo(labelName,darkoInfo[i]));
		}
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
