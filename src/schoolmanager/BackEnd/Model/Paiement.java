package schoolmanager.BackEnd.Model;

public class Paiement {
    private long id ;
    private Student std;
    private Group grp;
    private String date;
    private float amount;

    public Paiement() {}

    public Paiement(long id) {
        this.id = id;
    }

    public Paiement(Student std, Group grp) {
        this.std = std;
        this.grp = grp;
    }

    public Paiement(long id, Student std, String date, float amount) {
        this.id = id;
        this.std = std;
        this.date = date;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public Group getGrp() {
        return grp;
    }

    public void setGrp(Group grp) {
        this.grp = grp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
