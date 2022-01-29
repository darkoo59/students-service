package controller.department;

import exceptions.InvalidFieldException;
import interfaces.IAddingController;
import view.entity.AddingScreen;

public class AddDepartmentController implements IAddingController {
    @Override
    public void addNewEntity(AddingScreen dialog) {
        System.out.println("This feature is not yet supported!");
    }

    @Override
    public void validate(AddingScreen dialog) throws InvalidFieldException {

    }
}
