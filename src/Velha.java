import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Velha {

    public Velha() {
        System.out.println("""
                              Olá, o jogo da velha vai começar!
                              Regras:
                               1. O X começa o jogo.
                               2. Cada um joga um turno por vez.
                               3. Para escolher onde marcar no tabuleiro
                                  você precisa digitar a linha e a coluna que quer jogar
                                  Após o jogo perguntar 'Qual é a sua jogada?'.
                                  Exemplo: Qual é a sua jogada?
                                           2 3 (o jogador está marcando na posição
                                                que fica a linha 2 e a coluna 3)
                               3. A primeira pessoa a marcar 3 simbolos iguais
                                  em uma linha reta vence.""");

        while(!this.win){
            this.displayGrid();
            this.play();
            this.checkPlayValid();
            this.gridFormatter();
            this.playerChange();
        }
    }

     private String[][] plays = {
             {" ", " ", " "},
             {" ", " ", " "},
             {" ", " ", " "}
    };

    private Boolean win = false;
    private Integer[] currentPlay;
    private Integer currentPlayer = 1;

    private String locationToInsert = this.plays[this.currentPlay[0]][this.currentPlay[1]];

    private void displayGrid() {
        String grid = """
                      
                      |     |
                   %s  |  %s  |  %s
                 _____|_____|_____
                      |     |
                   %s  |  %s  |  %s
                 _____|_____|_____
                      |     |
                   %s  |  %s  |  %s
                      |     |
                
                """.formatted(this.flattenMatrix(this.plays));
        System.out.println(grid);
    }

    private String[] flattenMatrix(String[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
    }

    private void regexChecker(String userInput){
        Pattern pattern = Pattern.compile("[1-3]{2}|[1-3] [1-3]|[1-3]e[1-3]");
        Matcher matcher = pattern.matcher(userInput);
        if(!matcher.matches()){
            System.out.println("Essa entrada não é valida, tente algo como '12' ou '2 3' ou '3e1'.");
        }
    }

    private Integer[] inputToIntegerArray(String userInput){
        Integer[] output = new Integer[2];
        String cleanedUserInput = userInput.replaceAll("\\s|e","");
        String [] stringAsArray = cleanedUserInput.split("");
        for(int i = 0; i < stringAsArray.length; i++){
            output[i] = Integer.parseInt(stringAsArray[i]) - 1;// Subtraction used to keep the logic of "1 2" representing first line second column.
        }
        return output;
    }


    private void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual é a sua jogada?");
        String userInput = scanner.nextLine();
        this.regexChecker(userInput);
        this.currentPlay = this.inputToIntegerArray(userInput);
    }

    private void checkPlayValid() {
        if(this.locationToInsert.equals(" ")){
            this.gridFormatter();
        }
        else{
            System.out.println("Essa jogada já foi feita! Escolha outro lugar para jogar.");
            this.play();
        }
    }

    private void gridFormatter(){
            if((this.currentPlayer == 1)){
                this.locationToInsert = "X";
            }
            else{this.locationToInsert = "O";}

        }

    private void playerChange() {
        this.currentPlayer ^= 1;
    }
}
