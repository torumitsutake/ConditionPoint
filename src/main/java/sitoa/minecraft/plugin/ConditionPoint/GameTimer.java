package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {
    int counter = -5;
    int LIMITTIME = 300;
    private final JavaPlugin plugin;



    public GameTimer(JavaPlugin plugin, int TimerLimit){
        if(TimerLimit < 60){
            LIMITTIME = 300;
            throw new IllegalArgumentException("時刻には６０秒以上を設定してください。");
        }else {
            LIMITTIME = TimerLimit;
        }
        this.plugin = plugin;
        counter = -5;

    }


    @Override
    public void run(){
        if(counter < 0){
            Bukkit.broadcastMessage(ChatColor.AQUA+"ゲーム開始まで..."+counter+"");

        }else
        if(counter > LIMITTIME){
            Bukkit.broadcastMessage(ChatColor.RED+"ゲーム終了＾_＾");
            this.cancel();
        }else {
           // Bukkit.broadcastMessage(ChatColor.GREEN + "ゲーム残り時間" + (LIMITTIME - counter));
        }
            counter++;
    }

}
