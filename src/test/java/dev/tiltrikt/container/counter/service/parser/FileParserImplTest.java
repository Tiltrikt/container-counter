package dev.tiltrikt.container.counter.service.parser;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class FileParserImplTest {

  private final static String TEST_FILENAME = "src/test/resources/test.txt";

  @NotNull FileParser fileParser = new FileParserImpl(TEST_FILENAME);

  @SneakyThrows
  @BeforeAll
  static void setUp() {
    Path path = Paths.get(TEST_FILENAME);
    if (Files.notExists(path)) {
      Files.createDirectories(path.getParent());
      Files.createFile(path);
    }
  }

  @SneakyThrows
  @Test
  void givenFile_whenResolveFirstTime_thenReturnedResolvedMatrixWithOnlyTwoLines() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("467.\n....\n..*.");
    writer.close();

    char[][] matrix = new char[3][];
    fileParser.parseBunch(matrix);

    char[][] expected = {
        null,
        {'4', '6', '7', '.'},
        {'.', '.', '.', '.'}
    };
    assertArrayEquals(expected, matrix);
  }

  @SneakyThrows
  @Test
  void givenEmptyFile_whenResolveFirstTime_thenReturnedResolvedMatrixWithNulls() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("");
    writer.close();

    char[][] matrix = new char[3][];
    fileParser.parseBunch(matrix);

    char[][] expected = {
        null,
        null,
        null
    };
    assertArrayEquals(expected, matrix);
  }

  @SneakyThrows
  @Test
  void givenFileWithOneLine_whenResolveFirstTime_thenReturnedResolvedMatrix() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("456.");
    writer.close();

    char[][] matrix = new char[3][];
    fileParser.parseBunch(matrix);

    char[][] expected = {
        null,
        {'4', '5', '6', '.'},
        null
    };
    assertArrayEquals(expected, matrix);
  }

  @SneakyThrows
  @Test
  void givenFile_whenResolveSecondTime_thenReturnedResolvedMatrixWithThreeLines() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("467.\n....\n..*.");
    writer.close();

    char[][] matrix = new char[3][];
    fileParser.parseBunch(matrix);
    fileParser.parseBunch(matrix);

    char[][] expected = {
        {'4', '6', '7', '.'},
        {'.', '.', '.', '.'},
        {'.', '.', '*', '.'}
    };
    assertArrayEquals(expected, matrix);
  }
  @SneakyThrows
  @Test
  void givenFile_whenResolveLastTime_thenReturnedResolvedMatrixWithTwoLines() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("467.\n....\n..*.");
    writer.close();

    char[][] matrix = new char[3][];
    fileParser.parseBunch(matrix);
    fileParser.parseBunch(matrix);
    fileParser.parseBunch(matrix);

    char[][] expected = {
        {'.', '.', '.', '.'},
        {'.', '.', '*', '.'},
        null
    };
    assertArrayEquals(expected, matrix);
  }
}