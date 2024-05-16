package dev.tiltrikt.container.counter.service;

import dev.tiltrikt.container.counter.exception.EOLException;
import dev.tiltrikt.container.counter.model.MatrixElement;
import dev.tiltrikt.container.counter.model.NumberElement;
import dev.tiltrikt.container.counter.model.SymbolElement;
import org.jetbrains.annotations.NotNull;

import java.io.EOFException;
import java.util.*;

public class CounterServiceImpl implements CounterService {

  public int countContainers(@NotNull String fileName) {
    VectorService vectorService = new VectorServiceImpl();
    FileReaderService fileReaderService = new FileReaderServiceImpl(fileName);
    List<TreeMap<Double, NumberElement>> numberList = new ArrayList<>();
    List<Map<Double, SymbolElement>> symbolList = new ArrayList<>();
    int count = 0;
    numberList.add(new TreeMap<>());
    symbolList.add(new HashMap<>());

    while (true) {

      MatrixElement matrixElement;
      try {
        matrixElement = fileReaderService.readNextValue();
      } catch (EOFException e) {
        System.out.println(count);
        return count;
      } catch (EOLException e) {

        if (numberList.size() < 3) {
          numberList.add(new TreeMap<>());
          symbolList.add(new HashMap<>());
          continue;
        }

        Map<Double, SymbolElement> symbolMap = symbolList.get(1);
        for (Map.Entry<Double, SymbolElement> symbolDistance : symbolMap.entrySet()) {
          for (TreeMap<Double, NumberElement> numberMap : numberList) {
            Collection<NumberElement> l = numberMap.tailMap(symbolDistance.getKey() - 2, false).headMap(symbolDistance.getKey() + 2).values();

            Iterator<NumberElement> iterator = l.iterator();
            while (iterator.hasNext()) {
              NumberElement numberElement = iterator.next();
              MatrixElement vector = vectorService.subtractVectors(numberElement, symbolDistance.getValue());
              if (vectorService.getVectorLength(vector) < 2) {
                count += numberElement.getNumber();
                System.out.println(numberElement.getNumber());
                iterator.remove();
              }
            }

          }
        }

        numberList.removeFirst();
        symbolList.removeFirst();

        numberList.add(new TreeMap<>());
        symbolList.add(new HashMap<>());
        continue;
      }

      double distance = Math.sqrt(Math.pow(matrixElement.getPositionX(), 2) + Math.pow(matrixElement.getPositionY(), 2));
      if (matrixElement instanceof NumberElement) {
        numberList.getLast().put(distance, (NumberElement) matrixElement);
      } else {
        symbolList.getLast().put(distance, (SymbolElement) matrixElement);
      }
    }


  }
}