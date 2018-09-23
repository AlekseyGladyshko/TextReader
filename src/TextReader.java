import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextReader {
    private File textFile;
    private static String filePath = "Text.txt";
    private String fileText = "";
    private List<String> list = new ArrayList<String>();

    public TextReader (String textFilePath){
        textFile = new File(textFilePath);
    }

    public void loadFile() throws IOException{
        Reader reader = new InputStreamReader(new FileInputStream(filePath), "cp1251");
        BufferedReader br = new BufferedReader(reader);

        while(br.ready()){
            fileText += br.readLine();
        }
    }

    public void separateWord(){
        fileText = fileText.replaceAll("[^a-zA-Zа-яА-ЯЁёІіїїЄє-]", " ");
        fileText = fileText.replaceAll(" -", " ");
    }

    public int countVowels(String word){
        Pattern pattern = Pattern.compile("[AaEeUuIiOoАаЕеЁёИиОоУуЫыЭэЮюЯяІіЇїЄє]");
        int vowelsMatches = 0;

        Matcher match = pattern.matcher(word);

        while (match.find()) {
            vowelsMatches++;
        }

        return vowelsMatches;
    }

    public void makeTask() throws IOException{
        Scanner scanner = new Scanner(fileText);

        while(scanner.hasNext()){
            String word = scanner.next();
            //word = word.substring(0).toLowerCase();

            int matches = countVowels(word);

            if(list.size() > 0){
                if(!(list.contains(word))) {
                    int i = 0;
                    while (countVowels(list.get(i)) <= matches) {
                        i++;
                        if (!(list.size() > i)) {
                            break;
                        }
                    }
                    list.add(i, word);
                }
            } else {
                list.add(0, word);
            }
        }

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i) + " - " + countVowels(list.get(i)));
        }
    }

    public static void main(String[] args) throws IOException {

        TextReader textReader = new TextReader(filePath);
        textReader.loadFile();
        textReader.separateWord();
        textReader.makeTask();

    }
}