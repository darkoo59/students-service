package view.statusbar;

import view.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StatusBar extends JPanel {
    private JLabel tabName;
    private JLabel currentDateLabel;
    private JLabel appNameLabel;

    public StatusBar(JFrame frame) {
        super();
        setBackground(new Color(32,136,203));
        setPreferredSize(new Dimension(frame.getWidth(), 18));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        appNameLabel = new JLabel("Studentska Sluzba");
        appNameLabel.setForeground(Color.white);
        tabName = new JLabel("Studenti");
        tabName.setForeground(Color.white);
        currentDateLabel = new JLabel();
        currentDateLabel.setForeground(Color.white);

        add(Box.createHorizontalStrut(10));
        add(appNameLabel);
        add(Box.createHorizontalStrut(20));
        add(tabName);
        add(Box.createHorizontalGlue());
        add(currentDateLabel);
        add(Box.createHorizontalStrut(10));

        dynamicTime();
    }

    public void dynamicTime() {
        Thread time = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar calendar = new GregorianCalendar();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH) + 1;
                        int year = calendar.get(Calendar.YEAR);

                        int second = calendar.get(Calendar.SECOND);
                        int minute = calendar.get(Calendar.MINUTE);
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);

                        String currentTimeDate = String.format("%02d:%02d:%02d   %02d.%02d.%d.", hour, minute, second, day, month, year);
                        currentDateLabel.setText(currentTimeDate);

                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        time.start();
    }

    public void setTabName(String name) {
        tabName.setText(name);
    }

    public void changeLanguage(String tabName) {
        appNameLabel.setText(Screen.getInstance().getResourceBundle().getString("lblAppName"));
        setTabName(tabName);
    }
}
