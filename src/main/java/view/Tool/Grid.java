package view.Tool;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {

    public Grid() {
        setLayout(new GridBagLayout());
    }



    public void GridAdd(Component element, int x, int y,int left,int right,int topBot){
        Insets paddingInput = new Insets(topBot, left, topBot, right);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = paddingInput;
        gbc.gridx = x;
        gbc.gridy = y;

        add(element, gbc); // Thêm lớp trung gian vào JPanel
        // Đặt padding về giá trị mặc định
        gbc.insets = new Insets(0, 0, 0, 0);
    }

    public void GridAddCustom (Component element, int x, int y,int left,int right,int top,int Bot,int width){
        Insets paddingInput = new Insets(top, left, Bot, right);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = paddingInput;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;

        add(element, gbc); // Thêm lớp trung gian vào JPanel
        gbc.gridwidth = 1;
        // Đặt padding về giá trị mặc định
        gbc.insets = new Insets(0, 0, 0, 0);
    }
}

