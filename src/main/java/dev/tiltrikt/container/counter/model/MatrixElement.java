package dev.tiltrikt.container.counter.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class MatrixElement {

  int positionX;

  int positionY;
}
