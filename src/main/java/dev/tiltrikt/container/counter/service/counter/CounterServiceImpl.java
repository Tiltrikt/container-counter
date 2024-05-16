package dev.tiltrikt.container.counter.service.counter;

import dev.tiltrikt.container.counter.service.parser.FileParser;
import dev.tiltrikt.container.counter.service.parser.FileParserImpl;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CounterServiceImpl implements CounterService {

  @NotNull
  MatrixProcessor matrixProcessor;

  /**
   * Count sum of containers in the specified file. Result is printed to standard output.
   *
   * @param fileName The name of the file containing map with containers
   */
  public void countContainers(@NotNull String fileName) {
    FileParser fileParser = new FileParserImpl(fileName);

    long counter = 0;
    char[][] matrix = new char[3][];

    while (fileParser.parseBunch(matrix) != 0) {
      counter += matrixProcessor.count(matrix);
    }

    System.out.println(counter);
  }
}
