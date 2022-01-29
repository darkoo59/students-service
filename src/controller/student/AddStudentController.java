package controller.student;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IAddingController;
import model.Address;
import model.database.DataModel;
import model.Student;
import utils.Constants;
import utils.EnumConversion;
import view.Screen;
import view.entity.AddingScreen;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class AddStudentController implements IAddingController {
    @Override
    public void addNewEntity(AddingScreen dialog) {
        try {
            validate(dialog);
            Student student = createStudentObjectFromFields(dialog);
            DataModel.getInstance().addStudentToList(student);
            JOptionPane.showMessageDialog((JDialog) dialog, Screen.getInstance().getResourceBundle().getString("studentToList"));
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog((JDialog) dialog, e.getMessage(), Screen.getInstance().getResourceBundle().getString("error"), JOptionPane.WARNING_MESSAGE);
        }
    }

    private Student createStudentObjectFromFields(AddingScreen studWin) {
        String firstName = studWin.getTextField(0).getText();
        String secondName = studWin.getTextField(1).getText();
        LocalDate birthDate = LocalDate.parse(studWin.getTextField(2).getText());
        Address address = createAddressFromAddressString(studWin.getTextField(3).getText());
        String phoneNum = studWin.getTextField(4).getText();
        String email = studWin.getTextField(5).getText();
        String indexNum = studWin.getTextField(6).getText();
        int startingYear = Integer.parseInt(studWin.getTextField(7).getText());
        int currentYear = Integer.parseInt(studWin.getComboBox(8).getSelectedItem().toString());
        Constants.Status financing = EnumConversion.stringToStatus(studWin.getComboBox(9).getSelectedItem().toString());
        return new Student(firstName, secondName, birthDate, address, phoneNum, email, indexNum, startingYear,
                currentYear, financing, 0);
    }

    private Address createAddressFromAddressString(String addString) {
        String[] addressParts = addString.split(":");
        return new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);
    }

    public void validate(AddingScreen window) throws InvalidFieldException {
        EntityValidator validator = new EntityValidator();
        ArrayList<JComponent> fields = window.getFieldsReferences();
        for (int i = 0; i < fields.size() - 2; i++) {
            JTextField field = (JTextField) fields.get(i);

            if ((field.getName().toLowerCase(Locale.ROOT).contains("datum") || field.getName().toLowerCase(Locale.ROOT).contains("date")) && !validator.isValidDate(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("dateFormat"));

            if (field.getText().trim().equals(""))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("emptyField"));

            if ((field.getName().toLowerCase(Locale.ROOT).contains("adresa") || field.getName().toLowerCase(Locale.ROOT).contains("address"))
                    && !field.getName().toLowerCase(Locale.ROOT).contains("e-mail")
                    && !validator.isValidAdressNumber(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("addressFormat"));

            if (!validator.isValidNumberField(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("numberFormat"));

            if ((field.getName().toLowerCase().contains("indeks") || field.getName().toLowerCase().contains("index")) && !validator.isValidIndexNumber(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("indexFormat"));
            validator.setEmptyMessage(field);
        }
    }


}
