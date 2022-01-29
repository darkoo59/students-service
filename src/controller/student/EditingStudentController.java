package controller.student;

import controller.EntityValidator;
import exceptions.InvalidFieldException;
import interfaces.IEditingController;
import model.Address;
import model.database.DataModel;
import model.Student;
import utils.Constants;
import utils.EnumConversion;
import view.Screen;
import view.entity.EditingScreen;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Vector;

public class EditingStudentController implements IEditingController {

    @Override
    public void editEntity(EditingScreen dialog) {
        // TODO Auto-generated method stub

        try {
            validate(dialog);
            String studentIndexBeforeEdit = Screen.getInstance().getMainTab().getSelectedStudentIndex();
            Student student = getEditedStudent(dialog);
            DataModel model = DataModel.getInstance();
            model.setEditedStudent(studentIndexBeforeEdit, student);
            JOptionPane.showMessageDialog(dialog, Screen.getInstance().getResourceBundle().getString("successEditStudent"));
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog((JDialog) dialog, e.getMessage(), Screen.getInstance().getResourceBundle().getString("error"), JOptionPane.WARNING_MESSAGE);
        }

    }

    public static String[] findStudentDataForFields(Student student) {
        String data[] = {"", "", "", "", "", "", "", "", "", ""};
        data[0] = student.getFirstName();
        data[1] = student.getLastName();
        data[2] = student.getBirthDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        data[3] = addressToString(student.getAddress());
        data[4] = student.getPhoneNumber();
        data[5] = student.getEmailAddress();
        data[6] = student.getIndexNumber();
        data[7] = Integer.toString(student.getEntryYear());
        data[8] = Integer.toString(student.getStudyYear());
        data[9] = student.getStatus().getValue();
        return data;
    }

    public static String addressToString(Address address) {
        if(address == null) return "null";
        String data = "";
        data = address.getCountry() + ":" + address.getCity() + ":" + address.getStreet() + ":" + address.getStreetNumber();
        return data;
    }

    public Student getEditedStudent(EditingScreen dialog) {
        String firstName = dialog.getTextField(0).getText();
        String lastName = dialog.getTextField(1).getText();
        LocalDate birthDate = LocalDate.parse(dialog.getTextField(2).getText());
        Address address = createAddressFromAddressString(dialog.getTextField(3).getText());
        String phoneNum = dialog.getTextField(4).getText();
        String email = dialog.getTextField(5).getText();
        String indexNum = dialog.getTextField(6).getText();
        int startingYear = Integer.parseInt(dialog.getTextField(7).getText());
        int currentYear = Integer.parseInt(dialog.getComboBox(8).getSelectedItem().toString());
        Constants.Status financing = EnumConversion.stringToStatus(dialog.getComboBox(9).getSelectedItem().toString());
        return new Student(firstName, lastName, birthDate, address, phoneNum, email, indexNum, startingYear,
                currentYear, financing, 0);
    }

    public static Address createAddressFromAddressString(String addressString) {
        String[] addressParts = addressString.split(":");
        return new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);
    }

    public static boolean checkIfFieldsIsEmpty(EditingScreen dialog) {
        Vector<JComponent> fields = dialog.getFieldsReferences();
        for (int i = 0; i < fields.size() - 2; i++) {
            JTextField textField = (JTextField) fields.get(i);
            if (textField.getText().trim().equals(""))
                return false;
        }
        return true;
    }

    @Override
    public void validate(EditingScreen dialog) throws InvalidFieldException {
        // TODO Auto-generated method stub
        EntityValidator validator = new EntityValidator();
        Vector<JComponent> fields = dialog.getFieldsReferences();
        for (int i = 0; i < fields.size() - 2; i++) {
            JTextField field = (JTextField) fields.get(i);
            if ((field.getName().toLowerCase(Locale.ROOT).contains("datum") || field.getName().toLowerCase(Locale.ROOT).contains("date"))
                    && !validator.isValidDate(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("dateFormat"));

            if (field.getText().trim().equals(""))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("emptyField"));

            if ((field.getName().toLowerCase(Locale.ROOT).contains("adresa") || field.getName().toLowerCase(Locale.ROOT).contains("address"))
                    && !field.getName().toLowerCase(Locale.ROOT).contains("e-mail")
                    && !validator.isValidAdressNumber(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("addressFormat"));

            if (!validator.isValidNumberField(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("numberFormat"));

            if ((field.getName().toLowerCase().contains("indeks") || field.getName().toLowerCase().contains("index"))
                    && !validator.isValidIndexNumberForEditing(field))
                validator.throwInvalidValidation(field, Screen.getInstance().getResourceBundle().getString("indexFormat"));
            validator.setEmptyMessage(field);
        }
    }
}
