/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.uiPresenter;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static schoolmanager.BackEnd.uiPresenter.TestingMethods.testInt;

/**
 *
 * @author kadri
 */
public class UiSeance {

    private Label OfferErr, teacherErr, roomErr, dateErr, timeErr, groupErr;
    private ComboBox OfferCmb, teacherCmb, RoomCmb, GroupCmb;
    private JFXDatePicker date;
    private JFXTimePicker time;

    public UiSeance() {
    }

    public UiSeance(Label OfferErr, Label teacherErr, Label roomErr, Label dateErr, Label timeErr, Label groupErr, ComboBox OfferCmb, ComboBox teacherCmb, ComboBox RoomCmb, ComboBox GroupCmb, JFXDatePicker date, JFXTimePicker time) {
        this.OfferErr = OfferErr;
        this.teacherErr = teacherErr;
        this.roomErr = roomErr;
        this.dateErr = dateErr;
        this.timeErr = timeErr;
        this.groupErr = groupErr;
        this.OfferCmb = OfferCmb;
        this.teacherCmb = teacherCmb;
        this.RoomCmb = RoomCmb;
        this.GroupCmb = GroupCmb;
        this.date = date;
        this.time = time;
    }

    public JFXDatePicker getDate() {
        return date;
    }

    public void setDate(JFXDatePicker date) {
        this.date = date;
    }

    public JFXTimePicker getTime() {
        return time;
    }

    public void setTime(JFXTimePicker time) {
        this.time = time;
    }

    public Label getOfferErr() {
        return OfferErr;
    }

    public void setOfferErr(Label OfferErr) {
        this.OfferErr = OfferErr;
    }

    public Label getTeacherErr() {
        return teacherErr;
    }

    public void setTeacherErr(Label teacherErr) {
        this.teacherErr = teacherErr;
    }

    public Label getRoomErr() {
        return roomErr;
    }

    public void setRoomErr(Label roomErr) {
        this.roomErr = roomErr;
    }

    public Label getDateErr() {
        return dateErr;
    }

    public void setDateErr(Label dateErr) {
        this.dateErr = dateErr;
    }

    public Label getTimeErr() {
        return timeErr;
    }

    public void setTimeErr(Label timeErr) {
        this.timeErr = timeErr;
    }

    public Label getGroupErr() {
        return groupErr;
    }

    public void setGroupErr(Label groupErr) {
        this.groupErr = groupErr;
    }

    public ComboBox getOfferCmb() {
        return OfferCmb;
    }

    public void setOfferCmb(ComboBox OfferCmb) {
        this.OfferCmb = OfferCmb;
    }

    public ComboBox getTeacherCmb() {
        return teacherCmb;
    }

    public void setTeacherCmb(ComboBox teacherCmb) {
        this.teacherCmb = teacherCmb;
    }

    public ComboBox getRoomCmb() {
        return RoomCmb;
    }

    public void setRoomCmb(ComboBox RoomCmb) {
        this.RoomCmb = RoomCmb;
    }

    public ComboBox getGroupCmb() {
        return GroupCmb;
    }

    public void setGroupCmb(ComboBox GroupCmb) {
        this.GroupCmb = GroupCmb;
    }

    public static boolean UiSeanceInputIsValid(UiSeance uiSeance) {
        boolean bTeacher = false, bRoom = false, bOffer = false, bGroup = false, bdate = false, btime = false;
        if (uiSeance.getOfferCmb().getSelectionModel().getSelectedItem().toString().isEmpty()) {
            uiSeance.getOfferErr().setText("le offer est vide");
            uiSeance.getOfferErr().setVisible(true);
        } else {
            uiSeance.getOfferErr().setVisible(false);
            bOffer = true;
        }
        if (uiSeance.getTeacherCmb().getSelectionModel().getSelectedItem().toString().isEmpty()) {
            uiSeance.getTeacherErr().setText("prof est vide");
            uiSeance.getTeacherErr().setVisible(true);
        } else {
            uiSeance.getTeacherErr().setVisible(false);
            bTeacher = true;
        }
        if (uiSeance.getRoomCmb().getSelectionModel().getSelectedItem().toString().isEmpty()) {
            uiSeance.getRoomErr().setText("room est vide");
            uiSeance.getRoomErr().setVisible(true);
        } else {
            uiSeance.getRoomErr().setVisible(false);
            bRoom = true;
        }
        if (uiSeance.getGroupCmb().getSelectionModel().getSelectedItem().toString().isEmpty()) {
            uiSeance.getGroupErr().setText("group est vide");
            uiSeance.getGroupErr().setVisible(true);
        } else {
            uiSeance.getGroupErr().setVisible(false);
            bGroup = true;
        }
        if (uiSeance.getDate().toString().isEmpty()) {
            uiSeance.getDateErr().setText("date est vide");
            uiSeance.getDateErr().setVisible(true);
        } else {
            uiSeance.getDateErr().setVisible(false);
            bdate = true;
        }
        if (uiSeance.getTime().toString().isEmpty()) {
            uiSeance.getTimeErr().setText("time est vide");
            uiSeance.getTimeErr().setVisible(true);
        } else {
            uiSeance.getTimeErr().setVisible(false);
            btime = true;
        }
        return bTeacher && bRoom && bOffer && bGroup && bdate && btime;
    }

    public void clearInputs() {
        this.OfferCmb.getSelectionModel().clearSelection();
        this.teacherCmb.getSelectionModel().clearSelection();
        this.RoomCmb.getSelectionModel().clearSelection();
        this.GroupCmb.getSelectionModel().clearSelection();
    }
}
