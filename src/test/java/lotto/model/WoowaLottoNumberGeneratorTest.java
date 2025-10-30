package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class WoowaLottoNumberGeneratorTest {
    private final WoowaLottoNumberGenerator generator = new WoowaLottoNumberGenerator();

    @DisplayName("6개의 번호를 생성한다")
    @Test
    void generateLottoNumbers_sixNumbers() {
        // given
        int startInclusive = Constants.LOTTO_MIN_NUMBER;
        int endInclusive = Constants.LOTTO_MAX_NUMBER;
        int count = Constants.LOTTO_NUMBERS_SIZE;

        // when
        List<Integer> numbers = generator.generateLottoNumbers(startInclusive, endInclusive, count);

        // then
        assertThat(numbers).hasSize(count);
    }

    @DisplayName("중복되지 않은 번호를 생성한다")
    @Test
    void generateLottoNumbers_noDuplicate() {
        // given
        int startInclusive = Constants.LOTTO_MIN_NUMBER;
        int endInclusive = Constants.LOTTO_MAX_NUMBER;
        int count = Constants.LOTTO_NUMBERS_SIZE;

        // when
        List<Integer> numbers = generator.generateLottoNumbers(startInclusive, endInclusive, count);

        // then
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        assertThat(numbers).hasSize(uniqueNumbers.size());
    }

    @DisplayName("1~45 범위의 번호를 생성한다")
    @Test
    void generateLottoNumbers_validRange() {
        // given
        int startInclusive = Constants.LOTTO_MIN_NUMBER;
        int endInclusive = Constants.LOTTO_MAX_NUMBER;
        int count = Constants.LOTTO_NUMBERS_SIZE;

        // when
        List<Integer> numbers = generator.generateLottoNumbers(startInclusive, endInclusive, count);

        // then
        assertThat(numbers).allMatch(number -> number >= startInclusive && number <= endInclusive);
    }

    @DisplayName("여러 번 생성해도 항상 6개, 중복 없음, 1~45 범위를 만족한다")
    @RepeatedTest(10)
    void generateLottoNumbers_multipleGeneration() {
        // given
        int startInclusive = Constants.LOTTO_MIN_NUMBER;
        int endInclusive = Constants.LOTTO_MAX_NUMBER;
        int count = Constants.LOTTO_NUMBERS_SIZE;

        // when
        List<Integer> numbers = generator.generateLottoNumbers(startInclusive, endInclusive, count);

        // then
        assertThat(numbers).hasSize(count);
        Set<Integer> noDuplicatedNumbers = new HashSet<>(numbers);
        assertThat(numbers).hasSize(noDuplicatedNumbers.size());
        assertThat(numbers).allMatch(number -> number >= startInclusive && number <= endInclusive);
    }

}
