package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.lottoNumbers = mapToLottoNumbers(numbers);
    }

    public WinResult getWinResult(List<Integer> winNumbers) {
        int matchedCount = (int) winNumbers.stream()
                .map(winNumber -> lottoNumbers.contains(new LottoNumber(winNumber)))
                .filter(isContained -> isContained)
                .count();
        return WinResult.fromCount(matchedCount);
    }

    private void validate(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_DUPLICATE_ERROR.getMessage());
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return new HashSet<>(numbers).size() != LOTTO_NUMBERS_SIZE;
    }

    private List<LottoNumber> mapToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
