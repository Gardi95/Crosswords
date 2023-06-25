
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {

            Scanner sc=new Scanner(System.in);
            boolean choice = true;
            while (choice) {


                FileInputStream f = new FileInputStream("words.txt");
                Scanner s = new Scanner(f);
//			String array [] = new String[500000];
                ArrayList<String> array = new ArrayList<>();
                while (s.hasNext()) {
                    String word = s.nextLine();
                    array.add(word);
                    // System.out.println(word);
                }
                int wordCount = array.size();
                System.out.printf("Nr. of lines=%d\n", wordCount);
                Random rnd = new Random();
                String solution = array.get(rnd.nextInt(wordCount));
                System.out.println("Solution=" + solution);
                // random generator
                System.out.println("Ranomd generated words depend on solution:");
                int solutionwordChars = solution.length();
                String possibleWord = "";
                ArrayList<String> wordsRandomSolution = new ArrayList<String>();
                ArrayList<Integer> indexofFoundedLetters = new ArrayList<Integer>();
                int tempIndex = 0;
                Integer biggestIndex = 0;

                for (int i = 0; i < solutionwordChars; i++) {
                    char solutionchar = solution.charAt(i);
                    do {
                        possibleWord = array.get(rnd.nextInt(wordCount));
                        tempIndex = possibleWord.indexOf(solutionchar);

                    } while (tempIndex == -1);
                    indexofFoundedLetters.add(tempIndex);
                    if (tempIndex > biggestIndex) {
                        biggestIndex = tempIndex;
                    }

                    wordsRandomSolution.add(possibleWord);
                }


                for (int i = 0; i < wordsRandomSolution.size(); i++) {
                    StringBuilder builder = new StringBuilder();

                    String word = wordsRandomSolution.get(i);
                    char solutionchar = solution.charAt(i);
                    for (int j = 0; j < biggestIndex - indexofFoundedLetters.get(i); j++) {
                        builder.append(' ');
                    }
                    String first = word.substring(0, indexofFoundedLetters.get(i));
                    String third = word.substring(indexofFoundedLetters.get(i) + 1, word.length());

                    builder.append(first);
                    builder.append("|");
                    builder.append(solutionchar);
                    builder.append("|");
                    builder.append(third);


                    //atomic integer to increment each index while using it in foreach loop
                    AtomicInteger index = new AtomicInteger(0);

                    builder
                            //convert to string
                            .toString()
                            //make it steam
                            .chars()
                            //map steam to char steam
                            .mapToObj(c -> (char) c)
                            //loop through each charecter in the stream
                            .forEach(character -> {
                                //condition to change char if is not | or space
                                if (!character.equals('|') && !character.equals(' ')) {
                                    //replacing string builder at specific index to ba changed
                                    builder.replace(index.get(), index.get() + 1, "^");
                                }
                                //increment the index
                                index.incrementAndGet();

                            });

                    //print each line of data that string builder contains after looping through
                    System.out.println(builder);
                }

                System.out.println("\n=============================\n");

                for (int i = 0; i < wordsRandomSolution.size(); i++) {
                    String word = wordsRandomSolution.get(i);
                    char solutionchar = solution.charAt(i);
                    for (int j = 0; j < biggestIndex - indexofFoundedLetters.get(i); j++) System.out.print(' ');
                    System.out.printf("%s|%c|%s \n", word.substring(0, indexofFoundedLetters.get(i)), solutionchar,
                            word.substring(indexofFoundedLetters.get(i) + 1, word.length()));
                }
                s.close();
                f.close();

                System.out.println("\n=============================\n");



                System.out.print("please choose (1) to go again or enter other number to finish the code: ");
                int Choice = sc.nextInt();

                if(Choice != 1) choice = false;


            }
            sc.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    }

