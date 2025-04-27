package monster;

import entity.Entity;
import main.GamePanel;

public abstract class Monster extends Entity {

    public Monster(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {
        setAction();
        super.update(); // Cập nhật các hành động cơ bản
    }

    // Phuong thuc abstract cho hành động của quái vật
    public abstract void setAction();

    // Phuong thuc kiểm tra việc rơi đồ
    public abstract void checkDrop();
}
