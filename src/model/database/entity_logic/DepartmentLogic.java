package model.database.entity_logic;

import model.database.DataModel;
import model.Department;
import model.Professor;

import java.util.ArrayList;
import java.util.Locale;

public class DepartmentLogic {
    private DataModel dataModel;

    public DepartmentLogic(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Department getDepartmentById(String id) {
        ArrayList<Department> departments = dataModel.getDepartments();
        for (Department department : departments)
            if (department.getDepartmentId().equals(id)) return department;
        return null;
    }
    public ArrayList<Professor> filterProfessorForHeadOfDep(String depId) {
        ArrayList<Professor> filtered = new ArrayList<>();
        Department department = getDepartmentById(depId);
        for(Professor professor : department.getProfessorsList()) {
            if(professor.getWorkingYears() > 5 && (professor.getTitle().toLowerCase().equals("redovni_profesor") || professor.getTitle().toLowerCase().equals("vanredni_profesor"))) {
                filtered.add(professor);
            }
        }

        return filtered;
    }

    public ArrayList<Professor> filterProfessorsWithoutDepartment() {
        ArrayList<Professor> professors = dataModel.getProfessors();
        ArrayList<Professor> filtered = new ArrayList<>();
        for(Professor professor: professors)
            if (professor.getDepartment().equals("null")) filtered.add(professor);

        return filtered;
    }

    public void editDepartment(Department department, String oldDepId) {
        Department dep = getDepartmentById(oldDepId);
        dep.setDepartmentId(department.getDepartmentId());
        dep.setDepartmentName(department.getDepartmentName());
        dep.setHeadOfTheDepartment(department.getHeadOfTheDepartment());
    }

    public void loadProfessorsToDepartments() {
        ArrayList<Department> departments = dataModel.getDepartments();
        ArrayList<Professor> professors = dataModel.getProfessors();
        for(Department department: departments) {
            ArrayList<Professor> depProfs = new ArrayList<>();
            for(Professor professor: professors) {
                if(department.getDepartmentId().equals(professor.getDepartment())) {
                    depProfs.add(professor);
                }
            }
            department.setProfessorsList(depProfs);
        }
    }
}
