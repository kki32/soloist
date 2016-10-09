package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kki32 on 8/10/16.
 */
public class Topic {
  private String topicType;
  private String level;
  private List<Question> questions = new ArrayList<Question>();
  private Integer DEFAULT_QUESTION = 10;

  private Integer STAVE_QUESTION_TYPE = "stave";
  private Integer PIANO_QUESTION_TYPE = "piano";

  public Topic(String topicType, String level){
    this.topicType = topicType;
    this.level = level;
    }

//  private void generateQuestion(Integer number, String topicType, String level){
//
//  }

  private void generateQuestion(String questionType){
    if(questionType.equals(STAVE_QUESTION_TYPE)) {
      for(int ques = 0; ques < DEFAULT_QUESTION; ques++){
        questions.add(new Question(""))
      }
    }
      else if(questionType.equals(PIANO_QUESTION_TYPE)){
      for(int ques = 0; ques < DEFAULT_QUESTION; ques++){


      }
    }


  }




}
