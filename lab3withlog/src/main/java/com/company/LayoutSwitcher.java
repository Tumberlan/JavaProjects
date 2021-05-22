package com.company;

import javax.swing.*;
import java.awt.*;

public class LayoutSwitcher {

    RaceLayout RL = new RaceLayout();

    public void go() {
        JPanel rootPanel = new JPanel(new CardLayout());
        JPanel card1 = new JPanel();
        rootPanel.add(card1, "Menu");

        CardLayout cards = (CardLayout) rootPanel.getLayout();
        cards.show(rootPanel, "Race");
    }
}
