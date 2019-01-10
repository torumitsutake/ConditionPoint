package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Score;

public class ConditionPoint extends JavaPlugin {
    GameTimer timer;
    TeamClass teamclass;
    ScoreManager SM;
    public static boolean gamestart = false;
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        teamclass = new TeamClass();
        getLogger().info("ConditionPlugin has loaded.");
        SM = ScoreManager.getInstance();
        //リスナー登録
        getServer().getPluginManager().registerEvents(new ListenerClass(),this);
    }

    @Override
    public void onDisable(){
        getLogger().info("ConditionPlugin has Disable.");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        //プレイヤーをチームにjoinさせる
        if(cmd.getName().equalsIgnoreCase("setteam")){
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if(teamclass.setPlayertoTeam(target,args[1])) {
                target.sendMessage(ChatColor.GREEN+"you join the " + args[1] + "Team!");
            }else{
                sender.sendMessage("you have to choose red or blue");
            }
            return true;
        }else
            //ゲーム開始コマンド
            if(cmd.getName().equalsIgnoreCase("gamestart")) {
                SM.pointreset();
                int gametime = this.getConfig().getInt("gameconfig.gametime");
                Bukkit.broadcastMessage(ChatColor.AQUA+"ゲームを開始します...");
                Bukkit.broadcastMessage(ChatColor.AQUA+"ゲーム時間は"+gametime+"秒です！！");
                timer = new GameTimer(this,gametime);
                timer.runTaskTimer(this,10,20);
                gamestart = true;
         return true;
        }else
            //ゲームリセットコマンド
            if(cmd.getName().equalsIgnoreCase("gamereset")){
                resetAll();
                sender.sendMessage(ChatColor.GREEN+"ゲームがリセットされました。");
                gamestart = false;
                return true;
            }else
                if(cmd.getName().equalsIgnoreCase("setgametime")){
                    try {
                        this.getConfig().set("gameconfig.gametime", Integer.parseInt(args[0]));
                        this.saveConfig();
                        sender.sendMessage(ChatColor.GREEN + "ゲーム時間を" + args[0] + "秒に設定しました。");
                    }catch (NumberFormatException e){
                        sender.sendMessage( ChatColor.RED+"引数には数字を秒数で指定してください。");
                    }
                }


        return false;
    }



    //オールリセット処理
    public void resetAll(){
        SM.ScoreReset();
        timer.cancel();
        teamclass.teamReset();
        gamestart = false;
    }

    //ゲーム状況の取得
    public static boolean gameRunning(){
        return gamestart;
    }
    //ゲーム状況の変更
    public static void gameStop(){
        gamestart = false;
    }





}
