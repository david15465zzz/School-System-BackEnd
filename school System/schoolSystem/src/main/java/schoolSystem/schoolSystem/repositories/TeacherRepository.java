package schoolSystem.schoolSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolSystem.schoolSystem.beans.Teacher;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Integer> {

    @Query(value ="select * from teachers where first_name = ?1 and last_name = ?2",nativeQuery = true)
    Teacher findTeacherByFullName(String firstName,String lastName);

    Teacher findByEmail( String email );
}
