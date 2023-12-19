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
        this.displayGrid();
        this.play();
    }

     private String[][] values = {
             {" ", " ", " "},
             {" ", " ", " "},
             {" ", " ", " "}
    };


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
                
                """.formatted(this.values);
        System.out.println(grid);
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
            try{
                output[i] = Integer.parseInt(stringAsArray[i]);
            } catch (NumberFormatException ignored){}
        }
        return output;
    }


    private void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual é a sua jogada?");
        String userInput = scanner.nextLine();
        regexChecker(userInput);
        Integer[] output = this.inputToIntegerArray(userInput);
        for(Integer integer : output) {
            System.out.println(integer.toString());
        }

        //TODO: Insert here the function that converts the values of the string to ints in an array
//        if(!userInput.equals("")){
//            System.out.println();
//        }
    }
}