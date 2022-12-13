package schoolSystem.schoolSystem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import schoolSystem.schoolSystem.beans.Student;
import schoolSystem.schoolSystem.beans.Teacher;

import java.util.Calendar;
import java.util.Date;

@Component
public class TokenGenerator {
        protected String createTokenForDirector(){
            Calendar expiresAt = Calendar.getInstance();
            expiresAt.add(Calendar.MINUTE, 30);
            Date expires = expiresAt.getTime();
            String token = JWT.create()
                    .withIssuer("DavidTech")
                    .withIssuedAt(new Date())
                    .withClaim("id", 0)
                    .withClaim("role", "director")
                    .withExpiresAt(expires)
                    .sign(Algorithm.HMAC256("MyTopSecret"));
            return token;
        }
        protected String createTokenForTeacher(Teacher teacher){
            Calendar expiresAt = Calendar.getInstance();
            expiresAt.add(Calendar.MINUTE, 30);
            Date expires = expiresAt.getTime();
            String token = JWT.create()
                    .withIssuer("DavidTech")
                    .withIssuedAt(new Date())
                    .withClaim("id", teacher.getId())
                    .withClaim("role", "teacher")
                    .withExpiresAt(expires)
                    .sign(Algorithm.HMAC256("MyTopSecret"));
            return token;
        }
        protected String createTokenForStudent(Student student){
            Calendar expiresAt = Calendar.getInstance();
            expiresAt.add(Calendar.MINUTE, 30);
            Date expires = expiresAt.getTime();
            String token = JWT.create()
                    .withIssuer("DavidTech")
                    .withIssuedAt(new Date())
                    .withClaim("id", student.getId())
                    .withClaim("role", "student")
                    .withExpiresAt(expires)
                    .sign(Algorithm.HMAC256("MyTopSecret"));
            return token;
        }
}
