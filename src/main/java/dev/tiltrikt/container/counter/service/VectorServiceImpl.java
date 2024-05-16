package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.model.MatrixElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class VectorServiceImpl implements VectorService {

  @Contract("_, _ -> new")
  public @NotNull MatrixElement subtractVectors(@NotNull MatrixElement a, @NotNull MatrixElement b) {
    return MatrixElement.builder()
        .positionX(a.getPositionX() - b.getPositionX())
        .positionY(a.getPositionY() - b.getPositionY())
        .build();
  }

  public double getVectorLength(@NotNull MatrixElement vector) {
    return Math.sqrt(Math.pow(vector.getPositionX(), 2) + Math.pow(vector.getPositionY(), 2));
  }
}
