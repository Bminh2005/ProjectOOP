package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kết thúc khi nhấn x
        window.setResizable(false);//Ngăn chặn người dùng thay đổi kích thước cửa sổ game
        window.setTitle("2D Adventure Game"); //Tên của cửa sổ game

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setSize(1280, 720); // Đặt kích thước cửa sổ
        window.setLocationRelativeTo(null); // Canh giữa màn hình
        window.setVisible(true); //Hiển thị màn hình

        //gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
