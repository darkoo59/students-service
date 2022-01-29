package model;

import utils.Constants.Semester;

import java.util.ArrayList;

public class Subject {
    private String subjectId;
    private String subjectName;
    private Semester semester;
    private Professor professor;
    private int espb;
    private int yearOfStudy;
    private ArrayList<Student> studentsPassed;
    private ArrayList<Student> studentsFailed;

    public Subject(String subjectId, String subjectName, Semester semester, int yearOfStudy, Professor professor,
                   int espb) {
        super();
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.semester = semester;
        this.professor = professor;
        this.espb = espb;
        this.yearOfStudy = yearOfStudy;
        this.studentsPassed = new ArrayList<Student>();
        this.studentsFailed = new ArrayList<Student>();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public ArrayList<Student> getStudentsPassed() {
        return studentsPassed;
    }

    public void setStudentsPassed(ArrayList<Student> studentsPassed) {
        this.studentsPassed = studentsPassed;
    }

    public ArrayList<Student> getStudentsFailed() {
        return studentsFailed;
    }

    public void setStudentsFailed(ArrayList<Student> studentsFailed) {
        this.studentsFailed = studentsFailed;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Object getDataAt(int index) {
        switch (index) {
            case 0:
                return subjectId;
            case 1:
                return subjectName;
            case 2:
                return espb;
            case 3:
                return yearOfStudy;
            case 4:
                return semester.getValue();
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return subjectId + "," + subjectName + "," + semester.getValue() + "," + yearOfStudy + ","
                + (professor == null ? "null" : professor.getIdNumber()) + "," + espb;
    }
}
