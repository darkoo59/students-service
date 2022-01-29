package view.entity.abstract_model.list_model;

import model.database.DataModel;
import model.Professor;
import view.Screen;

import javax.swing.*;
import java.util.ArrayList;

public class DepartmentListModel extends AbstractListModel {
    private ArrayList<Professor> professors;

    public DepartmentListModel(ArrayList<Professor> professors) {
        this.professors = professors;
    }
    @Override
    public int getSize() {
        //String depId = Screen.getInstance().getMainTab().getSelectedDepartmentId();
        //return DataModel.getInstance().filterProfessorForHeadOfDep(depId).size();
        return professors.size();
    }

    public Professor getObjectAt(int index) {
        //String depId = Screen.getInstance().getMainTab().getSelectedDepartmentId();
       // return DataModel.getInstance().filterProfessorForHeadOfDep(depId).get(index);
        return professors.get(index);
    }

    @Override
    public Object getElementAt(int index) {
//        String depId = Screen.getInstance().getMainTab().getSelectedDepartmentId();
//        Professor professor = DataModel.getInstance().filterProfessorForHeadOfDep(depId).get(index);
        Professor professor = professors.get(index);
        return professor.getFirstName() + " " + professor.getLastName();
    }


}
