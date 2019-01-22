package sitoa.minecraft.plugin.ConditionPoint;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class ChestLock {
    private static ChestLock instance;

    private ChestLock(){

    }
    public static ChestLock getInstance(){
        if(instance == null){
            instance = new ChestLock();
        }
        return instance;

    }

    public static HashMap<Location, Inventory> redChests = new HashMap<Location, Inventory>();
    public static HashMap<Location, Inventory> blueChests = new HashMap<Location, Inventory>();


    public int lockChest(Inventory inv,Location loc,String team){

        System.out.println("LockChest:");
        Location loc1 = loc.clone();
        Location loc2 = loc.clone();
        Location loc3 = loc.clone();
        Location loc4 = loc.clone();
        loc1.setX(loc.getX() - 1);
        loc2.setX(loc.getX() + 1);
        loc3.setZ(loc.getZ() - 1);
        loc4.setZ(loc.getZ() + 1);
        System.out.println(loc);
        if(team.equalsIgnoreCase("red")){
            if(redChests.containsKey(loc) || redChests.containsKey(loc1) || redChests.containsKey(loc2)|| redChests.containsKey(loc3)|| redChests.containsKey(loc4)){
                return 1;
            }else
                if(blueChests.containsKey(loc) || blueChests.containsKey(loc1) || blueChests.containsKey(loc2)|| blueChests.containsKey(loc3)|| blueChests.containsKey(loc4)){
                    return 2;
                }else{
                    redChests.put(loc,inv);
                    return 0;
                }


        }else{
            if(blueChests.containsKey(loc) || blueChests.containsKey(loc1) || blueChests.containsKey(loc2)|| blueChests.containsKey(loc3)|| blueChests.containsKey(loc4)){
                return 1;
            }else
            if(redChests.containsKey(loc) || redChests.containsKey(loc1) || redChests.containsKey(loc2)|| redChests.containsKey(loc3)|| redChests.containsKey(loc4)){
                return 2;
            }else{
                blueChests.put(loc,inv);
                return 0;
            }


        }
    }

    public boolean canBreak(Location loc){
        System.out.println("BreakChest:");
        Location loc1 = loc.clone();
        Location loc2 = loc.clone();
        Location loc3 = loc.clone();
        Location loc4 = loc.clone();
        loc1.setX(loc.getX() - 1);
        loc2.setX(loc.getX() + 1);
        loc3.setZ(loc.getZ() - 1);
        loc4.setZ(loc.getZ() + 1);

        if(redChests.containsKey(loc)){
            return false;
        }
        if(redChests.containsKey(loc1)){
            return false;
        }
        if(redChests.containsKey(loc2)){
            return false;
        }
        if(redChests.containsKey(loc3)){
            return false;
        }
        if(redChests.containsKey(loc4)){
            return false;
        }
        if(blueChests.containsKey(loc)){
            return false;
        }
        if(blueChests.containsKey(loc1)){
            return false;
        }
        if(blueChests.containsKey(loc2)){
            return false;
        }
        if(blueChests.containsKey(loc3)){
            return false;
        }
        if(blueChests.containsKey(loc4)){
            return false;
        }
        return true;
    }

    public boolean UnlockChest(Location loc){
        System.out.println("UnLockChest:");
        Location loc1 = loc.clone();
        Location loc2 = loc.clone();
        Location loc3 = loc.clone();
        Location loc4 = loc.clone();
        loc1.setX(loc.getX() - 1);
        loc2.setX(loc.getX() + 1);
        loc3.setZ(loc.getZ() - 1);
        loc4.setZ(loc.getZ() + 1);
        if(redChests.containsKey(loc)){
            redChests.remove(loc);
            return true;
        }
        if(redChests.containsKey(loc1)){
            redChests.remove(loc1);
            return true;
        }
        if(redChests.containsKey(loc2)){
            redChests.remove(loc2);
            return true;
        }
        if(redChests.containsKey(loc3)){
            redChests.remove(loc3);
            return true;
        }
        if(redChests.containsKey(loc4)){
            redChests.remove(loc4);
            return true;
        }
        if(blueChests.containsKey(loc)){
            blueChests.remove(loc);
            return true;
        }
        if(blueChests.containsKey(loc1)){
            blueChests.remove(loc1);
            return true;
        }
        if(blueChests.containsKey(loc2)){
            blueChests.remove(loc2);
            return true;
        }
        if(blueChests.containsKey(loc3)){
            blueChests.remove(loc3);
            return true;
        }
        if(blueChests.containsKey(loc4)){
            blueChests.remove(loc4);
            return true;
        }

        return false;
    }


    //あけれるかどうか
    public boolean canOpen(Location loc, String team){
        Location loc1 = loc.clone();
        Location loc2 = loc.clone();
        Location loc3 = loc.clone();
        Location loc4 = loc.clone();
        loc1.setX(loc.getX() - 0.5);
        loc2.setX(loc.getX() + 0.5);
        loc3.setZ(loc.getZ() - 0.5);
        loc4.setZ(loc.getZ() + 0.5);
        if(team.equalsIgnoreCase("red")){
            if(blueChests.containsKey(loc) || blueChests.containsKey(loc1) || blueChests.containsKey(loc2)|| blueChests.containsKey(loc3)|| blueChests.containsKey(loc4)){
                return false;
            }
            return true;


        }else
            if(team.equalsIgnoreCase("blue")){

                if(redChests.containsKey(loc) || redChests.containsKey(loc1) || redChests.containsKey(loc2)|| redChests.containsKey(loc3)|| redChests.containsKey(loc4)){
                    return false;
                }
                return true;
            }else{
                return true;
            }

    }

}
