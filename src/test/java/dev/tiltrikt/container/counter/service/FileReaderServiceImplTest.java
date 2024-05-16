package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.model.MatrixElement;
import dev.tiltrikt.container.counter.model.NumberElement;
import dev.tiltrikt.container.counter.model.SymbolElement;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class FileReaderServiceImplTest {

  private final static String TEST_FILENAME = "test/test.txt";
  @NotNull
  FileReaderService fileReaderService = new FileReaderServiceImpl(TEST_FILENAME);

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
  void givenFileWithNumberAtTheBeginning_whenReadNextValue_thenReturnedNumberElementWithRightValues() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("467.");
    writer.close();

    MatrixElement matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(NumberElement.class, matrixElement);
    NumberElement numberElement = (NumberElement) matrixElement;
    assertEquals(0, numberElement.getPositionX());
    assertEquals(0, numberElement.getPositionY());
    assertEquals(467, numberElement.getNumber());
  }

  @SneakyThrows
  @Test
  void givenFileWithNumberInTheMiddle_whenReadNextValue_thenReturnedNumberElementWithRightValues() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write(".467.");
    writer.close();

    MatrixElement matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(NumberElement.class, matrixElement);
    NumberElement numberElement = (NumberElement) matrixElement;
    assertEquals(1, numberElement.getPositionX());
    assertEquals(0, numberElement.getPositionY());
    assertEquals(467, numberElement.getNumber());
  }

  @SneakyThrows
  @Test
  void givenFileWithNumberAtTheEnd_whenReadNextValue_thenReturnedNumberElementWithRightValues() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write(".467");
    writer.close();

    MatrixElement matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(NumberElement.class, matrixElement);
    NumberElement numberElement = (NumberElement) matrixElement;
    assertEquals(1, numberElement.getPositionX());
    assertEquals(0, numberElement.getPositionY());
    assertEquals(467, numberElement.getNumber());
  }

  @SneakyThrows
  @Test
  void givenFileWithNeighborsNumberAndSymbol_whenReadNextValue_thenReturnedNumberElementAndSymbolElement() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("467*");
    writer.close();

    MatrixElement matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(NumberElement.class, matrixElement);
    NumberElement numberElement = (NumberElement) matrixElement;
    assertEquals(0, numberElement.getPositionX());
    assertEquals(0, numberElement.getPositionY());
    assertEquals(467, numberElement.getNumber());

    matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(SymbolElement.class, matrixElement);
    SymbolElement symbolElement = (SymbolElement) matrixElement;
    assertEquals(3, symbolElement.getPositionX());
    assertEquals(0, symbolElement.getPositionY());
  }

  @SneakyThrows
  @Test
  void givenFileWithSeveralLines_whenReadNextValue_thenReturnedElementsFromAllLines() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("467.\n..*.\n.553");
    writer.close();

    MatrixElement matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(NumberElement.class, matrixElement);
    NumberElement numberElement = (NumberElement) matrixElement;
    assertEquals(0, numberElement.getPositionX());
    assertEquals(0, numberElement.getPositionY());
    assertEquals(467, numberElement.getNumber());

    matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(SymbolElement.class, matrixElement);
    SymbolElement symbolElement = (SymbolElement) matrixElement;
    assertEquals(2, symbolElement.getPositionX());
    assertEquals(1, symbolElement.getPositionY());

    matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(NumberElement.class, matrixElement);
    numberElement = (NumberElement) matrixElement;
    assertEquals(1, numberElement.getPositionX());
    assertEquals(2, numberElement.getPositionY());
    assertEquals(553, numberElement.getNumber());
  }

  @SneakyThrows
  @Test
  void givenFileWithSpecificSymbol_whenReadNextValue_thenReturnedSymbolElementWithRightValues() {
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(TEST_FILENAME));
    writer.write("*.");
    writer.close();

    MatrixElement matrixElement = fileReaderService.readNextValue();
    assertInstanceOf(SymbolElement.class, matrixElement);
    SymbolElement symbolElement = (SymbolElement) matrixElement;
    assertEquals(0, symbolElement.getPositionX());
    assertEquals(0, symbolElement.getPositionY());
  }

}