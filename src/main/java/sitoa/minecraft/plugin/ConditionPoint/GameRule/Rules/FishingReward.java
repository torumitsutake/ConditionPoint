package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class FishingReward extends BaseRuleListener {


    @EventHandler
    public void onPlayerFish(PlayerFishEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){

            }
        }
    }


}
