package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class DrowninWater extends BaseRuleListener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        if(ConditionPoint.gameRunning()){
            if(!this.isEnable()){
                return ;
            }else{
                if(event.getEntity() instanceof Player){
                    if(event.getCause() == EntityDamageEvent.DamageCause.DROWNING){
                        Player p = (Player)event.getEntity();
                        p.setHealth(0);

                    }
                }
            }
        }


    }


}
