package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ListenerClass implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){

    }

    @EventHandler
    public void playerkillOther(EntityDeathEvent e){
        if(ConditionPoint.gameRunning()) {
            Player killer = e.getEntity().getKiller();
            ScoreManager SM = ScoreManager.getInstance();
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getMainScoreboard();
            if (board.getTeam("REDTEAM").getPlayers().contains(killer)) {
                SM.parmPointChange("red", -10);
            }

            if (board.getTeam("BLUETEAM").getPlayers().contains(killer)) {
                SM.parmPointChange("blue", -10);

            }
        }
    }



}
