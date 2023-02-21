package ladder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FootholdTest {
    @ParameterizedTest
    @CsvSource({"true,Y", "false,N"})
    public void Foothold_생성(boolean state, Foothold expected) {
        assertThat(Foothold.from(state)).isEqualTo(expected);
    }
}
