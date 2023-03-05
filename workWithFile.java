import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class workWithFile{
    Map<Character, Integer> counter = new HashMap<>();
    Path name;
    public workWithFile(Path path){
        this.name = path;
    }
    public void openFile() throws NullPointerException, IOException {
        File file = new File(this.name.toUri());
        BufferedReader br = new BufferedReader(new FileReader(file));
        while(true){
            String q = br.readLine();
            if(q == null){
                break;
            }
            byte[] str = q.getBytes();
            for (byte b : str) {
                if(b < 0){
                    continue;
                }
                if (counter.containsKey((char) b)) {
                    counter.put((char) b, counter.get((char) b) + 1);
                } else {
                    counter.put((char) b, 1);
                }
            }
        }
        System.out.println(counter.toString());


    }
    public void writeToFile() throws IOException {
        for (Character i : counter.keySet()) {
            String g = "Символ " + "'" + i + "'" + " встречался в данном файле " + counter.get(i) + " раз.";

            Files.write(name, Collections.singleton(g), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        }
        System.out.println("Запись в файл успешно произведена.");
        System.out.println("Расположение файла: " + name);
    }

}
