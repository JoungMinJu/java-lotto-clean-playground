package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.Errors;

class LottoTest {

    @Nested
    @DisplayName("로또 생성 테스트")
    class createLotto {

        private static Stream<Arguments> methodSourceOfCreateLotto() {
            return Stream.of(
                Arguments.arguments(List.of(1, 4, 1, 30, 31, 32, 33)),
                Arguments.arguments(List.of(10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15))
            );
        }

        @ParameterizedTest(name = "{0}은 6 이상의 사이즈를 가지므로 예외가 발생한다.")
        @MethodSource("methodSourceOfCreateLotto")
        @DisplayName("생성되는 로또의 사이즈는 6이다.")
        void createLottoSizeTest(List<Integer> inputNumbers) {
            // given
            // when
            // then
            assertThatThrownBy(() -> new Lotto(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.WRONG_LOTTO_SIZE);
        }

        private static Stream<Arguments> methodSourceOfToString() {
            return Stream.of(
                Arguments.arguments(Arrays.asList(10, 9, 8, 1, 2, 3), "[1, 2, 3, 8, 9, 10]"),
                Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), "[10, 20, 25, 30, 40, 45]")
            );
        }

        @ParameterizedTest(name = "{0}이 로또 numbers이면 해당 로또를 toString() 한 값은 {1}이다.")
        @MethodSource("methodSourceOfToString")
        @DisplayName("로또를 toString() 하면 정렬된 숫자를 string으로 반환한다.")
        void toStringTest(List<Integer> inputNumbers, String expectedStatus) {
            // given
            Lotto lotto = new Lotto(inputNumbers);
            // when
            String lottoStatus = lotto.toString();
            // then
            assertThat(lottoStatus)
                .isEqualTo(expectedStatus);
        }
    }

    private static Stream<Arguments> methodSourceOfGetMatchingNumberCount() {
        return Stream.of(
            Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), Arrays.asList(45, 40, 1, 2, 3, 4), 2),
            Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), Arrays.asList(45, 40, 10, 30, 3, 4), 4)
        );
    }

    @ParameterizedTest(name = "{0}라는 로또와 {1}을 비교했을 때, 같은 숫자의 개수는 {2}개이다.")
    @MethodSource("methodSourceOfGetMatchingNumberCount")
    @DisplayName("주어진 리스트와 몇 개의 숫자가 일치하는지 계산할 수 있다.")
    void getMatchingNumberTest(List<Integer> lottoNumbers, List<Integer> comparingNumbers, int expectedCount) {
        // given
        Lotto lotto = new Lotto(lottoNumbers);
        // when
        final int matchingNumberCount = lotto.getMatchingNumberCount(comparingNumbers);
        // then
        assertThat(matchingNumberCount)
            .isEqualTo(expectedCount);
    }

}
