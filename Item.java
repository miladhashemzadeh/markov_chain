package ai.rock_paper_markov_chain;

import java.util.Arrays;
import java.util.List;

public enum Item {
  ROCK, PAPER, SCISSORS, LIZARD, SPOCK;

  public List<Item> losesTo;

  public boolean losesTo(Item other) {
    return losesTo.contains(other);
  }

  static {
    SCISSORS.losesTo = Arrays.asList(ROCK, SPOCK);
    ROCK.losesTo = Arrays.asList(PAPER, SPOCK);
    PAPER.losesTo = Arrays.asList(SCISSORS, LIZARD);
    SPOCK.losesTo = Arrays.asList(PAPER, LIZARD);
    LIZARD.losesTo = Arrays.asList(SCISSORS, ROCK);
  }
}