package view.entity.filters;

import javax.swing.*;
import java.util.Locale;

public class SubjectRowFilter<SubjectTableModel, String> extends RowFilter<SubjectTableModel, String> {

    private java.lang.String filterWord = "";

    public void setFilterWord(java.lang.String filterWord) {
        this.filterWord = filterWord;
    }

    @Override
    public boolean include(Entry<? extends SubjectTableModel, ? extends String> entry) {
        if (filterWord.equals("")) {
            return true;
        }
        java.lang.String[] words = filterWord.split(",");
        if (words.length == 1)
            return searchId(words[0], entry) || searchSubjectName(words[0], entry);
        if (words.length == 2)
            return searchSubjectName(words[1], entry) && searchId(words[0], entry);
        return false;
    }

    public boolean searchId(java.lang.String searched, Entry<? extends SubjectTableModel, ? extends String> entry) {
        java.lang.String id = (java.lang.String) entry.getValue(0);
        return id.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }

    public boolean searchSubjectName(java.lang.String searched, Entry<? extends SubjectTableModel, ? extends String> entry) {
        java.lang.String subName = (java.lang.String) entry.getValue(1);
        return subName.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }


}
