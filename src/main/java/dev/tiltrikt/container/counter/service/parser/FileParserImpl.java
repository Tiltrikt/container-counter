package dev.tiltrikt.container.counter.service.parser;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.io.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileParserImpl implements FileParser {

  @NotNull
  BufferedReader bufferedReader;

  @SneakyThrows
  public FileParserImpl(String fileName) {
    this.bufferedReader = new BufferedReader(new FileReader(fileName));
  }

  /**
   * Parses a bunch of lines from the file and stores them in the provided matrix.
   * The matrix is a 3xN character array where each row holds a line from the file.<br>
   *
   * If the matrix is initially empty, this method skips the first row of the matrix
   * and writes two lines into the next rows.<br>
   *
   * On subsequent calls, the method shifts the matrix rows up, discarding the first row, and
   * reads the next line into last row.
   * <br>
   *
   * In case of ending file method throw no exception but put null in the last row.
   *
   * @param matrix a 3xN character matrix to store the lines
   * @return {@code 1} if lines were successfully read, {@code 0} if the end of the file was reached
   */
  @SneakyThrows
  public int parseBunch(char[][] matrix) {

    if (matrix[1] == null) {
      matrix[0] = null;
      matrix[1] = parseLine();
      if((matrix[2] = parseLine()) == null) {
        return 0;
      }
      return 1;
    }

    matrix[0] = matrix[1];
    matrix[1] = matrix[2];
    if((matrix[2] = parseLine()) == null) {
      return 0;
    }
    return 1;
  }

  private char[] parseLine() throws IOException {
    String line = bufferedReader.readLine();
    if (line == null) {
      return null;
    } else {
      return line.toCharArray();
    }
  }

}
