import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class MazeGame extends JPanel {
    private BufferedImage buffer;
    private Graphics bufferGraphics;

    private static final int CELL_SIZE = 40;
    private Maze maze;
    private Player player;
    private int currentLevel;

    public MazeGame() {

        maze = new Maze();
        player = new Player(0, 0);
        currentLevel = 1;  // Nível inicial
        setBackground(Color.WHITE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // Tecla SPACE para alternar entre os níveis
                    switchLevel();
                } else {
                    movePlayer(e.getKeyCode());
                }
                repaint();
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setFocusable(true);
        requestFocus();
    }

    private void movePlayer(int keyCode) {
        int dRow = 0, dCol = 0;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                dRow = -1;
                break;
            case KeyEvent.VK_DOWN:
                dRow = 1;
                break;
            case KeyEvent.VK_LEFT:
                dCol = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dCol = 1;
                break;
        }

        int newRow = player.getRow() + dRow;
        int newCol = player.getCol() + dCol;

        if (isValidMove(newRow, newCol)) {
            player.move(dRow, dCol);
        }

        checkGameStatus();
    }

    private boolean isValidMove(int row, int col) {
        if (row >= 0 && row < maze.getMazeData().length && col >= 0 && col < maze.getMazeData()[0].length) {
            return !maze.isWall(row, col);
        }
        return false;
    }

    private void checkGameStatus() {
        if (player.getRow() == maze.getMazeData().length - 1 && player.getCol() == maze.getMazeData()[0].length - 1) {
            JOptionPane.showMessageDialog(this, "Parabéns! Você Encontrou A Saída!");
            switchLevel();  // Avance para o próximo nível ao vencer
        }
    }

    private void switchLevel() {
        // Avançar para o próximo nível (1 -> 2 -> 3 -> 1 -> ...)
        currentLevel = (currentLevel % 3) + 1;

        switch (currentLevel) {
            case 1:
                maze.setLayoutLevel1();
                break;
            case 2:
                maze.setLayoutLevel2();
                break;
            case 3:
                maze.setLayoutLevel3();
                break;
        }

        player.resetPosition();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            bufferGraphics = buffer.getGraphics();
        }

        bufferGraphics.clearRect(0, 0, getWidth(), getHeight());  // Limpa o buffer
        maze.draw(bufferGraphics, CELL_SIZE);
        player.draw(bufferGraphics, CELL_SIZE);

        // Desenha o buffer na tela
        g.drawImage(buffer, 0, 0, this);
    }

    private void telaJogo() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("WOLF MAZE");
            MazeGame mazeGame = new MazeGame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mazeGame);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            // Adicione o key listener diretamente ao frame
            frame.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        // Tecla SPACE para alternar entre os níveis
                        mazeGame.switchLevel();
                    } else {
                        mazeGame.movePlayer(e.getKeyCode());
                    }
                    mazeGame.repaint();
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            ImageIcon icone = new ImageIcon("src/imagens/TelaInicial.png");
            Image imagemIcone = icone.getImage();

            // Define o ícone da janela
            frame.setIconImage(imagemIcone);

            frame.setFocusable(true);
            frame.requestFocus();

            frame.setVisible(true);
        });
    }
}