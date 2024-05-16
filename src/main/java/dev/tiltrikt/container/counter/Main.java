package dev.tiltrikt.container.counter;


import dev.tiltrikt.container.counter.bootstrap.ContainerCounterBootstrap;

public class Main {

  private static final ContainerCounterBootstrap containerCounterBootstrap = new ContainerCounterBootstrap();

  public static void main(String[] args) {
    containerCounterBootstrap.bootstrap(args);
  }
}
