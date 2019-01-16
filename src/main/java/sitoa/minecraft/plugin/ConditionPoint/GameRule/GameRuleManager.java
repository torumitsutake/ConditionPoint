package sitoa.minecraft.plugin.ConditionPoint.GameRule;

import org.bukkit.plugin.java.JavaPlugin;
import sitoa.minecraft.plugin.ConditionPoint.ConditionPoint;
import sitoa.minecraft.plugin.ConditionPoint.GameRule.Rules.*;
import sitoa.minecraft.plugin.ConditionPoint.ScoreManager;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRuleManager {
    //シングルトン設計

    //グローバル変数
    private static GameRuleManager instance;
    private static ConditionPoint plugin;
    private HashMap<GameRule,BaseRule> Rules = new HashMap<GameRule,BaseRule>();



    //関数
    public static GameRuleManager getInstance(){
        if(instance == null){
            instance = new GameRuleManager();
        }
        return instance;
    }

    public void setPlugin(ConditionPoint plugin){
        this.plugin = plugin;
    }

    public void firstLoad(){
        loadfromconfig();
        setListeners();
    }

    public void setListeners(){
        for(GameRule key : Rules.keySet()){
            BaseRule rule = Rules.get(key);
            if(rule.isListenerRule()){
                BaseRuleListener listenerrule = (BaseRuleListener)rule;
                plugin.getServer().getPluginManager().registerEvents(listenerrule,plugin);
                plugin.getLogger().info(key+"がリスナーに登録されました。");
            }

        }

    }

    public HashMap<GameRule,BaseRule> getRules(){
        return Rules;
    }

    public boolean registerRule(GameRule enumrule,BaseRule rule){
        if(Rules.containsValue(rule)){
            return false;
        }else{
            Rules.put(enumrule,rule);
            plugin.getLogger().info(enumrule.getConfigName()+": 有効化されました。");
            return true;
        }
    }

    public void loadfromconfig(){
        for(GameRule rule : GameRule.values()){
            boolean isEnable = plugin.getConfig().getBoolean("ruleconfig."+rule.getConfigName());
            BaseRule ruleinstance = rule.getRuleInstance();
            if(isEnable){
                ruleinstance.setEnable(true);
                registerRule(rule,ruleinstance);
            }else{
                ruleinstance.setEnable(false);

            }
        }
    }


    //ルール一覧(Enum)
    /*
    ルールを追加したときはこことconfig.ymlに追記する.
     */
    protected  enum GameRule {
        EntityKillPenalty("entitykillpenalty", new EntityKillPenalty()),
        DesertDamage("desartdamagerule",new DesertDamage()),
        MagmaDeathPointReward("magmadeathtopoint",new MagmaDeathtoPoint()),
        TameReward("tamereward", new PlayerTamedtoPoint()),
        TorchPenalty("torchpenalty",new TorchPlacePenalty());

        private String cofigName;
        private BaseRule rule;
        private GameRule(String configName,BaseRule rule){
            this.cofigName = configName;
            this.rule = rule;
        }

        public String getConfigName(){
            return cofigName;
        }
        public BaseRule getRuleInstance(){
            return rule;
        }
    };



}
