package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // X = 1; O = 2;
    private boolean isX;
    private int[][] board;
    private  boolean keepGoing;
    public void appear(View view) {
        ImageView mark = (ImageView) view;
        String tagNum = mark.getTag().toString();
        int row =  Character.getNumericValue(tagNum.charAt(0));
        int col =  Character.getNumericValue(tagNum.charAt(1));
        if (board[row][col] == 0 && keepGoing) {
            if (isX) {
                mark.setImageResource(R.drawable.x);
                board[row][col] = 1;
                isX = false;
            } else {
                mark.setImageResource(R.drawable.o);
                board[row][col] = 2;
                isX = true;
            }
            check(row, col);
        }
    }

    public void check(int row, int col) {
        // Check row
        if (board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
            stop(board[row][col]);
        }
        if (board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
            stop(board[row][col]);
        }
        if (board[0][0] == board [1][1] && board[0][0] == board[2][2] && board[0][0] != 0) {
            stop(board[row][col]);
        }
        if (board[0][2] == board [1][1] && board[0][2] == board[2][0] && board[0][2] != 0) {
            stop(board[row][col]);
        }
    }

    public void stop(int win) {
        keepGoing = false;
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView winnerText = (TextView) findViewById((R.id.winnerText));
        String winner = "";
        if (win == 1) {
            winner = "X ";
        }
        else {
            winner = "O ";
        }
        winnerText.setText(winner + "has won!");
        winnerText.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.VISIBLE);

    }

    public void reset(View view) {
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView winnerText = (TextView) findViewById((R.id.winnerText));
        winnerText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLayout);
        for (int i = 0;i < grid.getChildCount(); i++) {
            ImageView mark = (ImageView) grid.getChildAt(i);
            mark.setImageDrawable(null);
        }
        preGame();
    }

    public void preGame() {
        board = new int[3][3];
        isX = true;
        keepGoing = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preGame();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
