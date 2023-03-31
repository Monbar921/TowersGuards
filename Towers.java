import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Towers {
    private static int numberOfTowers;
    private static int numberOfGuards;
    private static int result = 0;
    private static ArrayList<Integer> towersLenght = new ArrayList<>();
    private static ArrayList<Integer> guardsLenght = new ArrayList<>();
    public static void main(String[] args)  {
        try{
            reader();
            fixPlatforms();
            placeGuards();
            System.out.println(result);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void reader() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 3;++i){
            String temp = reader.readLine();
            String[] strToArr = temp.split(" ");
            if(i == 0){
                numberOfTowers = Integer.parseInt(strToArr[0]);
                numberOfGuards = Integer.parseInt(strToArr[1]);
            } else if(i == 1){
                for(String str : strToArr){
                    towersLenght.add(Integer.parseInt(str));
                }
            } else {
                for(String str : strToArr){
                    guardsLenght.add(Integer.parseInt(str));
                }
            }
        }
        if(numberOfTowers < 1 || numberOfTowers > 2 * Math.pow(10, 5)){
            throw new IOException();
        }
        if(numberOfGuards < 1 || numberOfGuards > 2 * Math.pow(10, 5)){
            throw new IOException();
        }
        if(towersLenght.size() < 1 || towersLenght.size() > Math.pow(10, 18)){
            throw new IOException();
        }
        if(guardsLenght.size() < 1 || guardsLenght.size() > Math.pow(10, 18)){
            throw new IOException();
        }
        Collections.sort(guardsLenght);
        Collections.reverse(guardsLenght);
    }

    public static void fixPlatforms(){

        for(int i = towersLenght.size() - 2; i >= 0; --i){
            int current = towersLenght.get(i);

            current = current -  towersLenght.get(i + 1);
            if(current <= 0){
                towersLenght.set(i, towersLenght.get(i + 1));
            }
        }
        for(int i = towersLenght.size() - 2; i >= 0 && towersLenght.size() != 1; --i){
            int current = towersLenght.get(i);
            int prev = towersLenght.get(i + 1);
            if(current == prev){
                while(i >= 0 && towersLenght.get(i) == prev){
                    towersLenght.set(i, null);
                    --i;
                }
            }
        }
        for(int i = 0; i<  towersLenght.size() - 1;++i){
            towersLenght.set(i, towersLenght.get(i) - towersLenght.get(i + 1));
        }
    }

    public static void placeGuards(){
        for(int guard : guardsLenght){
            for(int i = 0; i < towersLenght.size(); ++i){
                if(towersLenght.get(i) != null){
                    if(towersLenght.get(i) >= guard){
                        System.out.println(towersLenght.get(i) + " " + guard);
                        ++result;
                        towersLenght.set(i, null);
                        break;
                    }
                }
            }
        }
    }
}