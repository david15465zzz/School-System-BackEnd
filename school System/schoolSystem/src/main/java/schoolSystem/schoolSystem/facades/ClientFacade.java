package schoolSystem.schoolSystem.facades;

import schoolSystem.schoolSystem.repositories.GradeRepository;
import schoolSystem.schoolSystem.repositories.LessonRepository;
import schoolSystem.schoolSystem.repositories.StudentRepository;
import schoolSystem.schoolSystem.repositories.TeacherRepository;

public abstract class ClientFacade {

    protected GradeRepository gradeRepository;
    protected LessonRepository lessonRepository;
    protected StudentRepository studentRepository;
    protected TeacherRepository teacherRepository;

    public ClientFacade(GradeRepository gradeRepository, LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }
}
