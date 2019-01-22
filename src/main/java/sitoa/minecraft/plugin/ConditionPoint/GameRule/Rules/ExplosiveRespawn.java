package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class ExplosiveRespawn extends BaseRuleListener {
    @EventHandler
    public void onPlayerDeath(EntityDamageEvent e){
        if(ConditionPoint.gameRunning()){
            if(!this.isEnable()){
                return;
            }
            Entity ent = e.getEntity();
            EntityDamageEvent ede = ent.getLastDamageCause();
            EntityDamageEvent.DamageCause dc = ede.getCause();
            if(e.getEntity() instanceof Player && dc == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION){
                int percent = (int)(Math.random()*2);
                if(percent == 0) {
                    Player p = (Player) e.getEntity();
                    if (p.getHealth() - e.getDamage() <= 0) {
                        e.setCancelled(true);
                        p.teleport(p.getBedSpawnLocation());
                        p.sendMessage(ChatColor.RED + "爆発オチなんてサイテー！");
                    }
                }
            }
        }

    }
}
