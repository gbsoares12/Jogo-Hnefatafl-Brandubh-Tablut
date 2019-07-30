/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.FabricaNormal;
import control.FabricaTablut;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 42519630833
 */
public class Time {

    private List<Peca> listaLutadores = new ArrayList<>();
    private List<Peca> listaTerrenos = new ArrayList<>();
    private String nome;

    public Time(String nome) {
        this.nome = nome;
    }

    public List<Peca> getListaTerrenos() {
        return listaTerrenos;
    }

    public void setListaTerrenos(List<Peca> listaTerrenos) {
        this.listaTerrenos = listaTerrenos;
    }

    public List<Peca> getlistaLutadores() {
        return listaLutadores;
    }

    public void setlistaLutadores(List<Peca> listaLutadores) {
        this.listaLutadores = listaLutadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean existeLutador(Lutador peca) throws Exception {

        for (Peca listaLuta : listaLutadores) {
            if (peca.getClass() == listaLuta.getClass()) {
                return true;
            }
        }
        throw new Exception("Lutador não corresponde ao time");
    }

    public boolean existeTerreno(Peca peca) throws Exception {
        for (Peca listaTerreno : listaTerrenos) {
            if (peca.getClass() == listaTerreno.getClass()) {
                return true;
            }
        }
        throw new Exception("Campo não corresponde ao time");
    }

    public Peca validaCasa(Peca peca) throws Exception {
        for (Peca listaTerreno : listaTerrenos) {
            if (peca.getClass() == listaTerreno.getClass()) {
                return peca;
            }
        }
        throw new Exception("Erro na validação do campo");
    }

    public void montarTime(String tipoTime) {
        if (tipoTime.equalsIgnoreCase("defensor")) {
            listaLutadores.add(new FabricaNormal()
                    .criarDefensor());
            listaLutadores.add(new FabricaNormal()
                    .criarRei());
            listaTerrenos.add(new Campo());
            listaTerrenos.add(new RefugioNormal());
            listaTerrenos.add(new RefugioInicialRei());

        } else if (tipoTime.equalsIgnoreCase("mercenario")) {
            listaLutadores.add(new FabricaNormal()
                    .criarMercenario());
            listaTerrenos.add(new Campo());
            //testar interferencia nos outros mapas
            listaLutadores.add(new FabricaTablut()
                    .criarMercenario());
            listaTerrenos.add(new RefugioNormal());
        }
    }

}
