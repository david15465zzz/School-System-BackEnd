package schoolSystem.schoolSystem.LoginManager;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import schoolSystem.schoolSystem.beans.Credentials;
import schoolSystem.schoolSystem.facades.ClientFacade;
import schoolSystem.schoolSystem.facades.DirectorFacade;
import schoolSystem.schoolSystem.facades.StudentFacade;
import schoolSystem.schoolSystem.facades.TeacherFacade;

import javax.security.auth.login.LoginException;

@Service
public class LoginManager {

    private static ApplicationContext applicationContext;



    public LoginManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }





    public ClientFacade login(Credentials credentials) throws LoginException {
        switch(credentials.getClientType()){
            case Director:
                DirectorFacade directorFacade = applicationContext.getBean(DirectorFacade.class);
                if(!directorFacade.login(credentials.getEmail(), credentials.getPassword()))
                    throw new LoginException();
                return directorFacade;

            case Teacher:
                TeacherFacade teacherFacade= applicationContext.getBean(TeacherFacade.class);
                if(!teacherFacade.login(credentials.getEmail(), credentials.getPassword()))
                    throw new LoginException();
                return teacherFacade;

            case Student:
                StudentFacade studentFacade= applicationContext.getBean(StudentFacade.class);
                if(!studentFacade.login(credentials.getEmail(), credentials.getPassword()))
                    throw new LoginException();
                return studentFacade;

        }
        return null;
    }
}


