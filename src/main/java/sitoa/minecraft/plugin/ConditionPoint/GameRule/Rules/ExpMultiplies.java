package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.entity.Player;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRule;

public class ExpMultiplies extends BaseRule {

    public double multiple(Player p){
        if(isEnable()) {
            return ((double) p.getLevel() / 20.0 + 1.0);
        }else{
            return 1.0;
        }
    }
}
