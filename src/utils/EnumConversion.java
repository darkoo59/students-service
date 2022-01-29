package utils;

import utils.Constants.MarksValue;
import utils.Constants.Semester;
import utils.Constants.Status;

public class EnumConversion {

    public static Status stringToStatus(String status) {
        return status.equals("BUDZET") ? Status.BUDZET : Status.SAMOFINANSIRANJE;
    }

    public static Semester stringToSemester(String semester) {
        return semester.equals("ZIMSKI") ? Semester.ZIMSKI : Semester.LETNJI;
    }

    public static MarksValue stringToMark(String mark) {
        switch (mark) {
            case "6":
                return MarksValue.SEST;
            case "7":
                return MarksValue.SEDAM;
            case "8":
                return MarksValue.OSAM;
            case "9":
                return MarksValue.DEVET;
            case "10":
                return MarksValue.DESET;
            default:
                return MarksValue.SEST;
        }
    }
}
