package ai.rock_paper_markov_chain;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class RockPaperScissorsLizardSpock {

    private static DecimalFormat DECIMAL_FORMATTER = new DecimalFormat(".##");
    public static final Random RANDOM = new Random();
    // stats for the game (you win / tie / computer win)
    private int[] stats = new int[]{0, 0, 0};
    // we use a Markov Chain for the AI of our computer
    private int[][] markovChain; // used just for the prev to current throws prediction
    private int nbThrows = 0;
    private Item last = null;

    private void init() {
        int length = Item.values().length;
        markovChain = new int[length][length];

        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                markovChain[i][j] = 0;
    }

    private void updateMarkovChain(Item prev, Item next) {
        markovChain[prev.ordinal()][next.ordinal()]++;
    }

    private Item nextMove(Item prev) {
        if (nbThrows < 1) {
            // first move => we can't use Markov Chain prediction
            // so we use a random on the Item list
            return Item.values()[RANDOM.nextInt(Item.values().length)];
        }

        // we try to predict next Item choosen by the user by reading data in our Markov Chain
        // for the prev entry in the array
        int nextIndex = 0;

        for (int i = 0; i < Item.values().length; i++) {
            int prevIndex = prev.ordinal();

            if (markovChain[prevIndex][i] > markovChain[prevIndex][nextIndex]) {
                nextIndex = i;
            }
        }

        // Item probably played by the user is in nextIndex
        Item predictedNext = Item.values()[nextIndex];

        // we choose amongst item for which this probably item loses
        List<Item> losesTo = predictedNext.losesTo;
        return losesTo.get(RANDOM.nextInt(losesTo.size()));
    }

    public void play() {
        init();
        SomeStrategiesPlayer badPlayer = new SomeStrategiesPlayer();
        System.out.print("Make your choice : ");

        for (int i = 0; i < 50; i++) {
            String input=badPlayer.choose();
            // read user choise
            Item choice;

            try {
                choice = Item.valueOf(input.toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid choice");
                continue;
            }

            Item aiChoice = nextMove(last);
            nbThrows++;

            // update Markov Chain
            if (last != null) {
                updateMarkovChain(last, choice);
            }

            last = choice;

            System.out.println("Computer choice : " + aiChoice);

            if (aiChoice.equals(choice)) {
                System.out.println(" ==> Tie !\n");
                stats[1]++;
            } else if (aiChoice.losesTo(choice)) {
                System.out.println(" ==> You win !\n");
                stats[0]++;
            } else {
                System.out.println(" ==> You lose !\n");
                stats[2]++;
            }

            System.out.print("Make your choice : ");
        }

        // display Stats
        System.out.println("\n");
        System.out.println("Win Stats");
        int total = stats[0] + stats[1] + stats[2];
        System.out.println("You : " + stats[0] + " - " +
                DECIMAL_FORMATTER.format(stats[0] / (float) total * 100f) + "%");
        System.out.println("Tie : " + stats[1] + " - " +
                DECIMAL_FORMATTER.format(stats[1] / (float) total * 100f) + "%");
        System.out.println("Computer : " + stats[2] + " - " +
                DECIMAL_FORMATTER.format(stats[2] / (float) total * 100f) + "%");
    }

    public static void main(String[] args) {
        RockPaperScissorsLizardSpock rpsls = new RockPaperScissorsLizardSpock();
        rpsls.play();
    }

}