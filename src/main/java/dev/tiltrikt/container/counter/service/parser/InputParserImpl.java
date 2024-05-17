package dev.tiltrikt.container.counter.service.parser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * Class for reading and parsing lines from input.
 * Allows to convert input into a format convenient for processing
 * This implementation uses a {@link Scanner} to read the input line by line.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InputParserImpl implements InputParser {

  @NotNull
  Scanner scanner = new Scanner(System.in);

  /**
   * Parses a bunch of lines from input and stores them in the provided matrix.
   * The matrix is a 3xN character array where each row holds a line from input.<br>
   *
   * If the matrix is initially empty, this method skips the first row of the matrix
   * and writes two lines into the next rows.<br>
   *
   * On subsequent calls, the method shifts the matrix rows up, discarding the first row, and
   * reads the next line into last row.<br>
   *
   * In case of ending input method return null but still shifts matrix and put null in the last row.
   *
   * @param matrix a 3xN character matrix to store the lines
   * @return {@code 1} if lines were successfully read, {@code 0} if the end of the input was reached
   */
  public int parseBunch(char[][] matrix) {

    if (matrix[1] == null) {
      matrix[0] = null;
      matrix[1] = parseLine();
      if((matrix[2] = parseLine()) == null) {
        return 0;
      }
      return 1;
    }

    matrix[0] = matrix[1];
    matrix[1] = matrix[2];
    if((matrix[2] = parseLine()) == null) {
      return 0;
    }
    return 1;
  }

  /**
   * Reads a single line from input and converts it to a character array.<br>
   * In case of ending file no exception would be thrown but method return null.
   *
   * @return a character array representing the line, or {@code null} if the end of input is reached
   */
  private char[] parseLine() {
    if (scanner.hasNextLine()) {
      return scanner.nextLine().toCharArray();
    }
    return null;
  }

}
