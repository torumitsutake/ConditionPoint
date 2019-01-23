package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

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


    //Enum管理
    protected enum POINTITEM{
        DIAMOND(50,Material.DIAMOND),
        EMERALD(40,Material.EMERALD),
        GOLD_INGOT(20,Material.GOLD_INGOT),
        LAPIS_BLOCK(30, Material.LAPIS_BLOCK),
        IRON_INGOT(10,Material.IRON_INGOT),
        REDSTONE_BLOCK(15,Material.REDSTONE_BLOCK),
        DIAMOND_BLOCK(500,Material.DIAMOND_BLOCK),
        GOLD_BLOCK(200,Material.GOLD_BLOCK),
        EMERALD_BLOCK(400,Material.EMERALD_BLOCK),
        IRON_BLOCK(100,Material.IRON_BLOCK),
        GOLDEN_APPLE(190,Material.GOLDEN_APPLE),
        SUGAR_CANE(5,Material.SUGAR_CANE),
        ENDERPEARL(20,Material.ENDER_PEARL),
        BLAZEROD(10,Material.BLAZE_ROD),
        OBSIDIAN(20,Material.OBSIDIAN),
        COAL(1,Material.COAL),
        PISTON(300,Material.PISTON_BASE),
        WaterMelon(20,Material.MELON_BLOCK),
        GASTTEAR(100, Material.GHAST_TEAR),
        HEAD(2000,Material.SKULL_ITEM),
        ENCHANTBOOK(100,Material.ENCHANTED_BOOK),
        PUMPKIN(20,Material.PUMPKIN);



        private int point;
        private Material type;


        POINTITEM(int point,Material item){
            this.point = point;
            this.type = item;
        }

        public Material getType(){
            return type;
        }
        public int getPoint(){
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }
    }


    public int getPoint(Material type){
        for(POINTITEM item : POINTITEM.values()){
            if(item.getType() == type){
                return item.getPoint();
            }
        }
        return 0;
    }

    public void setPoint(Material type, int point){
        for(POINTITEM item : POINTITEM.values()){
            if(item.getType() == type){
                item.setPoint(point);
                return;
            }
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

    //MVPプレイヤーの選出
    public ArrayList<Player> getMVPPLayers(){
        int max = -10000;
                ArrayList<Player> MVP = new ArrayList<Player>();
        for(Player p: Bukkit.getOnlinePlayers()){
            int point = getPointfromPlayer(p);
            if(max == point){
                MVP.add(p);
            }else if(max < point){
                MVP.clear();
                MVP.add(p);
                max = point;
            }
        }
        return MVP;
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
        Objective TeamPoint = board.getObjective(ChatColor.GREEN+"GameInfo");
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
