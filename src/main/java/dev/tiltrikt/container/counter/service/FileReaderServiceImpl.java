package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.exception.EOLException;
import dev.tiltrikt.container.counter.model.MatrixElement;
import dev.tiltrikt.container.counter.model.NumberElement;
import dev.tiltrikt.container.counter.model.SymbolElement;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileReaderServiceImpl implements FileReaderService {

  @NotNull
  BufferedReader bufferedReader;

  @NonFinal int positionX = -1;

  @NonFinal int positionY = 0;

  @NonFinal Character buffer;

  @SneakyThrows
  public FileReaderServiceImpl(String fileName) {
    bufferedReader = new BufferedReader(new FileReader(fileName));
    Runtime.getRuntime().addShutdownHook(
        new Thread(new Runnable() {
          @Override
          @SneakyThrows
          public void run() {bufferedReader.close();}
        })
    );
  }

  public @NotNull MatrixElement readNextValue() throws EOFException {
    Character charValue = read();
    while(charValue.equals('.')) {
      charValue = read();
    }

    if (Character.isDigit(charValue)) {
      return NumberElement.builder()
          .positionX(positionX)
          .positionY(positionY)
          .number(readInteger(charValue))
          .build();
    }

    return SymbolElement.builder()
        .positionX(positionX)
        .positionY(positionY)
        .build();
  }

  @SneakyThrows
  private char read() throws EOFException {
    int intValue;
    if (buffer != null) {
      intValue = buffer;
      buffer = null;
    } else {
      intValue = bufferedReader.read();
      positionX++;
    }

    if (intValue == -1) {
      throw new EOFException();
    }

    if ((char) intValue == '\n') {
      positionY++;
      positionX = -1;
      throw new EOLException();
    }

    return (char) intValue;
  }

  private int readInteger(char firstDigit) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(firstDigit);

    char charValue;
    try {
      while (Character.isDigit(charValue = read())) {
        stringBuilder.append(charValue);
      }
    } catch (EOFException exception) {
      return Integer.parseInt(stringBuilder.toString());
    }

    buffer = charValue;
    return Integer.parseInt(stringBuilder.toString());
  }
}
