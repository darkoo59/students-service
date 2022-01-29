package model.database;

import model.*;
import utils.DataUtils;
import utils.EnumConversion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    private DataModel dm;

    public DataReader(DataModel dm) {
        this.dm = dm;
    }

    public <T> ArrayList<T> readEntityFromFile(String fileName, String className) throws FileNotFoundException {
        ArrayList<T> entityList = new ArrayList<T>();
        Scanner scanner = new Scanner(new FileInputStream(fileName), "UTF-8");
        while (scanner.hasNextLine()) {
            String scannedData = scanner.nextLine();
            if(scannedData.trim().equals("")) return entityList;
            String[] data = splitScannedData(scannedData);
            T obj = null;
            if (className.equals("Student"))
                obj = (T) loadStudentObject(trimSplittedString(data));
            else if (className.equals("Professor"))
                obj = (T) loadProfessorObject(trimSplittedString(data));
            else if (className.equals("Subject"))
                obj = (T) loadSubjectObject(trimSplittedString(data));
            else if (className.equals("Department"))
                obj = (T) loadDepartmentObject(trimSplittedString(data));
            else if (className.equals("Mark"))
                obj = (T) loadMarkObject(trimSplittedString(data));
            else if (className.equals("Address"))
                obj = (T) loadAddressObject(trimSplittedString(scannedData.split(":")));
            else if (className.equals("FailedSubjects"))
                obj = (T) loadFailedSubjectsObject(trimSplittedString(data));
            entityList.add(obj);
        }
        scanner.close();
        return entityList;
    }

    public void readStudentSubjectsFromFile(String fileName, String listName) throws FileNotFoundException {
    	Scanner scanner = new Scanner(new FileInputStream(fileName), "UTF-8");
        while (scanner.hasNextLine()) {
            String scannedData = scanner.nextLine();
            String[] data = splitScannedData(scannedData);
            Student student = dm.getStudentById(data[0]);

            Subject subject = dm.getSubjectById(data[1]);
            if (listName.equals("nepolozeni"))
                student.addFailedSubject(subject);
            else
                student.addPassedSubject(subject);
        }
    }


    public void readProfessorSubjectsFromFile(String fileName) throws FileNotFoundException {
    	Scanner scanner = new Scanner(new FileInputStream(fileName), "UTF-8");
    	while (scanner.hasNextLine()) {
            String rowData = scanner.nextLine();
            String[] dataSplit = rowData.split(",");
            Professor professor = dm.getProfessorById(dataSplit[0]);
            professor.getSubjects().add(dm.getSubjectById(dataSplit[1]));
        }
        scanner.close();
    }


    private Professor loadProfessorObject(String[] data) {
        return new Professor(data[0], data[1], LocalDate.parse(data[2]), convertStringToAddress(data[3]), data[4],
                data[5], loadAddressObject(data[6].split(":")), data[7], data[8], Integer.parseInt(data[9]), data[10]);
    }

    private Student loadStudentObject(String[] data) {
        return new Student(data[0], data[1], LocalDate.parse(data[2]), convertStringToAddress(data[3]), data[4],
                data[5], data[6], Integer.parseInt(data[7]), Integer.parseInt(data[8]),
                EnumConversion.stringToStatus(data[9]), Double.parseDouble(data[10]));
    }

    private Subject loadSubjectObject(String[] data) {
        return new Subject(data[0], data[1], EnumConversion.stringToSemester(data[2]), Integer.parseInt(data[3]),
                dm.getProfessorById(data[4]), Integer.parseInt(data[5]));
    }

    private Department loadDepartmentObject(String[] data) {
        return new Department(data[0], data[1], dm.getProfessorById(data[2]));
    }

    private Mark loadMarkObject(String[] data) {
        return new Mark(dm.getStudentById(data[0]), dm.getSubjectById(data[1]), EnumConversion.stringToMark(data[2]),
                LocalDate.parse(data[3]));
    }

    private Address loadAddressObject(String[] data) {
        if(data.length == 1 && data[0].trim().equals("null")) return null;
        return new Address(data[0], data[1], data[2], data[3]);
    }

    private Subject loadFailedSubjectsObject(String[] data) {
        return dm.getSubjectById(data[1]);
    }

    private String[] trimSplittedString(String[] data) {
        for (String point : data) {
            point = point.trim();
        }
        return data;
    }

    private Address convertStringToAddress(String data) {
        if(data.trim().equals("null")) return null;
        String[] addressChunks = data.split(":");
        return new Address(addressChunks[0], addressChunks[1], addressChunks[2], addressChunks[3]);
    }

    private String[] splitScannedData(String data) {
        return data.split(",");
    }

}
