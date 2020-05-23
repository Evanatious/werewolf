/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Alien: FIXME
 *
 * @author Evan Gao
 */
public class AlienRole implements Role {

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    @Override
    public Team getTeam() {
        return _team;
    }

    @Override
    public String getName() {
        return _name;
    }
}
