package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConditionPoint extends JavaPlugin {
    GameTimer timer;
    TeamClass teamclass;
    @Override
    public void onEnable(){
        teamclass = new TeamClass();
        getLogger().info("ConditionPlugin has loaded.");
    }

    @Override
    public void onDisable(){
        getLogger().info("ConditionPlugin has Disable.");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(cmd.getName().equalsIgnoreCase("setteam")){
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if(teamclass.setPlayertoTeam(target,args[1])) {
                Bukkit.broadcastMessage(args[0]);
                target.sendMessage("you join the " + args[1] + "Team!");
            }else{
                sender.sendMessage("you have to choose red or blue");
            }
            return true;
        }else
            if(cmd.getName().equalsIgnoreCase("gamestart")) {
                for(OfflinePlayer entry : teamclass.getRedteam().getPlayers()){
                 Bukkit.broadcastMessage(entry.getName());
                }
                timer = new GameTimer(this,60);
                timer.runTaskTimer(this,10,20);
         return true;
        }else
            if(cmd.getName().equalsIgnoreCase("gamereset")){

            }


        return false;
    }



    //アイテム→ポイント換算
    int POINT_DIAMOND = 50;
    int POINT_EMERALD = 40;
    int POINT_GOLD = 20;
    int POINT_LAPIS = 30;
    int POINT_IRON = 10;
    public int getPoint(Material type){
        switch (type){
            case DIAMOND:
                return 50;
            case EMERALD:
                return 40;
            case GOLD_INGOT:
                return 20;
            case LAPIS_BLOCK:
                return 30;
            case IRON_INGOT:
                return 10;
        }
        return 0;
    }

    public void setPoint(Material type, int point){
        switch (type){
            case DIAMOND:
                POINT_DIAMOND = point;
            case EMERALD:
                POINT_EMERALD = point;;
            case GOLD_INGOT:
                POINT_GOLD = point;
            case LAPIS_BLOCK:
                POINT_LAPIS = point;
            case IRON_INGOT:
                POINT_IRON = point;
        }
    }

}
