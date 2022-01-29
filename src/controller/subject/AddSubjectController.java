package controller.subject;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IAddingController;
import model.database.DataModel;
import model.Professor;
import model.Subject;
import utils.Constants;
import utils.EnumConversion;
import view.Screen;
import view.entity.AddingScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;

public class AddSubjectController implements IAddingController {
    @Override
    public void addNewEntity(AddingScreen dialog) {
        try {
            validate(dialog);
            Subject subject = createSubjectObjectFromFields(dialog);
            DataModel.getInstance().addSubjectToList(subject);
            JOptionPane.showMessageDialog((JDialog) dialog, Screen.getInstance().getResourceBundle().getString("subjectToList"));
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog((JDialog) dialog, e.getMessage(), Screen.getInstance().getResourceBundle().getString("error"), JOptionPane.WARNING_MESSAGE);
        }
    }

    private Subject createSubjectObjectFromFields(AddingScreen subWin) {
        String id = subWin.getTextField(0).getText();
        String name = subWin.getTextField(1).getText();
        Constants.Semester semester = EnumConversion
                .stringToSemester(subWin.getComboBox(2).getSelectedItem().toString());
        String professorId = subWin.getTextField(3).getText();
        Professor professor = DataModel.getInstance().getProfessorById(professorId);
        int espb = Integer.parseInt(subWin.getTextField(4).getText());
        int studyYear = Integer.parseInt(subWin.getTextField(5).getText());
        return new Subject(id, name, semester, studyYear, professor, espb);
    }

    public void validate(AddingScreen window) throws InvalidFieldException {
        EntityValidator validator = new EntityValidator();
        ArrayList<JComponent> fields = window.getFieldsReferences();
        for (int i = 0; i < fields.size(); i++) {
            if (i == 2)
                continue;
            JTextField field = (JTextField) fields.get(i);
            if ((field.getName().toLowerCase(Locale.ROOT).contains("datum") || field.getName().toLowerCase(Locale.ROOT).contains("date")) && !validator.isValidDate(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("dateFormat"));
            if (field.getText().trim().equals(""))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("emptyField"));
            if (!validator.isValidNumberField(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("numberFormat"));
            if ((field.getName().toLowerCase().contains("id predmeta") || field.getName().toLowerCase().contains("subject id")) && !validator.isValidSubjectId(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("idSubjectFormat"));

            validator.setEmptyMessage(field);

        }
    }

}
