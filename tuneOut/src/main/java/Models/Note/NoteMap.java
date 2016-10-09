package Models.Note;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NoteMap constructs a list for mapping between note and midi number. The range of the map is
 * between 0-127(midi number).
 */
public class NoteMap {
  public static ArrayList<Note> allNotes = new ArrayList<Note>();
  public static final int MIDI_NOTE_UPPER_BOUND = 127;

  /**
   * Constructor for notes between 12 and 127 on the midi note scale
   */
  static {
    //the range of midi note
    int initialMidi = 0;

    //the range of octave eg. starts from C0 to C9
    int initialOctave = -1;
    for (int octave = initialOctave; initialMidi <= MIDI_NOTE_UPPER_BOUND; octave++) {
      allNotes.add(new Note("C", octave, initialMidi, true));
      if (octave > -1) {
        allNotes.add(new Note("B#", octave - 1, initialMidi, false));   //B#-2 does not exist
      }
      allNotes.add(new Note("Dbb", octave, initialMidi++, false));

      allNotes.add(new Note("C#", octave, initialMidi, true));
      if (octave > -1) {
        allNotes.add(new Note("Bx", octave - 1, initialMidi, false));   //Bx-2 does not exist
      }
      allNotes.add(new Note("Db", octave, initialMidi++, false));

      allNotes.add(new Note("D", octave, initialMidi, true));
      allNotes.add(new Note("Cx", octave, initialMidi, false));
      allNotes.add(new Note("Ebb", octave, initialMidi++, false));

      allNotes.add(new Note("D#", octave, initialMidi, true));
      allNotes.add(new Note("Eb", octave, initialMidi, false));
      allNotes.add(new Note("Fbb", octave, initialMidi++, false));

      allNotes.add(new Note("E", octave, initialMidi, true));
      allNotes.add(new Note("Dx", octave, initialMidi, false));
      allNotes.add(new Note("Fb", octave, initialMidi++, false));

      allNotes.add(new Note("F", octave, initialMidi, true));
      allNotes.add(new Note("E#", octave, initialMidi, false));
      allNotes.add(new Note("Gbb", octave, initialMidi++, false));

      allNotes.add(new Note("F#", octave, initialMidi, true));
      allNotes.add(new Note("Ex", octave, initialMidi, false));
      allNotes.add(new Note("Gb", octave, initialMidi++, false));

      allNotes.add(new Note("G", octave, initialMidi, true));
      allNotes.add(new Note("Fx", octave, initialMidi, false));
      if (octave <= 8) {
        allNotes.add(new Note("Abb", octave, initialMidi, false));
      }
      initialMidi++;

      if (initialMidi <= MIDI_NOTE_UPPER_BOUND) {
        allNotes.add(new Note("G#", octave, initialMidi, true));
        allNotes.add(new Note("Ab", octave, initialMidi++, false));

        allNotes.add(new Note("A", octave, initialMidi, true));
        allNotes.add(new Note("Gx", octave, initialMidi, false));
        allNotes.add(new Note("Bbb", octave, initialMidi++, false));

        allNotes.add(new Note("A#", octave, initialMidi, true));
        allNotes.add(new Note("Bb", octave, initialMidi, false));
        allNotes.add(new Note("Cbb", octave + 1, initialMidi++, false));

        allNotes.add(new Note("B", octave, initialMidi, true));
        allNotes.add(new Note("Ax", octave, initialMidi, false));
        allNotes.add(new Note("Cb", octave + 1, initialMidi++, false));
      }
    }
  }

  /**
   * Check whether the given pair are within the given gap.
   *
   * @param firstNote  first note
   * @param secondNote second note
   * @param range      range between two notes
   * @return true
   */
  public static Boolean isClosePair(Note firstNote, Note secondNote, Integer range) {
    Integer midi = NoteMap.getMidi(firstNote.getBaseNoteWithOctave());
    Integer midi2 = NoteMap.getMidi(secondNote.getBaseNoteWithOctave());
    if (Math.abs(midi - midi2) <= range) {
      return true;
    }
    return false;
  }

