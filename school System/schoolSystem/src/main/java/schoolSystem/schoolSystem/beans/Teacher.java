package schoolSystem.schoolSystem.beans;

import schoolSystem.schoolSystem.enums.Classroom;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher extends Human{

    @Enumerated(EnumType.STRING)
    private Classroom classroom;

    public Teacher(int id, String firstName, String lastName, String email, String password, int age, Classroom classroom) {
        super(id, firstName, lastName, email, password, age);
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Teacher{" +Human.class.toString()+
                "classroom=" + classroom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return classroom == teacher.classroom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroom);
    }
}
