package schoolSystem.schoolSystem.controllers;

import com.auth0.jwt.JWT;
import org.springframework.web.bind.annotation.*;
import schoolSystem.schoolSystem.beans.*;
import schoolSystem.schoolSystem.enums.Subject;
import schoolSystem.schoolSystem.facades.StudentFacade;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping(path = "/student")
@CrossOrigin
public class StudentController {
    private HttpServletRequest request;

    private HashMap<Long, Facades> facades;

    public StudentController(HttpServletRequest request, HashMap<Long, Facades> facades) {
        this.request = request;
        this.facades = facades;
    }


    @GetMapping(path = "/getAllLessonsOfStudent")
    public List<Lesson> getAllLessonsOfStudent(){
        return studentFacade().getAllLessonsOfStudent();
    }
    @GetMapping(path = "/getAllLessonsOfStudentBySubject/{subject}")
    public  List<Lesson> getAllLessonsOfStudentBySubject(@PathVariable Subject subject){
        return  studentFacade().getAllLessonsOfStudentBySubject(subject);
    }
    @GetMapping(path = "/getAllGradesOfStudent")
    public List<Grade> getAllGradesOfStudent(){
        return  studentFacade().getAllGradesOfStudent();
    }
    @GetMapping(path = "/getAllGradesOfStudent/{min}/{max}")
    public List<Grade> getAllGradesOfStudentOfSpecificRange(@PathVariable int min, @PathVariable int max){
        return  studentFacade().getAllGradesOfStudentOfSpecificRange(min,max);
    }
    @GetMapping(path = "/getStudentDetails")
    public  Student getStudentDetails(){
        return  studentFacade().getStudentDetails();
    }




    public StudentFacade studentFacade() {
        String token = request.getHeader("authorization").replace("Bearer ", "");
        long id = JWT.decode(token).getClaim("id").asLong();
        Facades facade = facades.get(id);
        if (facade != null) {
            facade.setLastActive(System.currentTimeMillis());
            StudentFacade studentFacade = (StudentFacade) facade.getFacade();

            return studentFacade;
        }
        return null;
    }

}
