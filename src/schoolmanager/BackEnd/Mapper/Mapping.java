/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Mapper;

import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.uiPresenter.*;

/**
 *
 * @author Zed Yacine
 */
public class Mapping {

    public static Student getObjecStudentFromUiStudent(UiStudent uistd) {
        boolean a = UiStudent.UiStudentInputIsValid(uistd);
        if (a) {
            return new Student(uistd.getFirstName().getText(), uistd.getLastName().getText(),
                    uistd.getPhone1().getText(), uistd.getPhone2().getText());
        } else {
            return null;
        }
    }
    
    public static Teacher getObjecTeacherFromUiTeacher(UiTeacher uitech) {
        boolean a = UiTeacher.UiTeacherInputIsValid(uitech);
        if (a) {
            return new Teacher(uitech.getFirstName().getText(), uitech.getLastName().getText(),
                    uitech.getPhone().getText(), uitech.getWorkeSpace().getText());
        } else {
            return null;
        }
    }
    
    public static Level getObjecLevelFromUiLevl(UiLevel uiLevl) {
        boolean a = UiLevel.UiInputIsValid(uiLevl);
        if (a) {
            return new Level(uiLevl.getName().getText());
        } else {
            return null;
        }
    }
    
    public static Module getObjecModuleFromUiModule(UiModule uiModule) {
        boolean a = UiModule.UiInputIsValid(uiModule);
        if (a) {
            return new Module(uiModule.getName().getText());
        } else {
            return null;
        }
    }
    
    public static Type getObjecTypeFromUiType(UiType uiType) {
        boolean a = UiType.UiInputIsValid(uiType);
        if (a) {
            return new Type(uiType.getName().getText());
        } else {
            return null;
        }
    }

    public static Section getObjecSectionFromUiSection(UiSection uiSection) {
        boolean a = UiSection.UiInputIsValid(uiSection);
        if (a) {
            return new Section(uiSection.getName().getText());
        } else {
            return null;
        }
    }
}
