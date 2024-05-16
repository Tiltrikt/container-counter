package dev.tiltrikt.container.counter.service.processor;

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
