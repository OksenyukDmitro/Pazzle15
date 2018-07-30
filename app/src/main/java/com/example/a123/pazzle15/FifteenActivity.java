package com.example.a123.pazzle15;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;


public class FifteenActivity extends Activity {
    private Button[][] buttons = new Button[4][4];
    private int[][] array = new int[4][4];
    private Point emptySpace = new Point();
    private int[][] reqArray = new int[4][4];
    private static final String TAGi = "myI";
    private static final String TAGa = "myA";
    private static final String TAGj = "myJ";
    private int[] btnArray = new int[16];

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        btnArray = savedInstanceState.getIntArray("btnArray");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
            outState.putIntArray("btnArray", btnArray);
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restart();
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        initArray();
        generateArray();
        paintTable();
        setListenersOnButtons();


    }
    private void save() {

        for (int i = 0, v = 0; i < 4; i++ )
            for (int j = 0; j < 4; j++ ) {
                try {

                    btnArray[v] = Integer.valueOf(buttons[i][j].getText().toString());
                    v++;
                } catch(NumberFormatException nfe) {
                    btnArray[v]=0;
                    v++;
                }
            }


            }



    private  void restart(){
        if(btnArray[0]!=0&&btnArray[1]!=0){

        for (int i = 0, v = 0; i < 4; i++ )
            for (int j = 0; j < 4; j++ ) {
                Button button = buttons[i][j];
                button.setVisibility(View.VISIBLE);
                if(btnArray[v]==0){
                    emptySpace.x = i;
                    emptySpace.y = j;
                    button.setText("");
                    button.setVisibility(View.INVISIBLE);
                }
                else
                button.setText( btnArray[v]+"");

                v++;

            }}
    }

    private void setListenersOnButtons() {
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View myView) {
                Button clickedButton = (Button) myView;
                Point clickedPoint = getClickedPoint(clickedButton);
                if (clickedPoint != null && canMove(clickedPoint)) {

                    clickedButton.setVisibility(View.INVISIBLE);
                    String numberStr = clickedButton.getText().toString();
                    clickedButton.setText(" ");

                    Button button = buttons[emptySpace.x][emptySpace.y];
                    button.setVisibility(View.VISIBLE);
                    button.setText(numberStr);

                    emptySpace.x = clickedPoint.x;
                    emptySpace.y = clickedPoint.y;
                    ifWin();


                }
            }
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                button.setOnClickListener(listener);
            }
        }
    }


    private void ifWin() {
        int[][] butArray = new int[4][4];
        for (int i = 0; i < 4; i++ )
            for (int j = 0; j < 4; j++ ) {
                try {
                    butArray[i][j] = Integer.valueOf(buttons[i][j].getText().toString());
                } catch(NumberFormatException nfe) {}
                     }
        boolean areEqual = Arrays.deepEquals(butArray, array);
        if(areEqual) {
            Toast toast = Toast.makeText(this, "win", Toast.LENGTH_LONG);

            View view = toast.getView();
            view.setBackgroundResource(android.R.drawable.toast_frame);
            toast.show();

          }}

    private Point getClickedPoint(Button clickedButton) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (clickedButton == buttons[i][j]) {
                    Point point = new Point();
                    point.x = i;
                    point.y = j;
                    return point;
                }
            }
        }
        return null;
    }

    private boolean canMove(Point clicked) {
        if (clicked.equals(emptySpace)) {
            return false;
        }

        if (clicked.x == emptySpace.x) {
            int diff = Math.abs(clicked.y - emptySpace.y);
            if (diff == 1) {
                return true;
            }
        } else if (clicked.y == emptySpace.y) {
            int diff = Math.abs(clicked.x - emptySpace.x);
            if (diff == 1) {
                return true;
            }
        }

        return false;
    }

    private void generateArray() {
        int k = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (k >= 16) {

                    array[i][j] = 0;
                } else {
                    array[i][j] = k;

                }
                k++;
            }
        }
    }

    private void paintTable() {

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                reqArray[i][j]=0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                Button button = buttons[i][j];
                int[] retArray=rand();
                int randI = retArray[0];
                int randJ = retArray[1];

              int number = array[randI][randJ];
                    if (number > 0) {
                    button.setText( number+"");
                } else if(number==0){
                    emptySpace.x = i;
                    emptySpace.y = j;
                    button.setText("");
                    button.setVisibility(View.INVISIBLE);

                }
            }
        }
    }
    private int[] rand(){
        Random random = new Random();
        while(true){
        int[] returnArray = new int[2];
        int randI=random.nextInt(4+0)+0;
        int randJ=random.nextInt(4+0)+0;
        if(reqArray[randI][randJ] == 0){
            returnArray[0]=randI;
            returnArray[1]=randJ;
            reqArray[randI][randJ] = 1;
            return returnArray;
        }
            }
    }

    private void initArray() {

        buttons[0][0] =  findViewById(R.id.button11);
        buttons[0][1] =  findViewById(R.id.button12);
        buttons[0][2] =  findViewById(R.id.button13);
        buttons[0][3] =  findViewById(R.id.button14);

        buttons[1][0] =  findViewById(R.id.button21);
        buttons[1][1] =  findViewById(R.id.button22);
        buttons[1][2] =  findViewById(R.id.button23);
        buttons[1][3] =  findViewById(R.id.button24);

        buttons[2][0] =  findViewById(R.id.button31);
        buttons[2][1] =  findViewById(R.id.button32);
        buttons[2][2] =  findViewById(R.id.button33);
        buttons[2][3] =  findViewById(R.id.button34);

        buttons[3][0] =  findViewById(R.id.button41);
        buttons[3][1] =  findViewById(R.id.button42);
        buttons[3][2] =  findViewById(R.id.button43);
        buttons[3][3] =  findViewById(R.id.button44);

    }

    public void restartTable(View view) {

            Button button = buttons[emptySpace.x][emptySpace.y];
            button.setVisibility(View.VISIBLE);
            paintTable();
    }


    public void SaveArray(View view) {
      save();


        SharedPreferences settings=this.getSharedPreferences(SyncStateContract.Constants.ACCOUNT_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("MyArraySize", btnArray.length);
        for(int i=0; i < btnArray.length; i++)
            editor.putInt("MyArray"+i, btnArray[i]);
        editor.commit();

        //Log.d("STR", str.toString());
        Log.d("Array", String.valueOf(btnArray));

       Toast toast = Toast.makeText(this, "Save", Toast.LENGTH_SHORT);
             toast.show();
    }

        public void LoadArray(View view){

            SharedPreferences settings=this.getSharedPreferences(SyncStateContract.Constants.ACCOUNT_NAME, 0);



            for(int i=0; i < btnArray.length; i++)
               btnArray[i]=settings.getInt("MyArray"+i, 0);
            restart();
          Toast toast = Toast.makeText(this, "Success", Toast.LENGTH_SHORT);
            toast.show();

    }


}
