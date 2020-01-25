package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Shop {
    public static ArrayList<Upgrade> upgradeList;

    public Shop() {
        upgradeList = new ArrayList<>();
        upgradeList.add(new Upgrade(575,  80, 75));
        upgradeList.add(new Upgrade(575,  140, 100));
        upgradeList.add(new Upgrade(575, 200, 500));
    }

    public static ArrayList<Upgrade> getUpgradeList() {
        return upgradeList;
    }
}
