import java.util.ArrayList;

public class PunctuationMark extends Lexem {

    public static ArrayList<String> AllPunctuationMarks = new ArrayList<String>();

    public static void SetAllPunctuationMarks(){
        AllPunctuationMarks.add(";");
    }

    PunctuationMark(String value){
        super(value);
    }
    public static void SetColor(String NumColor){
        Color = NumColor;
    }
}
