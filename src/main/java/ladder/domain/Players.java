package ladder.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private static final int PLAYERS_SIZE_LOWER_BOUND_INCLUSIVE = 2;
    private static final String PLAYERS_SIZE_ERROR_MESSAGE = "플레이어 수는 2 이상이어야 합니다.";
    private final List<Player> players;

    private Players(List<Player> players) {
        validateSize(players);
        this.players = players;
    }

    public static Players from(List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(collectingAndThen(toUnmodifiableList(), Players::new));
    }

    private void validateSize(List<Player> players) {
        if (players.size() < PLAYERS_SIZE_LOWER_BOUND_INCLUSIVE) {
            throw new IllegalArgumentException(PLAYERS_SIZE_ERROR_MESSAGE);
        }
    }

    public int size() {
        return players.size();
    }

    public void setPrizes(Prizes prizes){
        for(int i = 0; i< size(); i++){
            Player player = players.get(i);
            Prize prize = prizes.get(i);
            player.setPrize(prize);
        }
    }

    public Player findByName(String name){
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("이름이 %s인 플레이어가 없습니다.", name)));
    }

    public Map<String,String> getPrizes(){
        Map<String, String> prizes = new HashMap<>();
        for(Player player : players){
            prizes.put(player.getName(), player.getPrizeName());
        }
        return prizes;
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(toUnmodifiableList());
    }
}
