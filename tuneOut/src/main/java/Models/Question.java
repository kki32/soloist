package Models;

/**
 * Created by kki32 on 8/10/16.
 */
public class Question {
  private String question;
  private String category;
  private String questionType;

  public Question(String question, String category, String questionType){
    this.question = question;
    this.category = category;
    this.questionType = questionType;
  }
}
