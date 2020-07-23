package com.miya10kei.command.subcommand;

import com.miya10kei.domain.dictionary.NotFoundDictionary;
import com.miya10kei.domain.dictionary.NotFoundWord;
import java.util.concurrent.Callable;

import static java.lang.String.format;

public abstract class BaseSubCommand implements Callable<Integer> {

  protected abstract void call0();

  @Override
  public Integer call() {
    try {
      call0();
    } catch (NotFoundDictionary e) {
      System.out.println(format("Not found dictionary(%s)", e.getDictionaryName()));
      return 1;
    } catch (NotFoundWord e) {
      System.out.println(format("Not found word by keyword(%s)", e.getKeyword()));
      return 2;
    } catch (Exception e) {
      e.printStackTrace();
      return 999;
    }
    return 0;
  }
}
