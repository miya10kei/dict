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
import picocli.CommandLine.Parameters;

import static java.util.Comparator.comparing;

@Command(name = "search")
@RequiredArgsConstructor
public class SearchCommand extends BaseSubCommand {

  private final DictionaryRepository repository;

  @Option(names = {"-a", "--all"}, description = "search words from all dictionaries")
  private boolean all;
  @Parameters(index = "0", description = "search keyword")
  private String keyword;

  @Override
  public void call0() {
    var words = all
        ? repository.fetchDictionaries().flatCollectWith(Dictionary::search, keyword)
        : repository.fetchDictionary(new DictionaryName(DictConfiguration.readCurrentDictionary())).search(keyword);
    words.toSortedList(comparing(Word::getRuby))
        .forEach(Printer::printWord);
  }
}
