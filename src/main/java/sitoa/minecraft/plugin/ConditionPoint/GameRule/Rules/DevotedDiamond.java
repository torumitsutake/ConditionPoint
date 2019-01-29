package sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules;

import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.BaseRuleListener;
import sitoa.minecraft.plugin.ConditionPoint.TeamClass;

import java.util.ArrayList;

public class DevotedDiamond extends BaseRuleListener {

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){
                if(e.getBlock().getType() == Material.DIAMOND_ORE){
                    int percent = (int)(Math.random()*10);
                    if(percent == 0) {
                        ItemStack devotedDiamond = new ItemStack(Material.DIAMOND);
                        ItemMeta DDMeta = devotedDiamond.getItemMeta();
                        DDMeta.setDisplayName(ChatColor.DARK_PURPLE + "DevotedDiamond");
                        devotedDiamond.setItemMeta(DDMeta);
                        e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), devotedDiamond);
                        e.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "呪われたダイヤモンドを手にしものよ");
                        e.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "ダイヤモンドをいけにえに一人処刑してさしあげよう。");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){

                if(e.getItem() == null){
                    return;
                }
                if(e.getItem().getItemMeta().getDisplayName() == null){
                    return;
                }
                if(e.getItem().getType() == Material.DIAMOND && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE+"DevotedDiamond")){
                    Player pl = e.getPlayer();
                    Team enemyteam = getPlayerEnemyTeamClass(pl);
                    if(enemyteam == null){
                        return;
                    }
                    int playerno = ((enemyteam.getSize()+1)/9)+1;
                    Inventory targetList = Bukkit.createInventory(null,playerno*9,"TargetList");
                    int counter = 0;
                    for(OfflinePlayer enemy : enemyteam.getPlayers()){
                        if(!enemy.isOnline()){
                            return;
                        }
                        ItemStack enemyskull = new ItemStack(Material.SKULL_ITEM,1,(byte) 3);
                        SkullMeta skullmeta = (SkullMeta)enemyskull.getItemMeta();
                        skullmeta.setOwningPlayer(enemy);
                        skullmeta.setDisplayName(enemy.getName());
                        enemyskull.setItemMeta(skullmeta);
                        targetList.setItem(counter,enemyskull);
                        counter++;
                    }
                    e.getPlayer().openInventory(targetList);
                }
            }
        }

    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        if(ConditionPoint.gameRunning()){
            if(isEnable()){
                if(e.getInventory().getName().equalsIgnoreCase("TargetList")){
                    ItemStack enemySkull = e.getCurrentItem();
                    if(enemySkull.getType() == Material.AIR){
                        return;
                    }
                    SkullMeta skullmeta = (SkullMeta)enemySkull.getItemMeta();
                    OfflinePlayer target = skullmeta.getOwningPlayer();
                    if(target.isOnline()){
                        Player LivePlayer = (Player)target;
                        if(LivePlayer.getHealth() > 0){
                            if(LivePlayer.getGameMode() == GameMode.CREATIVE){
                                e.getWhoClicked().sendMessage(ChatColor.DARK_PURPLE+"そいつは生きていない。");
                            }else {
                                LivePlayer.damage(30);
                                Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + e.getWhoClicked().getName() + "が" + LivePlayer.getName() + "を呪い殺した。");
                                Inventory handlerInventory = e.getWhoClicked().getInventory();
                                if (handlerInventory instanceof PlayerInventory) {
                                    ((PlayerInventory) handlerInventory).getItemInMainHand().setAmount(((PlayerInventory) handlerInventory).getItemInMainHand().getAmount() - 1);
                                }
                            }
                        }else{
                            e.getWhoClicked().sendMessage(ChatColor.DARK_PURPLE+"そいつはすでに死んでいる。");

                        }
                        e.setCancelled(true);
                        e.getWhoClicked().closeInventory();
                    }
                }
            }
        }
    }



}
