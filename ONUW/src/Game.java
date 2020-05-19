import java.util.*;

public class Game {
    private Set<Player> _players;
    private List<Role> _roles;
    private Card[] _middle;
    private boolean _gameOver;
    private Team _winningTeam;
    private static Game _app;

    private Game() {
        _players = new HashSet<Player>();
    }

    public static Game getApp() {
        if (_app == null) {
            _app = new Game();
        }
        return _app;
    }

    public void setPlayers(Set<Player> players) {
        assert players.size() > 2;
        _players = new HashSet<Player>(players);
    }


    public void setRoles(List<Role> roles) {
        assert roles.size() == _players.size() + 3;
        _roles = new ArrayList<Role>(roles);
    }

    public Set<Player> getPlayers() {
        return _players;
    }

    public List<Role> getRoles() {
        return _roles;
    }

    public void startGame() {
        assert _players.size() > 2 && _roles.size() == _players.size() + 3;
        _middle = new Card[3];
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Role r: _roles) {
            cards.add(new Card(r));
        }

        Collections.shuffle(cards);
        for (int i = 0; i < 3; i++) {
            _middle[i] = cards.get(i);
        }

        int index = 3;
        for (Player p: _players) {
            p.initCard(cards.get(index));
            index++;
        }
    }

    public boolean gameOver() {
        return _gameOver;
    }

    public Team getWinningTeam() {
        assert gameOver();
        return _winningTeam;
    }
}
