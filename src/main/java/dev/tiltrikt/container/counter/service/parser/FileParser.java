package dev.tiltrikt.container.counter.service.parser;

public interface FileParser {

  /**
   * Parses a bunch of lines from the file and stores them in the provided matrix.
   * The matrix is a 3xN character array where each row holds a line from the file.
   *
   * @param matrix 3xN character array to store the lines
   * @return {@code 1} if lines were successfully read, {@code 0} if the end of the file was reached
   */
  int parseBunch(char[][] matrix);
}
