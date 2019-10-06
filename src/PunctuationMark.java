import java.util.ArrayList;

public class PunctuationMark extends Lexem {

    public static ArrayList<String> AllPunctuationMarks;

    PunctuationMark(String value){
        super(value);
    }
    public static void SetColor(String NumColor){
        Color = NumColor;
    }
}
