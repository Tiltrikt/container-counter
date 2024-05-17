package dev.tiltrikt.container.counter.service.processor;

import dev.tiltrikt.container.counter.service.extactor.NumberExtractorImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class MatrixProcessorImplTest {

  @Mock
  @NotNull
  NumberExtractorImpl numberExtractor;

  @InjectMocks
  @NotNull
  MatrixProcessorImpl matrixProcessor;

  @Test
  void givenMatrixWithNumberUpInTheMiddle_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'4', '5', '7', '.'},
        {'.', '*', '.', '.'},
        {'.', '.', '.', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 0, 1)
    ).thenReturn(457);

    assertEquals(457, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberUpOnTheLeft_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'4', '.', '.', '.'},
        {'.', '*', '.', '.'},
        {'.', '.', '.', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 0, 0)
    ).thenReturn(4);

    assertEquals(4, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberUpOnTheRight_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'.', '.', '5', '5'},
        {'.', '*', '.', '.'},
        {'.', '.', '.', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 0, 2)
    ).thenReturn(55);

    assertEquals(55, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberOnTheRight_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'.', '*', '3', '.'},
        {'.', '.', '.', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 1, 2)
    ).thenReturn(3);

    assertEquals(3, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberOnTheLeft_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'3', '*', '.', '.'},
        {'.', '.', '.', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 1, 0)
    ).thenReturn(3);

    assertEquals(3, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberDownInTheMiddle_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'.', '*', '.', '.'},
        {'4', '5', '7', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 2, 1)
    ).thenReturn(457);

    assertEquals(457, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberDownOnTheLeft_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'.', '*', '.', '.'},
        {'4', '.', '.', '.'}
    };

    when(numberExtractor
        .extractNumber(matrix, 2, 0)
    ).thenReturn(4);

    assertEquals(4, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNumberDownOnTheRight_whenCount_thenReturnedInteger() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'.', '*', '.', '.'},
        {'.', '.', '5', '5'}
    };

    when(numberExtractor
        .extractNumber(matrix, 2, 2)
    ).thenReturn(55);

    assertEquals(55, matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNullTop_whenCount_thenThrownNothing() {
    char[][] matrix = {
        null,
        {'.', '*', '.', '.'},
        {'.', '.', '.', '.'}
    };

    assertDoesNotThrow(() -> matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithNullBottom_whenCount_thenThrownNothing() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'.', '*', '.', '.'},
        null
    };

    assertDoesNotThrow(() -> matrixProcessor.count(matrix));
  }

  @Test
  void givenEmptyMatrix_whenCount_thenThrownNothing() {
    char[][] matrix = {
        null,
        null,
        null
    };

    assertDoesNotThrow(() -> matrixProcessor.count(matrix));
  }

  @Test
  void givenMatrixWithLeftAndRightSymbol_whenCount_thenThrownNothing() {
    char[][] matrix = {
        {'.', '.', '.', '.'},
        {'*', ',', '.', '*'},
        {'.', '.', '.', '.'}
    };

    assertDoesNotThrow(() -> matrixProcessor.count(matrix));
  }
}