package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class WoowaLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generateLottoNumbers(int startInclusive, int endExclusive, int count) {
        return Randoms.pickUniqueNumbersInRange(startInclusive, endExclusive, count);
    }
}
