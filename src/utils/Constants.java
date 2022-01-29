package utils;

import view.Screen;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Constants {
    public enum MarksValue {
        SEST(6), SEDAM(7), OSAM(8), DEVET(9), DESET(10);

        private int value;

        MarksValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Semester {
        ZIMSKI("ZIMSKI"), LETNJI("LETNJI");

        private String value;

        Semester(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Status {
        BUDZET("BUDZET"), SAMOFINANSIRANJE("SAMOFINANSIRANJE");

        private String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static int ICON_BUTTON_HEIGHT = 20;
    public static int ICON_BUTTON_WIDTH = 20;
    public static final String basepath = "src/resources/";
    public static String[] studentLabelNames = {"Ime*", "Prezime*", "Datum rodjenja*", "Adresa stanovanja*",
            "Broj telefona*", "E-mail adresa*", "Broj indeksa*", "Godina upisa*", "Trenutna godina studija*",
            "Nacin finansiranja"};
    public static String[] finansingWayLabels = {"BUDZET", "SAMOFINANSIRANJE"};
    public static String[] yearsLabels = {"1", "2", "3", "4"};
    public static String[] marksLabels = {"6", "7", "8", "9", "10"};
    public static String[] professorLabelNames = {"Ime*", "Prezime*", "Datum rodjenja*", "Adresa stanovanja*",
            "Broj telefona*", "E-mail adresa*", "Adresa kancelarije*", "Broj licne karte*", "Zvanje*",
            "Godine iskustva*"};
    public static String[] subjectLabelNames = {"Id predmeta*", "Naziv predmeta*", "Semestar*", "Profesor*", "Espb*",
            "Godina studija*"};
    public static String[] takingExamLabelNames = {"Id predmeta*", "Naziv predmeta*", "Ocena*", "Datum polaganja*"};
    public static String[] passedExamsColumnNames = {"Id predmeta", "Naziv predmeta", "ESPB", "Ocena", "Datum"};
    public static String[] semesterValues = {"ZIMSKI", "LETNJI"};
    public static String[] studentColumnNames = {"Indeks", "Ime", "Prezime", "Godina studija", "Status", "Prosek"};
    public static String[] professorColumnNames = {"Ime", "Prezime", "Titula", "E-mail adresa", "Id katedre"};
    public static String[] subjectColumnNames = {"Sifra predmeta", "Naziv predmeta", "Broj ESPB bodova",
            "Godina na kojoj se predmet izvodi", "Semestar u kome se predmet izvodi"};
    public static String[] professorSubjectsColumnNames = {"Sifra", "Naziv", "Godina studija", "Semestar"};
    public static String[] departmentColumnNames = {"Id katedre", "Naziv katedre", "Sef katedre"};
    public static String[] departmentLabelNames = {"Id katedre*", "Naziv katedre*", "Sef katedre*"};
    public static String[] getStudentLabelNames() {
        return new String[]{Screen.getInstance().getResourceBundle().getString("lblStudentName0"), Screen.getInstance().getResourceBundle().getString("lblStudentName1"),
                Screen.getInstance().getResourceBundle().getString("lblStudentName2"), Screen.getInstance().getResourceBundle().getString("lblStudentName3"),
                Screen.getInstance().getResourceBundle().getString("lblStudentName4"), Screen.getInstance().getResourceBundle().getString("lblStudentName5"),
                Screen.getInstance().getResourceBundle().getString("lblStudentName6"), Screen.getInstance().getResourceBundle().getString("lblStudentName7"),
                Screen.getInstance().getResourceBundle().getString("lblStudentName8"), Screen.getInstance().getResourceBundle().getString("lblStudentName9")};
    }

    public static String[] getProfessorLabelNames() {
        return new String[]{Screen.getInstance().getResourceBundle().getString("lblProfessorName0"), Screen.getInstance().getResourceBundle().getString("lblProfessorName1"),
                Screen.getInstance().getResourceBundle().getString("lblProfessorName2"), Screen.getInstance().getResourceBundle().getString("lblProfessorName3"),
                Screen.getInstance().getResourceBundle().getString("lblProfessorName4"), Screen.getInstance().getResourceBundle().getString("lblProfessorName5"),
                Screen.getInstance().getResourceBundle().getString("lblProfessorName6"), Screen.getInstance().getResourceBundle().getString("lblProfessorName7"),
                Screen.getInstance().getResourceBundle().getString("lblProfessorName8"), Screen.getInstance().getResourceBundle().getString("lblProfessorName9")};
    }

    public static String[] getSubjectLabelNames() {
        return new String[]{Screen.getInstance().getResourceBundle().getString("lblSubjectName0"), Screen.getInstance().getResourceBundle().getString("lblSubjectName1"),
                Screen.getInstance().getResourceBundle().getString("lblSubjectName2"), Screen.getInstance().getResourceBundle().getString("lblSubjectName3"),
                Screen.getInstance().getResourceBundle().getString("lblSubjectName4"), Screen.getInstance().getResourceBundle().getString("lblSubjectName5")};
    }
    
    public static String[] getDepartmentLabelNames() {
    	 return new String[]{Screen.getInstance().getResourceBundle().getString("lblDepartmentName0"), Screen.getInstance().getResourceBundle().getString("lblDepartmentName1"),
                 Screen.getInstance().getResourceBundle().getString("lblDepartmentName2"), Screen.getInstance().getResourceBundle().getString("lblDepartmentName3")};
    }
//public static String[] departmentLabelNames = {"Id katedre*", "Naziv katedre*", "Sef katedre*"};
    public static String[] getTakingExamLabelNames() {
        return new String[]{Screen.getInstance().getResourceBundle().getString("lblExamName0"), Screen.getInstance().getResourceBundle().getString("lblExamName1"),
                Screen.getInstance().getResourceBundle().getString("lblExamName2"), Screen.getInstance().getResourceBundle().getString("lblExamName3")};
    }

    public static String[] getNumberFields() {
        return new String[]{Screen.getInstance().getResourceBundle().getString("lblSubjectName4"), Screen.getInstance().getResourceBundle().getString("lblStudentName7"),
                Screen.getInstance().getResourceBundle().getString("lblStudentName8"), Screen.getInstance().getResourceBundle().getString("lblProfessorName9")};
    }

    public static String[] getDepartmentLabels() {
        ResourceBundle bundle = ResourceBundle.getBundle("view.MessageResources.language", Locale.getDefault());
        return new String[]{bundle.getString("lblDepartment0"), bundle.getString("lblDepartment1"),
                bundle.getString("lblDepartment2")};
    }
    
    public static String[] getCreatorsLabels() {
    	return new String[]{Screen.getInstance().getResourceBundle().getString("lblCreators0"),Screen.getInstance().getResourceBundle().getString("lblCreators1"),
    			Screen.getInstance().getResourceBundle().getString("lblCreators2"),Screen.getInstance().getResourceBundle().getString("lblCreators3"),
    			Screen.getInstance().getResourceBundle().getString("lblCreators4"),Screen.getInstance().getResourceBundle().getString("lblCreators5"),
    			Screen.getInstance().getResourceBundle().getString("lblCreators6"),Screen.getInstance().getResourceBundle().getString("lblCreators7")};
    }
    
    public static String[] getSrdjanInfo() {
    	return new String[] {Screen.getInstance().getResourceBundle().getString("srdjanInfo0"),Screen.getInstance().getResourceBundle().getString("srdjanInfo1"),
    			Screen.getInstance().getResourceBundle().getString("srdjanInfo2"),Screen.getInstance().getResourceBundle().getString("srdjanInfo3"),
    			Screen.getInstance().getResourceBundle().getString("srdjanInfo4"),Screen.getInstance().getResourceBundle().getString("srdjanInfo5"),
    			Screen.getInstance().getResourceBundle().getString("srdjanInfo6"),Screen.getInstance().getResourceBundle().getString("srdjanInfo7")};
    	}
    
    public static String[] getDarkoInfo() {
    	return new String[] {Screen.getInstance().getResourceBundle().getString("darkoInfo0"),Screen.getInstance().getResourceBundle().getString("darkoInfo1"),
    			Screen.getInstance().getResourceBundle().getString("darkoInfo2"),Screen.getInstance().getResourceBundle().getString("darkoInfo3"),
    			Screen.getInstance().getResourceBundle().getString("darkoInfo4"),Screen.getInstance().getResourceBundle().getString("darkoInfo5"),
    			Screen.getInstance().getResourceBundle().getString("darkoInfo6"),Screen.getInstance().getResourceBundle().getString("darkoInfo7")};
    	}
    
    public static String[] getAppInfo() {
    	return new String[] {Screen.getInstance().getResourceBundle().getString("appInfo0"),Screen.getInstance().getResourceBundle().getString("appInfo1"),
    			Screen.getInstance().getResourceBundle().getString("appInfo2"),Screen.getInstance().getResourceBundle().getString("appInfo3"),
    			Screen.getInstance().getResourceBundle().getString("appInfo4")};
    }
    
    public static String[] getAppLabels() {
    	return new String[] {Screen.getInstance().getResourceBundle().getString("appInfoLbl0"),Screen.getInstance().getResourceBundle().getString("appInfoLbl1"),
    			Screen.getInstance().getResourceBundle().getString("appInfoLbl2"),Screen.getInstance().getResourceBundle().getString("appInfoLbl3"),
    			Screen.getInstance().getResourceBundle().getString("appInfoLbl4")};
    }
    public static String getDepartmentColumnNames(int index) {
        ResourceBundle bundle = ResourceBundle.getBundle("view.localization.language", Locale.getDefault());
        switch (index) {
            case 0:
                return bundle.getString("lblDepartment0");
            case 1:
                return bundle.getString("lblDepartment1");
            case 2:
                return bundle.getString("lblDepartment2");
            default:
                return "";
        }
    }

    public static String getProfessorSubjectColumnName(int index) {
        switch (index) {
            case 0:
                return Screen.getInstance().getResourceBundle().getString("lblProfSubj0");
            case 1:
                return Screen.getInstance().getResourceBundle().getString("lblProfSubj1");
            case 2:
                return Screen.getInstance().getResourceBundle().getString("lblProfSubj2");
            case 3:
                return Screen.getInstance().getResourceBundle().getString("lblProfSubj3");
            case 4:
                return Screen.getInstance().getResourceBundle().getString("lblProfSubj4");
            default:
                return "";
        }
    }

    public static String getPassedExamColumnName(int index) {
        switch (index) {
            case 0:
                return Screen.getInstance().getResourceBundle().getString("colPassed0");
            case 1:
                return Screen.getInstance().getResourceBundle().getString("colPassed1");
            case 2:
                return Screen.getInstance().getResourceBundle().getString("colPassed2");
            case 3:
                return Screen.getInstance().getResourceBundle().getString("colPassed3");
            case 4:
                return Screen.getInstance().getResourceBundle().getString("colPassed4");
            default:
                return "";
        }
    }

    public static String getFailedColumnName(int index) {
        switch (index) {
            case 0:
                return Screen.getInstance().getResourceBundle().getString("colFailed0");
            case 1:
                return Screen.getInstance().getResourceBundle().getString("colFailed1");
            case 2:
                return Screen.getInstance().getResourceBundle().getString("colFailed2");
            case 3:
                return Screen.getInstance().getResourceBundle().getString("colFailed3");
            case 4:
                return Screen.getInstance().getResourceBundle().getString("colFailed4");
            default:
                return "";
        }
    }

}
