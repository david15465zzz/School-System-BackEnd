package schoolSystem.schoolSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolSystem.schoolSystem.beans.Grade;
import schoolSystem.schoolSystem.beans.Lesson;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Integer> {

    @Query(value ="select * from grades where teacher_id = ?1",nativeQuery = true)
    List<Grade> findGradesByTeacher(int teacherId);

    @Query(value ="select * from grades where student_id = ?1",nativeQuery = true)
    List<Grade> findGradesByStudent(int studentId);

    @Query(value ="select * from grades where student_id = ?1 and score > ?2 and score < ?3",nativeQuery = true)
    List<Grade> findGradesByStudentOfSpecificRange(int studentId,int minScore,int maxScore);

    @Modifying
    @Transactional
    @Query(value = "delete from grades where student_id = ?1", nativeQuery = true)
    void  deleteGradesByStudent(int studentId);


}
