import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Player {
    private int row;
    private int col;
    private Image playerImage;

    public Player(int initialRow, int initialCol) {
        this.row = initialRow;
        this.col = initialCol;
        // Carregar a imagem do personagem
        playerImage = new ImageIcon("src/imagens/Scott.png").getImage();  // Substitua pelo caminho real da sua imagem
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int dRow, int dCol) {
        row += dRow;
        col += dCol;
    }

    public void resetPosition() {
        // Define a posição inicial do jogador, por exemplo, (0, 0)
        this.row = 0;
        this.col = 0;
    }

    public void draw(Graphics g, int cellSize) {
        // Desenhar a imagem do personagem
        g.drawImage(playerImage, col * cellSize, row * cellSize, cellSize, cellSize, null);
    }
}