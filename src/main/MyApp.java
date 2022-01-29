package main;

import model.database.DataModel;
import view.Screen;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyApp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Screen scr = Screen.getInstance();
        DataModel model = DataModel.getInstance();
        scr.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                DataModel.getInstance().writeDataToFiles();

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

}
