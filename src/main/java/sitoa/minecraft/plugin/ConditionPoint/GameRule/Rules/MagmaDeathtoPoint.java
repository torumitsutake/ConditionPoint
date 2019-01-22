package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;
import sitoa.minecraft.plugin.ConditionPoint.ScoreManager;

public class MagmaDeathtoPoint extends BaseRuleListener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        if(ConditionPoint.gameRunning()) {
            if (!this.isEnable()) {
                return;
            }
            Entity ent = e.getEntity();
            EntityDamageEvent ede = ent.getLastDamageCause();
            EntityDamageEvent.DamageCause dc = ede.getCause();
            if (ent instanceof Player && dc == EntityDamageEvent.DamageCause.LAVA) {
                Player player = (Player) ent;
                player.sendMessage("b");
                ScoreManager SM = ScoreManager.getInstance();
                changePoint(player, SM.getPointfromPlayer(player));
                player.getInventory().clear();

            }
        }
    }
}
