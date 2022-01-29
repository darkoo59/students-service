package controller.professor;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IAddingController;
import model.Address;
import model.database.DataModel;
import model.Professor;
import view.Screen;
import view.entity.AddingScreen;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class AddProfessorController implements IAddingController {
    @Override
    public void addNewEntity(AddingScreen dialog) {
        try {
            validate(dialog);
            Professor professor = createProfessorObjectFromFields(dialog);
            DataModel.getInstance().addProfessorToList(professor);
            JOptionPane.showMessageDialog((JDialog) dialog, Screen.getInstance().getResourceBundle().getString("professorToList"));
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog((JDialog) dialog, e.getMessage(), Screen.getInstance().getResourceBundle().getString("error"), JOptionPane.WARNING_MESSAGE);
        }
    }

    private Professor createProfessorObjectFromFields(AddingScreen profWin) {
        String firstName = profWin.getTextField(0).getText();
        String secondName = profWin.getTextField(1).getText();
        LocalDate birthDate = LocalDate.parse(profWin.getTextField(2).getText());
        Address address = createAddressFromAddressString(profWin.getTextField(3).getText());
        String phone = profWin.getTextField(4).getText();
        String email = profWin.getTextField(5).getText();
        Address office = createAddressFromAddressString(profWin.getTextField(6).getText());
        String id = profWin.getTextField(7).getText();
        String title = profWin.getTextField(8).getText();
        int experience = Integer.parseInt(profWin.getTextField(9).getText());

        return new Professor(firstName, secondName, birthDate, address, phone, email, office, id, title, experience, "null");
    }

    private Address createAddressFromAddressString(String addString) {
        String[] addressParts = addString.split(":");
        return new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);
    }

    public void validate(AddingScreen tnp) throws InvalidFieldException {
        EntityValidator validator = new EntityValidator();
        ArrayList<JComponent> fields = tnp.getFieldsReferences();
        for (int i = 0; i < fields.size(); i++) {
            JTextField field = (JTextField) fields.get(i);
            if ((field.getName().toLowerCase(Locale.ROOT).contains("datum") || field.getName().toLowerCase(Locale.ROOT).contains("date")) && !validator.isValidDate(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("dateFormat"));

            if (field.getText().trim().equals(""))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("emptyField"));

            if ((field.getName().toLowerCase(Locale.ROOT).contains("adresa") || field.getName().toLowerCase(Locale.ROOT).contains("address")) && !field.getName().toLowerCase(Locale.ROOT).contains("e-mail") && !validator.isValidAdressNumber(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("addressFormat"));

            if (!validator.isValidNumberField(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("numberFormat"));

            if ((field.getName().toLowerCase().contains("broj licne karte") || field.getName().toLowerCase().contains("id number")) && !validator.isValidLBO(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("idFormat"));
            validator.setEmptyMessage(field);
        }
    }

}
