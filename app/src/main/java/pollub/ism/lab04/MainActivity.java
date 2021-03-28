package pollub.ism.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String currentMove;
    private final String[] board = new String[9];
    private boolean gameEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeBoard();
    }

    public void onPlayerMove(View view) {
        if (this.gameEnd) {
            this.resetBoard();
        } else {
            String name = view.getResources().getResourceEntryName(view.getId());

            int row = Integer.parseInt(String.valueOf(name.charAt(1)));
            int col = Integer.parseInt(String.valueOf(name.charAt(2)));

            if (this.getBoardFieldValue(row, col).equals("")) {
                ((Button) view).setText(this.currentMove);
                this.setBoardFieldValue(row, col, this.currentMove);
                this.checkWin();
                this.changePlayer();
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            this.board[i] = "";
        }
        this.currentMove = "O";
    }

    private void checkWin() {
        String player = this.checkRows();
        if (player.equals("")) {
            player = this.checkCols();
        }
        if (player.equals("")) {
            player = this.checkDiagonals();
        }

        if (!player.equals("")) {
            // Win
            this.showWinToast();
            this.gameEnd = true;
            return;
        }

        if (!this.isAnyFieldEmpty()) {
            // Tie
            this.showTieToast();
            this.gameEnd = true;
        }
    }

    private void showWinToast() {
        Toast.makeText(this, this.getWinText(), Toast.LENGTH_LONG).show();
    }

    private void showTieToast() {
        Toast.makeText(this, "Tie!", Toast.LENGTH_LONG).show();
    }

    private void resetBoard() {
        this.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String textId = "b" + ((Integer) i).toString() + ((Integer) j).toString();
                int id = getResources().getIdentifier(textId, "id", getPackageName());
                ((Button) findViewById(id)).setText("");
            }
        }
        this.gameEnd = false;
    }

    private void changePlayer() {
        this.currentMove = this.currentMove.equals("O") ? "X" : "O";
    }

    private boolean isAnyFieldEmpty() {
        for (String field : board) {
            if (field.equals("")) {
                return true;
            }
        }
        return false;
    }

    private String checkRows() {
        String player = "";
        for (int i = 0; i < 3; i++) {
            player = this.getBoardFieldValue(i, 0);
            if (!player.equals("")) {
                for (int j = 1; j < 3; j++) {
                    if (!player.equals(this.getBoardFieldValue(i, j))) {
                        player = "";
                        break;
                    }
                }
                if (!player.equals("")) {
                    break;
                }
            }
        }
        return player;
    }

    private String checkCols() {
        String player = "";
        for (int i = 0; i < 3; i++) {
            player = this.getBoardFieldValue(0, i);
            if (!player.equals("")) {
                for (int j = 1; j < 3; j++) {
                    if (!player.equals(this.getBoardFieldValue(j, i))) {
                        player = "";
                        break;
                    }
                }
                if (!player.equals("")) {
                    break;
                }
            }
        }
        return player;
    }

    private String checkDiagonals() {
        String player = this.getBoardFieldValue(1, 1);
        if (!player.equals("")) {
            if (player.equals(this.getBoardFieldValue(0, 0))
                    && player.equals(this.getBoardFieldValue(2, 2))) {
                return player;
            }
            if (player.equals(this.getBoardFieldValue(0, 2))
                    && player.equals(this.getBoardFieldValue(2, 0))) {
                return player;
            }
        }
        return "";
    }

    private String getBoardFieldValue(int row, int col) {
        return this.board[this.getBoardIndex(row, col)];
    }

    private void setBoardFieldValue(int row, int col, String value) {
        this.board[this.getBoardIndex(row, col)] = value;
    }

    private int getBoardIndex(int row, int col) {
        return row * 3 + col;
    }

    private String getWinText() {
        return "Player '" + this.currentMove + "' Wins!";
    }
}