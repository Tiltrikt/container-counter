package dev.tiltrikt.container.counter.service.counter;

import dev.tiltrikt.container.counter.service.extactor.NumberExtractor;
import dev.tiltrikt.container.counter.service.extactor.NumberExtractorImpl;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessor;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessorImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CounterServiceImplTest {

  private final static String TEST_FILENAME = "src/test/resources/simple-map.txt";
  @NotNull
  NumberExtractor numberExtractor = new NumberExtractorImpl();
  @NotNull
  MatrixProcessor matrixProcessor = new MatrixProcessorImpl(numberExtractor);
  @NotNull
  CounterService counterService = new CounterServiceImpl(matrixProcessor);
  @NotNull
  ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(System.out);
  }

  @Test
  void givenExampleFile_whenCountContainers_thenReturnedRightValue() {
    assertTrue(Files.exists(Path.of(TEST_FILENAME)));
    counterService.countContainers(TEST_FILENAME);
    assertEquals("4361", outputStreamCaptor.toString().trim());
  }
}