import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class Game {
    private int countLines(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int linesNumber = 0;
        while (scanner.hasNextLine()) {
            linesNumber++;
            if (scanner.nextLine().equals("")) {
                break;
            }
        }
        return linesNumber;
    }
    public String getRandomMovie(File file) throws FileNotFoundException {
        int moviesCount =countLines(file);
        try {
            moviesCount = countLines(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        String[] moviesList = new String[moviesCount];
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String movie = scanner.nextLine();
                moviesList[i] = movie;
                i++;
            }
        }
        int randomNumber = (int) (Math.random() * (moviesCount - 1)) + 1;
        return moviesList[randomNumber];
    }

    public String hideString(String string) {
        char[] stringChars = string.toCharArray();
        StringBuilder hiddenString = new StringBuilder();
        for (int i = 0; i < stringChars.length; i++) {
            if (stringChars[i] == ' ') {
                stringChars[i] = ' ';
            } else {
                stringChars[i] = '_';
            }
            hiddenString.append(stringChars[i]);
        }
        return hiddenString.toString();
    }
    public boolean checkWon(char[] letters) {
        boolean isWon= false;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == '_') {
                isWon = false;
                break;
            } else {
                isWon = true;
            }
        }
        return isWon;
    }
}
/*import java.util.Scanner;
public class Game {
    private String movieToGuess;
    private int pointsLost;
    private String wrongLetters;
    private String rightLetters;
    private boolean gameWon;
    public Game(String pathname) {
        MovieList movieList = new MovieList(pathname);
        movieToGuess = movieList.getRandomMovie().trim();
        pointsLost = 0;
        rightLetters = "";
        wrongLetters = "";
        gameWon = false;
    }
    public String getMovieTitle() {
        return movieToGuess;
    }
    public String getHiddenMovieTitle() {
        if(rightLetters.equals("")){
            return movieToGuess.replaceAll("[a-zA-Z]", "_");
        }
        else{
            return movieToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters +"]]", "_");
        }
    }
    public String getWrongLetters() {
        return wrongLetters;
    }
    public boolean WonGame() {
        return gameWon;
    }
    public boolean gameEnded() {
        if (pointsLost >= 10) {
            return true;
        }
        if(!getHiddenMovieTitle().contains("_")) {
            gameWon = true;
            return true;
        }
        return false;
    }
    private String inputLetter(){
        System.out.println("Guess a letter:");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();
        if(!letter.matches("[a-z]")){
            System.out.println("That is not a letter.");
            return inputLetter();
        }
        else if(wrongLetters.contains(letter) || rightLetters.contains(letter)){
            System.out.println("You already guessed that letter.");
            return inputLetter();
        }
        else{
            return letter;
        }
    }
    public void guessLetter() {

        String guessedLetter = inputLetter();
        if (movieToGuess.toLowerCase().contains(guessedLetter)) {
            rightLetters += guessedLetter + guessedLetter.toUpperCase();
        }
        else {
            pointsLost++;
            wrongLetters += " " + guessedLetter;
        }
    }
}*/