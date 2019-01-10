package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.*;

public class ScoreManager {
    //シングルトン設計
    public static ScoreManager instance;

    private ScoreManager(){

    }

    public static ScoreManager getInstance(){
        if(instance == null){
            instance = new ScoreManager();
        }
        return instance;
    }
    //プレイヤーの行動によるポイント増減
    //redTeam
    int parmRedPoint;
    //blueteam
    int parmBluePoint;

    public void parmPointChange(String team, int changepoint){
        if(team.equalsIgnoreCase("red")){
            parmRedPoint += changepoint;

        }else if(team.equalsIgnoreCase("blue")){
                parmBluePoint += changepoint;
            }
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
                return POINT_DIAMOND;
            case EMERALD:
                return POINT_EMERALD;
            case GOLD_INGOT:
                return POINT_GOLD;
            case LAPIS_BLOCK:
                return POINT_LAPIS;
            case IRON_INGOT:
                return POINT_IRON;
            default:
                return 0;
        }
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


    //チームポイント処理
    public int RedPoint;
    public int BluePoint;

    //チームポイントリセット
    public void pointreset(){
        RedPoint = 0;
        BluePoint = 0;
        parmBluePoint = 0;
        parmRedPoint = 0;
    }

    //プレイヤーからスコアだし
    public int getPointfromPlayer(Player p){
        int outscore = 0;
        PlayerInventory inventory = p.getInventory();
        ItemStack[] items = inventory.getStorageContents();
        for(int i =0 ; i < items.length;i++){
            ItemStack item = items[i];
            {
                if(item == null){
                    continue;
                }
                outscore += (getPoint(item.getType()) * item.getAmount());
            }
        }
        return outscore;
    }

    //スコアをプレイヤーからロード
    public void LoadScorefromPlayer(){
        ScoreboardManager SBM = Bukkit.getScoreboardManager();
        Scoreboard board = SBM.getMainScoreboard();
        Objective TeamPoint = board.getObjective("teampoint");
        //redteam point calculate
        Team redteam = board.getTeam("REDTEAM");
        RedPoint = 0;
        for(OfflinePlayer player : redteam.getPlayers()){
            if(player.isOnline()){
                Player onPlayer = (Player)player;
                RedPoint += getPointfromPlayer(onPlayer);
            }
        }
        Score redscore = TeamPoint.getScore(ChatColor.RED + "RedPoint:");
        RedPoint += parmRedPoint;
        redscore.setScore(RedPoint);

        //blueteam point calculate
        Team blueteam = board.getTeam("BLUETEAM");
        BluePoint = 0;
        for(OfflinePlayer player : blueteam.getPlayers()){
            if(player.isOnline()){
                Player onPlayer = (Player)player;
                BluePoint += getPointfromPlayer(onPlayer);
            }
        }
        Score bluescore = TeamPoint.getScore(ChatColor.BLUE + "BluePoint:");
        BluePoint += parmBluePoint;
        bluescore.setScore(BluePoint);

    }


    public void ScoreReset(){

        ScoreboardManager SBM = Bukkit.getScoreboardManager();
        Scoreboard board = SBM.getMainScoreboard();
        board.resetScores(ChatColor.RED + "RedPoint:");
        board.resetScores(ChatColor.BLUE + "BluePoint:");
    }





}
