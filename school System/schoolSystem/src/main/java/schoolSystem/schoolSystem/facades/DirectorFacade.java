package schoolSystem.schoolSystem.facades;

import org.springframework.stereotype.Service;
import schoolSystem.schoolSystem.beans.Lesson;
import schoolSystem.schoolSystem.beans.Student;
import schoolSystem.schoolSystem.beans.Teacher;
import schoolSystem.schoolSystem.repositories.GradeRepository;
import schoolSystem.schoolSystem.repositories.LessonRepository;
import schoolSystem.schoolSystem.repositories.StudentRepository;
import schoolSystem.schoolSystem.repositories.TeacherRepository;

import java.util.Date;
import java.util.List;

@Service
public class DirectorFacade extends ClientFacade{


    public DirectorFacade(GradeRepository gradeRepository, LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        super(gradeRepository, lessonRepository, studentRepository, teacherRepository);
    }

    public boolean login(String email, String password) {
        if (email.equals("director@gmail.com") && password.equals("Dire"))
            return true;
        return false;
    }

    public void createLesson(Lesson lesson) throws Exception {
        Date date = new Date();
        if (lesson.getId() != 0)
            throw new Exception("id is not 0");
        else if (lessonRepository.existsById(lesson.getId()))
            throw new Exception("lesson already exists");
        else if (lesson.getDate().before(date))
            throw new Exception("lesson cannot be in the past");
        else if (!teacherRepository.findById(lesson.getTeacher().getId()).orElseThrow(() ->  new Exception("teacher doesn't exists")).equals(lesson.getTeacher()))
            throw new Exception("something wrong with teacher details");
        for (Student student : lesson.getStudentList()) {
             if (!lesson.getStudentList().contains(studentRepository.findById(student.getId()).orElseThrow(() ->  new Exception("student doesn't exists"))))
                throw new Exception("something wrong with student details");
            else if (student.getFinishesSchool().before(lesson.getDate()))
                throw new Exception("one or more of the students finishes school before the lesson");
        }
        lessonRepository.save(lesson);

    }

    public void updateLesson(Lesson lesson) throws Exception {
        Date date = new Date();
        if (!lessonRepository.existsById(lesson.getId()))
            throw new Exception("lesson doesn't exists");
        else if (lesson.getDate().before(date))
            throw new Exception("lesson cannot be in the past");
        else if (!teacherRepository.findById(lesson.getTeacher().getId()).orElseThrow(() ->  new Exception("teacher doesn't exists")).equals(lesson.getTeacher()))
            throw new Exception("something wrong with teacher details");
        for (Student student : lesson.getStudentList()) {
            if (!lesson.getStudentList().contains(studentRepository.findById(student.getId()).orElseThrow(() ->  new Exception("student doesn't exists"))))
                throw new Exception("something wrong with student details");
            else if (student.getFinishesSchool().before(lesson.getDate()))
                throw new Exception("one or more of the students finishes school before the lesson");
        }
        lessonRepository.save(lesson);

    }

    public void deleteLesson(int lessonId) throws Exception {
        if (!lessonRepository.existsById(lessonId))
            throw new Exception("lesson doesn't exists");
        lessonRepository.deleteLessonsListHistoryByLessonId(lessonId);
        lessonRepository.deleteById(lessonId);

    }

    public void createTeacher(Teacher teacher) throws Exception {
        if (teacher.getId() != 0)
            throw new Exception("id is not 0");
        else if(teacherRepository.findTeacherByFullName(teacher.getFirstName(),teacher.getLastName())!=null)
            throw new Exception("teacher already exists");
        teacherRepository.save(teacher);

    }

    public void updateTeacher(Teacher teacher) throws Exception {
        Teacher teacher1=teacherRepository.findById(teacher.getId()).orElseThrow(()->new Exception("teacher doest exits/problem with id"));
        teacher1.setEmail(teacher.getEmail());
        teacher1.setPassword(teacher.getPassword());
        teacherRepository.save(teacher1);
    }
    public void deleteTeacher(int teacherId) throws Exception {
        if (!teacherRepository.existsById(teacherId))
            throw new Exception("teacher doesn't exists");
        teacherRepository.deleteById(teacherId);

    }
    public void createStudent(Student student) throws Exception {
        if (student.getId() != 0)
            throw new Exception("id is not 0");
        else if(studentRepository.findStudentByFullName(student.getFirstName(),student.getLastName())!=null)
            throw new Exception("student already exists");
        studentRepository.save(student);
    }
    public void updateStudent(Student student) throws Exception {
        Student student1=studentRepository.findById(student.getId()).orElseThrow(()->new Exception("student doest exits/problem with id"));
        student1.setEmail(student.getEmail());
        student1.setPassword(student.getPassword());
        student1.setClassroom(student.getClassroom());
        student1.setFinishesSchool(student.getFinishesSchool());
        studentRepository.save(student1);
    }
    public void deleteStudent(int studentId) throws Exception {
        if (!studentRepository.existsById(studentId))
            throw new Exception("student doesn't exists");
        gradeRepository.deleteGradesByStudent(studentId);
        lessonRepository.deleteLessonsListHistoryByStudentId(studentId);
        studentRepository.deleteById(studentId);

    }

    public List<Student> getAllStudents(){
       return studentRepository.findAll();
    }
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }
    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }








}
