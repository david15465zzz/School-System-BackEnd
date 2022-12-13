package schoolSystem.schoolSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolSystem.schoolSystem.beans.Student;
import schoolSystem.schoolSystem.beans.Teacher;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer> {

    @Query(value ="select * from students where first_name = ?1 and last_name = ?2",nativeQuery = true)
    Teacher findStudentByFullName(String firstName, String lastName);

    Student findByEmail( String email );

}
