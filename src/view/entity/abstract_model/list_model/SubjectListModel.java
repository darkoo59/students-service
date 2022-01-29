package view.entity.abstract_model.list_model;

import model.Subject;

import javax.swing.*;
import java.util.ArrayList;

public class SubjectListModel extends AbstractListModel<String> {
    private ArrayList<Subject> subjects;

    public SubjectListModel(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public Subject getSelectedSubject(int index) {
        return subjects.get(index);
    }

    @Override
    public int getSize() {
        return subjects.size();
    }

    @Override
    public String getElementAt(int index) {
        return subjects.get(index).getSubjectName();
    }

}
