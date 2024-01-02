public class Board {

    public Board(){
        this.board = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}};

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

    private Boolean checkWin(){
        if(!this.p11.equals(" ") & this.p11.equals(this.p12) & this.p12.equals(this.p13)){
            return true;
        }
        else if(!this.p21.equals(" ") & this.p21.equals(this.p22) & this.p22.equals(this.p23)){
            return true;
        }
        else if(!this.p11.equals(" ") & this.p31.equals(this.p32) & this.p32.equals(this.p33)){
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
}
