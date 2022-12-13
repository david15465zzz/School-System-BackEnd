package schoolSystem.schoolSystem.beans;


import schoolSystem.schoolSystem.enums.Subject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grades")

public class Grade implements Comparable<Grade>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int score;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Teacher teacher;



    public int getId() {
        return id;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", subject=" + subject +
                ", date=" + date +
                ", score=" + score +
                ", student=" + student +
                ", teacher=" + teacher +
                '}';
    }

    @Override
    public int compareTo(Grade o) {
        return getDate().compareTo(o.getDate());
    }
}
