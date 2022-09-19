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
public abstract class Mapping {

    public static Student getObjectStudentFromUiStudent(UiStudent uistd) {
        boolean a = UiStudent.UiStudentInputIsValid(uistd);
        if (a) {
            return new Student(
                    uistd.getPhone1().getText(),
                    uistd.getPhone2().getText(),
                    uistd.getSectionName().getSelectionModel().getSelectedItem().toString(),
                    uistd.getFirstName().getText(), uistd.getLastName().getText());
        } else {
            return null;
        }
    }

    public static Seance getObjectSeanceFromUiSeance(UiSeance uiseance) {
        boolean a = UiSeance.UiSeanceInputIsValid(uiseance);
        int pT = 0;
        if (a) {

            return new Seance(
                    uiseance.getTeacherCmb().getSelectionModel().getSelectedItem().getId(),
                    uiseance.getRoomCmb().getSelectionModel().getSelectedItem().getId(),
                    uiseance.getGroupCmb().getSelectionModel().getSelectedItem().getId()
            );
        } else {
            return null;
        }
    }

    public static Teacher getObjectTeacherFromUiTeacher(UiTeacher uitech) {
        boolean a = UiTeacher.UiTeacherInputIsValid(uitech);
        if (a) {
            return new Teacher(uitech.getFirstName().getText(), uitech.getLastName().getText(),
                    uitech.getPhone().getText(), uitech.getWorkeSpace().getText());
        } else {
            return null;
        }
    }

    public static Level getObjectLevelFromUiLevl(UiLevel uiLevl) {
        boolean a = UiLevel.UiInputIsValid(uiLevl);
        if (a) {
            return new Level(uiLevl.getName().getText());
        } else {
            return null;
        }
    }

    public static Module getObjectModuleFromUiModule(UiModule uiModule) {
        boolean a = UiModule.UiInputIsValid(uiModule);
        if (a) {
            return new Module(uiModule.getName().getText());
        } else {
            return null;
        }
    }

    public static Type getObjectTypeFromUiType(UiType uiType) {
        boolean a = UiType.UiInputIsValid(uiType);
        if (a) {
            return new Type(uiType.getName().getText());
        } else {
            return null;
        }
    }

    public static Section getObjectSectionFromUiSection(UiSection uiSection) {
        boolean a = UiSection.UiInputIsValid(uiSection);
        if (a) {
            return new Section(uiSection.getName().getText());
        } else {
            return null;
        }
    }

    public static Room getObjectRoomFromUiRoom(UiRoom uiRoom) {
        boolean a = UiRoom.UiRoomInputIsValid(uiRoom);
        if (a) {
            int nbr = Integer.parseInt(uiRoom.getNbrChair().getText());
            return new Room(uiRoom.getName().getText(), nbr);
        } else {
            return null;
        }
    }

    public static Offer getOffreObjectFromOffreUi(UiOffre uiOffre) {
        boolean a = UiOffre.UiOffreInputIsValid(uiOffre);
        if (a) {
            return new Offer(uiOffre.getName().getText(),
                    uiOffre.getTypeCmb().getSelectionModel().getSelectedItem().toString(),
                    uiOffre.getModuleCmb().getSelectionModel().getSelectedItem().toString(),
                    uiOffre.getLevelCmb().getSelectionModel().getSelectedItem().toString(),
                    Integer.parseInt(uiOffre.getPrice().getText()));
        } else {
            return null;
        }
    }

    public static Group getObjectGroupeFromUiGroupe(UiGroupe uigrp) {
        boolean a = UiGroupe.UiGroupeInputIsValid(uigrp);
        if (a) {
            return new Group(uigrp.getOfferCmb().getSelectionModel().getSelectedItem().toString(),
                    uigrp.getName().getText(), Integer.parseInt(uigrp.getNbrPlace().getText()),
                    uigrp.getTeacherCmb().getSelectionModel().getSelectedItem());
        } else {
            return null;
        }
    }

    public static Paiement getObjectAccountFromUiStudentPaiementHistory(UiStudentPaiement uistd) {
        boolean a = UiStudentPaiement.UiStudentInputIsValid(uistd);
        if (a) {
            Group grp =  uistd.getGroupCB().getSelectionModel().getSelectedItem();
          return new Paiement(grp,uistd.getDateP().getValue().toString(),
                    Float.parseFloat(uistd.getAmount().getText())
          ,Float.parseFloat(uistd.getAmountP().getText()),uistd.getAroundCB().getSelectionModel().getSelectedItem());
        } else {
            return null;
        }
    }
}
