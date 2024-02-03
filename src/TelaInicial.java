import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Jogo do Labirinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Carrega a imagem do ícone
        ImageIcon icone = new ImageIcon("src/imagens/TelaInicial.png");
        Image imagemIcone = icone.getImage();

        // Define o ícone da janela
        setIconImage(imagemIcone);

        setSize(900, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JPanel panelImagem = new JPanel();

        // Substitua "caminho/para/sua/imagem.jpg" pelo caminho real da sua imagem
        ImageIcon imagemIcon = new ImageIcon("src/imagens/TelaInicia2.png");

        // Obtém as dimensões da janela
        int larguraJanela = getWidth();
        int alturaJanela = getHeight();

        // Redimensiona a imagem para caber na janela
        Image imagemRedimensionada = imagemIcon.getImage().getScaledInstance(larguraJanela, alturaJanela, Image.SCALE_SMOOTH);
        ImageIcon imagemRedimensionadaIcon = new ImageIcon(imagemRedimensionada);

        JLabel labelImagem = new JLabel(imagemRedimensionadaIcon);
        panelImagem.add(labelImagem);
        add(panelImagem, BorderLayout.CENTER);

        JButton botaoIniciar = new JButton("INICIAR");
        botaoIniciar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoIniciar.setHorizontalAlignment(JButton.CENTER);

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                abrirTelaJogo();
            }
        });

        add(botaoIniciar, BorderLayout.SOUTH);
    }

    private void abrirTelaJogo() {
        SwingUtilities.invokeLater(() -> {
            JFrame frameJogo = new JFrame("Maze Game");
            frameJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MazeGame mazeGame = new MazeGame();
            frameJogo.getContentPane().add(mazeGame);
            frameJogo.setSize(900, 900);
            frameJogo.setLocationRelativeTo(null);

            // Define o ícone do JFrame usando a imagem do ícone da janela inicial
            ImageIcon icone = new ImageIcon("src/imagens/TelaInicial.png");
            Image imagemIcone = icone.getImage();
            frameJogo.setIconImage(imagemIcone);

            frameJogo.setVisible(true);
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}