package model.database.entity_logic;

import model.database.DataModel;
import model.Mark;
import model.Student;
import model.Subject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class StudentLogic {
    private DataModel dataModel;

    public StudentLogic(DataModel dm) {
        dataModel = dm;
    }

    public void addStudentToList(Student newStudent) {
        dataModel.getStudents().add(newStudent);
        dataModel.notifyTable();
    }

    public Student getStudentById(String id) {
        ArrayList<Student> students = dataModel.getStudents();
        for (Student stud : students) {
            if (stud.getIndexNumber().equals(id)) {
                return stud;
            }
        }
        return null;
    }

    public void setEditedStudent(String oldIndex, Student studentNewInfo) {
        ArrayList<Student> students = dataModel.getStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getIndexNumber().equals(oldIndex)) {
                students.get(i).setFirstName(studentNewInfo.getFirstName());
                students.get(i).setLastName(studentNewInfo.getLastName());
                students.get(i).setBirthDay(studentNewInfo.getBirthDay());
                students.get(i).setAddress(studentNewInfo.getAddress());
                students.get(i).setPhoneNumber(studentNewInfo.getPhoneNumber());
                students.get(i).setEmailAddress(studentNewInfo.getEmailAddress());
                students.get(i).setIndexNumber(studentNewInfo.getIndexNumber());
                students.get(i).setEntryYear(studentNewInfo.getEntryYear());
                students.get(i).setStudyYear(studentNewInfo.getStudyYear());
                students.get(i).setStatus(studentNewInfo.getStatus());
                dataModel.notifyTable();
            }
        }
    }

    public boolean removeStudentByIndex(String index) {
        ArrayList<Student> students = dataModel.getStudents();
        for (Student student : students) {
            if (student.getIndexNumber().equals(index)) {
                students.remove(student);
                removeStudentDependencies(index);
                dataModel.notifyTable();
                return true;
            }

        }
        return false;
    }

    private void removeStudentDependencies(String index) {
        removeStudentFromPassed(index);
        removeStudentFromNotPassed(index);
        removeStudentFromMarks(index);
    }

    private void removeStudentFromPassed(String index) {
        ArrayList<Subject> subjects = dataModel.getSubjects();
        for (Subject subject : subjects) {
            ArrayList<Student> passed = subject.getStudentsPassed();
            for (Iterator<Student> studIt = passed.iterator(); studIt.hasNext(); ) {
                Student student = studIt.next();
                if (student.getIndexNumber().equals(index)) {
                    studIt.remove();
                    return;
                }
            }
        }
    }

    private void removeStudentFromNotPassed(String index) {
        ArrayList<Subject> subjects = dataModel.getSubjects();
        ArrayList<Student> students = dataModel.getStudents();
        for (Subject subject : subjects) {
            ArrayList<Student> failed = subject.getStudentsFailed();
            for (Iterator<Student> studIt = failed.iterator(); studIt.hasNext(); ) {
                Student student = studIt.next();
                if (student.getIndexNumber().equals(index)) {
                    studIt.remove();
                    return;
                }
            }
        }
    }

    private void removeStudentFromMarks(String index) {
        ArrayList<Mark> marks = dataModel.getMarks();
        for (Iterator<Mark> markIt = marks.iterator(); markIt.hasNext(); ) {
            Mark mark = markIt.next();
            if (mark.getPassedExam().getIndexNumber().equals(index)) {
                markIt.remove();
            }
        }
    }

    public void addFailedSubjectToStudent(String index, Subject subject) {
        ArrayList<Student> students = dataModel.getStudents();
        for (Student student : students) {
            if (student.getIndexNumber().equals(index)) {
                student.addFailedSubject(subject);
                subject.getStudentsFailed().add(student);
                //  dataModel.getFailedSubjects().add(subject);
                dataModel.notifyEditTable();
            }
        }

    }

    public ArrayList<Subject> getNewSubjectsForStudent(String index) {
        Student student = getStudentById(index);
        ArrayList<Subject> subjects = dataModel.getSubjects();
        ArrayList<Subject> failedSubjects = student.getFailedSubjects();
        ArrayList<Subject> passedSubjects = student.getPassedSubjects();
        ArrayList<Subject> resultList = new ArrayList<>();
        for (Subject subject : subjects) {
            if (!isSubjectFoundInList(subject.getSubjectId(), student.getFailedSubjects()) &&
                    !isSubjectFoundInList(subject.getSubjectId(), student.getPassedSubjects()) &&
                    student.getStudyYear() >= subject.getYearOfStudy()) {
                resultList.add(subject);
            }
        }
        return resultList;

    }

    public boolean isSubjectFoundInList(String subjectId, ArrayList<Subject> subjects) {
        for (Subject subject : subjects) {
            if (subject.getSubjectId().equals(subjectId))
                return true;
        }
        return false;
    }

    public boolean removeFailedSubjectFromStudentSubjects(String subjectId, String studentId) {
        for (Iterator<Student> studentIt = dataModel.getStudents().iterator(); studentIt.hasNext(); ) {
            Student student = studentIt.next();
            if (student.getIndexNumber().equals(studentId)) {
                student.removeFailedSubject(subjectId);
                removeStudentFromFailedInSubject(studentId, subjectId);
                dataModel.notifyEditTable();
                return true;
            }
        }
        return false;
    }

    public void removeStudentFromFailedInSubject(String studentId, String subjectId) {
        Subject subject = dataModel.getSubjectById(subjectId);
        for(Iterator<Student> studIt = subject.getStudentsFailed().iterator(); studIt.hasNext();) {
            Student student = studIt.next();
            if(student.getIndexNumber().equals(studentId)) {
                studIt.remove();
            }
        }

    }

    public boolean addPassedSubjectToStudent(String index, String subjectId) {
        for (Iterator<Student> studentIt = dataModel.getStudents().iterator(); studentIt.hasNext(); ) {
            Student student = studentIt.next();
            if (student.getIndexNumber().equals(index)) {
                student.addPassedSubject(dataModel.getSubjectById(subjectId));
                return true;
            }
        }
        return false;
    }

    public void undoMarkFromStudent(String subjectId, String studentId) {
        Subject subject = dataModel.getSubjectById(subjectId);
        Student student = dataModel.getStudentById(studentId);
        removeMarkForStudent(subjectId, studentId);
        switchSubjectFromPassedToFailed(subject.getSubjectId(), student);
        switchStudentFromPassedToFailed(student, subject);
        dataModel.notifyEditTable();
    }

    public void removeMarkForStudent(String subjectId, String studIndex) {
        ArrayList<Mark> marks = dataModel.getMarks();
        for (Iterator<Mark> markIt = marks.iterator(); markIt.hasNext(); ) {
            Mark mark = markIt.next();
            if (mark.getSubject().getSubjectId().equals(subjectId) && mark.getPassedExam().getIndexNumber().equals(studIndex)) {
                markIt.remove();
            }
        }
    }

    public void switchSubjectFromPassedToFailed(String id, Student student) {
        Subject savedSub = null;
        for (Iterator<Subject> subIt = student.getPassedSubjects().iterator(); subIt.hasNext(); ) {
            Subject subject = subIt.next();
            if (subject.getSubjectId().equals(id)) {
                savedSub = subject;
                subIt.remove();
                break;
            }
        }
        if (savedSub != null) student.addFailedSubject(savedSub);
    }

    public void switchStudentFromPassedToFailed(Student student, Subject subject) {
        Student savedStud = null;
        for (Iterator<Student> studIt = subject.getStudentsPassed().iterator(); studIt.hasNext(); ) {
            Student stud = studIt.next();
            if (stud.getIndexNumber().equals(student.getIndexNumber())) {
                studIt.remove();
                savedStud = stud;
                break;
            }
        }
        if (savedStud != null) subject.getStudentsFailed().add(savedStud);
    }

    public void calculateAverageForStudents() {
        ArrayList<Student> students = dataModel.getStudents();
        for(Student student: students) {
            ArrayList<Subject> passed = student.getPassedSubjects();
            if(passed.size() == 0) {
                student.setAverageMark(0);
                continue;
            }
            double average = 0;
            for(Subject subject: passed) {
                Mark mark = dataModel.getMarkByStudentAndSubject(student.getIndexNumber(), subject.getSubjectId());
                if(mark == null) {
                    continue;
                }
                average += mark.getMark().getValue();
            }

            average = average/passed.size();
            student.setAverageMark(cutDoubleTo2Decimal(average));
        }
    }

    private double cutDoubleTo2Decimal(double num) {
        int temp = (int)(num*100.0);
        double shortDouble = ((double)temp)/100.0;
        return shortDouble;
    }

}
