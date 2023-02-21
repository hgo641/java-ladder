package ladder.controller;

import java.util.List;
import ladder.domain.Height;
import ladder.domain.Ladder;
import ladder.domain.Players;
import ladder.domain.RandomLadderGenerator;
import ladder.domain.Width;
import ladder.view.InputView;
import ladder.view.ResultView;

public class LadderGame {
    private final InputView inputView;
    private final ResultView resultView;
    private final RandomLadderGenerator randomLadderGenerator;

    public LadderGame(InputView inputView, ResultView resultView, RandomLadderGenerator randomLadderGenerator) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.randomLadderGenerator = randomLadderGenerator;
    }

    public void run() {
        try {
            Players players = enroll();
            Ladder ladder = makeLadder(players.getPlayersSize());
            printLadderResult(players, ladder);
        } catch (IllegalArgumentException e) {
            resultView.printErrorMessage(e.getMessage());
        }

    }

    private Players enroll() {
        List<String> names = inputView.inputParticipants();
        Players players = Players.from(names);
        return players;
    }

    private Ladder makeLadder(int playersSize) {
        Height height = new Height(inputView.inputHeight());
        Width width = new Width(getWidthSize(playersSize));
        return randomLadderGenerator.generateRows(width, height);
    }

    private int getWidthSize(int playersSize) {
        return playersSize - 1;
    }

    private void printLadderResult(Players players, Ladder ladder) {
        resultView.printNames(players.getNames());
        resultView.printLadder(ladder.getLadder());
    }
}
