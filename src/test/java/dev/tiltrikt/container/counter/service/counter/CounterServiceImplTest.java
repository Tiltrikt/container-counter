package dev.tiltrikt.container.counter.service.counter;

import dev.tiltrikt.container.counter.service.extactor.NumberExtractor;
import dev.tiltrikt.container.counter.service.extactor.NumberExtractorImpl;
import dev.tiltrikt.container.counter.service.parser.InputParser;
import dev.tiltrikt.container.counter.service.parser.InputParserImpl;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessor;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessorImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
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
  InputParser inputParser = new InputParserImpl();
  @NotNull
  private final static ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  @NotNull
  CounterService counterService = new CounterServiceImpl(matrixProcessor, inputParser);


  @SneakyThrows
  @BeforeAll
  public static void setUp() {
    assertTrue(Files.exists(Path.of(TEST_FILENAME)));
    System.setIn(new FileInputStream(TEST_FILENAME));
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterAll
  public static void tearDown() {
    System.setOut(System.out);
    System.setIn(System.in);
  }

  @Test
  void givenExampleFile_whenCountContainers_thenReturnedRightValue() {
    counterService.countContainers();
    assertEquals("4361", outputStreamCaptor.toString().trim());
  }
}