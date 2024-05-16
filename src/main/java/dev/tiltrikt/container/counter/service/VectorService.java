package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.model.MatrixElement;
import org.jetbrains.annotations.NotNull;

public interface VectorService {

  @NotNull MatrixElement subtractVectors(@NotNull MatrixElement a, @NotNull MatrixElement b);

  double getVectorLength(@NotNull MatrixElement vector);

}
