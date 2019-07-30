package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import control.ControleJogo;
import control.ControleJogoImpl;
import control.Observador;
import control.ProcuraDefensor;
import control.ProcuraMercenario;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import model.DefineTabuleiroImpl;

public class Main extends JFrame implements Observador {

    private int tipoJogo;
    private String jogadorDefensor;
    private String jogadorMercenario;

    public int getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(int tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    private static final long serialVersionUID = 1L;

    class TabuleiroRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {

            setIcon((ImageIcon) value);
            return this;
        }

    }

    private ControleJogo controle;
    private JTable tabuleiro;
    private int tipoEspecialidadeMovimento = 0;
    private int tipoEspecialidadeMovimentoRei = 0;

    public Main(int tipoTabuleiro, String nomeDefensor, String nomeMercenario) throws Exception {
        if (JOptionPane.showInputDialog("Deseja especificar a quantidade de casas no movimento das peças? (s) (n)").equalsIgnoreCase("s")) {
            if (JOptionPane.showInputDialog("Deseja o movimento das peças como 1 casa? (s) (n)").equalsIgnoreCase("s")) {
                tipoEspecialidadeMovimento = 1;
            }

            if (JOptionPane.showInputDialog("Deseja especificar o movimento do rei? (s) (n)").equalsIgnoreCase("s")) {
                if (JOptionPane.showInputDialog("Deseja o movimento do rei com (1) ou (4) movimentos").equalsIgnoreCase("1")) {
                    tipoEspecialidadeMovimentoRei = 1;

                } else {
                    tipoEspecialidadeMovimentoRei = 4;
                }

            }
        }

        this.controle = ControleJogoImpl.getInstance();
        boolean continua = true;
        jogadorDefensor = nomeDefensor;
        jogadorMercenario = nomeMercenario;

        while (continua) {
            try {
                Insets in = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());

                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

                int width = d.width - (in.left + in.top);
                int height = d.height - (in.top + in.bottom);
                setSize(width, height);
                setLocation(in.left + 350, in.top + 50);
                this.tipoJogo = tipoTabuleiro;
                this.controle.inicializar(tipoTabuleiro, nomeDefensor, nomeMercenario, tipoEspecialidadeMovimento, tipoEspecialidadeMovimentoRei);

                this.controle.addObservador(this);

                setTitle("Jogos Hnefatafl");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                initComponents();
                pack();
                controle.run();
                continua = false;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
    }

    private void initComponents() throws Exception {
        // criar o tabuleiro e seus componentes
        tabuleiro = new JTable();
        tabuleiro.setModel(new DefineTabuleiroImpl().configuraTabuleiro(tipoJogo));//Padrão Strategy

        for (int x = 0; x < tabuleiro.getColumnModel().getColumnCount(); x++) {
            tabuleiro.getColumnModel().getColumn(x).setWidth(86);
            tabuleiro.getColumnModel().getColumn(x).setMinWidth(86);
            tabuleiro.getColumnModel().getColumn(x).setMaxWidth(86);
        }
        tabuleiro.setRowHeight(80);
        tabuleiro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabuleiro.setShowGrid(false);
        tabuleiro.setIntercellSpacing(new Dimension(0, 0));
        tabuleiro.setDefaultRenderer(Object.class, new TabuleiroRenderer());
        tabuleiro.setCellSelectionEnabled(true);

        tabuleiro.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent m) {
                int linha = tabuleiro.getSelectedRow();
                int coluna = tabuleiro.getSelectedColumn();

                try {
                    controle.selecionarCasa(linha, coluna);

                } catch (Exception ex) {
                    System.out.println("Msg de erro seleção: " + ex.getMessage());

                }
            }

            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {

            }

        });

        add(tabuleiro, CENTER);

        JPanel jp = new JPanel();
        JPanel jp2 = new JPanel();
        jp.setLayout(new BorderLayout());

        JLabel jlNomeDefensor = new JLabel("Defensor: " + jogadorDefensor);
        JLabel jlNomeMercenario = new JLabel("Mercenario: " + jogadorMercenario);
        // botao Resetar
        JPanel jpIniciar = new JPanel();
        JButton jbVoltarRodada = new JButton("Voltar Rodada");
        JButton jbResetar = new JButton("Resetar");

        JPanel jpEstatistica = new JPanel();
        JButton jbDefensoresMortos = new JButton("Defensores Mortos");
        JButton jbMercenariosMortos = new JButton("Mercenarios Mortos");

        //Configurações botão voltar rodada
        jbVoltarRodada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controle.voltarRodada();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
        });

        //Configurações botão resetar
        jbResetar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    controle.resetarJogo();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

//        //Configurações botão defensores mortos
//        jbDefensoresMortos.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                try {
//                    controle.acceptDefensoresMortos(new ProcuraDefensor());
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
//                }
//            }
//        });
//
//        //Configurações botão mercenarios mortos
//        jbMercenariosMortos.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                try {
//                    controle.acceptMercenariosMortos(new ProcuraMercenario());
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
//                }
//            }
//        });
        jpIniciar.add(jlNomeDefensor);
        jpIniciar.add(jbResetar);
        jpIniciar.add(jlNomeMercenario);
        jpIniciar.add(jbVoltarRodada);
        jpEstatistica.add(jbDefensoresMortos);
        jpEstatistica.add(jbMercenariosMortos);

        jp.add(jpIniciar, CENTER);
        add(jp, SOUTH);

    }

    //Métodos notificar
    @Override
    public void resetouJogo() {
        try {
            this.setVisible(false);

            TelaInicial novoJogo = new TelaInicial();
            novoJogo.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

    @Override
    public void mudouTabuleiro() {//Metodo notificar
        tabuleiro.repaint();
    }

    @Override
    public void fimDeJogo(String msgErro) {//Metodo notificar
        JOptionPane.showMessageDialog(null, msgErro);
        System.exit(0);
    }

    @Override
    public void reiRotaFuga() {
        JOptionPane.showMessageDialog(rootPane, "              Raichi!\nRei em rota de fuga!");
    }

    @Override
    public void reiEmPerigo() {
        JOptionPane.showMessageDialog(rootPane, "              Olha o Rei!");
    }

    @Override
    public void reiCapturado() {
        JOptionPane.showMessageDialog(rootPane, "Fim de jogo!\nRei capturado!\nMercenários venceram a batalha!");
        System.exit(0);
    }

    @Override
    public void reiFugiu() {
        JOptionPane.showMessageDialog(rootPane, "Fim de jogo!\nRei fugiu!\nDefensores venceram a batalha!");
        System.exit(0);
    }

    public void quantidadeDefensoresMortos(int quantidade) {
        JOptionPane.showMessageDialog(rootPane, quantidade + " Defensores mortos!");
    }

    public void quantidadeMercenariosMortos(int quantidade) {
        JOptionPane.showMessageDialog(rootPane, quantidade + " Mercenarios mortos!");
    }
}
