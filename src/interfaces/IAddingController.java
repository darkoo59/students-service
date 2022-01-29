package interfaces;

import exceptions.InvalidFieldException;
import view.entity.AddingScreen;

public interface IAddingController {
    void addNewEntity(AddingScreen dialog);

    void validate(AddingScreen dialog) throws InvalidFieldException;
}
