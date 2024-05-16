package dev.tiltrikt.container.counter.service.extactor;

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

    char c;
    int tmpX = x;
    while (tmpX > 0 && Character.isDigit(c = matrix[y][--tmpX])) {
      stringBuilder.insert(0, c);
      matrix[y][tmpX] = '.';
    }

    while (x < matrix[y].length - 1 && Character.isDigit(c = matrix[y][++x])) {
      stringBuilder.append(c);
      matrix[y][x] = '.';
    }

    return Integer.parseInt(stringBuilder.toString());
  }
}
