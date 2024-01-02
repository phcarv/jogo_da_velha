public class Main {
    public static void main(String[] args) {
        //Velha jogoDaVelha = new Velha();

         checkWin();

    }
    public String[][] plays = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };
    public static void checkWin(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.println("Printing %s");
            }
        }
    }
}