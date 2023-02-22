import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy {

    private List<Integer> dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5) {
        dice = Arrays.asList(d1, d2, d3, d4, d5);
    }

    public int chance() {
        return dice.stream().reduce(0, Integer::sum);
    }

    public int yatzy() {
        return new HashSet<>(dice).size() == 1 ? 50 : 0;
    }

    public int ones() {
        return (int) sumDicesByValue(1);
    }

    public int twos() {
        return (int) sumDicesByValue(2);
    }

    public int threes() {
        return (int) sumDicesByValue(3);
    }

    public int fours() {
        return (int) sumDicesByValue(4);
    }

    public int fives() {
        return (int) sumDicesByValue(5);
    }

    public int sixes() {
        return (int) sumDicesByValue(6);
    }

    private long sumDicesByValue(int x) {
        return dice.stream().filter(d -> d == x).count() * x;
    }

    public int score_pair() {
        List<Integer> list = getDiceValueTimesFrequencyByDiceFrequency(2);
        return list.size() > 0 ? list.get(0) : 0;
    }

    public int two_pair() {
        List<Integer> list = getDiceValueTimesFrequencyByDiceFrequency(2);
        return list.size() > 1 ? list.get(0) + list.get(1) : 0;
    }

    public int four_of_a_kind() {
        List<Integer> list = getDiceValueTimesFrequencyByDiceFrequency(4);
        return list.size() > 0 ? list.get(0) : 0;
    }

    public int three_of_a_kind() {
        List<Integer> list = getDiceValueTimesFrequencyByDiceFrequency(3);
        return list.size() > 0 ? list.get(0) : 0;
    }

    private List<Integer> getDiceValueTimesFrequencyByDiceFrequency(int x) {
        return dice.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
            .filter(e -> e.getValue() >= x)
            .map(e -> e.getKey() * x).collect(Collectors.toList());
    }

    public int smallStraight() {
        var set = new HashSet<>(dice);
        return set.size() == 5 && set.contains(1) ? 15 : 0;
    }

    public int largeStraight() {
        var set = new HashSet<>(dice);
        return set.size() == 5 && set.contains(6) ? 20 : 0;
    }

    public int fullHouse() {
        var list = dice.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(e -> e.getValue() == 3 || e.getValue() == 2)
            .map(e -> e.getValue() * e.getKey())
            .collect(Collectors.toList());
        return list.size() > 1 ? (int) (list.get(0) + list.get(1)) : 0;
    }
}



