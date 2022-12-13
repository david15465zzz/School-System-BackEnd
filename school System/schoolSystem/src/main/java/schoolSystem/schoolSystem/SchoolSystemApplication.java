package schoolSystem.schoolSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import schoolSystem.schoolSystem.beans.Facades;
import schoolSystem.schoolSystem.jobs.DeleteOldGradesJob;
import schoolSystem.schoolSystem.jobs.DeleteOldLessonsJob;

import java.util.HashMap;

@SpringBootApplication
public class SchoolSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SchoolSystemApplication.class, args);
		DeleteOldGradesJob deleteOldGradesJob= ctx.getBean(DeleteOldGradesJob.class);
		DeleteOldLessonsJob deleteOldLessonsJob= ctx.getBean(DeleteOldLessonsJob.class);

		deleteOldGradesJob.run();
		deleteOldLessonsJob.run();

	}
	@Bean
	public HashMap<Long, Facades> facades(){
		return new HashMap<Long, Facades>();
	}
}



