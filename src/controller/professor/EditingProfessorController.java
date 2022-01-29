package controller.professor;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IEditingController;
import model.Address;
import model.database.DataModel;
import model.Professor;
import view.Screen;
import view.entity.EditingScreen;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Vector;

public class EditingProfessorController implements IEditingController {

    @Override
    public void editEntity(EditingScreen dialog) {
        // TODO Auto-generated method stub

        try {
            validate(dialog);
            String professorIdBeforeEdit = Screen.getInstance().getMainTab().getSelectedProfessorId();
            Professor professor = getEditedProfessor(dialog);
            DataModel model = DataModel.getInstance();
            model.setEditedProfessor(professorIdBeforeEdit, professor);
            JOptionPane.showMessageDialog(dialog, Screen.getInstance().getResourceBundle().getString("successEdit"));
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog((JDialog) dialog, e.getMessage(), Screen.getInstance().getResourceBundle().getString("error"), JOptionPane.WARNING_MESSAGE);
        }
    }

    public Professor getEditedProfessor(EditingScreen dialog) {
        String firstName = dialog.getTextField(0).getText();
        String lastName = dialog.getTextField(1).getText();
        LocalDate birthDate = LocalDate.parse(dialog.getTextField(2).getText());
        Address address = createAddressFromAddressString(dialog.getTextField(3).getText());
        String phoneNum = dialog.getTextField(4).getText();
        String email = dialog.getTextField(5).getText();
        Address officeAddress = createAddressFromAddressString(dialog.getTextField(6).getText());
        String idNumber = dialog.getTextField(7).getText();
        String title = dialog.getTextField(8).getText();
        int workingYears = Integer.parseInt(dialog.getTextField(9).getText());
        return new Professor(firstName, lastName, birthDate, address, phoneNum, email, officeAddress, idNumber, title,
                workingYears, null);
    }

    public Address createAddressFromAddressString(String addressString) {
        String[] addressParts = addressString.split(":");
        return new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);
    }

    public boolean checkIfFieldsIsEmpty(EditingScreen dialog) {
        Vector<JComponent> fields = dialog.getFieldsReferences();
        for (int i = 0; i < fields.size() - 2; i++) {
            JTextField textField = (JTextField) fields.get(i);
            if (textField.getText().trim().equals(""))
                return false;
        }
        return true;
    }

    public static String[] findProfessorDataForFields(Professor professor) {
        String data[] = {"", "", "", "", "", "", "", "", "", ""};
        data[0] = professor.getFirstName();
        data[1] = professor.getLastName();
        data[2] = professor.getBirthDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        data[3] = addressToString(professor.getAddress());
        data[4] = professor.getPhoneNumber();
        data[5] = professor.getEmailAddress();
        data[6] = addressToString(professor.getOfficeAddress());
        data[7] = professor.getIdNumber();
        data[8] = professor.getTitle();
        data[9] = Integer.toString(professor.getWorkingYears());
        return data;
    }

    public static String addressToString(Address address) {
        String data = "";
        data = address.getCountry() + ":" + address.getCity() + ":" + address.getStreet() + ":" + address.getStreetNumber();
        return data;
    }

    @Override
    public void validate(EditingScreen dialog) throws InvalidFieldException {
        // TODO Auto-generated method stub
        EntityValidator validator = new EntityValidator();
        Vector<JComponent> fields = dialog.getFieldsReferences();
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
            if ((field.getName().toLowerCase().contains("broj licne karte") || field.getName().toLowerCase().contains("id number"))
                    && !validator.isValidLBOForEditing(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("idFormat"));
            validator.setEmptyMessage(field);
        }
    }

}
