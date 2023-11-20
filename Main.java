import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public enum Outcomes {
        SwitchedRight,
        SwitchedWrong,
        StayedRight,
        StayedWrong
    }

    static int NUMBER_OF_CYCLES_STAYED = 10000;
    static int NUMBER_OF_CYCLES_SWITCHED = 10000;


    public static HashMap<Integer, Outcomes> montyHallSim() {
        Random rng = new Random();
        HashMap<Integer, Outcomes> results = new HashMap<>();
        int correctDoor, choice;
        for (int i = 0; i < NUMBER_OF_CYCLES_STAYED; i++) {
            correctDoor = rng.nextInt(3);
            choice = rng.nextInt(3);
            results.put(i, choice == correctDoor ? Outcomes.StayedRight : Outcomes.StayedWrong);
        }
        for (int i = NUMBER_OF_CYCLES_STAYED; i < NUMBER_OF_CYCLES_STAYED + NUMBER_OF_CYCLES_SWITCHED; i++) {
            correctDoor = rng.nextInt(3);
            choice = rng.nextInt(3);
            results.put(i, choice == correctDoor ? Outcomes.SwitchedWrong : Outcomes.SwitchedRight);
        }
        return results;
    }

    public static void printResults(HashMap<Integer, Outcomes> results) {
        int stayedRightCount = Maps.filterEntries(results, input -> input.getValue() == Outcomes.StayedRight).size();
        int switchedRightCount = Maps.filterEntries(results, input -> input.getValue() == Outcomes.SwitchedRight).size();
        System.out.printf("Number of wins, where the contestant stuck with their initial choice: %d " +
                "(%f%% of all the simulations, where the contestant stuck with their initial choice)%n",
                stayedRightCount, (float)(100 * stayedRightCount) / NUMBER_OF_CYCLES_STAYED);
        System.out.printf("Number of wins, where the contestant changed their initial choice: %d " +
                        "(%f%% of all the simulations, where the contestant changed their initial choice)%n",
                switchedRightCount, (float)(100 * switchedRightCount) / NUMBER_OF_CYCLES_SWITCHED);
    }

    public static void main(String[] args) {
        printResults(montyHallSim());
    }
}
