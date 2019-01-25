package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;

public class Lottery extends BaseRuleListener {

    @EventHandler
    public void onPlayerCraft(CraftItemEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){
                if(e.getCurrentItem().getType() == Material.PAPER){
                for(HumanEntity pl : e.getViewers()) {
                    pl.sendMessage(ChatColor.GOLD + "くじを引くチャンスが与えられました。くじを引きたい人は紙をもって右クリックしてください。");
                }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerpickupItem(EntityPickupItemEvent e){
        if(e.getEntity() instanceof Player){
            if(ConditionPoint.gameRunning()){
                if(isEnable()){
                    if(e.getItem().getItemStack().getType() == Material.PAPER) {
                        Player p = (Player) e.getEntity();
                        p.sendMessage(ChatColor.GOLD + "くじを引くチャンスが与えられました。くじを引きたい人は紙をもって右クリックしてください。");
                    }
                }
            }
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()) {
                if(e.getPlayer().getItemInHand().getType() == Material.PAPER){
                    ItemStack item = e.getPlayer().getItemInHand();
                    item.setAmount(item.getAmount()-1);
                    int percent = (int)(Math.random()*100);
                    if(percent == 0){
                        e.getPlayer().sendMessage(ChatColor.GOLD+"大当たり！！");
                        Bukkit.broadcastMessage(e.getPlayer().getDisplayName()+"が大当たりを当選しました。");
                        changePoint(e.getPlayer(),1000);
                    }else if(percent < 10){
                        e.getPlayer().sendMessage(ChatColor.GOLD+"当たり！！");
                        changePoint(e.getPlayer(),200);

                    }else if(percent < 40){
                        e.getPlayer().sendMessage(ChatColor.AQUA+"小当たり！！");
                        changePoint(e.getPlayer(),50);

                    }else if(percent < 80){
                        e.getPlayer().sendMessage(ChatColor.GREEN+"残念賞！！");
                        changePoint(e.getPlayer(),10);

                    }else if(percent < 99){
                        e.getPlayer().sendMessage(ChatColor.RED+"はずれ！！");
                        changePoint(e.getPlayer(),-10);

                    }else{
                        e.getPlayer().sendMessage(ChatColor.DARK_RED+"ファンブル！！");
                        changePoint(e.getPlayer(),-1000);

                    }
                }

                }
            }

    }
}
