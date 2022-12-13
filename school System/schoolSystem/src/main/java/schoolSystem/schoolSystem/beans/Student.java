package schoolSystem.schoolSystem.beans;

import schoolSystem.schoolSystem.enums.Classroom;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student extends Human{


    @Temporal(TemporalType.DATE)
    private Date joinedTheSchool;
    @Temporal(TemporalType.DATE)
    private Date finishesSchool;
    @Enumerated(EnumType.STRING)
    private Classroom classroom;


    public Student(int id, String firstName, String lastName, String email, String password, int age, Date joinedTheSchool, Date finishesSchool, Classroom classroom) {
        super(id, firstName, lastName, email, password, age);
        this.joinedTheSchool = joinedTheSchool;
        this.finishesSchool = finishesSchool;
        this.classroom = classroom;
    }

    public Date getJoinedTheSchool() {
        return joinedTheSchool;
    }

    public void setJoinedTheSchool(Date joinedTheSchool) {
        this.joinedTheSchool = joinedTheSchool;
    }

    public Date getFinishesSchool() {
        return finishesSchool;
    }

    public void setFinishesSchool(Date finishesSchool) {
        this.finishesSchool = finishesSchool;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Student{"+ Human.class.toString()+
                "joinedTheSchool=" + joinedTheSchool +
                ", finishesSchool=" + finishesSchool +
                ", classroom=" + classroom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(joinedTheSchool, student.joinedTheSchool) && Objects.equals(finishesSchool, student.finishesSchool) && classroom == student.classroom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinedTheSchool, finishesSchool, classroom);
    }
}
