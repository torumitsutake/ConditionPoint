package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BossBarManager  implements Listener {
    public static BossBarManager instance;
    public static JavaPlugin plugin;
    public BossBar bossbar;

    public static  BossBarManager getInstance(){
        if(instance == null){
            instance = new BossBarManager();
        }
        return instance;
    }
    public void setPlugn(ConditionPoint pl){
        plugin = pl;
    }
    public void prepareBossbar(){
        bossbar = plugin.getServer().createBossBar("Time", BarColor.GREEN, BarStyle.SOLID);
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }


    //プレイヤー登録イベント
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        bossbar.addPlayer(e.getPlayer());

    }

    //bossbar get

    public  BossBar getBossBar(){
        return bossbar;
    }


}
