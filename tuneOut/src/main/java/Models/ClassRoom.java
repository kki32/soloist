package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kki32 on 8/10/16.
 */
public class ClassRoom {
  private List<Student> students = new ArrayList<Student>();
  private String classRoomName;

  public ClassRoom(String classRoomName){
    this.classRoomName = classRoomName;
  }

  public void addStudent(Student student){
    students.add(student);
  }
  public String getClassRoom(){
    return classRoomName;
  }
}
