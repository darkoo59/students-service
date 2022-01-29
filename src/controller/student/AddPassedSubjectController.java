package controller.student;

import model.database.DataModel;
import model.Mark;
import model.Student;
import model.Subject;
import view.entity.abstract_model.table_model.PassedSubjectsTableModel;
import view.entity.custom.edit_custom.PassedExamInfoPanel;
import view.entity.custom.edit_custom.PassedSubjectsButtons;
import view.Screen;
import view.entity.table.Table;
import view.entity.student.StudentEditPassedPanel;

import javax.swing.*;
import java.util.ArrayList;

public class AddPassedSubjectController {

    public void addNewPassedSubject(Subject subId) {

    }

    public void setESPBAndAverage(PassedExamInfoPanel infoPanel) {
        JLabel lblAvg = infoPanel.getAverageField();
        JLabel lblEspb = infoPanel.getEspbField();
        String studIndex = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        double average = calculateAverageMarkForStudent(studIndex);
        int espb = calculateSumOfESPB(studIndex);
        lblAvg.setText(average + "");
        lblEspb.setText(espb + "");
        Student student = DataModel.getInstance().getStudentById(Screen.getInstance().getMainTab().getSelectedStudentIndex());
        student.setAverageMark(average);
    }

    public void undoMark(PassedSubjectsButtons reference) {

        StudentEditPassedPanel studPassedPanel = (StudentEditPassedPanel) reference.getParent();
        Table marksTable = studPassedPanel.getPassedSubjectsTable();

        if (marksTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"),
                    Screen.getInstance().getResourceBundle().getString("advice"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("areYouSureRemove"),
                Screen.getInstance().getResourceBundle().getString("annulment"), JOptionPane.YES_NO_OPTION);
        if (result != 0) return;
        PassedSubjectsTableModel pstm = (PassedSubjectsTableModel) marksTable.getModel();
        String subId = pstm.getSelectedSubjectId(marksTable.getSelectedRow());
        String studId = Screen.getInstance().getMainTab().getSelectedStudentIndex();
        DataModel.getInstance().undoMarkFromStudent(subId, studId);
        StudentEditPassedPanel tesp = (StudentEditPassedPanel) reference.getParent();
        tesp.setESPBAndAverage();
    }

    public double calculateAverageMarkForStudent(String studId) {
        DataModel model = DataModel.getInstance();
        Student student = model.getStudentById(studId);
        if (student == null) return 0;
        ArrayList<Subject> subjectsPassed = student.getPassedSubjects();
        ArrayList<Mark> allMarks = model.getMarks();
        double averageMark = 0;
        for (Mark mark : allMarks) {
            for (Subject subject : subjectsPassed) {
                if (mark.getSubject().getSubjectId().equals(subject.getSubjectId())
                        && mark.getPassedExam().getIndexNumber().equals(student.getIndexNumber())) {
                    averageMark += mark.getMark().getValue();
                }
            }
        }
        if (subjectsPassed.size() == 0) return 0;
        return averageMark / (double) subjectsPassed.size();
    }

    public int calculateSumOfESPB(String studId) {
        Student student = DataModel.getInstance().getStudentById(studId);
        int count = 0;
        for (Subject subject : student.getPassedSubjects())
            count += subject.getEspb();
        return count;
    }
}
