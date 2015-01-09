package com.yuriy.logicsquare;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.Random;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    Button[] btn = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn[0] = (Button) findViewById(R.id.button1);
        btn[1] = (Button) findViewById(R.id.button2);
        btn[2] = (Button) findViewById(R.id.button3);
        btn[3] = (Button) findViewById(R.id.button4);
        btn[4] = (Button) findViewById(R.id.button5);
        btn[5] = (Button) findViewById(R.id.button6);
        btn[6] = (Button) findViewById(R.id.button7);
        btn[7] = (Button) findViewById(R.id.button8);
        btn[8] = (Button) findViewById(R.id.button9);

        for (int i = 0; i < 9; ++i)
            btn[i].setOnClickListener(this);

        newGame();

    }

    @Override
    public void onClick(View v) {

        switchButtons(v);

        //((Button)v).setText("1");
        //btn[0].setText("12");
        //btn[2].setText("s");


    }

    private void newGame(){
        Random rand;
        rand = new Random();
        for(int i = 0; i < 5; ++i){
            switchButtons((View)btn[rand.nextInt(9)]);
        }
    }

    private void switchButton(Button btn){
        if(btn.getText() == "1")
            btn.setText("0");
        else
            btn.setText("1");

    }

    private void switchButtons(View v){
        switch(v.getId()){
            case R.id.button1:
                switchButton(btn[0]);
                switchButton(btn[1]);
                switchButton(btn[3]);
                break;
            case R.id.button2:
                switchButton(btn[1]);
                switchButton(btn[0]);
                switchButton(btn[2]);
                switchButton(btn[4]);
                break;
            case R.id.button3:
                switchButton(btn[2]);
                switchButton(btn[1]);
                switchButton(btn[5]);
                break;
            case R.id.button4:
                switchButton(btn[0]);
                switchButton(btn[3]);
                switchButton(btn[4]);
                switchButton(btn[6]);
                break;
            case R.id.button5:
                switchButton(btn[4]);
                switchButton(btn[1]);
                switchButton(btn[3]);
                switchButton(btn[5]);
                switchButton(btn[7]);
                break;
            case R.id.button6:
                switchButton(btn[5]);
                switchButton(btn[4]);
                switchButton(btn[2]);
                switchButton(btn[8]);
                break;
            case R.id.button7:
                switchButton(btn[6]);
                switchButton(btn[3]);
                switchButton(btn[7]);
                break;
            case R.id.button8:
                switchButton(btn[4]);
                switchButton(btn[6]);
                switchButton(btn[7]);
                switchButton(btn[8]);
                break;
            case R.id.button9:
                switchButton(btn[7]);
                switchButton(btn[8]);
                switchButton(btn[5]);
                break;
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
