package dev.tiltrikt.container.counter.service.parser;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class InputParserImplTest {

  @AfterAll
  public static void tearDown() {
    System.setIn(System.in);
  }

  @SneakyThrows
  @Test
  void givenFile_whenResolveFirstTime_thenReturnedResolvedMatrixWithOnlyTwoLines() {
    System.setIn(new ByteArrayInputStream("467.\n....\n..*.".getBytes()));
    InputParser inputParser = new InputParserImpl();

    char[][] matrix = new char[3][];
    inputParser.parseBunch(matrix);

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
    System.setIn(new ByteArrayInputStream("".getBytes()));
    InputParser inputParser = new InputParserImpl();

    char[][] matrix = new char[3][];
    inputParser.parseBunch(matrix);

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
    System.setIn(new ByteArrayInputStream("456.".getBytes()));
    InputParser inputParser = new InputParserImpl();

    char[][] matrix = new char[3][];
    inputParser.parseBunch(matrix);

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
    System.setIn(new ByteArrayInputStream("467.\n....\n..*.".getBytes()));
    InputParser inputParser = new InputParserImpl();

    char[][] matrix = new char[3][];
    inputParser.parseBunch(matrix);
    inputParser.parseBunch(matrix);

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
    System.setIn(new ByteArrayInputStream("467.\n....\n..*.".getBytes()));
    InputParser inputParser = new InputParserImpl();

    char[][] matrix = new char[3][];
    inputParser.parseBunch(matrix);
    inputParser.parseBunch(matrix);
    inputParser.parseBunch(matrix);

    char[][] expected = {
        {'.', '.', '.', '.'},
        {'.', '.', '*', '.'},
        null
    };
    assertArrayEquals(expected, matrix);
  }
}