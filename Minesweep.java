package org.cis1200.minesweeper;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Minesweep extends JPanel {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;



    public static final String IMG_FILE = "files/bomb.png";
    public static final String IMG_FILE1 = "files/Minesweeper_1.png";
    public static final String IMG_FILE2 = "files/Minesweeper_2.png";
    public static final String IMG_FILE3 = "files/Minesweeper_3.png";
    public static final String IMG_FILE4 = "files/Minesweeper_4.png";

    public static final String IMG_FILE5 = "files/Minesweeper_5.png";
    public static final String IMG_BLANK = "files/Blank.png";

    private Board game; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 500;

    private static BufferedImage bomb;
    private static BufferedImage one;
    private static BufferedImage two;

    private static BufferedImage three;
    private static BufferedImage four;

    private static BufferedImage five;
    private static BufferedImage blank;

    private Board temp;

    private int x;
    private int y;

    private int xBomb;
    private int yBomb;

    public Minesweep(JLabel statusInit) {

        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        game = new Board(); // initializes model for the game
        status = statusInit; // initializes the status JLabel


        temp = new Board();
        initiateTemp();


        try {
            if (bomb == null) {
                bomb = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        try {
            if (blank == null) {
                blank = ImageIO.read(new File(IMG_BLANK));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        try {
            if (one == null) {
                one = ImageIO.read(new File(IMG_FILE1));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        try {
            if (two == null) {
                two = ImageIO.read(new File(IMG_FILE2));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        try {
            if (three == null) {
                three = ImageIO.read(new File(IMG_FILE3));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        try {
            if (four == null) {
                four = ImageIO.read(new File(IMG_FILE4));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        try {
            if (five == null) {
                five = ImageIO.read(new File(IMG_FILE5));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                x = p.x / 50;
                y = p.y / 50;
                // updates the model given the coordinates of the mouseclick
                game.playTurn(p.x / 50,p.y / 50);
                revealBoard(x, y);
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board

                }
            });

    }


    public void initiateTemp() {
        for (int i = 0; i < Minesweep.HEIGHT; i++) {
            for (int j = 0; j < Minesweep.WIDTH; j++) {
                temp.getCell(j,i).setNearbyBomb(-1);
            }
        }
    }
    public void reset() {
        game.reset();
        initiateTemp();

        status.setText("Running...");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (game.getGameOver()) {
            status.setText("Game Over!");
        } else if (game.getWin()) {
            status.setText("Game Won!");
        } else {
            status.setText("Running...");
        }

    }

    /**
     * Draws the game board.
     *
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!game.getGameOver() && !game.getWin()) {
            drawBoard(g);
            drawTemp(g);
        } else {
            drawBoard(g);
            drawTemp(g);
            revealBombs(g);
        }

    }
    public void revealBombs(Graphics g) {
        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                if (game.getCell(j,i).hasBomb()) {
                    g.drawImage(bomb,j * 50,i * 50,50,50,null);
                }
            }
        }
    }


    public void checkGameOver() {
        int counter = 0;
        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                if (temp.getCell(j,i).getNearbyBomb() == -1) {
                    counter++;
                }
            }
        }

        if (counter == 10) {
            game.setWin(true);
        }
    }

    public void drawTemp(Graphics g) {
        for (int i = 0; i < Minesweep.HEIGHT; i++) {
            for (int j = 0; j < Minesweep.WIDTH; j++) {
                if (temp.getCell(j,i).getNearbyBomb() == 10) {
                    g.drawImage(blank, j * 50, i * 50, 50, 50, null);

                } else if (temp.getCell(j,i).getNearbyBomb() == 11) {
                    g.drawImage(one, j * 50, i * 50, 50, 50, null);

                } else if (temp.getCell(j,i).getNearbyBomb() == 12) {
                    g.drawImage(two, j * 50, i * 50, 50, 50, null);

                } else if (temp.getCell(j,i).getNearbyBomb() == 13) {
                    g.drawImage(three, j * 50, i * 50, 50, 50, null);
                } else if (temp.getCell(j,i).getNearbyBomb() == 14) {
                    g.drawImage(four, j * 50, i * 50, 50, 50, null);
                } else if (temp.getCell(j,i).getNearbyBomb() == 15) {
                    g.drawImage(five, j * 50, i * 50, 50, 50, null);
                } else if (temp.getCell(j,i).getNearbyBomb() == 9) {
                    g.drawImage(bomb,j * 50,i * 50,50,50,null);
                }

            }
        }
    }
    public void drawBoard(Graphics g) {
        for (int i = 1; i < 10; i++) {
            g.drawLine(0,i * 50,500,i * 50);
        }

        for (int i = 1; i < 10; i++) {
            g.drawLine(50 * i,0,50 * i,500);
        }
    }
    public void revealBoard(int j, int i) {
//        System.out.println("reveal temp");
////        temp.print();
//        System.out.println("reveal game");
//        game.print();
        Cell state = game.getCell(j, i);
        if (state.hasBomb()) {
           // g.drawImage(bomb, j * 50, i * 50, 50, 50, null);
            temp.getCell(j, i).setNearbyBomb(9);
        } else if (state.getNearbyBomb() == 0) {
           // g.drawImage(blank, j * 50, i * 50, 50, 50, null);
            game.getCell(j, i).setReveal(true);
            //System.out.println("here");

            revealSurrounding(j, i);
            temp.getCell(j, i).setNearbyBomb(10);

        } else if (state.getNearbyBomb() == 1) {
          //  g.drawImage(one, j * 50, i * 50, 50, 50, null);
            temp.getCell(j, i).setNearbyBomb(11);
            game.getCell(j, i).setReveal(true);
        } else if (state.getNearbyBomb() == 2) {
           // g.drawImage(two, j * 50, i * 50, 50, 50, null);
            temp.getCell(j, i).setNearbyBomb(12);
            game.getCell(j, i).setReveal(true);
        } else if (state.getNearbyBomb() == 3) {
          //  g.drawImage(three, j * 50, i * 50, 50, 50, null);
            temp.getCell(j, i).setNearbyBomb(13);
            game.getCell(j, i).setReveal(true);
        } else if (state.getNearbyBomb() == 4) {
          //  g.drawImage(four, j * 50, i * 50, 50, 50, null);
            temp.getCell(j, i).setNearbyBomb(14);
            game.getCell(j, i).setReveal(true);
        } else if (state.getNearbyBomb() == 5) {
          //  g.drawImage(five, j * 50, i * 50, 50, 50, null);
            temp.getCell(j, i).setNearbyBomb(15);
            game.getCell(j, i).setReveal(true);
        }
        /*
        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                if (i == y && j == x) {
                    Cell state = game.getCell(j, i);
                    if (state.hasBomb()) {
                        g.drawImage(bomb, j * 50, i * 50, 50, 50, null);
                        temp.getCell(j, i).setNearbyBomb(9);
                    } else if (state.getNearbyBomb() == 0) {
                        g.drawImage(blank, j * 50, i * 50, 50, 50, null);
                        game.getCell(j,i).setReveal(true);
                        System.out.println("here");
                        revealSurrounding(g,j,i);
                        temp.getCell(j, i).setNearbyBomb(10);

                    } else if (state.getNearbyBomb() == 1) {
                        g.drawImage(one, j * 50, i * 50, 50, 50, null);
                        temp.getCell(j, i).setNearbyBomb(11);
                        game.getCell(j,i).setReveal(true);
                    } else if (state.getNearbyBomb() == 2) {
                        g.drawImage(two, j * 50, i * 50, 50, 50, null);
                        temp.getCell(j, i).setNearbyBomb(12);
                        game.getCell(j,i).setReveal(true);
                    } else if (state.getNearbyBomb() == 3) {
                        g.drawImage(three, j * 50, i * 50, 50, 50, null);
                        temp.getCell(j, i).setNearbyBomb(13);
                        game.getCell(j,i).setReveal(true);
                    } else if (state.getNearbyBomb() == 4) {
                        g.drawImage(four, j * 50, i * 50, 50, 50, null);
                        temp.getCell(j, i).setNearbyBomb(14);
                        game.getCell(j,i).setReveal(true);
                    } else if (state.getNearbyBomb() == 5) {
                        g.drawImage(five, j * 50, i * 50, 50, 50, null);
                        temp.getCell(j, i).setNearbyBomb(15);
                        game.getCell(j,i).setReveal(true);
                    }
                }
            }
        }
        */
    }
    //Temp works so that it is initiated as a 2D array with -1's everywhere
    // If a cell is clicked then that cell in temp has a value + 10 of the number of
    // surrounding bombs
    // If a cell has a bomb then it has a value of 9 in temp
    //
    public void revealSurrounding(int row, int col) {
        if (row < 0 || row >= Minesweep.WIDTH || col < 0 || col >= Minesweep.HEIGHT) {
            return;
        }
        if (temp.getCell(row,col).getNearbyBomb() != -1) {
            //System.out.println("revealed?" + temp.getCell(row,col).getNearbyBomb());
            return;
        }

        if (game.getCell(row,col).getNearbyBomb() > 0) {
            int n = game.getCell(row,col).getNearbyBomb();
            temp.getCell(row,col).setNearbyBomb(n + 10);
            //System.out.println("hit val " + game.getCell(row,col).getNearbyBomb());
            temp.print();
            return;
        }
        //g.drawLine(0,25,200,25);

        temp.getCell(row, col).setNearbyBomb(10);

        //System.out.println("0?" + temp.getCell(row,col).getNearbyBomb());
        if (game.getCell(row,col).getNearbyBomb() == 0) {
            revealSurrounding(row - 1, col - 1);
            revealSurrounding(row - 1, col);
            revealSurrounding(row - 1, col + 1);
            revealSurrounding(row, col - 1);
            revealSurrounding(row, col + 1);
            revealSurrounding(row + 1, col - 1);
            revealSurrounding(row + 1, col);
            revealSurrounding(row + 1, col + 1);
        }

    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    public void saveGameStateToFile(String fileName) {
        FileIO fileIO = new FileIO();
        fileIO.writeGameStateToFile(game, fileName);
    }

    public void loadGameStateFromFile(String fileName) {
        FileIO fileIO = new FileIO();
        Board loadedGameState = fileIO.readGameStateFromFile(fileName);
        if (loadedGameState != null) {
            game = loadedGameState;
        }
    }
}
