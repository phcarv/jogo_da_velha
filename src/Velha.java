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

        this.board = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}};

        while(!this.win){
            this.game();
        }

    }

    private String[][] board;

    private String p11;
    private String p12;
    private String p13;
    private String p21;
    private String p22;
    private String p23;
    private String p31;
    private String p32;
    private String p33;

    private void valuesUpdater(){
        this.p11 = this.board[0][0];
        this.p12 = this.board[0][1];
        this.p13 = this.board[0][2];
        this.p21 = this.board[1][0];
        this.p22 = this.board[1][1];
        this.p23 = this.board[1][2];
        this.p31 = this.board[2][0];
        this.p32 = this.board[2][1];
        this.p33 = this.board[2][2];
    }


    private Boolean win = false;
    private Integer[] currentPlay;
    private Integer currentPlayer = 1;

    private String typedPlay;


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
                
                """.formatted(this.flattenMatrix(this.board));
        System.out.println(grid);
    }

    private String[] flattenMatrix(String[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
    }

    private String playGetter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual é a sua jogada?");
        return scanner.nextLine();

    }

    private void playChecker(){
        this.typedPlay = this.playGetter();
        Pattern pattern = Pattern.compile("[1-3]{2}|[1-3] [1-3]|[1-3]e[1-3]");
        Matcher matcher = pattern.matcher(this.typedPlay);
        while(!matcher.matches()){
            System.out.println("Essa entrada não é valida, tente algo como '12' ou '2 3' ou '3e1'.");
            this.playChecker();
        }
    }

    private Integer[] playFormatter(String userInput){
        Integer[] output = new Integer[2];
        String cleanedUserInput = userInput.replaceAll("\\s|e","");
        String [] stringAsArray = cleanedUserInput.split("");
        for(int i = 0; i < stringAsArray.length; i++){
            try{
                output[i] = Integer.parseInt(stringAsArray[i]) - 1;// Subtraction used to keep the logic of "1 2" representing first line second column.
            } catch (NumberFormatException ignored){}
        }
        return output;
    }


    private Boolean playValid() {
        if(this.board[this.currentPlay[0]][this.currentPlay[1]].equals(" ")){
            this.gridFormatter();
            return true;
        }
        else{
            System.out.println("Essa jogada já foi feita! Escolha outro lugar para jogar.");
            return false;
        }
    }

    private void gridFormatter() {
        board[currentPlay[0]][currentPlay[1]] = (currentPlayer == 1) ? "X" : "O";
    }


    private void playerChange() {
        this.currentPlayer ^= 1;
    }

    private Boolean checkWin(){
        if(!this.p11.equals(" ") & this.p11.equals(this.p12) & this.p12.equals(this.p13)){
            return true;
        }
        else if(!this.p21.equals(" ") & this.p21.equals(this.p22) & this.p22.equals(this.p23)){
            return true;
        }
        else if(!this.p31.equals(" ") & this.p31.equals(this.p32) & this.p32.equals(this.p33)){
            return true;
        }
        else if(!this.p11.equals(" ") & this.p11.equals(this.p21) & this.p21.equals(this.p31)){
            return true;
        }
        else if(!this.p12.equals(" ") & this.p12.equals(this.p22) & this.p22.equals(this.p32)){
            return true;
        }
        else if(!this.p13.equals(" ") & this.p13.equals(this.p23) & this.p23.equals(this.p33)){
            return true;
        }
        else if(!this.p11.equals(" ") & this.p11.equals(this.p22) & this.p22.equals(this.p33)){
            return true;
        }
        else if(!this.p13.equals(" ") & this.p13.equals(this.p22) & this.p22.equals(this.p31)){
            return true;
        }
        else{
            return false;
        }
    }

    public void game(){
        this.displayGrid();
        Boolean isValid = false;
        while(!isValid){
            this.playChecker();
            this.currentPlay = this.playFormatter(this.typedPlay);
            isValid = this.playValid();
        }
        this.valuesUpdater();
        this.win = this.checkWin();
        this.playerChange();
    }
}
