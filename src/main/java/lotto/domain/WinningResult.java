package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum WinningResult {

    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000),
    NOT_MATCHED(-1, 0);

    private static final int ONLY_ONE_MATCHED = 1;
    private static final int FIRST_ELEMENT = 0;

    private final int count;
    private final int prize;
    private boolean bonusNumberMatched;

    WinningResult(int count, int prize, boolean bonusNumberMatched) {
        this.count = count;
        this.prize = prize;
        this.bonusNumberMatched = bonusNumberMatched;
    }

    WinningResult(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static WinningResult of(int count, boolean bonusNumberMatched) {
        List<WinningResult> winningResults = Arrays.stream(values())
                .filter(winningResult -> winningResult.count == count)
                .collect(Collectors.toList());

        if (winningResults.size() == ONLY_ONE_MATCHED) {
            return winningResults.get(FIRST_ELEMENT);
        }

        return winningResults.stream()
                .filter(winningResult -> winningResult.bonusNumberMatched == bonusNumberMatched)
                .findFirst()
                .orElse(NOT_MATCHED);
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isBonusNumberMatched() {
        return bonusNumberMatched;
    }
}
