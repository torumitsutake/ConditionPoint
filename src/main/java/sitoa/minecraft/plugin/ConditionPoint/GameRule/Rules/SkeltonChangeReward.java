package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class SkeltonChangeReward extends BaseRuleListener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        if(ConditionPoint.gameRunning()){
            if(!this.isEnable()){
                return;
            }
            Player killer = e.getEntity().getKiller();
            if(e.getEntity().getType() == EntityType.SKELETON){
                int percent = (int)(Math.random()*20);
                if(percent == 0){
                    e.getEntity().getLocation().getWorld().spawnEntity(e.getEntity().getLocation(),EntityType.WITHER_SKELETON);
                    killer.sendMessage(ChatColor.DARK_PURPLE+"まだおわらん！");
                    e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(),0.0F);
                }
            }else
                if(e.getEntity().getType() == EntityType.WITHER_SKELETON){

                    changePoint(killer,110);

                }

        }

    }
}
