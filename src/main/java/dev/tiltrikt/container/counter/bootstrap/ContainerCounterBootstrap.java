package dev.tiltrikt.container.counter.bootstrap;

import dev.tiltrikt.container.counter.service.counter.CounterService;
import dev.tiltrikt.container.counter.service.counter.CounterServiceImpl;
import dev.tiltrikt.container.counter.service.extactor.NumberExtractor;
import dev.tiltrikt.container.counter.service.extactor.NumberExtractorImpl;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessor;
import dev.tiltrikt.container.counter.service.processor.MatrixProcessorImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Paths;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContainerCounterBootstrap {

  @NotNull
  NumberExtractor numberExtractor = new NumberExtractorImpl();

  @NotNull
  MatrixProcessor matrixProcessor = new MatrixProcessorImpl(numberExtractor);

  @NotNull
  CounterService counterService = new CounterServiceImpl(matrixProcessor);

  @SneakyThrows
  public void bootstrap(String[] args) {
    try {
      validateArgs(args);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return;
    }

    counterService.countContainers(args[0]);
  }

  private void validateArgs(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException(String.format("Invalid number of arguments: %d (expected 1)", args.length));
    }
    if (args[0].isBlank()) {
      throw new IllegalArgumentException("Blank argument not allowed");
    }
    if (!args[0].endsWith(".txt")) {
      throw new IllegalArgumentException(String.format("Invalid file extension: %s (expected \".txt\")", args[0]));
    }
    if (Files.notExists(Paths.get(args[0]))) {
      throw new IllegalArgumentException(String.format("File not found: %s", args[0]));
    }
  }
}
