package com;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class GuessTheMovie {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("Welcome to Guess the Movie!");
        System.out.println("The rules are simple, the computer randomly picks a movie title, and shows you how " + "many " + "letters it's made up of.");
        System.out.println("Your goal is to try to figure out the movie by guessing one letter at a time.");
        System.out.println("If a letter is indeed in the title the computer will reveal its correct position in" + " the " + "word.");
        File file = new File("movies.txt");
outer:
        while (true) {
            String movieName = null;
            try {
                movieName = game.getRandomMovie(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String cryptedString = game.hideString(movieName);
            String y = null;
            int count = 0;
            char[] movieLetters;
            assert movieName != null;
            movieLetters = movieName.toCharArray();
            System.out.println("This movie contains " + movieLetters.length + " letters");
            System.out.println("");
            System.out.println("Enter a letter. You have 10 guesses left.");
            System.out.println(cryptedString);
            Scanner scanner = new Scanner(System.in);
            char[] correctLetters = cryptedString.toCharArray();
            String s = null;
            ArrayList<String> repeated = new ArrayList<>();
            while (count < 10) {
                s = scanner.nextLine();
                s = s.toLowerCase();
                if (!(repeated.isEmpty()))
                    System.out.println("\n" + "Please enter a letter");
                {
                    if (repeated.contains(s.substring(0))) {
                        System.out.println("Letter is repeated. Please enter another letter.");
                        continue;
                    }
                }
                String nameGuessed = null;
                if (!s.matches("[A-Za-z]")) {
                    System.out.println("Please enter single letter only ");
                } else {
                    int i = 0;
                    i = movieName.indexOf(s);
                    char ch = 0;
                    try {
                        ch = s.charAt(0);
                    } catch (Exception ex) {
                        System.out.println("Please enter only letter");
                    }
                    int x = 0;
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
                            System.out.println("Out of guesses, GAME OVER!");
                            System.out.println("Correct movie is " + movieName.toUpperCase());
                            repeated.add(s);
                            continue;
                        } else {
                            int remain = 10 - count;
                            System.out.println("WRONG! You have guessed " + count + " wrong letters!  try again. You have " + remain + " guesses left");
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
            System.out.println(y);
            if (y.substring(0).equals("y") || y.substring(0).equals("Y")) {
                continue outer;
            } else {
                System.out.println("Thanks for playing");
                break;
            }
        }
    }
}
