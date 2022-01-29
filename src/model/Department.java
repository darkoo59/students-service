package model;

import java.util.ArrayList;

public class Department {
    private String departmentId;
    private String departmentName;
    private Professor headOfTheDepartment;
    private ArrayList<Professor> professorsList;

    public Department(String departmentId, String departmentName, Professor headOfTheDepartment) {
        super();
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headOfTheDepartment = headOfTheDepartment;
        this.professorsList = new ArrayList<Professor>();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Professor getHeadOfTheDepartment() {
        return headOfTheDepartment;
    }

    public void setHeadOfTheDepartment(Professor headOfTheDepartment) {
        this.headOfTheDepartment = headOfTheDepartment;
    }

    public ArrayList<Professor> getProfessorsList() {
        return professorsList;
    }

    public void setProfessorsList(ArrayList<Professor> professorsList) {
        this.professorsList = professorsList;
    }

    public String getDataAt(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return departmentId;
            case 1:
                return departmentName;
            case 2:
                return headOfTheDepartment == null ? "" : headOfTheDepartment.getFirstName() + " " + headOfTheDepartment.getLastName();
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return departmentId + "," + departmentName + "," + (headOfTheDepartment == null ? "null" : headOfTheDepartment.getIdNumber());
    }
}
