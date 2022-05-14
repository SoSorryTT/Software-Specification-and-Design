import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    private Board board;
    private int boardSize = 10;
    private GridUI gridUI;
    private int mineCount = 10;
    private int TotalFlag = mineCount;
    private int RemainingFlag = TotalFlag;
    private JButton resetButton = new JButton("Reset");

    public Game() {
        board  = new Board(boardSize, mineCount);
        gridUI = new GridUI();
        add(gridUI);
        gridUI.add(resetButton);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void start() {
        setVisible(true);
    }

    class  GridUI extends JPanel {
        public static final int CELL_PIXEL_SIZE = 30;
        public static final int OFF_SET = 60;
        private Image imageCell, imageFlag, imageMine;

        public GridUI() {
            int cellSize = boardSize * CELL_PIXEL_SIZE;
            setPreferredSize(new Dimension(cellSize,cellSize + OFF_SET));
            imageCell = new ImageIcon("imgs/Cell.png").getImage();
            imageFlag = new ImageIcon("imgs/Flag.png").getImage();
            imageMine = new ImageIcon("imgs/Mine.png").getImage();

            resetButton.addActionListener((ActionListener) new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board = new Board(boardSize, mineCount);
                    RemainingFlag = TotalFlag;
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int row = (e.getY() - OFF_SET) / CELL_PIXEL_SIZE;
                    int col = e.getX() / CELL_PIXEL_SIZE;
                    Cell cell = board.getCell(row,col);
                    if (cell == null){
                        return;
                    }
                    if (cell.isCovered()) {
                        if(SwingUtilities.isRightMouseButton(e)){
                            if (!cell.isFlagged() && RemainingFlag > 0){
                                cell.setFlagged(!cell.isFlagged());
                            }else if (cell.isFlagged()){
                                cell.setFlagged(!cell.isFlagged());
                            }
                        } else if(SwingUtilities.isLeftMouseButton(e) && !cell.isFlagged()) {
                            board.uncover(row,col);
                            if(board.mineUncover()) {
                                JOptionPane.showMessageDialog(Game.this,"You Lose!", "You hit the mine!",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                            if (checkWin()) {
                                JOptionPane.showMessageDialog(Game.this,"You Win!", "Congratulations!",
                                        JOptionPane.OK_OPTION);
                            }
                        }
                    }
                    setData();
                    repaint();
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawString("Flag left: " + RemainingFlag, CELL_PIXEL_SIZE * boardSize-100, 30);
            for(int row=0; row < boardSize; row++) {
                for(int col=0; col < boardSize; col++) {
                    paintCell(g, row, col);
                }
            }
        }

        private boolean checkWin() {
            if (board.numUncover + 100 == mineCount) {
                return true;
            }
            return false;
        }

        public void setData(){
            RemainingFlag = TotalFlag - board.totalFlaged();
        }

        private void paintCell(Graphics g, int row, int col) {
            int x = col * CELL_PIXEL_SIZE;
            int y = (row * CELL_PIXEL_SIZE) + OFF_SET;
            Cell cell = board.getCell(row, col);

            if(cell.isCovered()){
                g.drawImage(imageCell, x,y,CELL_PIXEL_SIZE,CELL_PIXEL_SIZE,Color.black,null);

            } else {
                g.setColor(Color.black);
                g.fillRect(x,y,CELL_PIXEL_SIZE,CELL_PIXEL_SIZE);
                g.setColor(Color.gray);
                g.fillRect(x+1,y+1,CELL_PIXEL_SIZE+4,CELL_PIXEL_SIZE+4);
                if(cell.isMine()) {
                    g.drawImage(imageMine,x,y,CELL_PIXEL_SIZE,CELL_PIXEL_SIZE,null,null);
                } else if(cell.getAdjacentMines() > 0) {
                    g.setColor(Color.black);
                    if(cell.getAdjacentMines() == 2) {
                            g.setColor(Color.blue);
                    } else if(cell.getAdjacentMines() == 3) {
                        g.setColor(Color.red);
                    } else if(cell.getAdjacentMines() == 4) {
                        g.setColor(Color.green);
                    } else if(cell.getAdjacentMines() == 5) {
                        g.setColor(Color.white);
                    } else if(cell.getAdjacentMines() == 6) {
                        g.setColor(Color.pink);
                    } else if(cell.getAdjacentMines() == 7) {
                        g.setColor(Color.orange);
                    } else if(cell.getAdjacentMines() == 8) {
                        g.setColor(Color.yellow);
                    }
                    g.drawString(cell.getAdjacentMines() + "", x + (int)(CELL_PIXEL_SIZE*0.35),y + (int)(CELL_PIXEL_SIZE*0.55));
                }
            }

            if(cell.isFlagged()) {
                g.drawImage(imageFlag, x, y, CELL_PIXEL_SIZE, CELL_PIXEL_SIZE, null,null);
            }

        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
