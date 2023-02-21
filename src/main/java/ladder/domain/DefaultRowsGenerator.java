package ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultRowsGenerator {
    private static final Random random = new Random();
    private final Width width;
    private final Height height;

    public DefaultRowsGenerator(Width width, Height height) {
        this.width = width;
        this.height = height;
    }

    public List<Row> generateLadder() {
        List<Row> rows = generateEmptyRows();
        addOneFootholdAtEachRow(rows);
        return rows;
    }

    private List<Row> generateEmptyRows() {
        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < height.getHeight(); i++) {
            List<Foothold> row = new ArrayList<>();
            addEmptyRow(row);
            rows.add(Row.of(row, width.getWidth()));
        }
        return rows;
    }

    //TODO 메소드명 변경
    private void addEmptyRow(List<Foothold> row) {
        for (int i = 0; i < width.getWidth(); i++) {
            row.add(Foothold.N);
        }
    }

    //TODO 메소드명 변경 //list.get(new Random().nextInt(list.size()));
    // oxoxxo
    // xoxxxo
    private void addOneFootholdAtEachRow(List<Row> rows) {
        for (int widthIndex = 0; widthIndex < width.getWidth(); widthIndex++) {
            Row footholdPossibleRow = getFootholdPossibleRow(rows, widthIndex);
            footholdPossibleRow.addFoothold(widthIndex);
        }
    }

    private Row getFootholdPossibleRow(List<Row> rows, int widthIndex) {
        Row choice = rows.get(random.nextInt(rows.size()));
        if (!choice.isFootholdPossible(widthIndex)) {
            return getFootholdPossibleRow(rows, widthIndex);
        }
        return choice;
    }
}
