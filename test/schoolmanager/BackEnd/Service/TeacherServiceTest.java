/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Model.Teacher;
import schoolmanager.BackEnd.Results;

/**
 *
 * @author Zed Yacine
 */
public class TeacherServiceTest {

    public TeacherServiceTest() {
        DataBaseConnection.Connect();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addTeacher method, of class TeacherService.
     */
    @Test
    public void testAddTeacher() {
        System.out.println("addTeacher");
        Teacher yacine = new Teacher("zed", "yacine", "0658185867", "ciam wiaame");
        Teacher hamid = new Teacher("zed", "hamid", "0658185800", "ciam wiaame");
        Teacher sedik = new Teacher("zed", "sedik", "0658185801", "ciam wiaame");
        Results.Rstls result0 = TeacherService.addTeacher(yacine);
        Results.Rstls result1 = TeacherService.addTeacher(hamid);
        Results.Rstls result2 = TeacherService.addTeacher(sedik);
        System.err.println(result0 + " " + result1 + " " + result2);
    }

    /**
     * Test of updateTeacher method, of class TeacherService.
     */
    @Test
    public void testUpdateTeacher() {
        System.out.println("updateTeacher");
        Teacher teacher = new Teacher(1, "zed", "yacine", "0658185867", "ciam mehafir");
        Results.Rstls result = TeacherService.updateTeacher(teacher);
        System.err.println(result);
    }

    /**
     * Test of deleteTeacher method, of class TeacherService.
     */
    @Test
    public void testDeleteTeacher() {
        System.out.println("deleteTeacher");
        Teacher teacher = new Teacher(3);
        Results.Rstls result = TeacherService.deleteTeacher(teacher);
        System.err.println(result);
    }

    /**
     * Test of getAllTeachers method, of class TeacherService.
     */
    @Test
    public void testGetAllTeachers() {
        System.out.println("getAllTeachers");
        ObservableList<Teacher> teachers = TeacherService.getAllTeachers();
        teachers.forEach((t) -> {
           t.PresentObject(t);
        });
    }

    /**
     * Test of searchStudentByName method, of class TeacherService.
     */
    @Test
    public void testSearchStudentByName() {
        System.out.println("searchStudentByName");
        Teacher yacine = new Teacher("zed", "yacine");
        Teacher yacTeacher = TeacherService.searchStudentByName(yacine);
        System.err.println(yacTeacher.getId());
    }

}
