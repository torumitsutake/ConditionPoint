package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.inventory.ItemStack;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;


public class BreedingReward extends BaseRuleListener {
    @EventHandler
    public void onPlayerBreed(EntityBreedEvent e){
        if(ConditionPoint.gameRunning()){
            if(this.isEnable()){
                    e.getMother().getLocation().getWorld().dropItemNaturally(e.getMother().getLocation(), new ItemStack(Material.GOLD_INGOT,1));

            }
        }

    }
}
