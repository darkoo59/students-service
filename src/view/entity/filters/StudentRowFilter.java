package view.entity.filters;


import javax.swing.*;
import java.util.Locale;

public class StudentRowFilter<StudentTableModel, String> extends RowFilter<StudentTableModel, String> {
    private java.lang.String filterWord = "";

    public void setFilterWord(java.lang.String filterWord) {
        this.filterWord = filterWord;
    }

    @Override
    public boolean include(Entry<? extends StudentTableModel, ? extends String> entry) {
        if (filterWord.equals("")) {
            return true;
        }
        java.lang.String[] words = filterWord.split(",");
        if (words.length == 1)
            return searchName(words[0], entry) || searchSurname(words[0], entry) || searchIndex(words[0], entry);
        if (words.length == 2)
            return searchName(words[1], entry) && searchSurname(words[0], entry);
        if (words.length == 3)
            return searchIndex(words[0], entry) && searchName(words[1], entry) && searchSurname(words[2], entry);
        return false;
    }

    private boolean searchIndex(java.lang.String searched, Entry<? extends StudentTableModel, ? extends String> entry) {
        java.lang.String index = (java.lang.String) entry.getValue(0);
        return index.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }

    private boolean searchName(java.lang.String searched, Entry<? extends StudentTableModel, ? extends String> entry) {
        java.lang.String name = (java.lang.String) entry.getValue(1);
        return name.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }

    private boolean searchSurname(java.lang.String searched, Entry<? extends StudentTableModel, ? extends String> entry) {
        java.lang.String surname = (java.lang.String) entry.getValue(2);
        return surname.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }

}
