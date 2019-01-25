package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class SpawnerBreakReward extends BaseRuleListener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){
                if(e.getBlock().getType()  == Material.MOB_SPAWNER){
                    changePoint(e.getPlayer(),1000);
                    e.getPlayer().sendMessage("you break a mob spawner");
                }
            }
        }

    }
}
