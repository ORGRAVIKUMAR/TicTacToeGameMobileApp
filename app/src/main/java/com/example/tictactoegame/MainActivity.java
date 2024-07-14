package com.example.tictactoegame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean playerOneActive;

    private TextView playerOneScore, playerTwoScore, playerStatus;

    private Button[] buttons = new Button[9];

    private Button reset, playagain;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},

            {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int rounds;

    private int playerOneScoreCount, playerTwoScoreCount;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        playerOneScore = findViewById(R.id.player1score);

        playerTwoScore = findViewById(R.id.player2score);

        playerStatus = findViewById(R.id.status);

        reset = findViewById(R.id.reset);

        playagain = findViewById(R.id.playagain);



        buttons[0] = findViewById(R.id.btn0);

        buttons[1] = findViewById(R.id.btn1);

        buttons[2] = findViewById(R.id.btn2);

        buttons[3] = findViewById(R.id.btn3);

        buttons[4] = findViewById(R.id.btn4);

        buttons[5] = findViewById(R.id.btn5);

        buttons[6] = findViewById(R.id.btn6);

        buttons[7] = findViewById(R.id.btn7);

        buttons[8] = findViewById(R.id.btn8);



        for(int i=0; i<buttons.length; i++)
        {
            buttons[i].setOnClickListener(this::onClick);

        }
        playerOneScoreCount = 0;

        playerTwoScoreCount = 0;

        playerOneActive = true;

        rounds = 0;






    }

    public void onClick(View view)

    {

        if(!((Button)view).getText().toString().equals(""))

        {

            return;

        }

        else if(checkWinner())

        {

            return;

        }

        String buttonID  = view.getResources().getResourceEntryName(view.getId());

        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));



        if(playerOneActive)

        {

            ((Button)view).setText("X");

            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));

            gameState[gameStatePointer] = 0;

        }

        else

        {

            ((Button)view).setText("O");

            ((Button)view).setTextColor(Color.parseColor("#70fc3a"));

            gameState[gameStatePointer] = 1;

        }

        rounds++;



        if(checkWinner())

        {

            if(playerOneActive)

            {

                playerOneScoreCount++;

                updatePlayerScore();

                playerStatus.setText("Player-1 has won");

            }else

            {

                playerTwoScoreCount++;

                updatePlayerScore();

                playerStatus.setText("Player-2 has won");

            }

        }

        else if(rounds==9)

        {

            playerStatus.setText("No Winner");

        }

        else

        {

            playerOneActive = !playerOneActive;

        }



        reset.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                playAgain();

                playerOneScoreCount= 0;

                playerTwoScoreCount= 0;

                updatePlayerScore();

            }

        });



        playagain.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                playAgain();

            }

        });





    }



    private boolean checkWinner()

    {

        boolean winnerResults  = false;

        for (int[] i: winningPositions)

        {

            if(gameState[i[0]]==gameState[i[1]]&&

                    gameState[i[1]]==gameState[i[2]] &&

                    gameState[i[0]]!=2)

            {

                winnerResults = true;

            }

        }



        return winnerResults;



    }



    private void playAgain()

    {

        rounds = 0;

        playerOneActive = true;

        for (int i=0; i<buttons.length; i++)

        {

            gameState[i] = 2;

            buttons[i].setText("");

        }

        playerStatus.setText("Status");



    }



    private void updatePlayerScore()

    {

        playerOneScore.setText(Integer.toString(playerOneScoreCount));

        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }

}