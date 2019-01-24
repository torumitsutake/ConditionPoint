package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class WorldGuardClass implements Listener {
    ConditionPoint plugin ;

    WorldGuardClass(ConditionPoint pl){
        plugin = pl;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e){
        if(!e.getPlayer().isOp()){
            if(e.getBlock().getLocation().getY() < 40){
                return;
            }
            Location loc = Bukkit.getServer().getWorld("world").getSpawnLocation();
            loc.setX(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.x"));
            loc.setY(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.y"));
            loc.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.z"));
            Chunk spawnchunk = loc.getChunk();
            Location locred = Bukkit.getServer().getWorld("world").getSpawnLocation();
            locred.setX(plugin.getConfig().getDouble("gameconfig.startlocation.red.x"));
            locred.setY(plugin.getConfig().getDouble("gameconfig.startlocation.red.y"));
            locred.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.red.z"));
            Chunk redchunk = locred.getChunk();
            Location locblue = Bukkit.getServer().getWorld("world").getSpawnLocation();
            locblue.setX(plugin.getConfig().getDouble("gameconfig.startlocation.blue.x"));
            locblue.setY(plugin.getConfig().getDouble("gameconfig.startlocation.blue.y"));
            locblue.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.blue.z"));
            Chunk bluechunk = locblue.getChunk();
            Chunk placechunk = e.getBlock().getChunk();
            if(spawnchunk.getX() == placechunk.getX() && spawnchunk.getZ() == placechunk.getZ()){
                e.setCancelled(true);
            }

            if(redchunk.getX() == placechunk.getX() && redchunk.getZ() == placechunk.getZ()){
                e.setCancelled(true);
            }
            if(bluechunk.getX() == placechunk.getX() && bluechunk.getZ() == placechunk.getZ()){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e){
        if(!e.getPlayer().isOp()){
            if(e.getBlock().getLocation().getY() < 40){
                return;
            }
            Location loc = Bukkit.getServer().getWorld("world").getSpawnLocation();
            loc.setX(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.x"));
            loc.setY(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.y"));
            loc.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.spawn.z"));
            Chunk spawnchunk = loc.getChunk();
            Location locred = Bukkit.getServer().getWorld("world").getSpawnLocation();
            locred.setX(plugin.getConfig().getDouble("gameconfig.startlocation.red.x"));
            locred.setY(plugin.getConfig().getDouble("gameconfig.startlocation.red.y"));
            locred.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.red.z"));
            Chunk redchunk = locred.getChunk();
            Location locblue = Bukkit.getServer().getWorld("world").getSpawnLocation();
            locblue.setX(plugin.getConfig().getDouble("gameconfig.startlocation.blue.x"));
            locblue.setY(plugin.getConfig().getDouble("gameconfig.startlocation.blue.y"));
            locblue.setZ(plugin.getConfig().getDouble("gameconfig.startlocation.blue.z"));
            Chunk bluechunk = locblue.getChunk();
            Chunk placechunk = e.getBlock().getChunk();

            if(spawnchunk.getX() == placechunk.getX() && spawnchunk.getZ() == placechunk.getZ()){

                e.setCancelled(true);
            }

            if(redchunk.getX() == placechunk.getX() && redchunk.getZ() == placechunk.getZ()){
                e.setCancelled(true);
            }
            if(bluechunk.getX() == placechunk.getX() && bluechunk.getZ() == placechunk.getZ()){
                e.setCancelled(true);
            }
        }
    }
}
