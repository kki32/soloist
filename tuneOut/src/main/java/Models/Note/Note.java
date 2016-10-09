package Models.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Note class represents object which consists of note name, octave, midi number, and boolean flag
 * whether it is a primary note or not.
 */
public class Note implements Comparable<Note> {

  private String noteName;
  private int octave;
  private Integer midiIndex;
  private Boolean primary;

  /**
   * Construct note based name, octave and midi number
   *
   * @param noteName  name of note eg. C
   * @param octave    which octave eg. 4
   * @param midiIndex midi number eg. 60
   * @param primary   boolean flag which specifies whether the note is 'primary' note in
   *                  enharmonicMap.
   */
  public Note(String noteName, int octave, int midiIndex, Boolean primary) {
    this.noteName = noteName;
    this.octave = octave;
    this.midiIndex = midiIndex;
    this.primary = primary;
  }

  /**
   * gets the name of a note
   *
   * @return noteName name of note eg. C
   */
  public String getNoteName() {
    return noteName;
  }

  /**
   * Gets the base note with octave.
   * @return the note with no accidental with octave
   */
  public String getBaseNoteWithOctave() {
    return getNoteWithOctave().replace(this.getAccidental(),"");
  }

  /**
   * gets the note's octave
   *
   * @return which octave eg. 4
   */
  public int getOctave() {
    return octave;
  }

  /**
   * gets the midi number for the note
   *
   * @return midiIndex midi number eg. 60
   */
  public Integer getMidiNumber() {
    return midiIndex;
  }

  /**
   * gets the note name with octave specifier
   *
   * @return note name with octave eg. C4
   */
  public String getNoteWithOctave() {
    return noteName + octave;
  }

  /**
   * Gets the base note, which is the current note without accidental.
   *
   * @return Note object of the base note
   */
  public Note getBaseNote() {
    String baseNote = getNoteName().substring(0, 1) + getOctave();
    return NoteMap.getNote(baseNote);
  }

  /**
   * Gets the base note with no octave, which is the current note without accidental.
   *
   * @return Note object of the base note
   */
  public String getBaseNoteNoOctave() {
    return getNoteName().substring(0, 1);
  }


  /**
   * Checks if the note has an accidental(sharp or flat).
   *
   * @return true if the note has an accidental
   */
  public Boolean hasAccidental() {
    return noteName.endsWith("b") || noteName.endsWith("#") || noteName.endsWith("x");
  }

  /**
   * Gets the accidental of the note.
   *
   * @return Accidental, or space if no accidental
   */
  public String getAccidental() {
    if (this.hasAccidental()) {
      return noteName.substring(1);
    } else {
      return " ";
    }
  }

  /**
   * Checks if the note is a primary note.
   *
   * @return true if the note is a primary note
   */
  public Boolean isPrimary() {
    return primary;
  }


  @Override
  public String toString() {
    return noteName + octave;
  }


  @Override
  public int compareTo(Note note) {
    List<String> accOrder = new ArrayList<String>(Arrays.asList("bb","b"," ","#","x"));
    List<Character> noteOrder = new ArrayList<Character>(Arrays.asList('C','D','E','F','G','A','B'));

    if (this.getNoteWithOctave().equals(note.getNoteWithOctave())) {
      return 0;
    }

    // consider octave, then note letter, then accidental
    if (this.getOctave() < note.getOctave()) {
      return 1;
    } else if (this.getOctave() > note.getOctave()) {
      return -1;
    }

    if (noteOrder.indexOf(this.getNoteName().charAt(0)) < noteOrder.indexOf(note.getNoteName().charAt(0))) {
      return 1;
    } else if (noteOrder.indexOf(this.getNoteName().charAt(0)) > noteOrder.indexOf(note.getNoteName().charAt(0))) {
      return -1;
    }

    //note is same octave and letter so now check accidental
    if (accOrder.indexOf(this.getAccidental()) < accOrder.indexOf(note.getAccidental())) {
      return 1;
    } else if (accOrder.indexOf(this.getAccidental()) > accOrder.indexOf(note.getAccidental())) {
      return -1;
    }

    return 0;
  }
}