package ladder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DefaultLadderGeneratorTest {
    @ParameterizedTest(name = "너비: {0}, 높이: {1}이면 높이가 {2}인 사다리 생성된다.")
    @CsvSource({"2,2,2", "6,3,3", "3,5,5"})
    public void 생성_success(int width, int height, int expected) {
        DefaultRowsGenerator defaultRowsGenerator = new DefaultRowsGenerator(new Width(width), new Height(height));
        List<Row> rows = defaultRowsGenerator.generateLadder();
        assertThat(rows).hasSize(expected);
    }
}
