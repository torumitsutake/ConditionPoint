package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class DesertDamage extends BaseRuleListener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e){
        Location loc = e.getPlayer().getLocation();
        World world = loc.getWorld();
        Biome biome = world.getBiome(loc.getBlockX(),loc.getBlockZ());
        if(biome == Biome.DESERT  || biome == Biome.DESERT_HILLS){
            int percent = (int)(Math.random()*100);
            if(percent == 0){
                e.getPlayer().damage(1);
            }
        }

    }
}
