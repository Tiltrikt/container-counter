package dev.tiltrikt.container.counter.service.processor;

import dev.tiltrikt.container.counter.service.extactor.NumberExtractor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Provides methods to count numbers located next to specific symbols of a provided 3xN character array.
 * This implementation uses {@link NumberExtractor} to extract numbers from the matrix.<br>
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatrixProcessorImpl implements MatrixProcessor {

  @NotNull
  NumberExtractor numberExtractor;

  /**
   * Count numbers located next to the specific symbols in the
   * middle row of the provided 3xN character array.<br>
   *
   * Method look for specific symbols in middle row and then checks
   * neighboring positions for the presence of digit.<br>
   *
   * If on the top position is digit then method skip checking top-left
   * and top-right positions. Behavior for bottom is the same.
   *
   * @param matrix 3xN character array for which count must be executed
   * @return Sum of numbers located next to the specific symbols in the middle row
   */
  public int count(char[][] matrix) {
    int counter = 0;

    if (matrix[1] == null) {
      return counter;
    }

    for (int i = 0; i < matrix[1].length; i++) {
      if (!Character.isDigit(matrix[1][i]) && matrix[1][i] != '.') {

        counter += processRow(matrix, 0, i);
        counter += processRow(matrix, 2, i);
        counter += getNumberAtPosition(matrix, 1, i - 1);
        counter += getNumberAtPosition(matrix, 1, i + 1);
      }
    }
    return counter;
  }

  /**
   * Get the number at a specific position in the matrix. If the position
   * is outside the matrix, the method will return 0.
   *
   * @return The extracted number if the position contains a digit, otherwise 0;
   */
  private int getNumberAtPosition(char[][] matrix, int y, int x) {
    if (x < 0 || x >= matrix[y].length) {
      return 0;
    }
    if (Character.isDigit(matrix[y][x])) {
      return numberExtractor.extractNumber(matrix, y, x);
    }
    return 0;
  }

  /**
   * Process three positions (left, middle, right) in matrix to check if there is number.
   * Useful for checking top and bottom of specific symbol.
   * If number is in the middle there is no sense in checking left and right positions.
   * It allows to remove redundant memory calls.
   *
   * @return the sum of numbers located next to the specific symbol in the row
   */
  private int processRow(char[][] matrix, int y, int x) {
    int counter = 0;

    if (matrix[y] != null) {
      if (!(x < 0 || x >= matrix[y].length) && Character.isDigit(matrix[y][x])) {
        counter += numberExtractor.extractNumber(matrix, y, x);
      } else {
        counter += getNumberAtPosition(matrix, y, x - 1);
        counter += getNumberAtPosition(matrix, y, x + 1);
      }
    }
    return counter;
  }
}
