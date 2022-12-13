package schoolSystem.schoolSystem.jobs;

import org.springframework.stereotype.Service;
import schoolSystem.schoolSystem.beans.Grade;
import schoolSystem.schoolSystem.repositories.GradeRepository;

import java.util.Date;
import java.util.List;

@Service
public class DeleteOldGradesJob implements Runnable {

    private GradeRepository gradeRepository ;

    private boolean isWorking = true;



    public DeleteOldGradesJob(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }


    public void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public void run() {
        while (isWorking) {
            List<Grade> gradeList = gradeRepository.findAll();
            Date date = new Date();
            for (Grade grade : gradeList) {
                if (date.getYear()<grade.getDate().getYear())
                    gradeRepository.deleteById(grade.getId());
            }
            try {
                Thread.sleep(86400000);
            } catch (InterruptedException e) {
                System.out.println("Error encountered while attempting to run daily task");


            }


        }
    }
}
