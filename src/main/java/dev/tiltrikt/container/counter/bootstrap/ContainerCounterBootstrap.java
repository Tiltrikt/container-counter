package dev.tiltrikt.container.counter.bootstrap;

import dev.tiltrikt.container.counter.service.CounterServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContainerCounterBootstrap {

  @SneakyThrows
  public void bootstrap(String[] args) {
    validateArgs(args);

    CounterServiceImpl counterService = new CounterServiceImpl();
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
