package schoolSystem.schoolSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolSystem.schoolSystem.beans.Lesson;
import schoolSystem.schoolSystem.beans.Teacher;
import schoolSystem.schoolSystem.enums.Subject;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface LessonRepository  extends JpaRepository<Lesson, Integer> {

    @Query(value ="select * from lessons where teacher_id = ?1 and subject = ?2",nativeQuery = true)
    List<Lesson> findLessonsByTeacherAndSubject(int teacherId, Subject subject);

     @Query(value ="select * from lessons join lessons_student_list on lessons.id=lessons_student_list.lesson_id where lessons_student_list.student_list_id = ?1",nativeQuery = true)
     List<Lesson> findLessonsByStudentId(int studentId);

    @Query(value ="select * from lessons join lessons_student_list on lessons.id=lessons_student_list.lesson_id where lessons_student_list.student_list_id = ?1  and lessons.subject = ?2",nativeQuery = true)
    List<Lesson> findLessonsByStudentIdrAndSubject(int studentId, Subject subject);

    @Query(value ="select * from lessons where teacher_id = ?1",nativeQuery = true)
    List<Lesson> findLessonsByTeacher(int teacherId);


    @Modifying
    @Transactional
    @Query(value = "delete  from lessons_student_list where student_list_id = ?1", nativeQuery = true)
    void deleteLessonsListHistoryByStudentId(int studentId);

    @Modifying
    @Transactional
    @Query(value = "delete  from lessons_student_list where lesson_id = ?1", nativeQuery = true)
    void  deleteLessonsListHistoryByLessonId(int lessonId);
}
