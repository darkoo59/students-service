package model;

import utils.Constants.Status;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {

    private String lastName;
    private String firstName;
    private LocalDate birthDay;
    private Address address;
    private String phoneNumber;
    private String emailAddress;
    private String indexNumber;
    private int entryYear;
    private int studyYear;
    private Status status;
    private double averageMark;
    private ArrayList<Subject> passedSubjects;
    private ArrayList<Subject> failedSubjects;

    public Student(String firstName, String lastName, LocalDate birthDay, Address address, String phoneNumber,
                   String emailAddress, String indexNumber, int entryYear, int studyYear, Status status, double averageMark) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDay = birthDay;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.indexNumber = indexNumber;
        this.entryYear = entryYear;
        this.studyYear = studyYear;
        this.status = status;
        this.averageMark = averageMark;
        this.passedSubjects = new ArrayList<Subject>();
        this.failedSubjects = new ArrayList<Subject>();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(int entryYear) {
        this.entryYear = entryYear;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public ArrayList<Subject> getPassedSubjects() {
        return passedSubjects;
    }

    public void setPassedSubjects(ArrayList<Subject> passedSubjects) {
        this.passedSubjects = passedSubjects;
    }

    public ArrayList<Subject> getFailedSubjects() {
        return failedSubjects;
    }

    public void setFailedSubjects(ArrayList<Subject> failedSubjects) {
        this.failedSubjects = failedSubjects;
    }

    public void addFailedSubject(Subject subject) {
        this.failedSubjects.add(subject);
    }

    public void addPassedSubject(Subject subject) {
        this.passedSubjects.add(subject);
    }

    public void removeFailedSubject(String subjectId) {
        for (int i = 0; i < failedSubjects.size(); i++) {
            if (failedSubjects.get(i).getSubjectId().equals(subjectId)) {
                failedSubjects.remove(i);
                return;
            }
        }
    }

    public Object getDataAt(int index) {
        switch (index) {
            case 0:
                return indexNumber;
            case 1:
                return firstName;
            case 2:
                return lastName;
            case 3:
                return studyYear;
            case 4:
                return status.getValue();
            case 5:
                return averageMark;
            default:
                return "";
        }

    }


    @Override
    public String toString() {
        return firstName + "," + lastName + "," + birthDay + "," + (address != null?address.toString():"null") + "," + phoneNumber + ","
                + emailAddress + "," + indexNumber + "," + entryYear + "," + studyYear + "," + status.getValue() + ","
                + averageMark;
    }
}
