package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class TorchPlacePenalty extends BaseRuleListener {

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent e) {
        if (ConditionPoint.gameRunning()) {
            if(!this.isEnable()){
                return;
            }
            if (e.getBlock().getType() == Material.TORCH) {
                Player player = e.getPlayer();
                changePoint(player, -1);
            }
        }
    }

}
