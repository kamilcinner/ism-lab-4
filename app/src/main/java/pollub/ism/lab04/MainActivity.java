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
        int index = Integer.parseInt(String.valueOf(name.charAt(1))) * 3 + Integer.parseInt(String.valueOf(name.charAt(2)));

        if (this.board[index].equals("")) {
            ((Button) view).setText(this.currentMove);
            this.board[index] = this.currentMove;
            this.currentMove = this.currentMove.equals("O") ? "X" : "O";
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            this.board[i] = "";
        }
    }
}