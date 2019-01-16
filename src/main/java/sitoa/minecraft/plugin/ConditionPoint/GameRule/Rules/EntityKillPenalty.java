package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;
import sitoa.minecraft.plugin.ConditionPoint.ScoreManager;

public class EntityKillPenalty extends BaseRuleListener {
    @EventHandler
    public void playerkillOther(EntityDeathEvent e){
        if(ConditionPoint.gameRunning()) {
            if(!this.isEnable()){
                return;
            }
            Player killer = e.getEntity().getKiller();
                if (e.getEntity().getType() == EntityType.PLAYER) {
                    changePoint(killer,-100);
                } else if (e.getEntityType() == EntityType.ENDER_DRAGON) {
                    changePoint(killer,20000);
                } else {
                    changePoint(killer,-10);
                }

        }
    }
}