  /**
   * Checks whether the pairs are close to each other(one step in sheet note).
   *
   * @param notesInChord notes in chord
   * @return a list of note pair
   */
  public static List<List<Note>> findClosePairs(List<Note> notesInChord, Integer betweenGap) {
    List<List<Note>> solutionPairs = new ArrayList<List<Note>>();
    //if two notes are closely to each other
    for (int i = 0; i < notesInChord.size() - 1; i++) {
      Note firstNote = notesInChord.get(i);
      Note secondNote = notesInChord.get(i + 1);
      if (isClosePair(firstNote, secondNote, betweenGap)) {
        if (!solutionPairs.isEmpty()) {
          solutionPairs.add(Arrays.asList(notesInChord.get(i), notesInChord.get(i + 1)));
        } else {
          solutionPairs.add(Arrays.asList(notesInChord.get(i), notesInChord.get(i + 1)));
        }
      }
    }
    return solutionPairs;

  }


  /**
   * get the note(in String) with octave using the midi number
   *
   * @param midi The midi number to be converted to note name.
   * @return The name of the note the maps to the midi note.
   */
  public static String getNoteString(int midi) {
    for (Note note : allNotes) {
      if (note.getMidiNumber() == midi && note.isPrimary()) {
        return note.getNoteName() + note.getOctave();
      }
    }
    return null;
  }

  /**
   * Get the note with midi number
   *
   * @param midi The midi number to be converted to note.
   * @return The note that maps to the midi number.
   */
  public static Note getNoteFromMidi(int midi) {
    for (Note note : allNotes) {
      if (note.getMidiNumber() == midi && note.isPrimary()) {
        return note;
      }
    }
    return null;
  }

  /**
   * Gets the note object using the string which represents note with octave.
   *
   * @param noteWithOctave the string to be checked against
   * @return the note object that has the note name and the octave of note
   */
  public static Note getNote(String noteWithOctave) {
    for (Note note : allNotes) {
      if (note.getNoteWithOctave().equalsIgnoreCase(noteWithOctave)) {
        return note;
      }
    }
    return null;
  }

  /**
   * gets the midi note number from the note string if note is a " " (space) midi number will be -1
   * which used for silence Note
   *
   * @param noteWithOctave The note name to be converted to a midi.
   * @return The midi note that matches the note name.
   */
  public static Integer getMidi(String noteWithOctave) {
    final int SILENCE_MIDI = -1;
    final String SILENCE_NOTE = " ";

    for (Note note : allNotes) {
      if (!noteWithOctave.equals(SILENCE_NOTE)) {
        if (note.getNoteWithOctave().equalsIgnoreCase(noteWithOctave)) {
          return note.getMidiNumber();
        }
      } else {
        return SILENCE_MIDI;
      }
    }
    return null;
  }

  /**
   * Gets the note for the indicated number of semitones
   *
   * @param noteWithOctave The note name used to get the semitone up or down of it.
   * @param semiToneStep   The number of semitones to move up or down.
   * @return The note name that was semitone steps away from the note.
   */
  public static String getSemitone(String noteWithOctave, int semiToneStep) {
    if (semiToneStep != 0) {
      int midiNumber = getMidi(noteWithOctave) + semiToneStep;
      String adjustedNote = getNoteString(midiNumber);
//
//      //if going down and there is a flat, use that
//      if (semiToneStep < 0) {
//        String equivalentFlatNote = EnharmonicMap.getEquivalentEnharmonic(adjustedNote);
//        if (equivalentFlatNote != null && equivalentFlatNote.contains("b")) {
//          adjustedNote = equivalentFlatNote;
//        }
//      }
//
      return adjustedNote;
    }
    return noteWithOctave;
  }

  /**
   * Gets the note for the indicated number of semitones
   *
   * @param note The note used to get the semitone up or down of it.
   * @param semiToneStep   The number of semitones to move up or down.
   * @return The note name that was semitone steps away from the note.
   */
  public static Note getSemitone(Note note, int semiToneStep) {
    return getNoteFromMidi(note.getMidiNumber() + semiToneStep);
  }

  /**
   * Gets an array of Midi values from an array of note strings
   *
   * @param notes Array of notes to be converted to midi.
   * @return An array of midi notes.
   */
  public static List<Integer> getMidiArray(List<String> notes) {
    int length = notes.size();
    AbstractList<Integer> midiArray = new ArrayList<Integer>(length);
    for (int index = 0; index < length; ++index) {
      midiArray.add(index, NoteMap.getMidi(notes.get(index)));
    }
    return midiArray;
  }


}