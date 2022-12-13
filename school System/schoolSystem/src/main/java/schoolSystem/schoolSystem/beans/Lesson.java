package schoolSystem.schoolSystem.beans;

import schoolSystem.schoolSystem.enums.Subject;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "lessons")

public class Lesson implements Comparable<Lesson>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Teacher teacher;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> studentList;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    public Lesson(int id, Teacher teacher, Date date, Subject subject) {
        this.id = id;
        this.teacher = teacher;
        this.date = date;
        this.subject = subject;
    }


    public int getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public Set <Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set <Student> studentList) {
        this.studentList = studentList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", date=" + date +
                ", studentList=" + studentList +
                ", subject=" + subject +
                '}';
    }


    @Override
    public int compareTo(Lesson o) {
        return getDate().compareTo(o.getDate());
    }
}

