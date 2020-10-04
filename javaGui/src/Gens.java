import java.util.ArrayList;
import java.util.Random;

public class Gens {
    int much; //кол-во хромосом
    int gens; //кол-во генов + 1 для суммы + 1 из-за мaссива + 1 для процентов
    int mutation_chanse; //шанс скрещивания
    int pokolen; //кол-во поколений
    public StringBuilder mString;

    int[][] graf;
    int[][] genofond;


    public Gens(int[][] graf, int much, int gens, int mutation_chanse, int pokolen){
        this.much = much;
        this.gens = gens + 3;
        this.mutation_chanse=mutation_chanse;
        this.pokolen=pokolen;
        this.graf=graf;

        genofond = generator_hromosom(much, this.gens);
    }



    private int[][] generator_hromosom(int much, int gens){
        int[][]genofond;
        genofond=new int[much][gens];
        Random random = new Random();
        int rnd_min=gens-3;

        for(int i=0; i<much;i++){
            genofond[i][0]=0;
            genofond[i][gens-3]=gens-4;
        }

        for(int i=0; i<much;i++){
            for(int j=1; j<gens-3; j++){
                genofond[i][j]=random.nextInt(rnd_min - 1);
            }
        }

        return genofond;
    }

    private void summ(){
        int final_summa;
        for(int i=0; i<much; i++){
            final_summa=0;
            for(int j=0; j<gens-3; j++) {
                final_summa += graf[genofond[i][j]][genofond[i][j+1]];
            }
            genofond[i][gens-2]=final_summa;
        }
    }

    private void sort(){
        for(int n=0; n<much; n++){
            for(int i=0; i<much-1; i++){
                if(genofond[i][gens-2]>genofond[i+1][gens-2]){
                    for(int j=0; j<gens-1; j++){
                        int num = genofond[i][j];
                        genofond[i][j]=genofond[i+1][j];
                        genofond[i+1][j]=num;
                    }
                }
            }
        }
    }

    private void chance(){
        double base_chance=0.0;
        for(int i=0; i<much; i++){
            base_chance=(100-base_chance)/(2*(i+1));
            genofond[i][gens-1]= (int) base_chance;
        }
    }

    private void krossen(){
        Random random = new Random();
        int[][] genofond_new;
        genofond_new=new int[much][gens]; //создание нового массивва из старого
        int rnd_min=gens-3;

        int peace = (gens-3)/2; // длина

        for(int p = 0; p < much; p++){
            int random_first = random.nextInt(100 - 1);
            int i = 0;
            int base_chanse_first = genofond[i][gens - 1];

            int random_mutation_first=random.nextInt(100-1);

            if(random_mutation_first>mutation_chanse) {
                while (random_first > base_chanse_first) {
                    i++;
                    base_chanse_first += genofond[i + 1][gens - 1];
                }

                for (int k = 0; k < peace; k++) {
                    genofond_new[p][k] = genofond[i][k];
                }
            }else { // если нет, то увеличивает номер строки
                for (int k = 1; k < peace; k++) {
                    genofond_new[p][k] = random.nextInt(rnd_min - 1);
                }
                genofond_new[p][0]=0;
            }
        }

        for(int q=0; q<much; q++){
            int random_second = random.nextInt(100-1);
            int w=0;
            int base_chanse_second=genofond[w][gens-1];

            int random_mutation_second=random.nextInt(100-1);

            if(random_mutation_second>mutation_chanse) {
                while (random_second > base_chanse_second) {
                    w++;
                    base_chanse_second += genofond[w + 1][gens - 1];
                }

                for (int n = peace; n < gens - 2; n++) {
                    genofond_new[q][n] = genofond[w][n];
                }
            }else{
                for (int n = peace; n < gens - 3; n++) {
                    genofond_new[q][n] = random.nextInt(rnd_min - 1);
                }
                genofond_new[q][gens-3]=gens-4;
            }
        }

        genofond = genofond_new;

    }


// метод demonstrate() который теперь отдает в мейн ArrayList<String>
    public ArrayList<String> demonstrate(){

        // индекс для работы с ArrayList
        int index = 0;
        // создание ArrayList<String>
        ArrayList arrayList = new ArrayList();

        // создание строчки для записи всех элементов ихз цикла
        String mString;
        for(int o = 0; o < pokolen; o++){
            summ();
            sort();
            chance();

            // создание пустой строки
            mString = "";
            for(int i = 0; i < much; i++){
                for(int j = 0; j < gens; j++){
                    // добавление в строку параметров из цикла
                    mString += genofond[i][j];
                    mString += (" ");
                }
                mString += ("\n");

                // добавление строки в массив который отдается в мейн по завершении метода
                arrayList.add(index, mString);

                // увелечение индекса массива
                index += 1;

                // обнуление строки
                mString = "";
            }


            krossen();
            // добавление разделителей
            arrayList.add(index, "======================");
            index += 1;

            for(int i = 0; i < much; i++){
                mString = "";
                for(int j = 0; j < gens; j++){
                    mString += genofond[i][j];
                    mString += (" ");
                }
                mString += ("\n");
                arrayList.add(index, mString);
                index += 1;
            }

            arrayList.add(index, "======================");
            index += 1;
        }


        arrayList.add(index, "======================");
        index += 1;
        summ();
        sort();

        mString = "";
        for(int i = 0; i < much; i++){
            for(int j = 0; j < gens; j++){
                mString += genofond[i][j];
                mString += " ";
            }
            mString += "\n";
            arrayList.add(index, mString);
            index++;
            mString = "";
        }

        // здесь метод отдает массив в мейн для последующего создания окна
        return(arrayList);
    }

}
