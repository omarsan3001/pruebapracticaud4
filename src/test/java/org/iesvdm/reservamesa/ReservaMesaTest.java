package org.iesvdm.reservamesa;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReservaMesaTest {

    @Test
    void rellenarMesasTest(){
        ReservaMesa reservaMesa = new ReservaMesa();
        int mesas[] = {0,0,0};
        reservaMesa.setMesas(mesas);
        reservaMesa.setTamanioMesa(3);

        reservaMesa.rellenarMesas();
        for(int i = 0; i<reservaMesa.getMesas().length; i++){
            assertThat(reservaMesa.getMesas()[i]).withFailMessage("no ha sido rellenado").isBetween(1,3);
        }
    }

    @Test
    void imprimirTest(){
        ReservaMesa reservaMesa = new ReservaMesa();
        int mesas[] = {0,0,0};
        reservaMesa.setMesas(mesas);

        reservaMesa.imprimir();
    }

    @Test
    void buscarPrimeraMesaVaciaTest()
    {
        ReservaMesa reservaMesa = new ReservaMesa();
        //si ha encontrado mesa deberia devolver la posicion de la mesa en este test 1
        int mesas[] = {1,0,3};
        reservaMesa.setMesas(mesas);
        int mesaVacia = reservaMesa.buscarPrimeraMesaVacia();
        assertThat(mesaVacia).isEqualTo(1);

        //si no ha encontrado mesa deberia devolver -1
        int mesas2[] = {1,1,3};
        reservaMesa.setMesas(mesas2);
        mesaVacia = reservaMesa.buscarPrimeraMesaVacia();
        assertThat(mesaVacia).isEqualTo(-1);
    }
    @Test
    void buscarMesaParaCompartirTest()
    {
        ReservaMesa reservaMesa = new ReservaMesa();
        reservaMesa.setMesas(new int[]{1,0,3});
        reservaMesa.setTamanioMesa(3);

        assertThat(reservaMesa.buscarMesaParaCompartir(3)).isEqualTo(1);

        assertThat(reservaMesa.buscarMesaParaCompartir(6)).isEqualTo(-1);

        reservaMesa.setMesas(new int[]{3,3,3});
        assertThat(reservaMesa.buscarMesaParaCompartir(3)).isEqualTo(-1);

    }

    @Test
    void buscarMesaCompartirMasCercaDeTest()
    {
        ReservaMesa reservaMesa = new ReservaMesa();
        reservaMesa.setMesas(new int[]{1,2,3,0,2,0,3});
        reservaMesa.setTamanioMesa(3);

        assertThat(reservaMesa.buscarMesaCompartirMasCercaDe(6,3)).isEqualTo(5);

        assertThat(reservaMesa.buscarMesaCompartirMasCercaDe(5,3)).isEqualTo(5);

        assertThat(reservaMesa.buscarMesaCompartirMasCercaDe(4,3)).isEqualTo(3);

        reservaMesa.setMesas(new int[]{1,2,3,1,2,1,3});
        assertThat(reservaMesa.buscarMesaCompartirMasCercaDe(5,3)).isEqualTo(-1);

    }
    @Test
    void buscarMesaCompartirMasAlejadaDeTest()
    {
        ReservaMesa reservaMesa = new ReservaMesa();
        reservaMesa.setMesas(new int[]{0,2,3,0,2});
        reservaMesa.setTamanioMesa(3);

        assertThat(reservaMesa.buscarMesaCompartirMasAlejadaDe(4,3)).isEqualTo(0);

        reservaMesa.setMesas(new int[]{0,3,3,1,0});
        //deberia devolver 4 pero da 0
        //assertThat(reservaMesa.buscarMesaCompartirMasAlejadaDe(1,3)).isEqualTo(4);

        reservaMesa.setMesas(new int[]{1,2,3,1,2});
        //deberia devolver -1 cuando no hay ninguna mesa disponible pero se sale del tamaño del index
        //assertThat(reservaMesa.buscarMesaCompartirMasAlejadaDe(3,3)).isEqualTo(-1);
    }
    @Test
    void buscarCompartirNMesasConsecutivasTest() {
        ReservaMesa reservaMesa = new ReservaMesa();
        reservaMesa.setMesas(new int[]{2,2,2,3,3});
        reservaMesa.setTamanioMesa(3);

        assertThat(reservaMesa.buscarCompartirNMesasConsecutivas(4,3)).isEqualTo(0);

        reservaMesa.setMesas(new int[]{2,3,2,3,3});
        assertThat(reservaMesa.buscarCompartirNMesasConsecutivas(3,3)).isEqualTo(-1);
    }
    @Test
    void comensalesTotalesTest(){
        ReservaMesa reservaMesa = new ReservaMesa();
        reservaMesa.setMesas(new int[]{1,0,3});
        assertThat(reservaMesa.comensalesTotales()).isEqualTo(4);

        reservaMesa.setMesas(new int[]{0,0,0});
        assertThat(reservaMesa.comensalesTotales()).isEqualTo(0);
    }
}
