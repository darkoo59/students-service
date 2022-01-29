package view.entity.abstract_model.list_model;

import model.database.DataModel;
import model.Professor;

import javax.swing.*;

public class ProfessorListModel extends AbstractListModel<String> {

    @Override
    public int getSize() {
        return DataModel.getInstance().getProfessors().size();
    }

    public Professor getObjectAt(int index) {
        return DataModel.getInstance().getProfessors().get(index);
    }

    @Override
    public String getElementAt(int index) {
        Professor professor = DataModel.getInstance().getProfessors().get(index);
        return professor.getFirstName() + " " + professor.getLastName();
    }
}
