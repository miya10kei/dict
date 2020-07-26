package com.miya10kei.command;

import com.miya10kei.command.subcommand.ListSubCommand;
import com.miya10kei.command.subcommand.SearchSubCommand;
import com.miya10kei.command.subcommand.SelectSubCommand;
import com.miya10kei.repository.client.SqliteClient;
import com.miya10kei.repository.dictionary.DictionaryRepositoryImpl;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command
public class DictCommand {

  @Option(names = {"-n", "--name"}, description = "who are you?")
  private String name;

  public static void main(String... args) {
    var dictionaryRepository = new DictionaryRepositoryImpl(new SqliteClient());

    var status = new CommandLine(new DictCommand())
            .addSubcommand(new ListSubCommand(dictionaryRepository))
            .addSubcommand(new SearchSubCommand(dictionaryRepository))
            .addSubcommand(new SelectSubCommand())
            .execute(args);
    System.exit(status);
  }
}
