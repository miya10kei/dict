package com.miya10kei.command.subcommand;


import com.miya10kei.configuration.DictConfiguration;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "select", description = "select dictionary")
@RequiredArgsConstructor
public class SelectSubCommand extends BaseSubCommand {

  @Parameters(index = "0")
  private String dictionaryName;

  @Override
  protected void call0() {
    DictConfiguration.writeCurrentDictionary(dictionaryName);
  }
}
