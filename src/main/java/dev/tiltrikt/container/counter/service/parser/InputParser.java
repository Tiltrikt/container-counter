package dev.tiltrikt.container.counter.service.parser;

/**
 * Interface for parsing input to matrix. Allows
 * to convert input into a format convenient for further processing.
 */
public interface InputParser {

  /**
   * Parses a bunch of lines from input and stores them in the provided matrix.
   * The matrix is a 3xN character array where each row holds a line from input.
   *
   * @param matrix 3xN character array to store the lines
   * @return {@code 1} if lines were successfully read, {@code 0} if the end of input was reached.
   */
  int parseBunch(char[][] matrix);
}
