package dev.tiltrikt.container.counter.service.processor;

/**
 * Interface provides methods to count numbers located
 * next to specific symbols of a provided 3xN character array.
 */
public interface MatrixProcessor {

  /**
   * Count numbers located next to the specific symbols in the
   * middle row of the provided 3xN character array.<br>
   *
   * @param matrix 3xN character array for which count must be executed
   * @return Sum of numbers located next to the specific symbols in the middle row.
   */
  int count(char[][] matrix);
}
