package schoolSystem.schoolSystem.facades;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import schoolSystem.schoolSystem.beans.Grade;
import schoolSystem.schoolSystem.beans.Lesson;
import schoolSystem.schoolSystem.beans.Teacher;
import schoolSystem.schoolSystem.repositories.GradeRepository;
import schoolSystem.schoolSystem.repositories.LessonRepository;
import schoolSystem.schoolSystem.repositories.StudentRepository;
import schoolSystem.schoolSystem.repositories.TeacherRepository;
import java.util.*;
@Service
@Scope("prototype")
public class TeacherFacade extends ClientFacade {

    private int id;

    public TeacherFacade(GradeRepository gradeRepository, LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        super(gradeRepository, lessonRepository, studentRepository, teacherRepository);
    }

    public int getId() {
        return id;
    }

    public boolean login(String email, String password) {
        if (teacherRepository.findByEmail(email) != null && teacherRepository.findByEmail(email).getPassword().equals(password)) {
            this.id = teacherRepository.findByEmail(email).getId();
            return true;
        }
        return false;
    }

    public void createGrade(Grade grade) throws Exception {
        Date date=new Date();
        if (grade.getId() != 0)
            throw new Exception("id is not 0");
        else if (grade.getDate().after(date))
            throw new Exception("the date makes no sense, how you give a grade to a test that's in the future");
        else if (lessonRepository.existsById(grade.getId()))
            throw new Exception("grade already exists");
        else if (grade.getScore()>100||grade.getScore()<0)
            throw new Exception("grade cannot be higher than 100 or lower than 0");
        else if (!studentRepository.existsById(grade.getStudent().getId()))
            throw new Exception("student not exists");
        else if (lessonRepository.findLessonsByTeacherAndSubject(this.id, grade.getSubject()) == null)
            throw new Exception("you are not teaching this subject");
        List<Lesson> lessonList = lessonRepository.findLessonsByTeacherAndSubject(this.id, grade.getSubject());
        boolean exists=false;
        for (Lesson lesson : lessonList) {
            if(lesson.getStudentList().contains(studentRepository.findById(grade.getStudent().getId()))){
               exists=true;
               break;}
        }
        if(!exists)
            throw new Exception("student not taking part in one of your class about this subject");
        grade.setTeacher(teacherRepository.findById(this.id).get());
        gradeRepository.save(grade);
    }

    public void updateGrade(Grade grade) throws Exception {
        Date date=new Date();
        if (gradeRepository.findById(grade.getId()).orElseThrow(() ->  new Exception("grade doesn't exists")).getTeacher().getId()!=this.id)
            throw new Exception("the grade is not yours,cant delete");
        else if (grade.getDate().after(date))
            throw new Exception("the date makes no sense, how you give a grade to a test that's in the future");
        else if (grade.getScore()>100||grade.getScore()<0)
            throw new Exception("grade cannot be higher than 100 or lower than 0");
        else if (!studentRepository.existsById(grade.getStudent().getId()))
            throw new Exception("student not exists");
        else if (lessonRepository.findLessonsByTeacherAndSubject(this.id, grade.getSubject()) == null)
            throw new Exception("you are not teaching this subject");
        List<Lesson> lessonList = lessonRepository.findLessonsByTeacherAndSubject(this.id, grade.getSubject());
        boolean exists=false;
        for (Lesson lesson : lessonList) {
            if(lesson.getStudentList().contains(studentRepository.findById(grade.getStudent().getId()))){
                exists=true;
                break;}
        }
        if(!exists)
            throw new Exception("student not taking part in one of your class about this subject");
        grade.setTeacher(teacherRepository.findById(this.id).get());
        gradeRepository.save(grade);


    }

    public void deleteGrade(int gradeId) throws Exception {
        if(gradeRepository.findById(gradeId).orElseThrow(() ->  new Exception("grade doesn't exists")).getTeacher().getId()!=this.id)
            throw new Exception("the grade is not yours,cant delete");
        gradeRepository.deleteById(gradeId);
    }

    public List<Lesson> getTeachersLessons(){
        List<Lesson> lessonList= lessonRepository.findLessonsByTeacher(this.id);
        Collections.sort(lessonList);
        return lessonList;
    }
    public List<Grade> getTeachersGrades(){
        List<Grade> grades= gradeRepository.findGradesByTeacher(this.id);
        Collections.sort(grades);
        return grades;
    }

    public Teacher getTeacherDetails(){
        return teacherRepository.findById(this.id).orElseThrow();
    }



}
