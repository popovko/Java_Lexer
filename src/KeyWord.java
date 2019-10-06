import java.util.ArrayList;

public class KeyWord extends Lexem {

    public static ArrayList<String> AllKeyWords = new ArrayList<String>();

    public static void SetAllKeyWords(){
        String[] all = new String[]{"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
        "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for",
        "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package",
        "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch",
        "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};
        for(int i=0; i<all.length; i++){
            AllKeyWords.add(all[i]);
        }
    }

    KeyWord(String value){
        super(value);
    }
    public static void SetColor(String NumColor){
        Color = NumColor;
    }
}
