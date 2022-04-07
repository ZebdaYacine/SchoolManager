/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Mapper;

import javafx.scene.control.TextField;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.uiPresenter.UiStudent;
import static schoolmanager.BackEnd.uiPresenter.UiStudent.UiStudentInputIsValid;

/**
 *
 * @author Zed Yacine
 */
public class Mapping {

    public static Student getObjecStudentFromUiStudent(UiStudent uistd) {
        return ((UiStudentInputIsValid(uistd)) ? null
                : new Student(uistd.getFirstName().getText(), uistd.getLastName().getText(),
                        uistd.getPhone1().getText(), uistd.getPhone2().getText()));
    }
}
