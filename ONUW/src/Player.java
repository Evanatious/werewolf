public class Player {
    private String _name;
    private Role _initRole;
    private Card _card;
    private boolean _alive;
    private Game _game;

    public Player(String name, Game game) {
        _name = name;
        _game = game;
        _alive = true;
    }

    public void kill() {
        _alive = false;
    }

    public void initCard(Card card) {
        _card = card;
        _initRole = card.getRole();
    }

    public boolean isWinner() {
        assert _game.gameOver();
        return _card.getRole().getTeam() == _game.getWinningTeam();
    }

    public String getName() {
        return _name;
    }

    public Role getFinalRole() {
        //TODO
    }
}
