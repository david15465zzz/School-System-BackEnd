package schoolSystem.schoolSystem.controllers;

import com.auth0.jwt.JWT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schoolSystem.schoolSystem.beans.*;
import schoolSystem.schoolSystem.facades.TeacherFacade;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/teacher")
@CrossOrigin
public class TeacherController {

    private HttpServletRequest request;

    private HashMap<Long, Facades> facades;

    public TeacherController(HttpServletRequest request, HashMap<Long, Facades> facades) {
        this.request = request;
        this.facades = facades;
    }

    @PostMapping(path = "/createGrade")
    public ResponseEntity<?> createGrade(@RequestBody Grade grade) throws Exception {
        teacherFacade().createGrade(grade);
        return ResponseEntity.ok(grade);

    }

    @PutMapping(path = "/updateGrade")
    public ResponseEntity<?> updateGrade(@RequestBody  Grade grade) throws Exception {
        teacherFacade().updateGrade(grade);
        return ResponseEntity.ok(grade);

    }

    @DeleteMapping(path = "/deleteGrade/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable int id) throws Exception {
        teacherFacade().deleteGrade(id);
        return ResponseEntity.ok("grade deleted");

    }
    @GetMapping(path = "/getTeachersLessons")
    public List<Lesson> getTeachersLessons(){
        return teacherFacade().getTeachersLessons();
    }
    @GetMapping(path = "/getTeachersGrades")
    public List<Grade> getTeachersGrades(){
        return  teacherFacade().getTeachersGrades();
    }
    @GetMapping(path = "/getTeacherDetails")
    public Teacher getTeacherDetails(){
        return  teacherFacade().getTeacherDetails();
    }





















    public TeacherFacade teacherFacade() {
        String token = request.getHeader("authorization").replace("Bearer ", "");
        long id = JWT.decode(token).getClaim("id").asLong();
        Facades facade = facades.get(id);
        if (facade != null) {
            facade.setLastActive(System.currentTimeMillis());
            TeacherFacade teacherFacade = (TeacherFacade) facade.getFacade();

            return teacherFacade;
        }
        return null;
    }

}
