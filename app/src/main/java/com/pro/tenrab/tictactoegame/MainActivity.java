package com.pro.tenrab.tictactoegame;


import android.app.Activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,bng;
    Button[]bArray;

    boolean turn=true;
    int turn_count=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.one);
        b2=(Button)findViewById(R.id.two);
        b3=(Button)findViewById(R.id.three);
        b4=(Button)findViewById(R.id.four);
        b5=(Button)findViewById(R.id.five);
        b6=(Button)findViewById(R.id.six);
        b7=(Button)findViewById(R.id.seven);
        b8=(Button)findViewById(R.id.eight);
        b9=(Button)findViewById(R.id.nine);
        bng=(Button)findViewById(R.id.bNewGame);

        bArray=new Button[]{b1,b2,b3,b4,b5,b6,b7,b8,b9};

        for (Button b: bArray){
            b.setOnClickListener(this);
        }

        bng.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                turn=true;
                turn_count=0;
                enabledisableAllButtons(true);
            }
        });

    }

    @Override
    public void onClick(View v) {
        Button b=(Button)v;
        buttonClicked(b);

    }

    public void buttonClicked(Button b){

        if (turn){
            b.setText("X");
        }
        else {

            b.setText("O");
        }

        turn_count++;

        b.setBackgroundColor(Color.LTGRAY);
        b.setClickable(false);
        turn=!turn;

        checkForWinner();

    }

    private void checkForWinner(){
        boolean winner=false;

        //horizontal

        if (b1.getText() == b2.getText() && b2.getText()== b3.getText() && !b1.isClickable())
            winner=true;
        else if (b4.getText() == b5.getText() && b5.getText()== b6.getText() && !b4.isClickable())
            winner=true;
        else if (b7.getText() == b8.getText() && b8.getText()== b9.getText() && !b7.isClickable())
        winner=true;

        //vertical
        else if (b1.getText()==b4.getText() && b4.getText()==b7.getText() && !b1.isClickable())
            winner=true;
        else if (b2.getText()==b5.getText() && b5.getText()==b8.getText() && !b5.isClickable())
            winner=true;
        else if (b3.getText()==b6.getText() && b6.getText()==b9.getText() && !b9.isClickable())
            winner=true;

        //diagonal
        else if (b1.getText()==b5.getText() && b5.getText()==b9.getText() && !b1.isClickable())
            winner=true;
        else if (b3.getText()==b5.getText() && b5.getText()==b7.getText() && !b5.isClickable())
            winner=true;


        if (winner){
            if (!turn){
                toast("X wins");

            }else {
                toast("O wins");
            }

            enabledisableAllButtons(false);
        }else if (turn_count==9){
            toast("DRAW!");
        }


    }

    private void enabledisableAllButtons(boolean enable){
        for (Button b: bArray){
            b.setClickable(enable);

            if (enable) {
                b.setBackgroundColor(Color.parseColor("#33b5e5"));
                b.setText("");
            }
            else {
                b.setBackgroundColor(Color.LTGRAY);
            }

        }
    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
