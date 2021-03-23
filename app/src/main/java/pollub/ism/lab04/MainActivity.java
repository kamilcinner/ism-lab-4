package pollub.ism.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String currentMove = "O";
    private final String[] board = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeBoard();
    }

    public void onButtonClick(View view) {
        String name = view.getResources().getResourceEntryName(view.getId());

        int row = Integer.parseInt(String.valueOf(name.charAt(1)));
        int col = Integer.parseInt(String.valueOf(name.charAt(2)));

        if (this.getBoardFieldValue(row, col).equals("")) {
            ((Button) view).setText(this.currentMove);
            this.setBoardFieldValue(row, col, this.currentMove);
            this.currentMove = this.currentMove.equals("O") ? "X" : "O";
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            this.board[i] = "";
        }
    }

    private void checkWin() {
        String player = this.checkRows();
        if (!player.equals("")) {
            // Player wins

            return;
        }

        player = this.checkCols();
        if (!player.equals("")) {
            // Player wins

            return;
        }

        if (this.isAnyFieldEmpty()) {
            // Tie
            return;
        }
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
        String player = this.getBoardFieldValue(0, 0);

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
}