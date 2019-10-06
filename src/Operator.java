import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Operator extends  Lexem {
    public static ArrayList<String> AllOperators = new ArrayList<String>();

    public static void SetAllOperators(){
        String[] all = new String[]{"{","}","(",")","[", "]", ".", "++", "--", "!", "~", "*", "/", "%", "+", "-",
        ">>", ">>>", "<<", ">", "<", ">=", "<=", "==", "!=", "&", "^", "|", "&&", "||", "?:", "=", "+=",
        "-=", "*=", "/=", "%=", ">>=", "<<=", "&=", "^=", "|=", ","};
        for(int i=0; i<all.length; i++){
            AllOperators.add(all[i]);
        }
    }

    Operator(String value){
        super(value);
    }
    public static void SetColor(String NumColor){
        Color = NumColor;
    }
}
