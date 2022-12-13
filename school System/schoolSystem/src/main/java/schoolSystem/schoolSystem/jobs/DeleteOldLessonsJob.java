package schoolSystem.schoolSystem.jobs;

import org.springframework.stereotype.Service;
import schoolSystem.schoolSystem.beans.Lesson;
import schoolSystem.schoolSystem.repositories.LessonRepository;

import java.util.Date;
import java.util.List;
@Service
public class DeleteOldLessonsJob  implements Runnable {

    private LessonRepository lessonRepository ;

    private boolean isWorking = true;



    public DeleteOldLessonsJob(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }


    public void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public void run() {
        while (isWorking) {
            List<Lesson> lessonList = lessonRepository.findAll();
            Date date = new Date();
            for (Lesson lesson : lessonList) {
                if (date.getYear() < lesson.getDate().getYear() || date.getMonth() - lesson.getDate().getMonth() > 1 &&
                        date.getYear() > lesson.getDate().getYear())
                    lessonRepository.deleteById(lesson.getId());
            }
            try {
                Thread.sleep(86400000);
            } catch (InterruptedException e) {
                System.out.println("Error encountered while attempting to run daily task");


            }


        }
    }
}
