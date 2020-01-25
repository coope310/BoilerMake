package GameObjects;

import java.awt.*;

public class InventoryButtons {
    private Rectangle bounds;
    private int plantChoice;
    private boolean selected;

    public InventoryButtons(Rectangle r, int plantChoice) {
        bounds = r;
        selected = false;
        this.plantChoice = plantChoice;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getPlantChoice() {
        return plantChoice;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
