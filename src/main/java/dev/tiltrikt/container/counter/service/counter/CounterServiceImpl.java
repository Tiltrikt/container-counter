package dev.tiltrikt.container.counter.service.counter;

import dev.tiltrikt.container.counter.service.parser.FileParser;
import dev.tiltrikt.container.counter.service.parser.FileParserImpl;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Service is responsible for counting containers from a given file.
 * The counting process involves reading the file
 * and using a matrix processor to count the containers.<br>
 *
 * This implementation uses {@link MatrixProcessor} that counts containers in
 * 3xN matrix and {@link FileParser} for parsing file to matrix.
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CounterServiceImpl implements CounterService {

  @NotNull
  MatrixProcessor matrixProcessor;

  /**
   * Count sum of containers in the specified file. Result is printed to standard output.<br>
   * Process is started with parsing 3 lines from file to matrix where each row represents line
   * in file. Then matrix processor counts containers in this matrix. the process is repeated
   * until the end of file is reached.<br>
   *
   * Method also checks first and last line for the presence of specific symbols.
   *
   * @param fileName The name of the file containing map with containers.
   */
  public void countContainers(@NotNull String fileName) {
    FileParser fileParser = new FileParserImpl(fileName);

    long counter = 0;
    char[][] matrix = new char[3][];

    while (fileParser.parseBunch(matrix) != 0) {
      counter += matrixProcessor.count(matrix);
    }
    counter += matrixProcessor.count(matrix);

    System.out.println(counter);
  }
}
