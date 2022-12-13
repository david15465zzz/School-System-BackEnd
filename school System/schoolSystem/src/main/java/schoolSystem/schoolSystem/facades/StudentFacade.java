package schoolSystem.schoolSystem.facades;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import schoolSystem.schoolSystem.beans.Grade;
import schoolSystem.schoolSystem.beans.Lesson;
import schoolSystem.schoolSystem.beans.Student;
import schoolSystem.schoolSystem.enums.Subject;
import schoolSystem.schoolSystem.repositories.GradeRepository;
import schoolSystem.schoolSystem.repositories.LessonRepository;
import schoolSystem.schoolSystem.repositories.StudentRepository;
import schoolSystem.schoolSystem.repositories.TeacherRepository;

import java.util.Collections;
import java.util.List;
@Service
@Scope("prototype")
public class StudentFacade extends ClientFacade {

    private int id;

    public StudentFacade(GradeRepository gradeRepository, LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        super(gradeRepository, lessonRepository, studentRepository, teacherRepository);
    }

    public int getId() {
        return id;
    }

    public boolean login(String email, String password) {
        if (studentRepository.findByEmail(email) != null && teacherRepository.findByEmail(email).getPassword().equals(password)) {
            this.id = studentRepository.findByEmail(email).getId();
            return true;
        }
        return false;
    }

    public List<Lesson> getAllLessonsOfStudent(){
      List<Lesson> lessonList=lessonRepository.findLessonsByStudentId(this.id);
      Collections.sort(lessonList);
      return lessonList;
    }
    public List<Lesson> getAllLessonsOfStudentBySubject(Subject subject){
        List<Lesson> lessonList= lessonRepository.findLessonsByStudentIdrAndSubject(this.id,subject);
        Collections.sort(lessonList);
        return lessonList;
    }
    public List<Grade> getAllGradesOfStudent(){
        List<Grade> grades= gradeRepository.findGradesByStudent(this.id);
        Collections.sort(grades);
        return grades;
    }
    public List<Grade> getAllGradesOfStudentOfSpecificRange(int min,int max){
        List<Grade> grades= gradeRepository.findGradesByStudentOfSpecificRange(this.id,min,max);
        Collections.sort(grades);
        return grades;
    }

    public Student getStudentDetails() {
        return studentRepository.findById(this.id).orElseThrow();
    }



}
