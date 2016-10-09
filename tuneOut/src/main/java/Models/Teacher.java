package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kki32 on 8/10/16.
 */
public class Teacher {
  private String name;
  private List<ClassRoom> classRooms = new ArrayList<ClassRoom>();


  public void addTaughtClassRoom(ClassRoom classRoom){
    classRooms.add(classRoom);
  }

  public ClassRoom getTaughtClassRoom(String classRoom){
    for(ClassRoom classRm : classRooms){
      if(classRm.getClassRoom().equals(classRoom)){
        return classRm;
      }
    }
    return null;
  }


  public void addSchedule(ClassRoom classRoom, Schedule schedule){

  }

  public void addSchedule(Student student, ClassRoom classRoom, Schedule schedule){

  }
}
