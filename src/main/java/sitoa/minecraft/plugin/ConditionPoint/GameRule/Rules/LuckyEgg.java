package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEggThrowEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class LuckyEgg extends BaseRuleListener {
    @EventHandler
    public void onPlayerThrowEgg(PlayerEggThrowEvent e){
        if(ConditionPoint.gameRunning()){
            if(!this.isEnable()){
                return;
            }
                int percent = (int)(Math.random()*30);
                if(percent == 0){
                    e.getPlayer().sendMessage(ChatColor.GOLD+"You got a lucky egg!");
                    changePoint(e.getPlayer(),200);
                }
        }

    }

}
