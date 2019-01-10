package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class TeamClass {
    ScoreboardManager SBM =Bukkit.getScoreboardManager();
    Scoreboard board = SBM.getNewScoreboard();
    Team teamRed;
    Team teamBlue;
    Objective teampoint;


    public TeamClass(){  // メインスコアボードを取得します。
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        // チームが既に登録されているかどうか確認し、
        // 登録されていないなら新規作成します。
        teamRed = board.getTeam("REDTEAM");
        if ( teamRed == null ) {
            teamRed = board.registerNewTeam("REDTEAM");
            teamRed.setPrefix(ChatColor.RED.toString()+"[RED]");
            teamRed.setSuffix(ChatColor.RESET.toString());
            teamRed.setDisplayName("team red");
            teamRed.setAllowFriendlyFire(false);
            teamRed.setColor(ChatColor.RED);
        }
        teamBlue = board.getTeam("BLUETEAM");
        if ( teamBlue == null ) {
            teamBlue = board.registerNewTeam("BLUETEAM");
            teamBlue.setPrefix(ChatColor.BLUE.toString()+"[BLUE]");
            teamRed.setSuffix(ChatColor.RESET.toString());
            teamBlue.setDisplayName("team blue");
            teamBlue.setAllowFriendlyFire(false);
            teamBlue.setColor(ChatColor.BLUE);
        }
        teampoint = board.getObjective("teampoint");
        if(teampoint == null){
            teampoint = board.registerNewObjective("teampoint","dummy");
            teampoint.setDisplaySlot(DisplaySlot.SIDEBAR);
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
        if (team.equalsIgnoreCase("red")) {
            teamRed.addPlayer(player);
            return true;
        } else if (team.equalsIgnoreCase("blue")) {
            teamBlue.addPlayer(player);

            return true;
        }
        return false;
    }

    //teamReset
    public void teamReset(){
        for(OfflinePlayer joinplayer: teamRed.getPlayers()){
            teamRed.removePlayer(joinplayer);
        }
        for(OfflinePlayer joinplayer: teamBlue.getPlayers()){
            teamBlue.removePlayer(joinplayer);
        }
    }
}
