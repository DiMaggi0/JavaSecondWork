import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Введите имя файла, который хотите найти.");
        Scanner enter = new Scanner(System.in);
        String nameFile = enter.nextLine();

       while(!(Pattern.matches("^[^\\/?%*:|.;,]+.[^\\/?%*:|.,]{1,4}$", nameFile))){
            System.out.println("Введите корректное имя файла с расширением.");
            if(enter.hasNextLine()) {
                nameFile = enter.nextLine();
            }
        }
        Path name = Paths.get(nameFile);
        Path file = Paths.get(name.toAbsolutePath().getRoot().toString());
        MyFileVisitor h = new MyFileVisitor(name);
        System.out.println("Подождите, выполняется поиск заданного файла...");
        Files.walkFileTree(file, h);
        if(h.isFind){
            workWithFile a = new workWithFile(h.nameFile);
            System.out.println("Файл был успешно найден!");
            System.out.println("Расположение файла: " + a.name);
            a.openFile();
            System.out.println("Введите имя файла, в который будут записаны данные.");
            nameFile = enter.nextLine();

            while(!(Pattern.matches("^[^\\/?%*:|.;,]+.[^\\/?%*:|.,]{1,4}$", nameFile))){
                System.out.println("Введите корректное имя файла с расширением.");
                if(enter.hasNextLine()) {
                    nameFile = enter.nextLine();
                }
            }
            name = Paths.get(nameFile);
            file = Paths.get(name.toAbsolutePath().getRoot().toString());
            h.nameFile = name;
            h.isFind = false;
            System.out.println("Подождите, выполняется поиск заданного файла...");
            Files.walkFileTree(file, h);
            if(h.isFind){
                a.name = h.nameFile;
                a.writeToFile();

            }
            else{
                Path y = name.toAbsolutePath();
                File w = new File(y.toUri());
                if(w.createNewFile()){
                    System.out.println("Файл создан и расположен по пути " + w);
                }
                a.name = w.toPath();
                a.writeToFile();


            }
            a.counter.clear();
        }
        else{
            System.out.println("Не удалось найти файл с данным именем!");
        }



    }
}




