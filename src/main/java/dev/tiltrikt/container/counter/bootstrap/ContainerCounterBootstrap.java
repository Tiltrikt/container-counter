package dev.tiltrikt.container.counter.bootstrap;

import dev.tiltrikt.container.counter.service.counter.CounterService;
import dev.tiltrikt.container.counter.service.counter.CounterServiceImpl;
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

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContainerCounterBootstrap {

  @NotNull
  NumberExtractor numberExtractor = new NumberExtractorImpl();

  @NotNull
  MatrixProcessor matrixProcessor = new MatrixProcessorImpl(numberExtractor);

  @NotNull
  InputParser inputParser = new InputParserImpl();

  @NotNull
  CounterService counterService = new CounterServiceImpl(matrixProcessor, inputParser);

  @SneakyThrows
  public void bootstrap() {

    counterService.countContainers();
  }

}
