/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.uiPresenter;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Room;
import schoolmanager.BackEnd.Model.Teacher;

/**
 *
 * @author kadri
 */
public class UiSeance {

    private Label OfferErr, teacherErr, roomErr, dateErr, timeErr, groupErr;
    private JFXComboBox<Group> GroupCmb;
    private JFXComboBox<Offer> OfferCmb;
    private JFXComboBox<Teacher> teacherCmb;
    private JFXComboBox<Room> RoomCmb;
    private JFXDatePicker date;
    private JFXTimePicker time;
    private JFXCheckBox presenceTeacher;

    public UiSeance() {
    }

    public UiSeance(Label OfferErr, Label teacherErr, Label roomErr, Label dateErr, Label timeErr, Label groupErr, JFXComboBox<Group> GroupCmb, JFXComboBox<Offer> OfferCmb, JFXComboBox<Teacher> teacherCmb, JFXComboBox<Room> RoomCmb, JFXDatePicker date, JFXTimePicker time, JFXCheckBox presenceTeacher) {
        this.OfferErr = OfferErr;
        this.teacherErr = teacherErr;
        this.roomErr = roomErr;
        this.dateErr = dateErr;
        this.timeErr = timeErr;
        this.groupErr = groupErr;
        this.GroupCmb = GroupCmb;
        this.OfferCmb = OfferCmb;
        this.teacherCmb = teacherCmb;
        this.RoomCmb = RoomCmb;
        this.date = date;
        this.time = time;
        this.presenceTeacher = presenceTeacher;
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

    public JFXComboBox<Group> getGroupCmb() {
        return GroupCmb;
    }

    public void setGroupCmb(JFXComboBox<Group> GroupCmb) {
        this.GroupCmb = GroupCmb;
    }

    public JFXComboBox<Offer> getOfferCmb() {
        return OfferCmb;
    }

    public void setOfferCmb(JFXComboBox<Offer> OfferCmb) {
        this.OfferCmb = OfferCmb;
    }

    public JFXComboBox<Teacher> getTeacherCmb() {
        return teacherCmb;
    }

    public void setTeacherCmb(JFXComboBox<Teacher> teacherCmb) {
        this.teacherCmb = teacherCmb;
    }

    public JFXComboBox<Room> getRoomCmb() {
        return RoomCmb;
    }

    public void setRoomCmb(JFXComboBox<Room> RoomCmb) {
        this.RoomCmb = RoomCmb;
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

    public JFXCheckBox getPresenceTeacher() {
        return presenceTeacher;
    }

    public void setPresenceTeacher(JFXCheckBox presenceTeacher) {
        this.presenceTeacher = presenceTeacher;
    }

    public static boolean UiSeanceInputIsValid(UiSeance uiSeance) {
        boolean bTeacher = false, bRoom = false, bOffer = false, bGroup = false, bdate = false, btime = false;
        if (uiSeance.getOfferCmb().getSelectionModel().getSelectedItem() == null) {
            uiSeance.getOfferErr().setText("حدد العرض");
            uiSeance.getOfferErr().setVisible(true);
        } else {
            uiSeance.getOfferErr().setVisible(false);
            bOffer = true;
        }
        if (uiSeance.getTeacherCmb().getSelectionModel().getSelectedItem() == null) {
            uiSeance.getTeacherErr().setText("حدد الاستاذ");
            uiSeance.getTeacherErr().setVisible(true);
        } else {
            uiSeance.getTeacherErr().setVisible(false);
            bTeacher = true;
        }
        if (uiSeance.getRoomCmb().getSelectionModel().getSelectedItem() == null) {
            uiSeance.getRoomErr().setText("حدد القاعة");
            uiSeance.getRoomErr().setVisible(true);
        } else {
            uiSeance.getRoomErr().setVisible(false);
            bRoom = true;
        }
        if (uiSeance.getGroupCmb().getSelectionModel().getSelectedItem() == null) {
            uiSeance.getGroupErr().setText("حدد الفوج");
            uiSeance.getGroupErr().setVisible(true);
        } else {
            uiSeance.getGroupErr().setVisible(false);
            bGroup = true;
        }
        if (uiSeance.getDate()==null) {
            uiSeance.getDateErr().setText("أدخل التاريخ");
            uiSeance.getDateErr().setVisible(true);
        } else {
            uiSeance.getDateErr().setVisible(false);
            bdate = true;
        }
        if (uiSeance.getTime()==null) {
            uiSeance.getTimeErr().setText("أدخل الوقت");
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
