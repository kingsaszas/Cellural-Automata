package sample.com;

import java.util.ArrayList;
import java.util.List;

public class FunctionsNew {

        DataGenerator dg;

        public FunctionsNew(DataGenerator dg) {
            this.dg = dg;
        }

        //podzial modulo itp
        public int[] division() {

            int divided = Integer.parseInt(dg.selectedRule);
            int result;
            int modulo;
            int [] tab = {0,0,0,0,0,0,0,0};


            int i = 0;
            do {
                result = divided / 2;
                modulo = divided % 2;

                divided = result;
                tab[i] = modulo;
                i++;

            } while (divided != 0);

            for(int j = 0; j<tab.length;j++) {
                System.out.println(tab[j]);
            }
            return tab;
        }


        //uzupelniam pierwsza linie tablicy zerami i jedynką po środku
        public void firstLine(){

            for(int i = 0;i< dg.wiLength ;i++) {
                if(dg.wiLength/2 == i)
                    dg.tabFilled[0][i] = 1;
                else
                    dg.tabFilled[0][i] = 0;
            }
        }

        //wyswietlam w konsoli tablice
        public void showArrayWithNumbers() {
            for(int hi = 0; hi < dg.hiLength; hi ++) {
                for( int wi = 0; wi < dg.wiLength; wi ++) {
                    System.out.print(dg.tabFilled[hi][wi] + " ");
                }
                System.out.print("\n");
            }
        }


        public void fillArrayWithTheOneEdges() {
            //wypełnienie pierwszego rzedu
            firstLine();
            int [] arr = division();

            //reszta lini na poczatku i na koncu ma byc 1, uzupelniam pierwsza linie o te skrajne jedynki
            dg.tabFilled[0][0] = 1;
            dg.tabFilled[0][dg.wiLength -1] = 1;

            //uzupelniam reszte lini w tablicy
            for(int hi = 1; hi < dg.hiLength; hi ++) {
                for( int wi = 0; wi < dg.wiLength; wi ++) {
                    //skrajne wartosci, tutaj zawsze beda mialy wartosc 1
                    if( wi == 0 || wi == dg.wiLength - 1 ){
                        dg.tabFilled[hi][wi] = 1;
                    } else {
                     elementaryAutomaton(arr,hi,wi,hi-1,wi-1,wi+1);
                    }
                }
            }

            //showArrayWithNumbers();
        }

        public void fillPeriedicArray(){
            //wypełnienie pierwszego rzedu
            firstLine();
            int [] arr = division();

            for(int hi = 1; hi < dg.hiLength; hi ++) {
                for( int wi = 0; wi < dg.wiLength; wi ++) {
                    // pierwsza wartosc w tabeli
                    if( wi == 0 ){
                        elementaryAutomaton(arr, hi, wi, hi-1,dg.wiLength -1,wi+1);
                    }
                    // ostatnia wartosc w tabeli
                    else if(wi == dg.wiLength -1){
                        elementaryAutomaton(arr, hi, wi, hi-1,wi-1,0);
                    }
                    else {
                        elementaryAutomaton(arr, hi, wi, hi-1,wi-1,wi+1);
                    }
                }
            }

            //showArrayWithNumbers();
        }


        public void elementaryAutomaton(int[]tabModulo, int hi, int wi, int hprev, int wprev, int wnext) {

            if(dg.tabFilled[hprev][wprev] == 1 &&  dg.tabFilled[hprev][wnext]== 1) {
                if(dg.tabFilled[hprev][wi] == 1)
                    dg.tabFilled[hi][wi] = tabModulo[7];
                 else
                    dg.tabFilled[hi][wi] = tabModulo[5];

            } else if(dg.tabFilled[hprev][wprev] == 0 &&  dg.tabFilled[hprev][wnext]== 0) {
                if(dg.tabFilled[hprev][wi] == 0)
                    dg.tabFilled[hi][wi] = tabModulo[0];
                 else
                    dg.tabFilled[hi][wi] = tabModulo[2];

            } else if(dg.tabFilled[hprev][wprev] == 1 &&  dg.tabFilled[hprev][wnext]== 0) {
                if (dg.tabFilled[hprev][wi] == 0)
                    dg.tabFilled[hi][wi] = tabModulo[4];
                 else
                    dg.tabFilled[hi][wi] = tabModulo[6];

            } else if(dg.tabFilled[hprev][wprev] == 0 &&  dg.tabFilled[hprev][wnext]== 1) {
                if (dg.tabFilled[hprev][wi] == 0)
                    dg.tabFilled[hi][wi] = tabModulo[1];
                 else
                    dg.tabFilled[hi][wi] = tabModulo[3];
            }
        }
}


