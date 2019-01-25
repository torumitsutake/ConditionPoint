package sitoa.minecraft.plugin.ConditionPoint.GameRule;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import sitoa.minecraft.plugin.ConditionPoint.ScoreManager;

public class BaseRule {


    public boolean useListener = false;



    //リスナーを使用するルールかどうか
    public boolean isListenerRule(){
        return useListener;
    }

    //ルールEnable 設定
    public boolean enable = true;

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public boolean isEnable(){
        return enable;
    }

    public String getPlayerTeam(Player p){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
        if(board.getTeam("REDTEAM").getPlayers().contains(p)){
            return "red";
        }else
            if(board.getTeam("BLUETEAM").getPlayers().contains(p)){
                return "blue";
            }else{
                return "none";
            }

    }


    public void changePoint(Player p, int point){
        String team = getPlayerTeam(p);
        if(team.equalsIgnoreCase("red") || team.equalsIgnoreCase("blue")){
            ScoreManager SM = ScoreManager.getInstance();
            SM.parmPointChange(team,point);
        }

    }

    public Team getPlayerEnemyTeamClass(Player p){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
        if(board.getTeam("BLUETEAM").getPlayers().contains(p)){
            return board.getTeam("REDTEAM");
        }else
        if(board.getTeam("REDTEAM").getPlayers().contains(p)){
            return board.getTeam("BLUETEAM");
        }else{
            return null;
        }

    }

}
