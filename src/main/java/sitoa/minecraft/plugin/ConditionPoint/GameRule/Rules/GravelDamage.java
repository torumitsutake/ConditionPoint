package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class GravelDamage extends BaseRuleListener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){
                Player p = e.getPlayer();
                if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType()  == Material.GRAVEL){
                    int percent = (int)(Math.random()*50);
                    if(percent == 0){
                        p.damage(2);
                    }

                }
            }
        }
    }
}
