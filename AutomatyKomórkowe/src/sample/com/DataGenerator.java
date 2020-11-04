package sample.com;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DataGenerator {

    BufferedImage image;
    int [][] tabFilled;// = new int[100][100];

    int wiLength = 550;      //   -
    int hiLength = 300;      //    |

    String selectedRule;
    String selectedTypeOfArray;

    public DataGenerator() {
        tabFilled = new int[hiLength][wiLength];
    }       //[20 wierszy][80 - wierszy]
}
