package lotto.model;

import java.util.List;

public interface LottoNumberGenerator {
    public List<Integer> generateLottoNumbers(int startInclusive, int endExclusive, int count);
}
