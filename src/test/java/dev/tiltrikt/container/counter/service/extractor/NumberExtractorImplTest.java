package dev.tiltrikt.container.counter.service.extractor;

import dev.tiltrikt.container.counter.service.extactor.NumberExtractor;
import dev.tiltrikt.container.counter.service.extactor.NumberExtractorImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class NumberExtractorImplTest {

  NumberExtractor digitResolver = new NumberExtractorImpl();

  @Test
  void givenArrayWithNumberInTheMiddle_whenExtract_Number_ThenReturnedIntegerAndRemovedItFromMatrix() {
    char[][] matrix = {
        {'.', '1', '2', '3', '.'}
    };
    assertEquals(123, digitResolver.extractNumber(matrix, 0, 2));

    char[][] expected = {
        {'.', '.', '.', '.', '.'}
    };
    assertArrayEquals(matrix, expected);
  }

  @Test
  void givenArrayWithNumberAtTheBeginning_whenExtract_Number_ThenReturnedIntegerAndRemovedItFromMatrix() {
    char[][] matrix = {
        {'1', '2', '3', '.'}
    };
    assertEquals(123, digitResolver.extractNumber(matrix, 0, 2));

    char[][] expected = {
        {'.', '.', '.', '.'}
    };
    assertArrayEquals(matrix, expected);
  }

  @Test
  void givenArrayWithNumberAtTheEnd_whenExtract_Number_ThenReturnedIntegerAndRemovedItFromMatrix() {
    char[][] matrix = {
        {'.', '1', '2', '3'}
    };
    assertEquals(123, digitResolver.extractNumber(matrix, 0, 2));

    char[][] expected = {
        {'.', '.', '.', '.'}
    };
    assertArrayEquals(matrix, expected);
  }
}