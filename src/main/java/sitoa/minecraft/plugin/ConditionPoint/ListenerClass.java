package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ListenerClass implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //チェストロック機能
    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e){
        ChestLock CL = ChestLock.getInstance();
        if (e.getInventory().getHolder() instanceof Chest || e.getInventory().getHolder() instanceof DoubleChest){
            String team = "";
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getMainScoreboard();
            if(board.getTeam("REDTEAM").getPlayers().contains(e.getPlayer())){
                team ="red";
            }else
            if(board.getTeam("BLUETEAM").getPlayers().contains(e.getPlayer())){
                team="blue";
            }else{
                return;
            }
            System.out.println(e.getInventory().getLocation());
            if(CL.canOpen(e.getInventory().getLocation(),team)){
                return;
            }else{
                e.getPlayer().sendMessage("このチェストは敵チームによってロックされています。");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        if(!e.hasBlock()){
            return;
        }

        if(e.getClickedBlock().getType()== Material.CHEST){
            System.out.println("ChestClicked");
            ChestLock CL = ChestLock.getInstance();
            //ChestLockSystem
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getMainScoreboard();
            String team = "";
            if(board.getTeam("REDTEAM").getPlayers().contains(e.getPlayer())){
                team ="red";
            }else
                if(board.getTeam("BLUETEAM").getPlayers().contains(e.getPlayer())){
                    team="blue";
                }else{
                    return;
                }

                if(!(e.getClickedBlock().getState() instanceof Chest)){
                    return;
                }

            System.out.println("get team");
            Chest targetChest = (Chest) e.getClickedBlock().getState();
            Inventory targetInventory = targetChest.getInventory();
            System.out.println(targetInventory.getLocation());


            //鍵付き
            if(e.getPlayer().getItemInHand().getType() == Material.IRON_BLOCK){
                int result = CL.lockChest(targetInventory,targetChest.getLocation(),team);
                System.out.println("LockCode:"+result);
                switch(result){
                    case 0:
                        e.getPlayer().sendMessage("チェストがロックされました。");

                        ItemStack item = e.getPlayer().getItemInHand();
                        item.setAmount(item.getAmount()-1);
                        break;
                    case 1:
                        e.getPlayer().sendMessage("自チームがすでにこのチェストをロックしています。");
                        break;
                    case 2:
                        e.getPlayer().sendMessage("相手チームにこのチェストはロックされています。");
                        break;
                }

            }
            //鍵開け
            if(e.getPlayer().getItemInHand().getType() == Material.GOLD_BLOCK){
                    if(CL.UnlockChest(targetChest.getLocation())){
                        e.getPlayer().sendMessage("ロックが解除されました");
                        ItemStack item = e.getPlayer().getItemInHand();
                        item.setAmount(item.getAmount()-1);
                    }else{
                        e.getPlayer().sendMessage("ロックがかかっていません");
                    }

            }

        }

    }

    @EventHandler
    public void onPlayerBlockBreakEvent(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.CHEST){
            ChestLock CL = ChestLock.getInstance();
            if(!CL.canBreak(e.getBlock().getLocation())){
                e.setCancelled(true);
                e.getPlayer().sendMessage("このチェストはロックされています。");
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





}
