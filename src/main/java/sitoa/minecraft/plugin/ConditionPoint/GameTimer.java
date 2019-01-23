package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class GameTimer extends BukkitRunnable {
    int counter = -5;
    int LIMITTIME = 300;
    private final JavaPlugin plugin;
    ScoreManager SM;


    public GameTimer(JavaPlugin plugin, int TimerLimit){
        if(TimerLimit*60 < 60){
            LIMITTIME = 300;
            throw new IllegalArgumentException("時刻には一分以上を設定してください。");
        }else {
            LIMITTIME = TimerLimit*60;
        }
        this.plugin = plugin;
        counter = -5;
        SM = ScoreManager.getInstance();

    }


    @Override
    public void run(){
        ScoreboardManager SBM = Bukkit.getScoreboardManager();
        Scoreboard board = SBM.getMainScoreboard();
        if(counter < 0){
            Bukkit.broadcastMessage(ChatColor.AQUA+"ゲーム開始まで..."+((-1)*counter)+"");
        }else
            if(counter == 0){
                //初期位置にプレイヤーたちをテレポート
                Team redteam = board.getTeam("REDTEAM");
                Team blueteam  = board.getTeam("BLUETEAM");


                //赤チームテレポート
                Location redpoint = Bukkit.getServer().getWorld("world").getSpawnLocation();
                redpoint.setX(plugin.getConfig().getDouble("gameconfig.startlocation.red.x"));
                redpoint.setY(plugin.getConfig().getDouble("gameconfig.startlocation.red.y"));
                redpoint.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.red.z"));
                for(OfflinePlayer joiner : redteam.getPlayers()){
                    if(joiner.isOnline()){
                        Player player = (Player)joiner;
                        player.teleport(redpoint);
                        player.setBedSpawnLocation(redpoint,true);
                    }
                }

                //青チームテレポート
                Location bluepoint = Bukkit.getServer().getWorld("world").getSpawnLocation();
                bluepoint.setX(plugin.getConfig().getDouble("gameconfig.startlocation.blue.x"));
                bluepoint.setY(plugin.getConfig().getDouble("gameconfig.startlocation.blue.y"));
                bluepoint.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.blue.z"));
                for(OfflinePlayer joiner : blueteam.getPlayers()){
                    if(joiner.isOnline()){
                        Player player = (Player)joiner;
                        player.teleport(bluepoint);
                        player.setBedSpawnLocation(bluepoint,true);
                    }
                }


            }else
                //残り時間通知
                if(counter == LIMITTIME-900){
                    Bukkit.broadcastMessage(ChatColor.GREEN+"ゲーム終了１5分前");
                }else
                if(counter == LIMITTIME-600){
                    Bukkit.broadcastMessage(ChatColor.GREEN+"ゲーム終了10分前");
                }
                if(counter == LIMITTIME-300){
                     Bukkit.broadcastMessage(ChatColor.GREEN+"ゲーム終了５分前");
                }else
                if(counter == LIMITTIME-60) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "ゲーム終了1分前");
                }else
                if(counter <= LIMITTIME   && counter > LIMITTIME-10){
                    Bukkit.broadcastMessage(ChatColor.GREEN +"ゲーム終了まで"+(LIMITTIME-counter)+"秒");
                }else
        if(counter > LIMITTIME){

            Bukkit.broadcastMessage(ChatColor.RED+"ゲーム終了!");
            this.cancel();
            ConditionPoint.gameStop();
            Location loc = Bukkit.getServer().getWorld("world").getSpawnLocation();
            loc.setX(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.x"));
            loc.setY(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.y"));
            loc.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.z"));
            for(Player p: Bukkit.getOnlinePlayers()){
                p.setBedSpawnLocation(loc,true);
                p.teleport(loc);
            }
            Bukkit.broadcastMessage(ChatColor.GOLD+"[MVP PLAYER]");
            ArrayList<Player> MVPs = SM.getMVPPLayers();
            for(Player p : MVPs){
                Bukkit.broadcastMessage(ChatColor.GOLD+"[MVP]"+p.getDisplayName()+"   "+SM.getPointfromPlayer(p)+"pt");
            }
            BossBar  bossbar = BossBarManager.getInstance(plugin).getBossBar();
            bossbar.removeAll();


        }
        SM.LoadScorefromPlayer();
        //時間表示用スコア設定
        int minutes = (LIMITTIME-counter)/60;
        BossBar  bossbar = BossBarManager.getInstance(plugin).getBossBar();
        bossbar.setTitle("Time;"+minutes);
        double percent = counter/LIMITTIME;
        bossbar.setProgress(percent);

        counter++;
    }

}
