package model.database;

import model.*;
import model.database.dto.PassedSubject;
import model.database.entity_logic.DepartmentLogic;
import model.database.entity_logic.ProfessorLogic;
import model.database.entity_logic.StudentLogic;
import model.database.entity_logic.SubjectLogic;
import utils.Constants;
import view.entity.table.Table;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataModel {
    //Data lists
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
    private ArrayList<Subject> subjects;
    private ArrayList<Department> departments;
    private ArrayList<Mark> marks;
    private ArrayList<Address> addresses;
    //Observer tables
    private Table tableObserver;
    private Table editTableObserver;
    //Instance
    private static DataModel instance = null;
    //Logic classes
    private StudentLogic studLogic;
    private ProfessorLogic profLogic;
    private SubjectLogic subjLogic;
    private DepartmentLogic depLogic;

    private DataModel() {
        DataReader reader = new DataReader(this);
        studLogic = new StudentLogic(this);
        profLogic = new ProfessorLogic(this);
        subjLogic = new SubjectLogic(this);
        depLogic = new DepartmentLogic(this);
        try {
            String basepath = Constants.basepath;
            students = reader.readEntityFromFile(basepath + "studenti.txt", "Student");
            professors = reader.readEntityFromFile(basepath + "profesori.txt", "Professor");
            subjects = reader.readEntityFromFile(basepath + "predmeti.txt", "Subject");
            departments = reader.readEntityFromFile(basepath + "katedre.txt", "Department");
            marks = reader.readEntityFromFile(basepath + "ocene.txt", "Mark");
            reader.readStudentSubjectsFromFile(basepath + "nepolozeni.txt", "nepolozeni");
            reader.readStudentSubjectsFromFile(basepath + "polozeni.txt", "polozeni");
            subjLogic.loadPassedAndNotPassed();
            profLogic.loadSubjectsForProfessor();
            depLogic.loadProfessorsToDepartments();
            studLogic.calculateAverageForStudents();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    // Writing data to files
    public void writeDataToFiles() {
        DataWriter writer = new DataWriter();
        String basepath = Constants.basepath;
        writer.writeEntitiesToFile(basepath + "studenti.txt", students);
        writer.writeEntitiesToFile(basepath + "profesori.txt", professors);
        writer.writeEntitiesToFile(basepath + "predmeti.txt", subjects);
        writer.writeEntitiesToFile(basepath + "katedre.txt", departments);
        writer.writeEntitiesToFile(basepath + "ocene.txt", marks);
        writer.writeFailedSubjectsToFile(basepath + "nepolozeni.txt", students);
        writer.writePassedSubjectsToFile(basepath + "polozeni.txt", getAllPassedSubjectsList());
        writer.writeProfessorSubjectsToFile(basepath + "profesor_predmeti.txt", professors);
    }

    // Entity find by unique id methods
    public Professor getProfessorById(String id) {
        return profLogic.getProfessorById(id);
    }

    public Student getStudentById(String id) {
        return studLogic.getStudentById(id);
    }

    public Subject getSubjectById(String id) {
        return subjLogic.getSubjectById(id);
    }

    public Department getDepartmentById(String id) {
        return depLogic.getDepartmentById(id);
    }

    public String getProfessorIdFromEmail(String email) {
        return profLogic.getProfessorIdFromEmail(email);
    }

    // Adding entities methods
    public void addProfessorToList(Professor newProfessor) {
        profLogic.addProfessorToList(newProfessor);
    }

    public void addStudentToList(Student newStudent) {
        studLogic.addStudentToList(newStudent);
    }

    public void addSubjectToList(Subject newSubject) {
        subjLogic.addSubjectToList(newSubject);
    }

    public void addMarkToList(Mark mark) {
        marks.add(mark);
    }

    // Editing entities methods
    public void setEditedStudent(String oldIndex, Student studentNewInfo) {
        studLogic.setEditedStudent(oldIndex, studentNewInfo);
    }

    public void setEditedProfessor(String oldId, Professor professorNewInfo) {
        profLogic.setEditedProfessor(oldId, professorNewInfo);
    }

    public void setEditedSubject(String oldId, Subject subjectNewInfo) {
        subjLogic.setEditedSubject(oldId, subjectNewInfo);
    }

    // Deleting entities methods
    public boolean removeStudentByIndex(String index) {
        return studLogic.removeStudentByIndex(index);
    }

    public boolean removeProfessorById(String id) {
        return profLogic.removeProfessorById(id);
    }

    public boolean removeSubjectById(String id) {
        return subjLogic.removeSubjectById(id);
    }


    public ArrayList<Subject> getNewSubjectsForStudent(String index) {
        return studLogic.getNewSubjectsForStudent(index);
    }

    public ArrayList<Subject> getNewSubjectsForProfessor(String id) {
        return profLogic.getNewSubjectsForProfessor(id);
    }

    public int getLinesOfFailedToWrite() {
        int lines = 0;
        for (Student student : students) {
            lines += student.getFailedSubjects().size();
        }
        return lines;
    }

    public int getLinesOfProfessorSubjectsToWrite() {
        int lines = 0;
        for (Professor professor : professors) {
            lines += professor.getSubjects().size();
        }
        return lines;
    }

    public ArrayList<PassedSubject> getAllPassedSubjectsList() {
        ArrayList<PassedSubject> passedSubs = new ArrayList<>();
        for (Student student : students) {
            for (Subject subject : student.getPassedSubjects()) {
                passedSubs.add(new PassedSubject(student.getIndexNumber(), subject.getSubjectId()));
            }
        }
        return passedSubs;
    }

    public Mark getMarkByStudentAndSubject(String studId, String subId) {
        for (Mark mark : marks) {
            if (mark.getSubject().getSubjectId().equals(subId) && mark.getPassedExam().getIndexNumber().equals(studId)) {
                return mark;
            }
        }

        return null;
    }

    public void editDepartment(Department department, String oldDepId) {
        depLogic.editDepartment(department, oldDepId);
    }

    public boolean removeSubjectFromProfessorSubjects(String subjectId, String professorId) {
        return profLogic.removeSubjectFromProfessorSubjects(subjectId, professorId);
    }

    //Failed subjects logic
    public boolean removeFailedSubjectFromStudentSubjects(String subjectId, String studentId) {
        return studLogic.removeFailedSubjectFromStudentSubjects(subjectId, studentId);
    }

    public void addFailedSubjectToStudent(String index, Subject subject) {
        studLogic.addFailedSubjectToStudent(index, subject);
    }

    public void addSubjectToProfessor(String id, Subject subject) {
        profLogic.addSubjectToProfessor(id, subject);
    }

    public void undoMarkFromStudent(String subId, String studId) {
        studLogic.undoMarkFromStudent(subId, studId);
    }

    public void addPassedSubjectToStudent(String index, String subjectId) {
        studLogic.addPassedSubjectToStudent(index, subjectId);
    }

    public void deleteProfessorFromSubject(String professorId, String subjectId) {
        subjLogic.deleteProfessorFromSubject(professorId, subjectId);
    }

    public void addStudentToSubjectPassedList(String studentIndex, String subjectId) {
        subjLogic.addStudentToSubjectPassedList(studentIndex, subjectId);
    }

    public void addProfessorToSubject(String professorId, String subjectId) {
        subjLogic.addProfessorToSubject(professorId, subjectId);
    }

    public ArrayList<Professor> filterProfessorsWithoutDepartment() {
        return depLogic.filterProfessorsWithoutDepartment();
    }

    public ArrayList<Professor> filterProfessorForHeadOfDep(String depId) {
        return depLogic.filterProfessorForHeadOfDep(depId);
    }


    //Singleton implementation
    public static DataModel getInstance() {
        if (instance == null)
            instance = new DataModel();
        return instance;
    }

    //Observer methods
    public void setTableObserver(Table table) {
        tableObserver = table;
    }

    public void notifyTable() {
        AbstractTableModel model = (AbstractTableModel) tableObserver.getModel();
        model.fireTableDataChanged();
    }

    public void setEditTableObserver(Table table) {
        editTableObserver = table;
    }

    public void notifyEditTable() {
        AbstractTableModel model = (AbstractTableModel) editTableObserver.getModel();
        model.fireTableDataChanged();
    }
    // Getters and setters
    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Mark> marks) {
        this.marks = marks;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

}
