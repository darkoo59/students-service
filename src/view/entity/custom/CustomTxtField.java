package view.entity.custom;

import controller.student.TakingExamController;
import exceptions.InvalidFieldException;
import interfaces.IAddingController;
import interfaces.IEditingController;
import view.Screen;
import view.entity.AddingScreen;
import view.entity.EditingScreen;
import view.entity.student.StudentEnteringMark;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CustomTxtField extends JTextField implements DocumentListener {


    public CustomTxtField(String name) {
        super();
        setName(name);
        setPreferredSize(new Dimension(150, 30));
        this.getDocument().addDocumentListener(this);
    }

    public void setEdit(Boolean trueOrFalse) {
        this.setEditable(trueOrFalse);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        executeValidation();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        executeValidation();
    }

    @Override

    public void changedUpdate(DocumentEvent e) {
        executeValidation();
    }


    private void executeValidation() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof AddingScreen) {
            AddingScreen addingScreen = (AddingScreen) window;
            IAddingController controller = Screen.getInstance().getMainTab().getAddingController();
            try {
                controller.validate(addingScreen);
                addingScreen.getTenex().getButtonConfirm().setEnabled(true);
            } catch (InvalidFieldException exception) {
                addingScreen.getTenex().getButtonConfirm().setEnabled(false);
            }
        } else if (window instanceof EditingScreen) {
            EditingScreen editingScreen = (EditingScreen) window;
            IEditingController controller = Screen.getInstance().getMainTab().getEditingController();
            try {
                controller.validate(editingScreen);
                editingScreen.getEnterExit().getButtonConfirm().setEnabled(true);
            } catch (InvalidFieldException exception) {
                editingScreen.getEnterExit().getButtonConfirm().setEnabled(false);
            }
        } else if (window instanceof StudentEnteringMark) {
            StudentEnteringMark editingScreen = (StudentEnteringMark) window;
            TakingExamController controller = editingScreen.getExamController();
            try {
                controller.validate(editingScreen);
                editingScreen.getEnterExit().getButtonConfirm().setEnabled(true);
            } catch (InvalidFieldException excpetion) {
                editingScreen.getEnterExit().getButtonConfirm().setEnabled(false);
            }
        }
    }
}
