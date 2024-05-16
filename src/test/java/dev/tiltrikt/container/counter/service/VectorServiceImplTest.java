package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.model.MatrixElement;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class VectorServiceImplTest {

  @NotNull VectorServiceImpl vectorService = new VectorServiceImpl();

  @Test
  void givenMatrixElement_whenGetVectorLength_thenReturnedRightLength() {
    MatrixElement matrixElement = MatrixElement.builder()
        .positionX(3)
        .positionY(4)
        .build();

    assertEquals(5, vectorService.getVectorLength(matrixElement));
  }

  @Test
  void givenTwoMatrixElements_whenSubtractVectors_thenReturnedNewMatrixElementWithRightValues() {
    MatrixElement a = MatrixElement.builder()
        .positionX(0)
        .positionY(5)
        .build();
    MatrixElement b = MatrixElement.builder()
        .positionX(5)
        .positionY(0)
        .build();

    MatrixElement result = vectorService.subtractVectors(a, b);
    assertEquals(-5, result.getPositionX());
    assertEquals(5, result.getPositionY());
  }
}