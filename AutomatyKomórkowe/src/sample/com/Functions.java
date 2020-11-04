package sample.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {

    List<Integer> tab;

    DataGenerator dg;

    public Functions(DataGenerator dg) {
        this.dg = dg;
    }


    //podzial modulo itp
    public void division() {

        int divided = Integer.parseInt(dg.selectedRule);
        int div = divided;
        int result;
        int modulo;
        //int [] tabModulo = new int [];
        //ArrayList<Integer> tab = new ArrayList<>();
        tab = new ArrayList();
        tab.clear();

        do {
            result = divided / 2;
            modulo = divided % 2;

            divided = result;
            tab.add(modulo);

        } while (divided != 0);


        for(int i = 0; i<tab.size();i++) {
            System.out.println(tab.get(i));
        }

    }

    //uzupelniam pierwsza linie tablicy wynikiem z division
    public void firstLine(){
        int numberOfModulo = tab.size()-1;

        for(int i = 0;i< dg.wiLength ;i++) {
            dg.tabFilled[0][i] = tab.get(numberOfModulo);
            numberOfModulo--;
            if(numberOfModulo == -1){
                numberOfModulo = tab.size() - 1;
            }

        }
    }

    //wyswietlam w konsoli tablice
    public void showArrayWithNumbers() {
        for(int hi = 0; hi < dg.hiLength; hi ++) {
            for( int wi = 0; wi < dg.wiLength -1; wi ++) {
                System.out.print(dg.tabFilled[hi][wi] + " ");
            }
            System.out.print("\n");
        }
    }

    public void fillArrayWithTheOneEdges() {
        //wypełnienie pierwszego rzedu
        firstLine();

        //reszta lini na poczatku i na koncu ma byc 1, uzupelniam pierwsza linie o te skrajne jedynki
        dg.tabFilled[0][0] = 1;
        dg.tabFilled[0][dg.wiLength -1] = 1;

        //uzupelniam reszte lini w tablicy
        for(int hi = 1; hi < dg.hiLength; hi ++) {
            for( int wi = 0; wi < dg.wiLength -1; wi ++) {
                //skrajne wartosci, tutaj zawsze beda mialy wartosc 1
                if( wi == 0 || wi == dg.wiLength -1){                           //////
                    dg.tabFilled[hi][wi] = 1;
                }
                // a tutaj dla reszty juz normalnie biore dwie wartosci po bokach
                else {
                    if(dg.tabFilled[hi-1][wi-1] == dg.tabFilled[hi-1][wi+1]) {
                        dg.tabFilled[hi][wi] = 0;
                    } else {
                        dg.tabFilled[hi][wi] = 1;
                    }
                }
            }
        }

        showArrayWithNumbers();

    }

    public void fillPeriedicArray(){
        //wypełnienie pierwszego rzedu
        firstLine();

        for(int hi = 1; hi < dg.hiLength; hi ++) {
            for( int wi = 0; wi < dg.wiLength; wi ++) {
                // pierwsza wartosc w tabeli
                if( wi == 0 ){
                    if(dg.tabFilled[hi-1][dg.wiLength -1] == dg.tabFilled[hi-1][wi+1]) {
                        dg.tabFilled[hi][wi] = 0;
                    } else {
                        dg.tabFilled[hi][wi] = 1;
                    }
                }
                // ostatnia wartosc w tabeli
                else if(wi == dg.wiLength -1){
                    if(dg.tabFilled[hi-1][0] == dg.tabFilled[hi-1][wi-1]) {
                        dg.tabFilled[hi][wi] = 0;
                    } else {
                        dg.tabFilled[hi][wi] = 1;
                    }
                }
                // dla reszty
                else {
                    if(dg.tabFilled[hi-1][wi-1] == dg.tabFilled[hi-1][wi+1]) {
                        dg.tabFilled[hi][wi] = 0;
                    } else {
                        dg.tabFilled[hi][wi] = 1;
                    }
                }
            }
        }

        showArrayWithNumbers();

    }
}
