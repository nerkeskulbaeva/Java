import javax.swing.*;
import java.util.ArrayList;

public class Main extends JFrame {
    public static void main(String[] args) {
        int[][] graf ={
                {0,3,2,5,999,999,999,999,999},
                {3,0,4,8,5,999,999,999,999},
                {2,4,0,7,4,5,999,999,999},
                {5,8,7,0,4,4,7,999,9},
                {999,5,999,4,0,999,6,10,999},
                {999,999,3,4,999,0,1,999,8},
                {999,999,999,7,6,1,0,3,2},
                {999,999,999,999,10,999,3,0,10},
                {999,999,999,9,999,8,2,10,0}
        };

        int much = 10; //кол-во хромосом
        int gens = 9; // длина хромосомы
        int mutation_chanse = 15; //шанс скрещивания
        int pokolen = 3; //кол-во поколений

        Gens gens1 = new Gens(graf, much, gens, mutation_chanse, pokolen);

        // новый массив строк - в который записывается все из метода demonstrate
        ArrayList arrayList = new ArrayList(gens1.demonstrate());

        // создание окна и вызов метода Solve с передачей в него массива с результатом
        solve win = new solve(arrayList);

        // задание видимости окна

        win.setVisible(true);
    }

}
