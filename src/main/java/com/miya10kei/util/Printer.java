package com.miya10kei.util;

import com.google.common.base.Splitter;
import com.miya10kei.domain.dictionary.Word;
import org.eclipse.collections.api.factory.Lists;

import static java.lang.String.format;

public class Printer {

  private static final String INDENT = " ".repeat(14);
  private static final String SEPARATOR = "-".repeat(64);
  private static final int MAX_WIDTH = 50;

  public static void printWord(Word word) {
    System.out.println(SEPARATOR);
    System.out.println(format("name        : %s", word.getName()));
    System.out.println(format("ruby        : %s", word.getRuby()));
    Lists.immutable
            .ofAll(Splitter.fixedLength(MAX_WIDTH).split(word.getDescription()))
            .forEachWithIndex((it, i) -> {
              if (i == 0) {
                System.out.println(String.format("description : %s", it));
              } else {
                System.out.println(String.format("%s%s", INDENT, it));
              }
            });
    System.out.println(SEPARATOR);
  }
}
