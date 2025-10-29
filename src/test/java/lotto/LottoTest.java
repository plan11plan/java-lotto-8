package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("6개의 번호로 로또를 생성한다")
    @Test
    void createLotto_sixNumbers() {
        // given
        List<Integer> given = List.of(1, 2, 3, 4, 5, 6);

        // expect
        assertThatCode(() -> new Lotto(given))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 로또 번호가 6개 미만이면 생성할 수 없다")
    @Test
    void createLotto_notSixNumbers_throwsException() {
        // given
        List<Integer> given = List.of(1, 2, 3, 4, 5);

        // expect
        assertThatThrownBy(() -> new Lotto(given))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("중복이 없는 번호로 로또를 생성한다")
    @Test
    void createLotto_noDuplicate() {
        // given
        List<Integer> given = List.of(1, 2, 3, 4, 5, 6);

        // expect
        assertThatCode(() -> new Lotto(given))
                .doesNotThrowAnyException();
    }


    @DisplayName("1~45 범위의 번호로 로또를 생성한다")
    @Test
    void createLotto_validRange() {
        // given
        List<Integer> given = List.of(1, 2, 3, 4, 5, 45);

        // expect
        assertThatCode(() -> new Lotto(given))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 1 미만의 번호는 생성할 수 없다")
    @Test
    void createLotto_lessThanMin_throwsException() {
        // given
        List<Integer> given = List.of(0, 2, 3, 4, 5, 6);

        // expect
        assertThatThrownBy(() -> new Lotto(given))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외: 45 초과의 번호는 생성할 수 없다")
    @Test
    void createLotto_moreThanMax_throwsException() {
        // given
        List<Integer> given = List.of(1, 2, 3, 4, 5, 46);

        // expect
        assertThatThrownBy(() -> new Lotto(given))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("번호를 오름차순으로 정렬하여 저장한다")
    @Test
    void createLotto_sorted() {
        // given
        List<Integer> given = List.of(3, 2, 1, 6, 5, 4);

        // when
        Lotto lotto = new Lotto(given);

        // then
        assertThat(lotto.numbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

}
