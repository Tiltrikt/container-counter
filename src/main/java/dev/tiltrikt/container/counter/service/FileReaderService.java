package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.model.MatrixElement;
import org.jetbrains.annotations.NotNull;

import java.io.EOFException;

public interface FileReaderService {

  @NotNull MatrixElement readNextValue() throws EOFException;
}
