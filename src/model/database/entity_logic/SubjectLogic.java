package model.database.entity_logic;

import model.database.DataModel;
import model.Professor;
import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.Iterator;

public class SubjectLogic {
    private DataModel dataModel;

    public SubjectLogic(DataModel dm) {
        dataModel = dm;
    }

    public void addSubjectToList(Subject newSubject) {
        ArrayList<Subject> subjects = dataModel.getSubjects();
        subjects.add(newSubject);
        dataModel.notifyTable();
    }

    public Subject getSubjectById(String id) {
        ArrayList<Subject> subjects = dataModel.getSubjects();
        for (Subject subj : subjects) {
            if (subj.getSubjectId().equals(id)) {
                return subj;
            }
        }
        return null;
    }

    public void setEditedSubject(String oldId, Subject subjectNewInfo) {
        ArrayList<Subject> subjects = dataModel.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectId().equals(oldId)) {
                subjects.get(i).setSubjectId(subjectNewInfo.getSubjectId());
                subjects.get(i).setSubjectName(subjectNewInfo.getSubjectName());
                subjects.get(i).setSemester(subjectNewInfo.getSemester());
                subjects.get(i).setProfessor(subjectNewInfo.getProfessor());
                subjects.get(i).setEspb(subjectNewInfo.getEspb());
                subjects.get(i).setYearOfStudy(subjectNewInfo.getYearOfStudy());
                dataModel.notifyTable();
            }
        }
    }

    public boolean removeSubjectById(String id) {
        // Treba dodati uklanjanje svih zavisnosti entiteta u drugim listama
        ArrayList<Subject> subjects = dataModel.getSubjects();
        for (Subject subject : subjects) {
            if (subject.getSubjectId().equals(id)) {
                subjects.remove(subject);
                removeSubjectDependencies(id);
                dataModel.notifyTable();
                return true;
            }
        }
        return false;
    }

    public void removeSubjectDependencies(String id) {
        removeSubjectFromStudents(id);
        removeSubjectFromProfessors(id);
    }

    public void removeSubjectFromStudents(String id) {
        ArrayList<Student> students = dataModel.getStudents();
        for (Student student : students) {
            ArrayList<Subject> passed = student.getPassedSubjects();
            ArrayList<Subject> failed = student.getFailedSubjects();
            for (Iterator<Subject> subIt = passed.iterator(); subIt.hasNext(); ) {
                Subject subject = subIt.next();
                if (subject.getSubjectId().equals(id))
                    subIt.remove();
            }
            for (Iterator<Subject> subIt = failed.iterator(); subIt.hasNext(); ) {
                Subject subject = subIt.next();
                if (subject.getSubjectId().equals(id))
                    subIt.remove();
            }
        }
    }

    public void removeSubjectFromProfessors(String id) {
        ArrayList<Professor> professors = dataModel.getProfessors();
        for (Professor professor : professors) {
            ArrayList<Subject> profSubjects = professor.getSubjects();
            for (Iterator<Subject> its = profSubjects.iterator(); its.hasNext(); ) {
                Subject subject = its.next();
                if (subject.getSubjectId().equals(id)) {
                    its.remove();
                }
            }

        }
    }

    public void deleteProfessorFromSubject(String professorId, String subjectId) {
        Subject subject = dataModel.getSubjectById(subjectId);
        if (subject.getProfessor().getIdNumber().equals(professorId)) {
            subject.setProfessor(null);
        }
    }

    public void addProfessorToSubject(String professorId, String subjectId) {
        Subject subject = dataModel.getSubjectById(subjectId);
        subject.setProfessor(dataModel.getProfessorById(professorId));
    }

    public void addStudentToSubjectPassedList(String studentIndex, String subjectId) {
        Student student = dataModel.getStudentById(studentIndex);
        Subject subject = getSubjectById(subjectId);
        subject.getStudentsPassed().add(student);
    }

    public void loadPassedAndNotPassed() {
        ArrayList<Student> students = dataModel.getStudents();
        for(Student student: students) {
            for(Subject subject: student.getPassedSubjects()) {
                subject.getStudentsPassed().add(student);
            }
            for(Subject subject: student.getFailedSubjects()) {
                subject.getStudentsFailed().add(student);
            }
        }
    }




}
