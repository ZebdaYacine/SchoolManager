package schoolmanager.BackEnd.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Account extends  Student{
    private long  id,idStudent;
    private String day;
    private float amount;

    public Account() {
    }

    public Account(long id) {
        this.id = id;
    }

    public Account( long idStudent, String day, float amount) {
        this.idStudent = idStudent;
        this.day = day;
        this.amount = amount;
    }

    public Account(long id, long idStudent, String day, float amount) {
        this.id = id;
        this.idStudent = idStudent;
        this.day = day;
        this.amount = amount;
    }


    public Account(long id , String firstName, String lastName, String phone1, String phone2,
                   String sectionName, String day, float amount) {
        super(firstName, lastName, phone1, phone2, sectionName);
        this.day = day;
        this.amount = amount;
        this.id=id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void PresentAccount() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(this);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
