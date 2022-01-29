package view;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import controller.subject.AddProfessorToSubjectController;
import controller.professor.DeleteSubjectFromProfessorController;
import controller.department.EditDepartmentController;
import model.database.DataModel;
import view.about.AboutWindow;
import view.entity.subject.SubjectEditDialog;
import view.help.HelpWindow;
import view.entity.abstract_model.table_model.ProfessorTableModel;
import view.entity.abstract_model.table_model.StudentTableModel;
import view.entity.abstract_model.table_model.SubjectTableModel;
import view.entity.custom.edit_custom.AddDeleteButtons;
import view.entity.filters.ProfessorRowFilter;
import view.entity.filters.StudentRowFilter;
import view.entity.filters.SubjectRowFilter;
import view.tabs.MainTab;
import view.entity.table.Table;
import view.entity.AddingScreen;
import view.entity.department.DepartmentEditDialog;
import view.entity.EditingScreen;
import view.entity.professor.ProfessorEditDialog;
import view.entity.professor.ProfessorEditSubjectsPanel;
import view.entity.professor.ProfessorNewDialog;
import view.entity.student.StudentEditDialog;
import view.entity.student.StudentEditFailedPanel;
import view.entity.student.StudentEnteringMark;
import view.entity.student.StudentNewDialog;
import view.entity.subject.SubjectNewDialog;

public class ListenerHandler {

    public static ActionListener openWindowListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen frame = Screen.getInstance();
                if (frame.getSelectedTab() == 0) {
                    new StudentNewDialog();
                    return;
                }

                if (frame.getSelectedTab() == 1) {
                    new ProfessorNewDialog();
                    return;
                }

