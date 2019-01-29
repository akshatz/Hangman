import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class GuessTheMovie {

    private static File file;

    public static void main(String[] args) throws FileNotFoundException{
        Game game = new Game();
        File file = new File("C:\\Users\\User2\\Desktop\\ud282-master\\GuesstheMovie\\src\\com\\movies.txt");
        if(!file.exists()){
            System.err.println("ERROR! file containing movielist is missing. Please input appropriate file and rerun.");
            System.exit(0);
        }
        System.out.println("Welcome to Guess the Movie!");
        System.out.println("The rules are simple, the computer randomly picks a movie title, and shows you how " + "many " + "characters it's made up of. Please enter special characters like numbers,"+"!,"+"',"+" | "+" if you feel.");
        System.out.println("Your goal is to try to figure out the movie by guessing one letter at a time.");
        System.out.println("If a character is indeed in the title the computer will reveal its correct position in" + " the " + "word.");

outer:
        while (true) {
            String movieName;
            movieName = game.getRandomMovie(file);
            String cryptedString = game.hideString(movieName);
            String y;
            int count = 0;
            char[] movieLetters;
            assert movieName != null;
            movieLetters = movieName.toCharArray();
            System.out.println("This movie contains " + movieLetters.length + " characters");
            System.out.println("");
            System.out.println("Enter a character. You have 10 guesses left.");
            System.out.println(cryptedString);
            Scanner scanner = new Scanner(System.in);
            char[] correctLetters = cryptedString.toCharArray();
            String s = null;
            ArrayList<String> repeated = new ArrayList<>();
            while (count < 10) {
                s = scanner.nextLine();
                s = s.toLowerCase();
                if (!(repeated.isEmpty()))
                    System.out.println("\n" + "Please enter a character");
                {
                    if (repeated.contains(s)) {
                        System.err.println("Character is repeated. Please enter another character.");
                        continue;
                    }
                }
                String nameGuessed;
                if (!s.matches("[A-Za-z0-9':-|!,.]"))
                {
                    System.err.println("Please enter single character only ");
                }
                else {
                    int i = 0;
                    i = movieName.indexOf(s);
                    char ch = 0;
                    try {
                        ch = s.charAt(0);
                    } catch (Exception ex) {
                        System.err.println("Please enter characters, and numbers");
                    }
                    int x;
                    if (i >= 0) {
                        for (x = 0; x < movieLetters.length; x++) {
                            if (movieLetters[x] == ch) {
                                correctLetters[x] = movieLetters[x];
                                repeated.add(s);
                            }
                        }
                        nameGuessed = new String(correctLetters);
                        System.out.println("");
                        System.out.println(nameGuessed);
                        System.out.println("");
                        if (game.checkWon(correctLetters)) {
                            System.out.println("Well Done!!You have correctly identified movie name!");
                            repeated.add(s);
                            break;
                        }
                    } else {
                        count++;
                        if (count == 10) {
                            System.err.println("Out of guesses, GAME OVER!");
                            System.err.println("Correct movie is " + movieName.toUpperCase());
                            repeated.add(s);
                            continue;
                        } else {
                            int remain = 10 - count;
                            System.err.println("WRONG! You have guessed " + count + " wrong letters!  try again. You have " + remain + " guesses left");
                            nameGuessed = new String(correctLetters);
                            System.out.println(nameGuessed);
                            repeated.add(s);
                        }
                    }
                }
            }
            System.out.println("Would you like to continue ? Y/N");
            y = scanner.nextLine();
            y = y.toLowerCase();
            if (y.equals("y") || y.equals("Y")) {
                continue outer;
            } else {
                System.out.println("Thanks for playing");
                break;
            }
        }
    }
}
