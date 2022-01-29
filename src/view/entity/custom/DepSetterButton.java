package view.entity.custom;

import controller.department.EditDepartmentController;
import view.entity.department.DepartmentEditDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepSetterButton extends JButton implements ActionListener {
    private EditDepartmentController editDepartmentController;
    private DepartmentEditDialog departmentEditDialog;
    public DepSetterButton(EditDepartmentController editDepartmentController, DepartmentEditDialog departmentEditDialog) {
        super("+");
        setMaximumSize(new Dimension(50, 30));
        setPreferredSize(new Dimension(50, 30));
        this.editDepartmentController = editDepartmentController;
        this.departmentEditDialog = departmentEditDialog;
        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        editDepartmentController.addProfessorToDepartment(departmentEditDialog);
    }
}
