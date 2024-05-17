package dev.tiltrikt.container.counter.service.counter;

import org.jetbrains.annotations.NotNull;

 /** Service is responsible for counting containers from a given file.*/
public interface CounterService {

  /**
   * Count sum of containers in the specified file.
   *
   * @param fileName The name of the file containing map with containers
   */
  void countContainers(@NotNull String fileName);
}
