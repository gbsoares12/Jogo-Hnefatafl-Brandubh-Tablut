/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Excecoes.EstiloDeJogoNull;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.Campo;
import model.DefensorNormal;
import model.Lutador;
import model.MercenarioNormal;
import model.MercenarioTablut;
import model.Peca;
import model.RefugioInicialRei;
import model.RefugioNormal;
import model.ReiNormal;
import model.Time;

/**
 *
 * @author 42519630833
 */
public class ControleJogoImpl implements ControleJogo {

    private Peca[][] tabuleiro;

    private int linhaAnterior;
    private int colunaAnterior;

    private int tipoMovimentacao;
    private int tipoEspecialidadeMovimentoRei;

    private Peca pecaAtual;

    private Lutador lutadorAnterior = null;
    private Time timeMercenario;
    private Time timeDefensor;
    private int rodada;
    private boolean fazerMovimentacao = false;
    private int tabuleiroAtual;

    private int colunaAtual;
    private int linhaAtual;
    private final Invoker inv = new Invoker(); //Padrão Command 

    private MovimentoLutador movimentoLutador;

    private List<Observador> observadores = new ArrayList<>();

    private static ControleJogoImpl instance;//Padrão Singleton

    public synchronized static ControleJogoImpl getInstance() {//Padrão Singleton
        if (instance == null) {
            instance = new ControleJogoImpl();
        }
        return instance;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    @Override
    public void addObservador(Observador obs) {
        observadores.add(obs);
    }

    public Peca[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getTabuleiroAtual() {
        return tabuleiroAtual;
    }

    @Override
    public void inicializar(int tipoTabuleiro, String defensor, String mercenario, int tipoEspecialidadeMovimento, int tipoEspecialidadeMovimentoRei) throws EstiloDeJogoNull {
        FabricaNormal fn = new FabricaNormal();
        this.tipoMovimentacao = tipoEspecialidadeMovimento;
        this.tipoEspecialidadeMovimentoRei = tipoEspecialidadeMovimentoRei;

        timeDefensor = new Time(defensor);
        timeMercenario = new Time(mercenario);
        timeDefensor.montarTime("defensor");
        timeMercenario.montarTime("mercenario");
        tabuleiroAtual = tipoTabuleiro;

        switch (tipoTabuleiro) {
            case 1:
                //tabuleiro Hnefatafl
                Peca[][] tabuleiroHnefatafl;
                tabuleiroHnefatafl = new Peca[11][11];
                //Coluna 0
                tabuleiroHnefatafl[0][0] = new RefugioNormal();
                tabuleiroHnefatafl[1][0] = new Campo();
                tabuleiroHnefatafl[2][0] = new Campo();
                tabuleiroHnefatafl[3][0] = fn.criarMercenario();///Mercenario
                tabuleiroHnefatafl[4][0] = fn.criarMercenario();//Mercenario
                tabuleiroHnefatafl[5][0] = fn.criarMercenario();//Mercenario
                tabuleiroHnefatafl[6][0] = fn.criarMercenario();//Mercenario
                tabuleiroHnefatafl[7][0] = fn.criarMercenario();//Mercenario
                tabuleiroHnefatafl[8][0] = new Campo();
                tabuleiroHnefatafl[9][0] = new Campo();
                tabuleiroHnefatafl[10][0] = new RefugioNormal();
                //Coluna 1
                tabuleiroHnefatafl[0][1] = new Campo();
                tabuleiroHnefatafl[1][1] = new Campo();
                tabuleiroHnefatafl[2][1] = new Campo();
                tabuleiroHnefatafl[3][1] = new Campo();
                tabuleiroHnefatafl[4][1] = new Campo();
                tabuleiroHnefatafl[5][1] = fn.criarMercenario();//Mercenario
                tabuleiroHnefatafl[6][1] = new Campo();
                tabuleiroHnefatafl[7][1] = new Campo();
                tabuleiroHnefatafl[8][1] = new Campo();
                tabuleiroHnefatafl[9][1] = new Campo();
                tabuleiroHnefatafl[10][1] = new Campo();
                //Coluna 2
                tabuleiroHnefatafl[0][2] = new Campo();
                tabuleiroHnefatafl[1][2] = new Campo();
                tabuleiroHnefatafl[2][2] = new Campo();
                tabuleiroHnefatafl[3][2] = new Campo();
                tabuleiroHnefatafl[4][2] = new Campo();
                tabuleiroHnefatafl[5][2] = new Campo();
                tabuleiroHnefatafl[6][2] = new Campo();
                tabuleiroHnefatafl[7][2] = new Campo();
                tabuleiroHnefatafl[8][2] = new Campo();
                tabuleiroHnefatafl[9][2] = new Campo();
                tabuleiroHnefatafl[10][2] = new Campo();
                //Coluna 3
                tabuleiroHnefatafl[0][3] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[1][3] = new Campo();
                tabuleiroHnefatafl[2][3] = new Campo();
                tabuleiroHnefatafl[3][3] = new Campo();
                tabuleiroHnefatafl[4][3] = new Campo();
                tabuleiroHnefatafl[5][3] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[6][3] = new Campo();
                tabuleiroHnefatafl[7][3] = new Campo();
                tabuleiroHnefatafl[8][3] = new Campo();
                tabuleiroHnefatafl[9][3] = new Campo();
                tabuleiroHnefatafl[10][3] = fn
                        .criarMercenario();//Mercenario
                //Coluna 4
                tabuleiroHnefatafl[0][4] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[1][4] = new Campo();
                tabuleiroHnefatafl[2][4] = new Campo();
                tabuleiroHnefatafl[3][4] = new Campo();
                tabuleiroHnefatafl[4][4] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[5][4] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[6][4] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[7][4] = new Campo();
                tabuleiroHnefatafl[8][4] = new Campo();
                tabuleiroHnefatafl[9][4] = new Campo();
                tabuleiroHnefatafl[10][4] = fn
                        .criarMercenario();//Mercenario
                //Coluna 5
                tabuleiroHnefatafl[0][5] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[1][5] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[2][5] = new Campo();
                tabuleiroHnefatafl[3][5] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[4][5] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[5][5] = fn
                        .criarRei();//Rei
                tabuleiroHnefatafl[6][5] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[7][5] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[8][5] = new Campo();
                tabuleiroHnefatafl[9][5] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[10][5] = fn
                        .criarMercenario();//Mercenario
                //Coluna 6
                tabuleiroHnefatafl[0][6] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[1][6] = new Campo();
                tabuleiroHnefatafl[2][6] = new Campo();
                tabuleiroHnefatafl[3][6] = new Campo();
                tabuleiroHnefatafl[4][6] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[5][6] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[6][6] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[7][6] = new Campo();
                tabuleiroHnefatafl[8][6] = new Campo();
                tabuleiroHnefatafl[9][6] = new Campo();
                tabuleiroHnefatafl[10][6] = fn
                        .criarMercenario();//Mercenario
                //Coluna 7
                tabuleiroHnefatafl[0][7] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[1][7] = new Campo();
                tabuleiroHnefatafl[2][7] = new Campo();
                tabuleiroHnefatafl[3][7] = new Campo();
                tabuleiroHnefatafl[4][7] = new Campo();
                tabuleiroHnefatafl[5][7] = fn
                        .criarDefensor();//Defensor
                tabuleiroHnefatafl[6][7] = new Campo();
                tabuleiroHnefatafl[7][7] = new Campo();
                tabuleiroHnefatafl[8][7] = new Campo();
                tabuleiroHnefatafl[9][7] = new Campo();
                tabuleiroHnefatafl[10][7] = fn
                        .criarMercenario();//Mercenario
                //Coluna 8
                tabuleiroHnefatafl[0][8] = new Campo();
                tabuleiroHnefatafl[1][8] = new Campo();
                tabuleiroHnefatafl[2][8] = new Campo();
                tabuleiroHnefatafl[3][8] = new Campo();
                tabuleiroHnefatafl[4][8] = new Campo();
                tabuleiroHnefatafl[5][8] = new Campo();
                tabuleiroHnefatafl[6][8] = new Campo();
                tabuleiroHnefatafl[7][8] = new Campo();
                tabuleiroHnefatafl[8][8] = new Campo();
                tabuleiroHnefatafl[9][8] = new Campo();
                tabuleiroHnefatafl[10][8] = new Campo();
                //Coluna 9
                tabuleiroHnefatafl[0][9] = new Campo();
                tabuleiroHnefatafl[1][9] = new Campo();
                tabuleiroHnefatafl[2][9] = new Campo();
                tabuleiroHnefatafl[3][9] = new Campo();
                tabuleiroHnefatafl[4][9] = new Campo();
                tabuleiroHnefatafl[5][9] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[6][9] = new Campo();
                tabuleiroHnefatafl[7][9] = new Campo();
                tabuleiroHnefatafl[8][9] = new Campo();
                tabuleiroHnefatafl[9][9] = new Campo();
                tabuleiroHnefatafl[10][9] = new Campo();
                //Coluna 10
                tabuleiroHnefatafl[0][10] = new RefugioNormal();
                tabuleiroHnefatafl[1][10] = new Campo();
                tabuleiroHnefatafl[2][10] = new Campo();
                tabuleiroHnefatafl[3][10] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[4][10] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[5][10] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[6][10] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[7][10] = fn
                        .criarMercenario();//Mercenario
                tabuleiroHnefatafl[8][10] = new Campo();
                tabuleiroHnefatafl[9][10] = new Campo();
                tabuleiroHnefatafl[10][10] = new RefugioNormal();
                tabuleiro = tabuleiroHnefatafl;
                break;
            case 2:
                //Tabuleiro Brandubh
                Peca[][] tabuleiroBrandubh;
                tabuleiroBrandubh = new Peca[7][7];
                //Coluna 0
                tabuleiroBrandubh[0][0] = new RefugioNormal();
                tabuleiroBrandubh[1][0] = new Campo();
                tabuleiroBrandubh[2][0] = new Campo();
                tabuleiroBrandubh[3][0] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[4][0] = new Campo();
                tabuleiroBrandubh[5][0] = new Campo();
                tabuleiroBrandubh[6][0] = new RefugioNormal();
                //Coluna 1
                tabuleiroBrandubh[0][1] = new Campo();
                tabuleiroBrandubh[1][1] = new Campo();
                tabuleiroBrandubh[2][1] = new Campo();
                tabuleiroBrandubh[3][1] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[4][1] = new Campo();
                tabuleiroBrandubh[5][1] = new Campo();
                tabuleiroBrandubh[6][1] = new Campo();
                //Coluna 2
                tabuleiroBrandubh[0][2] = new Campo();
                tabuleiroBrandubh[1][2] = new Campo();
                tabuleiroBrandubh[2][2] = new Campo();
                tabuleiroBrandubh[3][2] = fn.criarDefensor();//Defensor
                tabuleiroBrandubh[4][2] = new Campo();
                tabuleiroBrandubh[5][2] = new Campo();
                tabuleiroBrandubh[6][2] = new Campo();
                //Coluna 3
                tabuleiroBrandubh[0][3] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[1][3] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[2][3] = fn.criarDefensor();//Defensor
                tabuleiroBrandubh[3][3] = fn.criarRei();//Rei 
                tabuleiroBrandubh[4][3] = fn.criarDefensor();//Defensor
                tabuleiroBrandubh[5][3] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[6][3] = fn.criarMercenario();//Mercenario
                //Coluna 4
                tabuleiroBrandubh[0][4] = new Campo();
                tabuleiroBrandubh[1][4] = new Campo();
                tabuleiroBrandubh[2][4] = new Campo();
                tabuleiroBrandubh[3][4] = fn.criarDefensor();//Defensor
                tabuleiroBrandubh[4][4] = new Campo();
                tabuleiroBrandubh[5][4] = new Campo();
                tabuleiroBrandubh[6][4] = new Campo();
                //Coluna 5
                tabuleiroBrandubh[0][5] = new Campo();
                tabuleiroBrandubh[1][5] = new Campo();
                tabuleiroBrandubh[2][5] = new Campo();
                tabuleiroBrandubh[3][5] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[4][5] = new Campo();
                tabuleiroBrandubh[5][5] = new Campo();
                tabuleiroBrandubh[6][5] = new Campo();
                //Coluna 6
                tabuleiroBrandubh[0][6] = new RefugioNormal();
                tabuleiroBrandubh[1][6] = new Campo();
                tabuleiroBrandubh[2][6] = new Campo();
                tabuleiroBrandubh[3][6] = fn.criarMercenario();//Mercenario
                tabuleiroBrandubh[4][6] = new Campo();
                tabuleiroBrandubh[5][6] = new Campo();
                tabuleiroBrandubh[6][6] = new RefugioNormal();
                tabuleiro = tabuleiroBrandubh;
                break;
            case 3:
                FabricaTablut ft = new FabricaTablut();
                // Tabuleiro Tablut
                Peca[][] tabuleiroTablut;
                tabuleiroTablut = new Peca[9][9];
                //Coluna 0
                tabuleiroTablut[0][0] = new Campo();
                tabuleiroTablut[1][0] = new Campo();
                tabuleiroTablut[2][0] = new Campo();
                tabuleiroTablut[3][0] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[4][0] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[5][0] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[6][0] = new Campo();
                tabuleiroTablut[7][0] = new Campo();
                tabuleiroTablut[8][0] = new Campo();
                //Coluna 1
                tabuleiroTablut[0][1] = new Campo();
                tabuleiroTablut[1][1] = new Campo();
                tabuleiroTablut[2][1] = new Campo();
                tabuleiroTablut[3][1] = new Campo();
                tabuleiroTablut[4][1] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[5][1] = new Campo();
                tabuleiroTablut[6][1] = new Campo();
                tabuleiroTablut[7][1] = new Campo();
                tabuleiroTablut[8][1] = new Campo();
                //Coluna 2
                tabuleiroTablut[0][2] = new Campo();
                tabuleiroTablut[1][2] = new Campo();
                tabuleiroTablut[2][2] = new Campo();
                tabuleiroTablut[3][2] = new Campo();
                tabuleiroTablut[4][2] = ft.criarDefensor();//Defensor
                tabuleiroTablut[5][2] = new Campo();
                tabuleiroTablut[6][2] = new Campo();
                tabuleiroTablut[7][2] = new Campo();
                tabuleiroTablut[8][2] = new Campo();
                //Coluna 3
                tabuleiroTablut[0][3] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[1][3] = new Campo();
                tabuleiroTablut[2][3] = new Campo();
                tabuleiroTablut[3][3] = new Campo();
                tabuleiroTablut[4][3] = ft.criarDefensor();//Defensor
                tabuleiroTablut[5][3] = new Campo();
                tabuleiroTablut[6][3] = new Campo();
                tabuleiroTablut[7][3] = new Campo();
                tabuleiroTablut[8][3] = ft.criarMercenario();//Mercenario
                //Coluna 4
                tabuleiroTablut[0][4] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[1][4] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[2][4] = ft.criarDefensor();//Defensor
                tabuleiroTablut[3][4] = ft.criarDefensor();//Defensor
                tabuleiroTablut[4][4] = ft.criarRei();//Rei
                tabuleiroTablut[5][4] = ft.criarDefensor();//Defensor
                tabuleiroTablut[6][4] = ft.criarDefensor();//Defensor
                tabuleiroTablut[7][4] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[8][4] = ft.criarMercenario();//Mercenario
                //Coluna 5
                tabuleiroTablut[0][5] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[1][5] = new Campo();
                tabuleiroTablut[2][5] = new Campo();
                tabuleiroTablut[3][5] = new Campo();
                tabuleiroTablut[4][5] = ft.criarDefensor();//Defensor
                tabuleiroTablut[5][5] = new Campo();
                tabuleiroTablut[6][5] = new Campo();
                tabuleiroTablut[7][5] = new Campo();
                tabuleiroTablut[8][5] = ft.criarMercenario();//Mercenario
                //Coluna 6
                tabuleiroTablut[0][6] = new Campo();
                tabuleiroTablut[1][6] = new Campo();
                tabuleiroTablut[2][6] = new Campo();
                tabuleiroTablut[3][6] = new Campo();
                tabuleiroTablut[4][6] = ft.criarDefensor();//Defensor
                tabuleiroTablut[5][6] = new Campo();
                tabuleiroTablut[6][6] = new Campo();
                tabuleiroTablut[7][6] = new Campo();
                tabuleiroTablut[8][6] = new Campo();
                //Coluna 7
                tabuleiroTablut[0][7] = new Campo();
                tabuleiroTablut[1][7] = new Campo();
                tabuleiroTablut[2][7] = new Campo();
                tabuleiroTablut[3][7] = new Campo();
                tabuleiroTablut[4][7] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[5][7] = new Campo();
                tabuleiroTablut[6][7] = new Campo();
                tabuleiroTablut[7][7] = new Campo();
                tabuleiroTablut[8][7] = new Campo();
                //Coluna 8
                tabuleiroTablut[0][8] = new Campo();
                tabuleiroTablut[1][8] = new Campo();
                tabuleiroTablut[2][8] = new Campo();
                tabuleiroTablut[3][8] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[4][8] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[5][8] = ft.criarMercenario();//Mercenario
                tabuleiroTablut[6][8] = new Campo();
                tabuleiroTablut[7][8] = new Campo();
                tabuleiroTablut[8][8] = new Campo();
                tabuleiro = tabuleiroTablut;
                break;
            default:
                throw new EstiloDeJogoNull("Insira uma opção válida");
        }
    }

    @Override
    public Icon getPeca(int col, int row) {
        return (tabuleiro[col][row] == null ? null : tabuleiro[col][row].getImagem());
    }

    @Override
    public void selecionarCasa(Integer linha, Integer coluna) throws Exception {

        Peca pecaSelecionada = this.tabuleiro[coluna][linha];
        String acaoJogador;
        linhaAtual = linha;
        colunaAtual = coluna;

        pecaAtual = pecaSelecionada;
        if (((pecaSelecionada.getClass() == ReiNormal.class || pecaSelecionada.getClass() == DefensorNormal.class) && (verificaRodada(rodada) == 0))) {
            acaoJogador = "SelecionarDefensor";
        } else if (pecaSelecionada.getClass() == MercenarioNormal.class && verificaRodada(rodada) == 1) {
            acaoJogador = "SelecionarMercenario";
        } else if (pecaSelecionada.getClass() == MercenarioTablut.class && verificaRodada(rodada) == 1) {
            acaoJogador = "SelecionarMercenarioTablut";
        } else if (timeDefensor.existeTerreno(pecaSelecionada) && ((verificaRodada(rodada) == 0))) {
            acaoJogador = "movimentoDefensor";
        } else if (timeMercenario.existeTerreno(pecaSelecionada) && (verificaRodada(rodada) == 1) && (tabuleiroAtual == 1 || tabuleiroAtual == 2)) {
            acaoJogador = "movimentoMercenario";
        } else if (timeMercenario.existeTerreno(pecaSelecionada) && (verificaRodada(rodada) == 1) && (tabuleiroAtual == 3)) {
            acaoJogador = "movimentoMercenarioTablut";
        } else {
            throw new Exception("Peça não corresponde ao jogador do momento");
        }

        switch (acaoJogador) {
            case "SelecionarDefensor": //Inicio validação seleção do defensor

                if (pecaSelecionada.getClass() == ReiNormal.class) {
                    ReiNormal rn = (ReiNormal) pecaSelecionada;

                    if ((linhaAnterior == linhaAtual && colunaAnterior == colunaAtual) && rn.isIsSelecionada()) {
                        lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/RefugioComRei.png"));
                        rn.setIsSelecionada(false);
                        lutadorAnterior = null;
                    } else {

                        if (rn.isIsSelecionada()) {//Troca imagem do Rei

                            rn.setImagem((Icon) new ImageIcon("imagens/Rei.png"));
                            rn.setIsSelecionada(false);
                            this.tabuleiro[coluna][linha] = rn;

                        } else {//Quando o rei for selecionado

                            if (lutadorAnterior != null && (lutadorAnterior.getClass() == ReiNormal.class || lutadorAnterior.getClass() == DefensorNormal.class)) {//Verifica se a ultima casa é != de null se não for setta a peca anterior como deselecionada e igual para imagem da mesma
                                if (lutadorAnterior.getClass() == DefensorNormal.class) {
                                    lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Defensor.png"));

                                } else {
                                    lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/RefugioComRei.png"));
                                }
                                lutadorAnterior.setIsSelecionada(false);
                                lutadorAnterior = null;
                            }
                            rn.setImagem((Icon) new ImageIcon("imagens/ReiSelecinado.png"));
                            rn.setIsSelecionada(true);
                            this.tabuleiro[coluna][linha] = rn;
                            lutadorAnterior = rn; //Segura o ultimo lutador que foi selecionada
                            linhaAnterior = linha;
                            colunaAnterior = coluna;

                        }
                    }

                } else if (verificaRodada(rodada) == 0) {//Quando o defensor for selecionado

                    DefensorNormal dn = (DefensorNormal) pecaSelecionada;

                    if (dn.isIsSelecionada()) {
                        dn.setImagem((Icon) new ImageIcon("imagens/Defensor.png"));
                        dn.setIsSelecionada(false);
                        this.tabuleiro[coluna][linha] = dn;
                        lutadorAnterior = null;

                    } else {//Troca imagem para selecionado

                        if ((lutadorAnterior != null) && (lutadorAnterior.getClass() == DefensorNormal.class || lutadorAnterior.getClass() == ReiNormal.class)) {//Verifica se a ultima casa é != de null se não for setta a peca anterior como deselecionada e igual para imagem da mesma
                            if (lutadorAnterior.getClass() == DefensorNormal.class) {
                                lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Defensor.png"));
                            } else {
                                if (tabuleiro[(tabuleiro.length - 1) / 2][(tabuleiro.length - 1) / 2].getClass() == ReiNormal.class) {
                                    lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/RefugioComRei.png"));
                                } else {
                                    lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Rei.png"));

                                }
                            }
                            lutadorAnterior.setIsSelecionada(false);
                            lutadorAnterior = null;
                        }

                        dn.setImagem((Icon) new ImageIcon("imagens/DefensorSelecionado.png"));
                        dn.setIsSelecionada(true);
                        this.tabuleiro[coluna][linha] = dn;
                        lutadorAnterior = dn; //Segura o ultimo lutador que foi selecionada
                        linhaAnterior = linha;
                        colunaAnterior = coluna;
                    }
                }

                break;

            case "SelecionarMercenario": //Inicio validação seleção do mercenário

                MercenarioNormal mn = (MercenarioNormal) pecaSelecionada;

                if (mn.isIsSelecionada()) {
                    mn.setImagem((Icon) new ImageIcon("imagens/Mercenario.png"));
                    mn.setIsSelecionada(false);
                    this.tabuleiro[coluna][linha] = mn;
                    lutadorAnterior = null;

                } else {//Troca imagem para selecionado

                    if (lutadorAnterior != null && lutadorAnterior.getClass() == MercenarioNormal.class) {
                        lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Mercenario.png"));
                        lutadorAnterior.setIsSelecionada(false);
                        lutadorAnterior = null;
                    }

                    mn.setImagem((Icon) new ImageIcon("imagens/MercenarioSelecionado.png"));
                    mn.setIsSelecionada(true);
                    this.tabuleiro[coluna][linha] = mn;
                    lutadorAnterior = mn; //Segura o ultimo lutador que foi selecionada
                    linhaAnterior = linha;
                    colunaAnterior = coluna;
                }

                break;
            case "SelecionarMercenarioTablut":
                MercenarioTablut mt = (MercenarioTablut) pecaSelecionada;

                if (mt.isIsSelecionada()) {
                    mt.setImagem((Icon) new ImageIcon("imagens/MercenarioRefugio.png"));
                    mt.setIsSelecionada(false);
                    this.tabuleiro[coluna][linha] = mt;
                    lutadorAnterior = null;

                } else {//Troca imagem para selecionado

                    if (lutadorAnterior != null && lutadorAnterior.getClass() == MercenarioTablut.class) {
                        if (fazerMovimentacao
                                || !((colunaAnterior == 3 && linhaAnterior == 0)
                                || (colunaAnterior == 4 && linhaAnterior == 0)
                                || (colunaAnterior == 5 && linhaAnterior == 0)
                                || (colunaAnterior == 4 && linhaAnterior == 1)
                                || (colunaAnterior == 0 && linhaAnterior == 3)
                                || (colunaAnterior == 0 && linhaAnterior == 4)
                                || (colunaAnterior == 0 && linhaAnterior == 5)
                                || (colunaAnterior == 1 && linhaAnterior == 4)
                                || (colunaAnterior == 4 && linhaAnterior == 7)
                                || (colunaAnterior == 3 && linhaAnterior == 8)
                                || (colunaAnterior == 4 && linhaAnterior == 8)
                                || (colunaAnterior == 5 && linhaAnterior == 8)
                                || (colunaAnterior == 8 && linhaAnterior == 3)
                                || (colunaAnterior == 8 && linhaAnterior == 4)
                                || (colunaAnterior == 8 && linhaAnterior == 5)
                                || (colunaAnterior == 7 && linhaAnterior == 4))) {
                            lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Mercenario.png"));
                            lutadorAnterior.setIsSelecionada(false);
                            lutadorAnterior = null;
                        } else {
                            lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/MercenarioRefugio.png"));
                            lutadorAnterior.setIsSelecionada(false);
                            lutadorAnterior = null;
                        }
                    }

                    mt.setImagem((Icon) new ImageIcon("imagens/MercenarioSelecionado.png"));
                    mt.setIsSelecionada(true);
                    this.tabuleiro[coluna][linha] = mt;
                    lutadorAnterior = mt; //Segura o ultimo lutador que foi selecionada
                    linhaAnterior = linha;
                    colunaAnterior = coluna;
                }
                break;

            case "movimentoDefensor":

                if (lutadorAnterior.getClass() == DefensorNormal.class) {
                    lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Defensor.png"));
                    movimentoLutador = new MovimentoDefensor(linha, coluna, lutadorAnterior, timeDefensor);

                } else {
                    lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Rei.png"));
                    movimentoLutador = new MovimentoRei(linha, coluna, lutadorAnterior, timeDefensor);

                }
                fazerMovimentacao = true;
                lutadorAnterior.setIsSelecionada(false);

                break;

            case "movimentoMercenario":

                lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Mercenario.png"));
                movimentoLutador = new MovimentoMercenario(linha, coluna, lutadorAnterior, timeMercenario);
                fazerMovimentacao = true;
                lutadorAnterior.setIsSelecionada(false);

                break;

            case "movimentoMercenarioTablut":

                lutadorAnterior.setImagem((Icon) new ImageIcon("imagens/Mercenario.png"));
                movimentoLutador = new MovimentoMercenario(linha, coluna, lutadorAnterior, timeMercenario);
                fazerMovimentacao = true;
                lutadorAnterior.setIsSelecionada(false);

                break;
        }
        notificarMudancaTabuleiro();
    }

    @Override
    public void run() {

        Thread t = new Thread() {

            @Override
            public void run() {

                try {

                    while (true) {

                        if (fazerMovimentacao) {

                            int posicaoReiLinha = (tabuleiro.length - 1) / 2;
                            int posicaoReiColuna = (tabuleiro.length - 1) / 2;

                            inv.addCommand(new RealizarMovimentoCommand(linhaAnterior, colunaAnterior, linhaAtual, colunaAtual, getInstance(), movimentoLutador, tabuleiro, pecaAtual, lutadorAnterior, tabuleiroAtual, posicaoReiLinha, posicaoReiColuna, tipoMovimentacao, tipoEspecialidadeMovimentoRei));
                            if (inv.execute()) {
                                if (lutadorAnterior.getClass() == ReiNormal.class) {
                                    verificaReiPodeFugir(colunaAtual, linhaAtual, posicaoReiColuna, posicaoReiLinha, tabuleiro, tabuleiroAtual);
                                }
                            }

                            if (tabuleiro[colunaAtual][linhaAtual] == lutadorAnterior) {//Verifica se houve a troca de posição.
                                rodada++;
                            }
                            lutadorAnterior = null;
                            fazerMovimentacao = false;

                        }

                        notificarMudancaTabuleiro();
                        Thread.sleep(200); // só para dar um tempinho
                    }
                } catch (Exception e) {

                    notificarFimJogo(e.getMessage());
                }
            }
        };
        t.start();

    }

    public boolean realizarMovimentacao(int linhaAnterior, int colunaAnterior, int linhaAtual, int colunaAtual, MovimentoLutador movimentoLutador, Peca[][] tabuleiro, Peca pecaAtual, Lutador lutadorAnterior, int tipoTabuleiro, int posicaoReiLinha, int posicaoReiColuna, int limiteDeCasas, int tipoEspecialidadeMovimentoRei) {

        if (validaCaminho(linhaAnterior, colunaAnterior, linhaAtual, colunaAtual, tabuleiro, limiteDeCasas, tipoEspecialidadeMovimentoRei)) {
            if (movimentoLutador.moverPeca(pecaAtual)) {
                for (int i = 0; i < tabuleiro.length; i++) {
                    for (int j = 0; j < tabuleiro.length; j++) {
                        if (pecaAtual == tabuleiro[i][j]) {
                            tabuleiro[i][j] = lutadorAnterior;//Lutador assume posição nova

                            //Particularidade do chão do Rei entre o RefugioRei, RefugioComRei e Campo
                            if ((lutadorAnterior.getClass() == ReiNormal.class) && (tabuleiro[posicaoReiColuna][posicaoReiLinha].getClass() == ReiNormal.class) && (linhaAnterior == posicaoReiLinha && colunaAnterior == posicaoReiColuna)) {
                                pecaAtual = new RefugioInicialRei();
                            } else {
                                pecaAtual = new Campo();
                            }

                            //Particularidade mercenario tablut
                            if ((lutadorAnterior.getClass() == MercenarioTablut.class)//Coloca o refugio ao sair da casa
                                    && ((colunaAnterior == 3 && linhaAnterior == 0)
                                    || (colunaAnterior == 4 && linhaAnterior == 0)
                                    || (colunaAnterior == 5 && linhaAnterior == 0)
                                    || (colunaAnterior == 4 && linhaAnterior == 1)
                                    || (colunaAnterior == 0 && linhaAnterior == 3)
                                    || (colunaAnterior == 0 && linhaAnterior == 4)
                                    || (colunaAnterior == 0 && linhaAnterior == 5)
                                    || (colunaAnterior == 1 && linhaAnterior == 4)
                                    || (colunaAnterior == 4 && linhaAnterior == 7)
                                    || (colunaAnterior == 3 && linhaAnterior == 8)
                                    || (colunaAnterior == 4 && linhaAnterior == 8)
                                    || (colunaAnterior == 5 && linhaAnterior == 8)
                                    || (colunaAnterior == 8 && linhaAnterior == 3)
                                    || (colunaAnterior == 8 && linhaAnterior == 4)
                                    || (colunaAnterior == 8 && linhaAnterior == 5)
                                    || (colunaAnterior == 7 && linhaAnterior == 4))) {
                                pecaAtual = new RefugioNormal(new ImageIcon("imagens/RefugioRei.png"));
                            } else if (lutadorAnterior.getClass() == MercenarioTablut.class) {
                                pecaAtual = new Campo();
                            }

                            tabuleiro[colunaAnterior][linhaAnterior] = pecaAtual; // Casa  assume posição anterior do lutador
                            matarPeca(linhaAnterior, colunaAnterior, linhaAtual, colunaAtual, tabuleiro);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }

    public void desfazerMovimentacao(int linhaAnterior, int colunaAnterior, int linhaAtual, int colunaAtual, Peca[][] tabuleiro, Peca pecaAtual, Lutador lutadorAnterior) {

        tabuleiro[colunaAnterior][linhaAnterior] = lutadorAnterior;
        tabuleiro[colunaAtual][linhaAtual] = pecaAtual;
        notificarMudancaTabuleiro();
    }

    private void notificarFimJogo(String msgErro) {
        for (Observador obs : observadores) {
            obs.fimDeJogo(msgErro);
        }
    }

    private void notificarResetouJogo() {
        for (Observador obs : observadores) {
            obs.resetouJogo();
        }

        this.rodada = 0;
    }

    private void notificarMudancaTabuleiro() {
        for (Observador obs : observadores) {
            obs.mudouTabuleiro();
        }

    }

    private void notificarReiRotaFuga() {
        for (Observador obs : observadores) {
            obs.reiRotaFuga();
        }
    }

    private void notificarReiEmPerigo() {
        for (Observador obs : observadores) {
            obs.reiEmPerigo();
        }
    }

    private void notificarReiCapturado() {
        for (Observador obs : observadores) {
            obs.reiCapturado();
        }
    }

//    private void notificarQuantidadeDefensoresMortos(int quantidade) {
//        for (Observador obs : observadores) {
//            obs.quantidadeDefensoresMortos(quantidade);
//        }
//    }
//
//    private void notificarQuantidadeMercenariosMortos(int quantidade) {
//        for (Observador obs : observadores) {
//            obs.quantidadeMercenariosMortos(quantidade);
//        }
//    }
    private void notificarReiFugiu() {
        for (Observador obs : observadores) {
            obs.reiFugiu();
        }
    }

    @Override
    public void resetarJogo() throws Exception {
        //METODO PARA RESETAR O JOGO
        notificarResetouJogo();
        observadores.remove(observadores.size() - 1);
    }

    public int verificaRodada(int rodada) {
        int verificador = rodada % 2;
        if (verificador == 0) {//Verifica se é par
            return 0;
        } else {//Se não é impar
            return 1;
        }
    }

    private int limitadorCasas;
    private int limitadorCasasRei;

    public boolean validaLimitadorCasaMovimento(int linhaLutadorAntigo, int colunaLutadorAntigo, int linhaNova, int colunaNova, Peca[][] tabuleiro, int limiteDeCasas, int tipoEspecialidadeMovimentoRei) {

        switch (tipoEspecialidadeMovimentoRei) {
            case 1:
                limitadorCasasRei = tipoEspecialidadeMovimentoRei;
                break;
            case 4:
                limitadorCasasRei = tipoEspecialidadeMovimentoRei;
                break;
            default:
                limitadorCasasRei = 99;
                break;
        }

        if (tabuleiro[colunaLutadorAntigo][linhaLutadorAntigo].getClass() == ReiNormal.class) {
            if (colunaLutadorAntigo == colunaNova) {
                if (limitadorCasasRei == 99 || (Math.abs(linhaLutadorAntigo - linhaNova) <= limitadorCasasRei)) {
                    return true;
                } else {
                    return false;
                }
            } else if (linhaLutadorAntigo == linhaNova) {
                if (limitadorCasasRei == 99 || (Math.abs(colunaLutadorAntigo - colunaNova) <= limitadorCasasRei)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (limiteDeCasas != 0) {
                if (colunaLutadorAntigo == colunaNova) {
                    if ((Math.abs(linhaLutadorAntigo - linhaNova) <= limiteDeCasas)) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (linhaLutadorAntigo == linhaNova) {
                    if ((Math.abs(colunaLutadorAntigo - colunaNova) <= limiteDeCasas)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    public boolean validaCaminho(int linhaLutadorAntigo, int colunaLutadorAntigo, int linhaNova, int colunaNova, Peca[][] tabuleiro, int limiteDeCasas, int tipoEspecialidadeMovimentoRei) {

        if (colunaLutadorAntigo == colunaNova) {//Andar somente na mesma coluna
            if (validaLimitadorCasaMovimento(linhaLutadorAntigo, colunaLutadorAntigo, linhaNova, colunaNova, tabuleiro, limiteDeCasas, tipoEspecialidadeMovimentoRei)) {
                if (linhaLutadorAntigo < linhaNova) {
                    for (int i = linhaLutadorAntigo + 1; i < linhaNova; i++) {
                        if (tabuleiro[colunaNova][i].getClass() == MercenarioNormal.class || tabuleiro[colunaNova][i].getClass() == DefensorNormal.class || tabuleiro[colunaNova][i].getClass() == ReiNormal.class || tabuleiro[colunaNova][i].getClass() == MercenarioTablut.class) {

                            return false;//Encontrou um obstaculo verticalmente para baixo
                        }
                    }
                } else {
                    for (int i = linhaLutadorAntigo - 1; i > linhaNova; i--) {
                        if (tabuleiro[colunaNova][i].getClass() == MercenarioNormal.class
                                || tabuleiro[colunaNova][i].getClass() == DefensorNormal.class
                                || tabuleiro[colunaNova][i].getClass() == ReiNormal.class
                                || tabuleiro[colunaNova][i].getClass() == MercenarioTablut.class) {

                            return false;//Encontrou um obstaculo verticalmente para cima
                        }
                    }
                }
                return true;
            }

        } else if (linhaLutadorAntigo == linhaNova) {//Andar somente na mesma linha
            if (validaLimitadorCasaMovimento(linhaLutadorAntigo, colunaLutadorAntigo, linhaNova, colunaNova, tabuleiro, limiteDeCasas, tipoEspecialidadeMovimentoRei)) {
                if (colunaLutadorAntigo < colunaNova) {
                    for (int i = colunaLutadorAntigo + 1; i < colunaNova; i++) {
                        if (tabuleiro[i][linhaNova].getClass() == MercenarioNormal.class
                                || tabuleiro[i][linhaNova].getClass() == DefensorNormal.class
                                || tabuleiro[i][linhaNova].getClass() == ReiNormal.class
                                || tabuleiro[i][linhaNova].getClass() == MercenarioTablut.class) {

                            return false;//Encontrou um obstaculo horizontal para direita
                        }
                    }
                } else {
                    for (int i = colunaLutadorAntigo - 1; i > colunaNova; i--) {
                        if (tabuleiro[i][linhaNova].getClass() == MercenarioNormal.class
                                || tabuleiro[i][linhaNova].getClass() == DefensorNormal.class
                                || tabuleiro[i][linhaNova].getClass() == ReiNormal.class
                                || tabuleiro[i][linhaNova].getClass() == MercenarioTablut.class) {

                            return false;//Encontrou um obstaculo horizontal para esquerda
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    public void verificaReiPodeFugir(int colunaNovaRei, int linhaNovaRei, int colunaInicialRei, int linhaInicialRei, Peca[][] tabuleiro, int tabuleiroAtual) {

        boolean reiEmRotaDeFuga;
        boolean verificaTodasDirecoes = false; //Quando o jogo é estilo Tablut, dispara a verificação nas quatro direções;

        String verificaSentidoCaminho = monitoraPosicaoRei(tabuleiroAtual, tabuleiro);
        boolean NaoEncontrouObstaculo = false;
        boolean notificaCaminhoLivre = false;
        boolean jaEncontrouCaminhoLivreTablut = false;

        Peca colunaPosteriorRei;
        Peca colunaAnteriorRei;
        Peca linhaPosteriorRei;
        Peca linhaAnteriorRei;

        if (tabuleiroAtual == 3) {
            verificaTodasDirecoes = true;
        }
        reiEmRotaDeFuga = !verificaSentidoCaminho.equalsIgnoreCase("");

        if (reiEmRotaDeFuga) {
            //O REI ESTÁ NA LINHA OU COLUNA DO REFUGIO
            if (verificaSentidoCaminho.equalsIgnoreCase("linha") || verificaTodasDirecoes) {

                if (colunaNovaRei != 0 && colunaNovaRei != tabuleiro.length - 1) {
                    colunaPosteriorRei = tabuleiro[colunaNovaRei + 1][linhaNovaRei];
                    colunaAnteriorRei = tabuleiro[colunaNovaRei - 1][linhaNovaRei];

                } else {//Não faz parte da validação pois nunca haverá refugio perto das extremidades
                    if (colunaNovaRei == 8) {

                        colunaAnteriorRei = tabuleiro[colunaNovaRei - 1][linhaNovaRei];
                        colunaPosteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei];
                    } else {
                        if (tabuleiro[colunaNovaRei][linhaNovaRei].getClass() == RefugioNormal.class) {//Rei ganha o jogo ao chegar no refugio
                            notificarReiFugiu();
                        }
                        colunaAnteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei];
                        colunaPosteriorRei = tabuleiro[colunaNovaRei + 1][linhaNovaRei];
                    }
                }

                for (int i = 1; i < colunaNovaRei; i++) {//Primeira validação
                    Peca ponteiroCorreLinha = tabuleiro[i][linhaNovaRei];

                    if (colunaPosteriorRei.getClass() == RefugioNormal.class || colunaAnteriorRei.getClass() == RefugioNormal.class) {
                        notificaCaminhoLivre = true;
                        NaoEncontrouObstaculo = true;
                        jaEncontrouCaminhoLivreTablut = true;
                        break;
                    }
                    if (colunaAnteriorRei.getClass() != Campo.class && colunaAnteriorRei.getClass() != RefugioInicialRei.class) {
                        notificaCaminhoLivre = false;
                        break;
                    }
                    if (tabuleiroAtual == 1 || tabuleiroAtual == 2) {//Particularidade tabuleiro 1 e 2
                        if (ponteiroCorreLinha.getClass() != Campo.class) {
                            //Encontrou um obstaculo horizontal para esquerda           
                            notificaCaminhoLivre = false;
                            NaoEncontrouObstaculo = false;
                            break;
                        } else {
                            notificaCaminhoLivre = true;
                            NaoEncontrouObstaculo = true;
                        }
                    } else {//Particularidade tablut
                        if (tabuleiro[i - 1][linhaNovaRei].getClass() == RefugioNormal.class) {
                            for (int j = i; j <= colunaNovaRei; j++) {
                                if (tabuleiro[j][linhaNovaRei].getClass() == Campo.class || tabuleiro[j][linhaNovaRei].getClass() == RefugioInicialRei.class) {
                                    if (tabuleiro[j + 1][linhaNovaRei].getClass() == ReiNormal.class) {
                                        notificaCaminhoLivre = true;
                                        jaEncontrouCaminhoLivreTablut = true;
                                        NaoEncontrouObstaculo = true;
                                        break;
                                    }
                                }
                                if (tabuleiro[j][linhaNovaRei].getClass() == MercenarioTablut.class || tabuleiro[j][linhaNovaRei].getClass() == DefensorNormal.class) {
                                    notificaCaminhoLivre = false;
                                    jaEncontrouCaminhoLivreTablut = false;
                                    NaoEncontrouObstaculo = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!NaoEncontrouObstaculo) {//Segunda Validação
                    for (int i = tabuleiro.length - 2; i > colunaNovaRei; i--) {
                        if (colunaPosteriorRei.getClass() == RefugioNormal.class || colunaAnteriorRei.getClass() == RefugioNormal.class) {
                            notificaCaminhoLivre = true;
                            jaEncontrouCaminhoLivreTablut = true;
                            break;
                        }
                        if (colunaPosteriorRei.getClass() != Campo.class && colunaPosteriorRei.getClass() != RefugioInicialRei.class) {
                            notificaCaminhoLivre = false;
                            break;
                        }
                        if (tabuleiroAtual == 1 || tabuleiroAtual == 2) {//Particularidade Tabuleiro 1 e 2
                            if (tabuleiro[i][linhaNovaRei].getClass() != Campo.class) {
                                notificaCaminhoLivre = false;
                                break;

                            } else {
                                //Encontrou um obstaculo horizontal para direita
                                notificaCaminhoLivre = true;
                            }
                        } else {//Particularidade tablut
                            if (tabuleiro[i + 1][linhaNovaRei].getClass() == RefugioNormal.class) {
                                for (int j = i + 1; j >= colunaNovaRei; j--) {
                                    if (tabuleiro[j][linhaNovaRei].getClass() == Campo.class || tabuleiro[j][linhaNovaRei].getClass() == RefugioInicialRei.class) {
                                        if (tabuleiro[j - 1][linhaNovaRei].getClass() == ReiNormal.class) {
                                            notificaCaminhoLivre = true;
                                            jaEncontrouCaminhoLivreTablut = true;
                                            NaoEncontrouObstaculo = true;
                                            break;
                                        }
                                    }
                                    if (tabuleiro[j][linhaNovaRei].getClass() == MercenarioTablut.class || tabuleiro[j][linhaNovaRei].getClass() == DefensorNormal.class) {
                                        notificaCaminhoLivre = false;
                                        jaEncontrouCaminhoLivreTablut = false;
                                        NaoEncontrouObstaculo = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if ((verificaSentidoCaminho.equalsIgnoreCase("coluna") || verificaTodasDirecoes) && !jaEncontrouCaminhoLivreTablut) {
                if (linhaNovaRei != tabuleiro.length - 1 && linhaNovaRei != 0) {
                    linhaPosteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei + 1];
                    linhaAnteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei - 1];

                } else {//Não faz parte da validação pois nunca haverá refugio perto das extremidades
                    if (linhaNovaRei == 8) {
                        linhaPosteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei];
                        linhaAnteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei - 1];
                    } else {
                        if (tabuleiro[colunaNovaRei][linhaNovaRei].getClass() == RefugioNormal.class) {//Rei ganha o jogo ao chegar no refugio
                            notificarReiFugiu();
                        }
                        linhaPosteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei + 1];
                        linhaAnteriorRei = tabuleiro[colunaNovaRei][linhaNovaRei];
                    }
                }
                for (int i = 1; i < linhaNovaRei; i++) {//Terceira Validação
                    if (linhaPosteriorRei.getClass() == RefugioNormal.class || linhaAnteriorRei.getClass() == RefugioNormal.class) {
                        notificaCaminhoLivre = true;
                        NaoEncontrouObstaculo = true;
                        break;
                    }
                    if (linhaAnteriorRei.getClass() != Campo.class && linhaAnteriorRei.getClass() != RefugioInicialRei.class) {
                        notificaCaminhoLivre = false;
                        break;
                    }
                    if (tabuleiroAtual == 1 || tabuleiroAtual == 2) {//Particularidade tabuleiro 1 e 2
                        if (tabuleiro[colunaNovaRei][i].getClass() != Campo.class) {
                            //Encontrou um obstaculo vertical de cima para baixo
                            NaoEncontrouObstaculo = false;
                            notificaCaminhoLivre = false;
                            break;
                        } else {
                            notificaCaminhoLivre = true;
                            NaoEncontrouObstaculo = true;
                        }
                    } else {//Particularidade tablut
                        if (tabuleiro[colunaNovaRei][i - 1].getClass() == RefugioNormal.class) {
                            for (int j = i; j <= linhaNovaRei; j++) {
                                if (tabuleiro[colunaNovaRei][j].getClass() == Campo.class || tabuleiro[colunaNovaRei][j].getClass() == RefugioInicialRei.class) {
                                    if (tabuleiro[colunaNovaRei][j + 1].getClass() == ReiNormal.class) {
                                        notificaCaminhoLivre = true;
                                        NaoEncontrouObstaculo = true;
                                        break;
                                    }
                                }
                                if (tabuleiro[colunaNovaRei][j].getClass() == MercenarioTablut.class || tabuleiro[colunaNovaRei][j].getClass() == DefensorNormal.class) {
                                    notificaCaminhoLivre = false;
                                    NaoEncontrouObstaculo = false;
                                    break;
                                }
                            }
                        }
                    }

                }
                if (!NaoEncontrouObstaculo) {
                    for (int i = tabuleiro.length - 2; i > linhaNovaRei; i--) {//Quarta validação
                        if (linhaAnteriorRei.getClass() == RefugioNormal.class || linhaPosteriorRei.getClass() == RefugioNormal.class) {
                            notificaCaminhoLivre = true;
                            break;
                        }
                        if (linhaPosteriorRei.getClass() != Campo.class && linhaPosteriorRei.getClass() != RefugioInicialRei.class) {
                            notificaCaminhoLivre = false;
                            break;
                        }
                        if (tabuleiroAtual == 1 || tabuleiroAtual == 2) {//Particularidade tabuleiro 1 e 2
                            if (tabuleiro[colunaNovaRei][i].getClass() != Campo.class) {
                                //Encontrou um obstaculo vertical para cima
                                notificaCaminhoLivre = false;
                                break;
                            } else {
                                notificaCaminhoLivre = true;
                            }
                        } else {//Particularidade tablut
                            if (tabuleiro[colunaNovaRei][i + 1].getClass() == RefugioNormal.class) {
                                for (int j = i + 1; j >= linhaNovaRei; j--) {
                                    if (tabuleiro[colunaNovaRei][j].getClass() == Campo.class || tabuleiro[colunaNovaRei][j].getClass() == RefugioInicialRei.class) {
                                        if (tabuleiro[colunaNovaRei][j - 1].getClass() == ReiNormal.class) {
                                            notificaCaminhoLivre = true;
                                            break;
                                        }
                                    }
                                    if (tabuleiro[colunaNovaRei][j].getClass() == MercenarioTablut.class || tabuleiro[colunaNovaRei][j].getClass() == DefensorNormal.class) {
                                        notificaCaminhoLivre = false;
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }
            }
            if (notificaCaminhoLivre) {
                notificarReiRotaFuga();
                reiEmRotaDeFuga = false;
            }
        }
    }

    public String monitoraPosicaoRei(int tipoTabuleiro, Peca[][] tabuleiro) {

        String verificaSentidoCaminho = "";
        if (tipoTabuleiro == 1 || tipoTabuleiro == 2) {
            for (int i = 0; i < tabuleiro.length; i++) {
                if (tabuleiro[i][0].getClass() == ReiNormal.class) {
                    return verificaSentidoCaminho = "linha";

                } else if (tabuleiro[i][tabuleiro.length - 1].getClass() == ReiNormal.class) {
                    return verificaSentidoCaminho = "linha";
                }
            }
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[0][j].getClass() == ReiNormal.class) {
                    return verificaSentidoCaminho = "coluna";

                } else if (tabuleiro[tabuleiro.length - 1][j].getClass() == ReiNormal.class) {
                    return verificaSentidoCaminho = "coluna";

                }
            }
        } else {
            return verificaSentidoCaminho = "verificaTodosSentidos";
        }

        return verificaSentidoCaminho;
    }

    @Override
    public void voltarRodada() throws Exception {
        inv.undo();
        notificarMudancaTabuleiro();
        --this.rodada;
    }

    public void matarPeca(int linhaLutadorAntigo, int colunaLutadorAntigo, int linhaNova, int colunaNova, Peca[][] tabuleiro) {

        Peca pecaAtualJogada = tabuleiro[colunaNova][linhaNova];
        Peca pecaDireita = null;
        Peca peca2Direta = null;
        Peca pecaEsquerda = null;
        Peca peca2Esquerda = null;
        Peca pecaCima = null;
        Peca peca2Cima = null;
        Peca pecaAbaixo = null;
        Peca peca2Abaixo = null;
        boolean validaLimiteDireita = false;
        boolean validaLimiteEsquerda = false;
        boolean validaLimiteCima = false;
        boolean validaLimiteBaixo = false;
        boolean validaLimite2Direita = false;
        boolean validaLimite2Esquerda = false;
        boolean validaLimite2Cima = false;
        boolean validaLimite2Baixo = false;

        //Testa cada direção das casas adjacentes da nova posição do lutador atual
        if (linhaNova < (tabuleiro.length - 1)) {
            pecaAbaixo = tabuleiro[colunaNova][linhaNova + 1];
            validaLimiteBaixo = true;
        }
        if (linhaNova < (tabuleiro.length - 2)) {
            peca2Abaixo = tabuleiro[colunaNova][linhaNova + 2];
            validaLimite2Baixo = true;
        }
        if (linhaNova >= 1) {
            pecaCima = tabuleiro[colunaNova][linhaNova - 1];
            validaLimiteCima = true;
        }
        if (linhaNova >= 2) {
            peca2Cima = tabuleiro[colunaNova][linhaNova - 2];
            validaLimite2Cima = true;
        }
        if (colunaNova < (tabuleiro.length - 1)) {
            pecaDireita = tabuleiro[colunaNova + 1][linhaNova];
            validaLimiteDireita = true;
        }
        if (colunaNova < (tabuleiro.length - 2)) {
            peca2Direta = tabuleiro[colunaNova + 2][linhaNova];
            validaLimite2Direita = true;
        }
        if (colunaNova >= 1) {
            pecaEsquerda = tabuleiro[colunaNova - 1][linhaNova];
            validaLimiteEsquerda = true;
        }
        if (colunaNova >= 2) {
            peca2Esquerda = tabuleiro[colunaNova - 2][linhaNova];
            validaLimite2Esquerda = true;
        }

        /*Caso for um do time de defensores que está jogando no momento*/
        if (pecaAtualJogada.getClass() == DefensorNormal.class || pecaAtualJogada.getClass() == ReiNormal.class) {
            /*
                                    ** Valida se pode matar para direita **
             */
            if (validaLimite2Direita && ((pecaDireita.getClass() == MercenarioNormal.class || pecaDireita.getClass() == MercenarioTablut.class)
                    && (peca2Direta.getClass() == DefensorNormal.class
                    || peca2Direta.getClass() == RefugioNormal.class
                    || peca2Direta.getClass() == RefugioInicialRei.class
                    || peca2Direta.getClass() == ReiNormal.class))) {

                tabuleiro[colunaNova + 1][linhaNova] = new Campo();
                notificarMudancaTabuleiro();

                /*
                    AQUI EU IRIA CHAMAR O METODO MORRER DA CLASSE LUTADOR, porem primeiro iria saber se é um lutador!
                
                 */
            }
            /*
                                    ** Valida se pode matar para esquerda **
             */
            if (validaLimite2Esquerda && ((pecaEsquerda.getClass() == MercenarioNormal.class || pecaEsquerda.getClass() == MercenarioTablut.class)
                    && (peca2Esquerda.getClass() == DefensorNormal.class
                    || peca2Esquerda.getClass() == RefugioNormal.class
                    || peca2Esquerda.getClass() == RefugioInicialRei.class
                    || peca2Esquerda.getClass() == ReiNormal.class))) {

                tabuleiro[colunaNova - 1][linhaNova] = new Campo();
                notificarMudancaTabuleiro();
            }
            /*
                                    ** Valida se pode matar para cima **
             */
            if (validaLimite2Cima && ((pecaCima.getClass() == MercenarioNormal.class || pecaCima.getClass() == MercenarioTablut.class)
                    && (peca2Cima.getClass() == DefensorNormal.class
                    || peca2Cima.getClass() == RefugioNormal.class
                    || peca2Cima.getClass() == RefugioInicialRei.class
                    || peca2Cima.getClass() == ReiNormal.class))) {

                tabuleiro[colunaNova][linhaNova - 1] = new Campo();
                notificarMudancaTabuleiro();
            }
            /*
                                    ** Valida se pode matar para baixo **
             */
            if (validaLimite2Baixo && ((pecaAbaixo.getClass() == MercenarioNormal.class || pecaAbaixo.getClass() == MercenarioTablut.class)
                    && (peca2Abaixo.getClass() == DefensorNormal.class
                    || peca2Abaixo.getClass() == RefugioNormal.class
                    || peca2Abaixo.getClass() == RefugioInicialRei.class
                    || peca2Abaixo.getClass() == ReiNormal.class))) {

                tabuleiro[colunaNova][linhaNova + 1] = new Campo();
                notificarMudancaTabuleiro();
            }
        }
        /*Caso for um do time de mercenarios que está jogando no momento*/
        if (pecaAtualJogada.getClass() == MercenarioNormal.class || pecaAtualJogada.getClass() == MercenarioTablut.class) {
            /*
                                    ** Valida se pode matar para direita **
             */
            if (validaLimite2Direita && (pecaDireita.getClass() == DefensorNormal.class
                    && (peca2Direta.getClass() == MercenarioNormal.class
                    || peca2Direta.getClass() == RefugioNormal.class
                    || peca2Direta.getClass() == RefugioInicialRei.class
                    || peca2Direta.getClass() == MercenarioTablut.class))) {

                tabuleiro[colunaNova + 1][linhaNova] = new Campo();
                notificarMudancaTabuleiro();
            }
            /*
                                    ** Valida se pode matar para esquerda **
             */
            if (validaLimite2Esquerda && ((pecaEsquerda.getClass() == DefensorNormal.class)
                    && (peca2Esquerda.getClass() == MercenarioNormal.class
                    || peca2Esquerda.getClass() == RefugioNormal.class
                    || peca2Esquerda.getClass() == RefugioInicialRei.class
                    || peca2Esquerda.getClass() == MercenarioTablut.class))) {

                tabuleiro[colunaNova - 1][linhaNova] = new Campo();
                notificarMudancaTabuleiro();
            }
            /*
                                    ** Valida se pode matar para cima **
             */
            if (validaLimite2Cima && ((pecaCima.getClass() == DefensorNormal.class)
                    && (peca2Cima.getClass() == MercenarioNormal.class
                    || peca2Cima.getClass() == RefugioNormal.class
                    || peca2Cima.getClass() == RefugioInicialRei.class
                    || peca2Cima.getClass() == MercenarioTablut.class))) {

                tabuleiro[colunaNova][linhaNova - 1] = new Campo();
                notificarMudancaTabuleiro();
            }
            /*
                                    ** Valida se pode matar para baixo **
             */
            if (validaLimite2Baixo && ((pecaAbaixo.getClass() == DefensorNormal.class)
                    && (peca2Abaixo.getClass() == MercenarioNormal.class
                    || peca2Abaixo.getClass() == RefugioNormal.class
                    || peca2Abaixo.getClass() == RefugioInicialRei.class
                    || peca2Abaixo.getClass() == MercenarioTablut.class))) {

                tabuleiro[colunaNova][linhaNova + 1] = new Campo();
                notificarMudancaTabuleiro();
            }
            /*
                                    ** Valida se pode matar o Rei para direita
             */
            if (validaLimiteDireita && (pecaDireita.getClass() == ReiNormal.class && validaAdjacentesRei(linhaNova, (colunaNova + 1), tabuleiro))) {
                tabuleiro[colunaNova + 1][linhaNova] = new Campo();
                notificarMudancaTabuleiro();
                notificarReiCapturado();
            }
            /*
                                    ** Valida se pode matar o Rei para esquerda
             */
            if (validaLimiteEsquerda && (pecaEsquerda.getClass() == ReiNormal.class && validaAdjacentesRei(linhaNova, (colunaNova - 1), tabuleiro))) {
                tabuleiro[colunaNova - 1][linhaNova] = new Campo();
                notificarMudancaTabuleiro();
                notificarReiCapturado();
            }
            /*
                                    ** Valida se pode matar o Rei para cima
             */
            if (validaLimiteCima && (pecaCima.getClass() == ReiNormal.class && validaAdjacentesRei((linhaNova - 1), colunaNova, tabuleiro))) {
                tabuleiro[colunaNova][linhaNova - 1] = new Campo();
                notificarMudancaTabuleiro();
                notificarReiCapturado();
            }
            /*
                                    ** Valida se pode matar o Rei para baixo
             */
            if (validaLimiteBaixo && (pecaAbaixo.getClass() == ReiNormal.class && validaAdjacentesRei((linhaNova + 1), colunaNova, tabuleiro))) {
                tabuleiro[colunaNova][linhaNova + 1] = new Campo();
                notificarMudancaTabuleiro();
                notificarReiCapturado();
            }
        }
    }

    public boolean validaAdjacentesRei(int linhaRei, int colunaRei, Peca[][] tabuleiro) {

        Peca pecaAcimaRei = null;
        Peca pecaAbaixoRei = null;
        Peca pecaEsquerdaRei = null;
        Peca pecaDireitaRei = null;
        boolean validaLimiteDireita = false;
        boolean validaLimiteEsquerda = false;
        boolean validaLimiteCima = false;
        boolean validaLimiteBaixo = false;
        int casaAdjacentesOcupadas = 0;
        boolean reiNaExtremidade = false;

        if (linhaRei > 0) {
            pecaAcimaRei = tabuleiro[colunaRei][linhaRei - 1];
            validaLimiteCima = true;
        }
        if (linhaRei < (tabuleiro.length - 1)) {
            pecaAbaixoRei = tabuleiro[colunaRei][linhaRei + 1];
            validaLimiteBaixo = true;
        }
        if (colunaRei > 0) {
            pecaEsquerdaRei = tabuleiro[colunaRei - 1][linhaRei];
            validaLimiteEsquerda = true;
        }
        if (colunaRei < (tabuleiro.length - 1)) {
            pecaDireitaRei = tabuleiro[colunaRei + 1][linhaRei];
            validaLimiteDireita = true;
        }
        if (validaLimiteCima
                && (pecaAcimaRei.getClass() == MercenarioNormal.class
                || pecaAcimaRei.getClass() == MercenarioTablut.class
                || pecaAcimaRei.getClass() == RefugioInicialRei.class
                || pecaAcimaRei.getClass() == RefugioNormal.class)) {
            casaAdjacentesOcupadas++;
        }
        if (validaLimiteBaixo
                && (pecaAbaixoRei.getClass() == MercenarioNormal.class
                || pecaAbaixoRei.getClass() == MercenarioTablut.class
                || pecaAbaixoRei.getClass() == RefugioInicialRei.class
                || pecaAbaixoRei.getClass() == RefugioNormal.class)) {
            casaAdjacentesOcupadas++;
        }
        if (validaLimiteEsquerda
                && (pecaEsquerdaRei.getClass() == MercenarioNormal.class
                || pecaEsquerdaRei.getClass() == MercenarioTablut.class
                || pecaEsquerdaRei.getClass() == RefugioInicialRei.class
                || pecaEsquerdaRei.getClass() == RefugioNormal.class)) {
            casaAdjacentesOcupadas++;
        }
        if (validaLimiteDireita
                && (pecaDireitaRei.getClass() == MercenarioNormal.class
                || pecaDireitaRei.getClass() == MercenarioTablut.class
                || pecaDireitaRei.getClass() == RefugioInicialRei.class
                || pecaDireitaRei.getClass() == RefugioNormal.class)) {
            casaAdjacentesOcupadas++;
        }
        if (linhaRei == (tabuleiro.length - 1) || colunaRei == (tabuleiro.length - 1) || linhaRei == 0 || colunaRei == 0) {
            reiNaExtremidade = true;
        }
        if (reiNaExtremidade && casaAdjacentesOcupadas > 2) {
            return true;
        } else if (casaAdjacentesOcupadas == 3 || (reiNaExtremidade && casaAdjacentesOcupadas > 1)) {
            notificarReiEmPerigo();
        }
        if (casaAdjacentesOcupadas > 3) {
            return true;
        }

        return false;
    }

//    @Override
//    public void acceptDefensoresMortos(ProcuraDefensor visitor) {
//        visitor.visit(getInstance());
//        ProcuraDefensor visitorComControle = visitor;
//        notificarQuantidadeDefensoresMortos(visitorComControle.getQuantidadeMortos());
//    }
//
//    @Override
//    public void acceptMercenariosMortos(ProcuraMercenario visitor) {
//
//        visitor.visit(getInstance());
//        ProcuraMercenario visitorComControle = visitor;
//        notificarQuantidadeMercenariosMortos(visitorComControle.getQuantidadeMortos());
//    }
}
