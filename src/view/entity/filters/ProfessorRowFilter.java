package view.entity.filters;

import javax.swing.*;
import java.util.Locale;

public class ProfessorRowFilter<ProfessorTableModel, String> extends RowFilter<ProfessorTableModel, String> {
    private java.lang.String filterWord = "";

    public void setFilterWord(java.lang.String filterWord) {
        this.filterWord = filterWord;
    }

    @Override
    public boolean include(Entry<? extends ProfessorTableModel, ? extends String> entry) {
        if (filterWord.equals("")) {
            return true;
        }
        java.lang.String[] words = filterWord.split(",");
        if (words.length == 1)
            return searchName(words[0], entry) || searchSurname(words[0], entry);
        if (words.length == 2)
            return searchName(words[1], entry) && searchSurname(words[0], entry);
        return false;
    }

    private boolean searchName(java.lang.String searched, Entry<? extends ProfessorTableModel, ? extends String> entry) {
        java.lang.String name = (java.lang.String) entry.getValue(0);
        return name.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }

    private boolean searchSurname(java.lang.String searched, Entry<? extends ProfessorTableModel, ? extends String> entry) {
        java.lang.String surname = (java.lang.String) entry.getValue(1);
        return surname.toLowerCase(Locale.ROOT).trim().contains(searched.toLowerCase(Locale.ROOT).trim());
    }
}
