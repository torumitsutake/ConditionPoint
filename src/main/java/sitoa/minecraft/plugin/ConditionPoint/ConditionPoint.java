package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.GameRuleManager;

public class ConditionPoint extends JavaPlugin {
    GameTimer timer;
    TeamClass teamclass;
    ScoreManager SM;
    GameRuleManager GRM;
    ChestLock CL;
    public static boolean gamestart = false;
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        teamclass = new TeamClass();
        getLogger().info("ConditionPlugin has loaded.");
        SM = ScoreManager.getInstance();
        CL = ChestLock.getInstance();
        GRM = GameRuleManager.getInstance();
        GRM.setPlugin(this);
        GRM.firstLoad();
        //リスナー登録
        getServer().getPluginManager().registerEvents(new ListenerClass(),this);
    }

    @Override
    public void onDisable(){
        getLogger().info("[ConditionPoint]"+"ConditionPlugin has Disable.");
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
                gamestart = true;
                Bukkit.broadcastMessage(ChatColor.AQUA+"ゲーム時間は"+gametime+"分です！！");
                timer = new GameTimer(this,gametime);
                timer.runTaskTimer(this,10,20);
         return true;
        }else
            //ゲームリセットコマンド
            if(cmd.getName().equalsIgnoreCase("gamereset")){
                resetAll();
                sender.sendMessage(ChatColor.GREEN+"ゲームがリセットされました。");
                gamestart = false;
                return true;
            }else
                //ゲーム時間設定コマンド
                if(cmd.getName().equalsIgnoreCase("setgametime")) {
                    try {
                        this.getConfig().set("gameconfig.gametime", Integer.parseInt(args[0]));
                        this.saveConfig();
                        sender.sendMessage(ChatColor.GREEN + "ゲーム時間を" + args[0] + "分に設定しました。");
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + "引数には数字を分数で指定してください。");
                    }
                    return true;
                }
                //初期位置を決めるコマンド
                else if(cmd.getName().equalsIgnoreCase("startpoint")){
                    if(args[0].equalsIgnoreCase("red") || args[0].equalsIgnoreCase("blue") || args[0].equalsIgnoreCase("spawn") ){
                        if(sender instanceof  Player){
                            Player player = (Player)sender;
                            Location setloc = player.getLocation();
                            this.getConfig().set("gameconfig.startlocation."+args[0]+".x",setloc.getX());
                            this.getConfig().set("gameconfig.startlocation."+args[0]+".y",setloc.getY());
                            this.getConfig().set("gameconfig.startlocation."+args[0]+".z",setloc.getZ());
                            this.saveConfig();
                            sender.sendMessage(args[0]+"チームの初期位置を決定しました。");
                        }else{
                            sender.sendMessage("プレイヤーから実行してください");
                        }

                    }else{
                        sender.sendMessage("引数はred,blue,spawnにしてください。");
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
