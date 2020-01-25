package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Shop {
    public static ArrayList<Upgrade> upgradeList;

    public Shop() {
        upgradeList = new ArrayList<>();
        upgradeList.add(new Upgrade(520, 20, 0));
        upgradeList.add(new Upgrade(520,  80, 75));
        upgradeList.add(new Upgrade(520,  140, 100));
    }
}
