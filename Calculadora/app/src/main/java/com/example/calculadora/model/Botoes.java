package com.example.calculadora.model;

public class Botoes {



    private int btn = 0;
    private int resultado = 0;


    public void setBtn(int btn) {
        this.btn = btn;
    }


    public int btn(int btn){
        switch (btn){
            case 1 :
                resultado = 1;
                break;
            case 2 :
                resultado = 2;
                break;
            case 3 :
                resultado = 3;
                break;
            case 4 :
                resultado = 4;
                break;
            case 5 :
                resultado = 5;
                break;
            case 6 :
                resultado = 6;
                break;
            case 7 :
                resultado = 7;
                break;
            case 8 :
                resultado = 8;
                break;
            case 9 :
                resultado = 9;
                break;
            case 0 :
                resultado = 0;
                break;

        }
        return resultado;
    }

}
