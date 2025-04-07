package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; // 16x16 Tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    public KeyHandler keyH = new KeyHandler(this);

    Thread gameThread;

    // Player (ô vuông người chơi)
    int squareX = 100;
    int squareY = 100;
    final int squareSpeed = 5;

    // Monsters (2 ô vuông quái vật)
    int monster1X = 300;
    int monster1Y = 300;
    int monster2X = 500;
    int monster2Y = 500;

    final int monsterSpeed = 20;
    final int monsterSpeedFollow = 2;

    // Bán kính quái vật đuổi theo người chơi
    final int chaseRadius = 200; // Khoảng cách mà quái vật sẽ đuổi theo người chơi (pixel)

    // Thời gian di chuyển quái vật ngẫu nhiên
    long lastMonsterMoveTime1 = 0;
    long lastMonsterMoveTime2 = 0;
    final long moveInterval = 500000000L; // 2 giây (tính bằng nanoTime)

    // Khởi tạo màn hình game
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Thiết lập cửa sổ
        this.setBackground(Color.black); //Khởi tạo nền màu đen
        this.setDoubleBuffered(true); // Vẽ trước vào bộ nhớ rồi hiển thị lên màn hình → mượt hơn, giảm độ nhấp nháy
        this.addKeyListener(keyH); //Nhận sự kiện từ bàn phím
        this.setFocusable(true); //Nhận input từ bàn phím
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // Di chuyển ô vuông của người chơi (player)
        if (keyH.upPressed) {
            squareY -= squareSpeed; // Di chuyển lên
        }
        if (keyH.downPressed) {
            squareY += squareSpeed; // Di chuyển xuống
        }
        if (keyH.leftPressed) {
            squareX -= squareSpeed; // Di chuyển sang trái
        }
        if (keyH.rightPressed) {
            squareX += squareSpeed; // Di chuyển sang phải
        }

        // Di chuyển quái vật 1 nếu gần người chơi hoặc di chuyển ngẫu nhiên
        moveMonster(1);

        // Di chuyển quái vật 2 nếu gần người chơi hoặc di chuyển ngẫu nhiên
        moveMonster(2);
    }

    // Tính toán khoảng cách giữa quái vật và người chơi
    public double calculateDistance(int monsterX, int monsterY, int playerX, int playerY) {
        return Math.sqrt(Math.pow(monsterX - playerX, 2) + Math.pow(monsterY - playerY, 2));
    }

    // Di chuyển quái vật về phía người chơi nếu trong phạm vi bán kính
    public void moveMonster(int monsterNumber) {
        int monsterX = (monsterNumber == 1) ? monster1X : monster2X;
        int monsterY = (monsterNumber == 1) ? monster1Y : monster2Y;
        long currentTime = System.nanoTime();

        // Tính toán khoảng cách giữa quái vật và người chơi
        double distance = calculateDistance(monsterX, monsterY, squareX, squareY);

        if (distance < chaseRadius) {
            // Nếu quái vật trong phạm vi bán kính, di chuyển về phía người chơi
            int deltaX = squareX - monsterX;
            int deltaY = squareY - monsterY;
            double angle = Math.atan2(deltaY, deltaX); // Tính góc hướng đến người chơi

            // Di chuyển quái vật về phía người chơi
            monsterX += (int) (monsterSpeedFollow * Math.cos(angle)); // Di chuyển theo trục X
            monsterY += (int) (monsterSpeedFollow * Math.sin(angle)); // Di chuyển theo trục Y
        } else {
            // Di chuyển quái vật ngẫu nhiên nếu không đuổi theo người chơi
            if (currentTime - (monsterNumber == 1 ? lastMonsterMoveTime1 : lastMonsterMoveTime2) >= moveInterval) {
                Random rand = new Random();
                int direction = rand.nextInt(4); // 0: lên, 1: xuống, 2: trái, 3: phải

                switch (direction) {
                    case 0:
                        monsterY -= monsterSpeed; // Di chuyển lên
                        break;
                    case 1:
                        monsterY += monsterSpeed; // Di chuyển xuống
                        break;
                    case 2:
                        monsterX -= monsterSpeed; // Di chuyển trái
                        break;
                    case 3:
                        monsterX += monsterSpeed; // Di chuyển phải
                        break;
                }

                // Cập nhật thời gian di chuyển ngẫu nhiên
                if (monsterNumber == 1) {
                    lastMonsterMoveTime1 = currentTime;
                } else {
                    lastMonsterMoveTime2 = currentTime;
                }
            }
        }

        // Cập nhật lại vị trí của quái vật
        if (monsterNumber == 1) {
            monster1X = monsterX;
            monster1Y = monsterY;
        } else {
            monster2X = monsterX;
            monster2Y = monsterY;
        }
    }

    // Vẽ các đối tượng lên màn hình
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clear the screen
        Graphics2D g2d = (Graphics2D) g;

        // Render ô vuông người chơi (square)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(squareX, squareY, tileSize, tileSize);

        // Render ô vuông quái vật 1
        g2d.setColor(Color.RED);
        g2d.fillRect(monster1X, monster1Y, tileSize, tileSize);

        // Render ô vuông quái vật 2
        g2d.setColor(Color.BLUE);
        g2d.fillRect(monster2X, monster2Y, tileSize, tileSize);

        g2d.dispose(); // Dọn dẹp đối tượng graphics
    }
}
