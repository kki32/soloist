import Models.ClassRoom;
import Models.Schedule;
import Models.Teacher;
import Models.Topic;

import java.util.Date;

/**
 * Created by kki32 on 8/10/16.
 */
public class App {

  public static void main(String[] args) {
    Teacher teacher = new Teacher();
    teacher.addTaughtClassRoom(new ClassRoom("13A"));
    teacher.addTaughtClassRoom(new ClassRoom("12B"));

    Schedule schedule = new Schedule();
    schedule.addTopic(new Topic("note", "grade 1"));
    schedule.addTopic(new Topic("chord", "grade 1"));
    teacher.addSchedule(teacher.getTaughtClassRoom("12B"), schedule);

  }
}
