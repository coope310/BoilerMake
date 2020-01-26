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
        upgradeList.add(new Upgrade(575, 200, 25));
        upgradeList.add(new Upgrade(575, 260, 325));
        upgradeList.add(new Upgrade(575, 320, 400));
        upgradeList.add(new Upgrade(575, 380, 1250));
        upgradeList.add(new Upgrade(575, 440, 500));
    }

    public static ArrayList<Upgrade> getUpgradeList() {
        return upgradeList;
    }
}
