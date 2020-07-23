package com.miya10kei.command.subcommand;

import com.miya10kei.configuration.DictConfiguration;
import com.miya10kei.domain.dictionary.Dictionary;
import com.miya10kei.domain.dictionary.DictionaryName;
import com.miya10kei.domain.dictionary.DictionaryRepository;
import com.miya10kei.domain.dictionary.Word;
import com.miya10kei.util.Printer;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static java.util.Comparator.comparing;

@Command(name = "list", description = "display all words")
@RequiredArgsConstructor
public class ListSubCommand extends BaseSubCommand {

  private final DictionaryRepository repository;

  @Option(names = {"-a", "--all"}, description = "list all words from all dictionaries")
  private boolean all;

  @Override
  public void call0() {
    var words = all
            ? repository.fetchDictionaries().flatCollect(Dictionary::getWords)
            : repository.fetchDictionary(new DictionaryName(DictConfiguration.readCurrentDictionary())).getWords();
    words.toSortedList(comparing(Word::getRuby))
            .forEach(Printer::printWord);
  }
}
