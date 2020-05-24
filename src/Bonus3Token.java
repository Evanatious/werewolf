public enum Bonus3Token implements Artifact {
    ALIEN_ARTIFACT("Alien Artifact") {

    };

    /** The constructor for a DaybreakToken object. */
    Bonus3Token(String name) {
        _name = name;
    }

    /** The name of the token. */
    private String _name;

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
