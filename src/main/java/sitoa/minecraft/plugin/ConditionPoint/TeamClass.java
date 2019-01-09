package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamClass {
    ScoreboardManager SBM =Bukkit.getScoreboardManager();
    Scoreboard board = SBM.getNewScoreboard();
    Team teamRed;
    Team teamBlue;


    public TeamClass(){  // メインスコアボードを取得します。
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        // チームが既に登録されているかどうか確認し、
        // 登録されていないなら新規作成します。
        teamRed = board.getTeam("REDTEAM");
        if ( teamRed == null ) {
            teamRed = board.registerNewTeam("REDTEAM");
            teamRed.setPrefix(ChatColor.RED.toString());
            teamRed.setSuffix(ChatColor.RESET.toString());
            teamRed.setDisplayName("team red");
            teamRed.setAllowFriendlyFire(false);
        }
        teamBlue = board.getTeam("BLUETEAM");
        if ( teamBlue == null ) {
            teamBlue = board.registerNewTeam("BLUETEAM");
            teamBlue.setPrefix(ChatColor.BLUE.toString());
            teamBlue.setSuffix(ChatColor.RESET.toString());
            teamBlue.setDisplayName("team blue");
            teamBlue.setAllowFriendlyFire(false);
        }

    }

    public Team getRedteam(){
        return teamRed;
    }
    public Team getBlueteam(){
    return teamBlue;
    }

    public Scoreboard getboard(){
        return board;
    }
    public boolean setPlayertoTeam(Player player, String team) {
        if (team == "red") {
            teamRed.addPlayer(player);
            return true;
        } else if (team == "blue") {
            teamBlue.addPlayer(player);
            return true;
        }
        return false;
    }
}
