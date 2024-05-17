package dev.tiltrikt.container.counter.service.extactor;

/**
 * Interface provides a method to extract a number from a specified position
 * in a character matrix.
 */
public interface NumberExtractor {

  /**
   * Extracts a number from a specified position in the character matrix.
   *
   * @param matrix the 3xN character array from which the number must be extracted
   * @param y the y-coordinate (row index) in the matrix
   * @param x the x-coordinate (column index) in the matrix
   * @return the extracted number as an integer
   */
  int extractNumber(char[][] matrix, int y, int x);
}
