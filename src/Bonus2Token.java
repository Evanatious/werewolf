
public enum Bonus2Token implements TokenType {
    VAMPIRE("Mist of the Vampire") {

    }, TRAITOR("Dagger of the Traitor") {

    }; //These two tokens seem to be repeats/copies of mark of the vampire and mark of the traitor from the Vampires expansion...

    /** The constructor for a DaybreakToken object. */
    Bonus2Token(String name) {
        _name = name;
    }

    /** The name of the token. */
    private String _name;

    @Override
    public String getName() {
        return null;
    }
}
