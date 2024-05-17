package dev.tiltrikt.container.counter.service.counter;

/** Service is responsible for counting containers from a given file.*/
public interface CounterService {

  /**
   * Count sum of containers in the specified file.
   */
  void countContainers();
}
