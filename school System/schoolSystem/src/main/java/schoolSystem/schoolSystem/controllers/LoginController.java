package schoolSystem.schoolSystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import schoolSystem.schoolSystem.LoginManager.LoginManager;
import schoolSystem.schoolSystem.beans.Credentials;
import schoolSystem.schoolSystem.beans.Facades;
import schoolSystem.schoolSystem.beans.Student;
import schoolSystem.schoolSystem.facades.ClientFacade;
import schoolSystem.schoolSystem.facades.StudentFacade;
import schoolSystem.schoolSystem.facades.TeacherFacade;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class LoginController {

    private LoginManager loginManager;
    private HashMap<Long, Facades> facades;
    TokenGenerator tokenGenerator;

    public LoginController(LoginManager loginManager, HashMap<Long, Facades> facades, TokenGenerator tokenGenerator) {
        this.loginManager = loginManager;
        this.facades = facades;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) throws LoginException {
        ClientFacade facade;
        facade = loginManager.login(credentials);
        String token;
        long id;
        if(facade instanceof StudentFacade){
            id = ((StudentFacade) facade).getId();
            token=tokenGenerator.createTokenForStudent(((StudentFacade) facade).getStudentDetails());}
        else if(facade instanceof TeacherFacade){
            id = ((TeacherFacade) facade).getId();
            token=tokenGenerator.createTokenForTeacher(((TeacherFacade)  facade).getTeacherDetails());}
        else {
            id = 0;
            token=tokenGenerator.createTokenForDirector();
        }
        facades.put(id, new Facades(facade, System.currentTimeMillis()));
        return ResponseEntity.ok(token);



    }


}
