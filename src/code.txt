import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Automata {
    ArrayList<Character> A;
    ArrayList<Integer> S;
    Integer S0;
    ArrayList<Integer> finalS;
    BiHashMap<Integer, Character, Integer> f;


    Automata(ArrayList<Character> A, ArrayList<Integer> S, Integer S0,  ArrayList<Integer> finalS, BiHashMap<Integer, Character, Integer> f ){
        Set<Character> setA = new HashSet<Character>(A);
        Set<Integer> setS = new HashSet<Integer>(S);
        if(setA.size() != A.size() || setS.size()!= S.size())
            System.out.println("Invalid");
        this.finalS = finalS;
        this.A = A;
        this.S = S;
        this.S0 = S0;
        this.f = f;
    }
    Automata(){
        this.finalS = new ArrayList<Integer>();
        this.A = new ArrayList<Character>();
        this.S = new ArrayList<Integer>();
        this.S0 = 0;
        this.f = new BiHashMap<Integer, Character, Integer>();
    }

    public void readAutomat(String path){

        try{
            //BiHashMap<Integer, Character, Integer> f = new BiHashMap<Integer, Character, Integer>();
           // ArrayList<Character> A = new ArrayList<Character>();
            //ArrayList<Integer> S = new ArrayList<Integer>();
            //Integer S0;
            //ArrayList<Integer> finalS = new ArrayList<Integer>();
            File file = new File(path);
            FileReader reader = new FileReader(file);
            //ЧИТАЄМО ВХІДНИЙ АЛФАВІТ
            int c;
            while ( (char)(c = reader.read())!= '\n'){
                if(c>=97 && c<=122){
                    A.add((char)c);
                }

            }
            int n = 0;
            //ЧИТАЄМО МНОЖИНУ СТАНІВ
            while(true){
                c = reader.read();
                if(c>=48 && c<=57){
                    n = n*10+(c - 48);
                }
                if((char)c == ' '){
                    S.add(n);
                    n = 0;
                    continue;
                }
                if((char)c == '\n'){
                    S.add(n);
                    n = 0;
                    break;
                }
            }
            //ЧИТАЄМО ПОЧАТКОВИЙ СТАН
            while(true){
                c = reader.read();
                if((char)c == '\n'){
                    S0 = n;
                    n = 0;
                    break;
                }
                if(c>=48 && c<57){
                    n = n*10+(c - 48);
                }
            }
            //ЧИТАЄМО ФІНАЛЬНІ СТАНИ
            while(true){
                c = reader.read();
                if(c>=48 && c<=57){
                    n = n*10+(c - 48);
                }
                if((char)c == ' '){
                    finalS.add(n);
                    n = 0;
                }
                if((char)c== '\n'){
                    finalS.add(n);
                    n = 0;
                    break;
                }
            }
            //ФУНКЦІЯ ПЕРЕХОДІВ
            while ((char)(c=reader.read())!= '.'){
                Integer s0 = 0;
                Character a;
                Integer news = 0;

                while(c>=48 && c<=57){
                    n = n*10 + c - 48;
                    c = reader.read();
                }
                s0 = n;
                n = 0;

                a = (char)reader.read();
                c = reader.read();
                c = reader.read();
                while(c>=48 && c<=57){
                    n = n*10 + c - 48;
                    c = reader.read();
                }

                news = n;
                n = 0;
                f.put(s0, a, news);
                reader.read();

            }

            Set<Character> setA = new HashSet<Character>(A);
            Set<Integer> setS = new HashSet<Integer>(S);
            Set<Integer> setfinalS = new HashSet<Integer>(finalS);
            if(setA.size() != A.size() || setS.size()!= S.size() || setfinalS.size() != finalS.size())
                throw new IOException("Dublicate data");

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }

    public boolean fit(String s){
        char[] word = s.toCharArray();
        int CurrStan = S0;
        for(int i=0; i<s.length(); i++){
            if(finalS.contains(CurrStan) && i==s.length()-1){
                return true;
            }
            if(!f.containsKeys(CurrStan, word[i])){
                System.out.println(1);
                return false;
            }
            if( f.get(CurrStan, word[i]) == null){
                System.out.println(2);
                return false;
            }
            CurrStan = f.get(CurrStan, word[i]);
        }
        if(finalS.contains(CurrStan)){
            return true;
        }
        return false;
    }

    public HashMap<String, Integer> getAllWords(int order)
    {
        HashMap<String, Integer> all = new HashMap<String, Integer>();
        HashMap<String, Integer> nlen = new HashMap<String, Integer>();
        all.put("", S0);

        for(int i=1; i<=order; i++) {
            for(Map.Entry<String, Integer> entry: all.entrySet()){

                String key = entry.getKey();
                Integer value = entry.getValue();
                for(int j= 0; j<A.size(); j++){
                    if (!f.containsKeys(value, A.get(j))) {
                        continue;
                    }
                    nlen.put(key + A.get(j), f.get(value, A.get(j)));
                }
            }
            all.putAll(nlen);
            nlen.clear();
        }

        /*for(Map.Entry<String, Integer> entry: all.entrySet()) {

            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("--------");
            System.out.println(key);
            System.out.println(value);
        }*/
        return  all;

    }

    public ArrayList<String> onlyTrue(int order){
        ArrayList<String> res = new ArrayList<String>();
        HashMap<String, Integer> all = getAllWords(order);
        for(Map.Entry<String, Integer> entry: all.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(finalS.contains(value)){
                res.add(key);
            }
        }
        return res;
    }

    public static boolean equals(Automata a1, Automata a2, int order){
        ArrayList<String> onlyTrueA1 = a1.onlyTrue(order);
        ArrayList<String> onlyTrueA2 = a2.onlyTrue(order);
        for(int i=0; i< onlyTrueA1.size(); i++){
            if(a2.fit(onlyTrueA1.get(i))==false){
                return false;
            }
        }
        for(int i=0; i< onlyTrueA2.size(); i++){
            if(a1.fit(onlyTrueA2.get(i))==false){
                return false;
            }
        }
        return true;
    }

    public void showAutomata(){
        for(int i = 0; i<A.size(); i++){
            System.out.println(A.get(i));
        }
        System.out.println("-------");
        for(int i = 0; i<S.size(); i++){
            System.out.println(S.get(i));
        }
        System.out.println("-------");
        System.out.println(S0);
        System.out.println("-------");
        for(int i=0; i<finalS.size(); i++){
            System.out.println(finalS.get(i));
        }
        System.out.println("-------");
        for(int i = 0; i<S.size(); i++){
            for(int j = 0; j<A.size(); j++){
                System.out.println(S.get(i));
                System.out.println(A.get(j));
                System.out.println(f.get(S.get(i), A.get(j)));
                System.out.println("-------");
            }
        }
       // System.out.println(S.size());
    }

}