                if (frame.getSelectedTab() == 2) {
                    new SubjectNewDialog();
                    return;
                }

            }

        };
    }

    public static ActionListener getOpenStudentTabListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Screen.getInstance().getMainTab().setSelectedIndex(0);
            }

        };
    }

    public static ActionListener getOpenProfessorTabListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Screen.getInstance().getMainTab().setSelectedIndex(1);
            }

        };
    }

    public static ActionListener getOpenSubjectTabListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Screen.getInstance().getMainTab().setSelectedIndex(2);
            }

        };
    }

    public static ActionListener getOpenDepartmentTabListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Screen.getInstance().getMainTab().setSelectedIndex(3);
            }

        };
    }

    public static ActionListener saveFilesListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                DataModel.getInstance().writeDataToFiles();
            }

        };
    }

    public static ActionListener closeAppListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }

        };
    }

    public static ActionListener openHelpFrame() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new HelpWindow();
            }

        };
    }

    public static ActionListener openAboutFrame() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new AboutWindow();
            }

        };
    }


    public static ActionListener getChangeToSerbianListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Locale.setDefault(new Locale("sr", "RS"));
                Screen.getInstance().changeLanguage();
            }

        };
    }

    public static ActionListener getChangeToUsListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Locale.setDefault(new Locale("en", "US"));
                Screen.getInstance().changeLanguage();
            }

        };
    }

    public static ActionListener getButtonConfirmListener(JButton btnConfirm) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parent = SwingUtilities.getWindowAncestor(btnConfirm);
                if (parent instanceof AddingScreen) {
                    Screen.getInstance().getMainTab().addNewEntity((AddingScreen) parent);
                } else if (parent instanceof EditingScreen) {
                    Screen.getInstance().getMainTab().editNewEntity((EditingScreen) parent);
                } else if (parent instanceof StudentEnteringMark) {
                    StudentEnteringMark enteringMark = (StudentEnteringMark) parent;
                    enteringMark.getExamController().studentTakingExam(enteringMark);
                }

            }
        };
    }

    public static ActionListener getButtonCancelListener(JButton cancelButton) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parent = SwingUtilities.getWindowAncestor(cancelButton);
                if (parent instanceof StudentNewDialog) {
                    StudentNewDialog dialog = (StudentNewDialog) parent;
                    dialog.dispose();
                } else if (parent instanceof ProfessorNewDialog) {
                    ProfessorNewDialog dialog = (ProfessorNewDialog) parent;
                    dialog.dispose();
                } else if (parent instanceof SubjectNewDialog) {
                    SubjectNewDialog dialog = (SubjectNewDialog) parent;
                    dialog.dispose();
                } else if (parent instanceof StudentEditDialog) {
                    StudentEditDialog dialog = (StudentEditDialog) parent;
                    dialog.dispose();
                } else if (parent instanceof ProfessorEditDialog) {
                    ProfessorEditDialog dialog = (ProfessorEditDialog) parent;
                    dialog.dispose();
                } else if (parent instanceof StudentEnteringMark) {
                    StudentEnteringMark dialog = (StudentEnteringMark) parent;
                    dialog.dispose();
                } else if (parent instanceof SubjectEditDialog) {
                    SubjectEditDialog dialog = (SubjectEditDialog) parent;
                    dialog.dispose();
                } else if (parent instanceof DepartmentEditDialog) {
                    DepartmentEditDialog dialog = (DepartmentEditDialog) parent;
                    dialog.dispose();
                }
            }
        };
    }

    public static ActionListener getButtonDeleteListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen.getInstance().getMainTab().deleteEntity();
            }
        };
    }

    public static ActionListener getAddFailedSubjectListener(StudentEditFailedPanel studentFailedPanel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentFailedPanel.addFailedSubject();
            }
        };
    }

    public static ActionListener getAddSubjectToProfessorListener(ProfessorEditSubjectsPanel professorSubjectsPanel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorSubjectsPanel.addSubject();
            }
        };
    }

    public static ActionListener openEditDialogListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Screen frame = Screen.getInstance();
                if (frame.getSelectedTab() == 0) {
                    if (Screen.getInstance().getMainTab().getStudentTable().getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedStudent"),
                                Screen.getInstance().getResourceBundle().getString("editingStudentTitle"), JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    StudentEditDialog editDialog = new StudentEditDialog();
                    editDialog.setVisible();
                    return;
                }

                if (frame.getSelectedTab() == 1) {
                    if (Screen.getInstance().getMainTab().getProfessorTable().getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedProfessor"),
                                Screen.getInstance().getResourceBundle().getString("editingProfessorTitle"), JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    ProfessorEditDialog editDialog = new ProfessorEditDialog();
                    editDialog.setVisible();
                    return;
                }

                if (frame.getSelectedTab() == 2) {
                    if (Screen.getInstance().getMainTab().getSubjectTable().getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"),
                                Screen.getInstance().getResourceBundle().getString("editingSubjectTitle"), JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    new SubjectEditDialog();
                    return;
                }

                if (frame.getSelectedTab() == 3) {
                    if (Screen.getInstance().getMainTab().getDepartmentTable().getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedDepartment"),
                                Screen.getInstance().getResourceBundle().getString("editingDepartmentTitle"), JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    new DepartmentEditDialog(new EditDepartmentController());
                    return;
                }
                // TODO Auto-generated method stub

            }

        };
    }

    public static ActionListener getButtonDeleteFailedSubjectListener(StudentEditFailedPanel failedPanel) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (failedPanel.getFailedSubjectsTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"),
                            Screen.getInstance().getResourceBundle().getString("deletingSubjectTitle"), JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                failedPanel.getDeleteController().deleteFailedSubject(failedPanel);
                return;
            }

        };
    }

    public static ActionListener getDeleteSubjectFromProfessorListener(ProfessorEditSubjectsPanel professorSubjectsPanel) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (professorSubjectsPanel.getProfessorSubjectsTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"),
                            Screen.getInstance().getResourceBundle().getString("notice"), JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                professorSubjectsPanel.setDeleteController(new DeleteSubjectFromProfessorController(professorSubjectsPanel));
                return;
            }

        };
    }

    public static ActionListener getButtonTakingExamListener(StudentEditFailedPanel failedPanel) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (failedPanel.getFailedSubjectsTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, Screen.getInstance().getResourceBundle().getString("notSelectedSubject"),
                            Screen.getInstance().getResourceBundle().getString("takingExam"), JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                new StudentEnteringMark(failedPanel);
                return;
            }

        };
    }

    public static FocusListener getAdressScreenListener() {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!((JTextField) e.getSource()).getText().trim().equals("")) return;
                JTextField countryName = new JTextField();
                JTextField cityName = new JTextField();
                JTextField streetName = new JTextField();
                JTextField streetNumber = new JTextField();

                final JComponent[] inputs = new JComponent[]{
                        new JLabel(Screen.getInstance().getResourceBundle().getString("country")),
                        countryName,
                        new JLabel(Screen.getInstance().getResourceBundle().getString("city")),
                        cityName,
                        new JLabel(Screen.getInstance().getResourceBundle().getString("street")),
                        streetName,
                        new JLabel(Screen.getInstance().getResourceBundle().getString("streetNumber")),
                        streetNumber
                };
                String resultText = "";
                JTextField field = ((JTextField) e.getSource());
                int result = JOptionPane.showConfirmDialog(null, inputs, Screen.getInstance().getResourceBundle().getString("enterAddress"), JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION)
                    resultText = countryName.getText() + ":" + cityName.getText() + ":" + streetName.getText() + ":" + streetNumber.getText();
                else
                    resultText = Screen.getInstance().getResourceBundle().getString("addressResult");

                field.setText(resultText);
                field.getParent().requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        };
    }

    public static ActionListener searchTables() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainTab tab = Screen.getInstance().getMainTab();
                String filterWord = Screen.getInstance().getToolbar().getSearchField().getText();
                int selectedIndex = tab.getSelectedIndex();
                if (selectedIndex == 0) {
                    Table table = tab.getStudentTable();
                    TableRowSorter<StudentTableModel> rowSorter = (TableRowSorter<StudentTableModel>) table.getRowSorter();
                    StudentRowFilter filter = (StudentRowFilter) rowSorter.getRowFilter();
                    filter.setFilterWord(filterWord);
                } else if (selectedIndex == 1) {
                    Table table = tab.getProfessorTable();
                    TableRowSorter<ProfessorTableModel> rowSorter = (TableRowSorter<ProfessorTableModel>) table.getRowSorter();
                    ProfessorRowFilter filter = (ProfessorRowFilter) rowSorter.getRowFilter();
                    filter.setFilterWord(filterWord);
                } else if (selectedIndex == 2) {
                    Table table = tab.getSubjectTable();
                    TableRowSorter<SubjectTableModel> rowSorter = (TableRowSorter<SubjectTableModel>) table.getRowSorter();
                    SubjectRowFilter filter = (SubjectRowFilter) rowSorter.getRowFilter();
                    filter.setFilterWord(filterWord);
                }
                DataModel.getInstance().notifyTable();

            }
        };
    }

    public static ActionListener addEntityToFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor((JButton) e.getSource());
                if (window instanceof SubjectEditDialog) {
                    AddProfessorToSubjectController addProfessorToSubjectController = new AddProfessorToSubjectController();
                    SubjectEditDialog subjectEdit = (SubjectEditDialog) window;
                    addProfessorToSubjectController.addNewProfessorToSubject(subjectEdit);
                } else if (window instanceof DepartmentEditDialog) {
                    DepartmentEditDialog departmentEdit = (DepartmentEditDialog) window;
                    departmentEdit.showList();
                }
            }
        };
    }

    public static ActionListener deleteEntityFromFieldListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor((JButton) e.getSource());
                if (window instanceof SubjectEditDialog) {
                    SubjectEditDialog subjectEdit = (SubjectEditDialog) window;

                    int resp = JOptionPane.showConfirmDialog(null, Screen.getInstance().getResourceBundle().getString("questionDeletingProfessor"),
                            Screen.getInstance().getResourceBundle().getString("areYouSureProfessor"), JOptionPane.YES_NO_OPTION);

                    if (resp != 0) return;
                    ((JTextField) subjectEdit.getFieldsReferences().get(3)).setText("");
                    subjectEdit.setChoosenProfessor("");
                    subjectEdit.switchAddDeleteButtons();


                } else if (window instanceof DepartmentEditDialog) {
                    DepartmentEditDialog departmentEdit = (DepartmentEditDialog) window;
                    JTextField txtField = departmentEdit.getTextField(2);
                    txtField.setText("");
                    departmentEdit.setChoosenProfessor("");
                    departmentEdit.switchAddDeleteButtons();
                }
            }
        };
    }

    public static ActionListener deleteDepartmentListener(JTextField field, AddDeleteButtons addDeleteButtons) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDeleteButtons.getBtnDelete().setEnabled(false);
                addDeleteButtons.getBtnAdd().setEnabled(true);
                field.setText("");
            }
        };
    }

}
