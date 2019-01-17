package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTameEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class PlayerTamedtoPoint extends BaseRuleListener {

    @EventHandler
    public void onTame(EntityTameEvent e){
        if(ConditionPoint.gameRunning()) {
            if (!this.isEnable()) {
                return;
            }
            Player player = (Player) e.getOwner();
            player.sendMessage(ChatColor.AQUA + player.getDisplayName() + " tamed " + e.getEntity().getName());
            changePoint(player, 500);
        }

    }
}
