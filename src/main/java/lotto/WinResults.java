package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinResults {

    private final List<WinResult> winResults;

    private WinResults(List<WinResult> winResults) {
        this.winResults = winResults;
    }

    public static WinResults of(List<Lotto> lottos, List<Integer> winNumbers) {
        List<WinResult> winResults = lottos.stream()
                .map(lotto -> lotto.getWinResult(winNumbers))
                .filter(winResult -> winResult != WinResult.DEFEAT)
                .collect(Collectors.toList());
        return new WinResults(winResults);
    }

    public static WinResults from(WinResult... winResults) {
        return new WinResults(Arrays.asList(winResults));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinResults that = (WinResults) o;
        return Objects.equals(winResults, that.winResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winResults);
    }
}
