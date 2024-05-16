package dev.tiltrikt.container.counter.service.processor;

import dev.tiltrikt.container.counter.service.extactor.NumberExtractor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

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

    for (int i = 0; i < matrix[1].length; i++) {
      if (!Character.isDigit(matrix[1][i]) && matrix[1][i] != '.') {

        if (matrix[0] != null) {
          if (Character.isDigit(matrix[0][i])) {
            counter += numberExtractor.extractNumber(matrix, 0, i);
          } else {
            counter += getNumberAtPosition(matrix, 0, i - 1);
            counter += getNumberAtPosition(matrix, 0, i + 1);
          }
        }

        counter += getNumberAtPosition(matrix, 1, i - 1);
        counter += getNumberAtPosition(matrix, 1, i + 1);

        if (matrix[2] != null) {
          if (Character.isDigit(matrix[2][i])) {
            counter += numberExtractor.extractNumber(matrix, 2, i);
          } else {
            counter += getNumberAtPosition(matrix, 2, i - 1);
            counter += getNumberAtPosition(matrix, 2, i + 1);
          }
        }
      }
    }
    return counter;
  }

  private int getNumberAtPosition(char[][] matrix, int y, int x) {
    if (x < 0 || x >= matrix[y].length) {
      return 0;
    }
    if (Character.isDigit(matrix[y][x])) {
      return numberExtractor.extractNumber(matrix, y, x);
    }
    return 0;
  }

}
