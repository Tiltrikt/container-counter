package dev.tiltrikt.container.counter.service.extactor;


import org.jetbrains.annotations.NotNull;

/**
 * Provides convenient method for extracting number from matrix. This implementation uses
 * {@link StringBuilder}
 */
public class NumberExtractorImpl implements NumberExtractor {

  /**
   * Extracts a number from a specified position in the character matrix.
   * The method starts at the given coordinates (y, x) and looks for digits
   * to the left and right to from the initial position. Then these digits are replaced with '.'
   *
   * @param matrix the 3xN character array from which the number must be extracted
   * @param y the y-coordinate (row index) in the matrix
   * @param x the x-coordinate (column index) in the matrix
   * @return the extracted number as an integer
   */
  public int extractNumber(char[][] matrix, int y, int x) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(matrix[y][x]);
    matrix[y][x] = '.';

    stringBuilder.insert(0, extractLeft(matrix, y, x));
    stringBuilder.append(extractRight(matrix, y, x));

    return Integer.parseInt(stringBuilder.toString());
  }

  /**
   * Extracts a string of digits to the left of the specified coordinates in the matrix.
   * Digits are collected until a non-digit character or the border of the matrix is reached.
   * The collected digits are removed from the matrix by replacing them with '.'.
   *
   * @param matrix the 3xN character array from which the number must be extracted
   * @param y the y-coordinate (row index) in the matrix
   * @param x the x-coordinate (column index) in the matrix from which extracting must be started
   * @return A string containing the extracted digits.
   */
  private @NotNull String extractLeft(char[][] matrix, int y, int x) {
    StringBuilder stringBuilder = new StringBuilder();

    char c;
    while (x > 0 && Character.isDigit(c = matrix[y][--x])) {
      stringBuilder.insert(0, c);
      matrix[y][x] = '.';
    }
    return stringBuilder.toString();
  }


  /**
   * Extracts a string of digits to the right of the specified coordinates in the matrix.
   * Digits are collected until a non-digit character or the border of the matrix is reached.
   * The collected digits are removed from the matrix by replacing them with '.'.
   *
   * @param matrix the 3xN character array from which the number must be extracted
   * @param y the y-coordinate (row index) in the matrix
   * @param x the x-coordinate (column index) in the matrix from which extracting must be started
   * @return A string containing the extracted digits.
   */
  private @NotNull String extractRight(char[][] matrix, int y, int x) {
    StringBuilder stringBuilder = new StringBuilder();

    char c;
    while (x < matrix[y].length - 1 && Character.isDigit(c = matrix[y][++x])) {
      stringBuilder.append(c);
      matrix[y][x] = '.';
    }
    return stringBuilder.toString();
  }
}
