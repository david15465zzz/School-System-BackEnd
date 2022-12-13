package schoolSystem.schoolSystem.controllers;

import com.auth0.jwt.JWT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schoolSystem.schoolSystem.beans.Facades;
import schoolSystem.schoolSystem.beans.Lesson;
import schoolSystem.schoolSystem.beans.Student;
import schoolSystem.schoolSystem.beans.Teacher;
import schoolSystem.schoolSystem.facades.DirectorFacade;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/director")
@CrossOrigin
public class DirectorController {

    private HttpServletRequest request;

    private HashMap<Long, Facades> facades;

    public DirectorController(HttpServletRequest request, HashMap<Long, Facades> facades) {
        this.request = request;
        this.facades = facades;
    }


    @PostMapping(path = "/createLesson")
    public ResponseEntity<?> createLesson(@RequestBody Lesson lesson) throws Exception {
        directorFacade().createLesson(lesson);
        return ResponseEntity.ok(lesson);

    }

    @PutMapping(path = "/updateLesson")
    public ResponseEntity<?> updateLesson(@RequestBody Lesson lesson) throws Exception {
        directorFacade().updateLesson(lesson);
        return ResponseEntity.ok(lesson);

    }

    @DeleteMapping(path = "/deleteLesson/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable int id) throws Exception {
        directorFacade().deleteLesson(id);
        return ResponseEntity.ok("lesson deleted");

    }

    @PostMapping(path = "/createTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) throws Exception {
        directorFacade().createTeacher(teacher);
        return ResponseEntity.ok(teacher);

    }

    @PutMapping(path = "/updateTeacher")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher) throws Exception {
        directorFacade().updateTeacher(teacher);
        return ResponseEntity.ok(teacher);

    }

    @DeleteMapping(path = "/deleteTeacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id) throws Exception {
        directorFacade().deleteTeacher(id);
        return ResponseEntity.ok("teacher deleted");

    }


    @PostMapping(path = "/createStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) throws Exception {
        directorFacade().createStudent(student);
        return ResponseEntity.ok(student);

    }

    @PutMapping(path = "/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) throws Exception {
        directorFacade().updateStudent(student);
        return ResponseEntity.ok(student);

    }

    @DeleteMapping(path = "/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) throws Exception {
        directorFacade().deleteStudent(id);
        return ResponseEntity.ok("student deleted");

    }
    @GetMapping(path = "/getAllStudents")
    public List<Student> getAllStudents(){
        return directorFacade().getAllStudents();
    }
    @GetMapping(path = "/getAllTeachers")
    public List<Teacher> getAllTeachers(){
        return  directorFacade().getAllTeachers();
    }
    @GetMapping(path = "/getAllLessons")
    public List<Lesson> getAllLessons(){
        return  directorFacade().getAllLessons();
    }











    public DirectorFacade directorFacade() {
        String token = request.getHeader("authorization").replace("Bearer ", "");
        long id = JWT.decode(token).getClaim("id").asLong();
        Facades facade = facades.get(id);
        if (facade != null) {
            facade.setLastActive(System.currentTimeMillis());
            DirectorFacade directorFacade = (DirectorFacade) facade.getFacade();

            return directorFacade;
        }
        return null;
    }
}
