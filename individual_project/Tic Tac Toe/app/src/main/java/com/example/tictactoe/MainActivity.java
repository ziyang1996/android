package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {
    // Represents the internal state of the game

    private TicTacToeGame mGame;
    private Button mBoardButtons[];
    // Various text displayed
    private TextView mInfoTextView;
    private  TextView showResults;

    // game record
    private Button clearRecord;
    private int a,b,c,first;
    private String A,B,C;

    // Restart Button
    private Button startButton;
    Boolean mGameOver;

    // difficulty choose
    private Spinner choose;
    private int ch;

    // button music
    private SoundPool soundPool;
    private int soundIDwin,soundIDtie,soundIDlose;
    private MediaPlayer music;

    // time record
    Calendar calendars;
    private int bestTime,startTime,endTime;
    private TextView bestRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initSound();

        startTime=getTime();
        loadPreferences();
        first=0;
        bestRecord=(TextView)findViewById(R.id.show_bestTime);
        showResults=(TextView) findViewById(R.id.show_results);

        makeRecord();

        choose = (Spinner) findViewById(R.id.Level);
        choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //获取到Spinner下列选项值
                String val = choose.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this,val,Toast.LENGTH_LONG).show();

                if (val.equals("简单")||val.equals("easy")) ch=1;
                else if (val.equals("困难")||val.equals("hard")) ch=2;
                else if (val.equals("专家")||val.equals("expert")) ch=3;
                else
                {
                    Toast.makeText(MainActivity.this,"wrong!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ch=1;
            }
        });


        // Buttons making up the board

        mGame = new TicTacToeGame();

        mBoardButtons = new Button[mGame.BOARD_SIZE];
        mBoardButtons[0] = (Button) findViewById(R.id.button0);
        mBoardButtons[1] = (Button) findViewById(R.id.button1);
        mBoardButtons[2] = (Button) findViewById(R.id.button2);
        mBoardButtons[3] = (Button) findViewById(R.id.button3);
        mBoardButtons[4] = (Button) findViewById(R.id.button4);
        mBoardButtons[5] = (Button) findViewById(R.id.button5);
        mBoardButtons[6] = (Button) findViewById(R.id.button6);
        mBoardButtons[7] = (Button) findViewById(R.id.button7);
        mBoardButtons[8] = (Button) findViewById(R.id.button8);
        mInfoTextView = (TextView) findViewById(R.id.information);
        mGame = new TicTacToeGame();

        startNewGame();
    }

    //--- Set up the game board.
    private void startNewGame() {
        mGameOver = false;
        mGame.clearBoard();
        //---Reset all buttons
        for (int i = 0; i < mBoardButtons.length; i++) {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }
        //---Human goes first
        if (first==0)
        {
            first=1;
            mInfoTextView.setText(R.string.begin);
        }
        else // android goes first
        {
            first=0;
            int location=mGame.getComputerMove(ch);
            mInfoTextView.setText(R.string.your_turn);
            setMove(TicTacToeGame.COMPUTER_PLAYER, location);
        }

    }

    private class ButtonClickListener implements View.OnClickListener {
        int location;

        public ButtonClickListener(int location) {
            this.location = location;
        }

        @Override
        public void onClick(View v) {
            if (mGameOver == false) {
                if (mBoardButtons[location].isEnabled()) {
                    setMove(TicTacToeGame.HUMAN_PLAYER, location);
                    //--- If no winner yet, let the computer make a move
                    int winner = mGame.checkForWinner();
                    if (winner == 0) {
                        mInfoTextView.setText(R.string.android_turn);
                        int move = mGame.getComputerMove(ch);
                        setMove(TicTacToeGame.COMPUTER_PLAYER, move);
                        winner = mGame.checkForWinner();
                    }
                    if (winner == 0) {
                        mInfoTextView.setTextColor(Color.rgb(0, 0, 0));
                        mInfoTextView.setText(R.string.your_turn);
                    } else if (winner == 1) {
                        playSoundTie();
                        c+=1;
                        mInfoTextView.setTextColor(Color.rgb(0, 0, 200));
                        mInfoTextView.setText(R.string.tie);
                        makeRecord();
                        mGameOver = true;
                    } else if (winner == 2) {
                        updateTimeRecord();
                        playSoundWin();
                        a+=1;
                        mInfoTextView.setTextColor(Color.rgb(0, 200, 0));
                        mInfoTextView.setText(R.string.won);
                        makeRecord();
                        mGameOver = true;
                    } else {
                        playSoundLose();
                        b+=1;
                        mInfoTextView.setTextColor(Color.rgb(200, 0, 0));
                        mInfoTextView.setText(R.string.lose);
                        makeRecord();
                        mGameOver = true;
                    }
                }
            }
        }
    }

    private void setMove(char player, int location) {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        mBoardButtons[location].setText(String.valueOf(player));
        if (player == TicTacToeGame.HUMAN_PLAYER)
            mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0));
        else
            mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0));
    }
    //--- OnClickListener for Restart a New Game Button
    public void newGame(View v) {
        startTime=getTime();
        startNewGame();
    }

    // clear record
    public void Clear(View v)
    {
        a=0;
        b=0;
        c=0;
        bestTime=-1;
        makeRecord();
    }

    // difficulty choose and game exit
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the aaction bar if it is present
        getMenuInflater().inflate(R.menu.quit, menu);
        return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Quit:
                finish();
                return true;
            case R.id.menu_easy:
                ch=1;
                return true;
            case R.id.menu_hard:
                ch=2;
                return true;
            case R.id.menu_expert:
                ch=3;
                return true;
        }
        return false; }


    private void PlayMusic(int MusicId) {
        music = MediaPlayer.create(this, MusicId);
        music.start();
    }

    @SuppressLint("NewApi")
    private void initSound() {
        soundPool = new SoundPool.Builder().build();
        soundIDwin = soundPool.load(this, R.raw.win, 1);
        soundIDtie = soundPool.load(this, R.raw.tie, 1);
        soundIDlose = soundPool.load(this, R.raw.lose, 1);
    }

    private void playSoundWin() {
        soundPool.play(soundIDwin,1f,1f,0,0,1 );
    }
    private void playSoundTie() {
        soundPool.play(soundIDtie,1f,1f,0,0,1 );
    }
    private void playSoundLose() {
        soundPool.play(soundIDlose,1f,1f,0,0,1 );
    }


    private int getTime() {
        calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int min = Integer.parseInt(String.valueOf(calendars.get(Calendar.MINUTE)));
        int second = Integer.parseInt(String.valueOf(calendars.get(Calendar.SECOND)));
        return min*60+second;
    }

    private void updateTimeRecord()
    {
        endTime=getTime();
        if (startTime>endTime) endTime+=3600;
        if (endTime-startTime<bestTime||bestTime==-1)
        {
            bestTime=endTime-startTime;
            makeRecord();
        }
    }

    private void makeRecord()
    {
        String x;
        if (bestTime==-1)
        {
            x=getResources().getString(R.string.bestTime)+" non seconds";
        }
        else
        {
            x=getResources().getString(R.string.bestTime)+" "+bestTime+" seconds";
        }
        bestRecord.setText(x);

        A=getResources().getString(R.string.a);
        B=getResources().getString(R.string.b);
        C=getResources().getString(R.string.c);

        String S="  "+A+a+"  "+B+b+"  "+C+c;
        showResults.setText(S);
        savePreferences();
    }

    public void savePreferences() {
        SharedPreferences pref = getSharedPreferences("TicTac", MODE_PRIVATE);
        String _a=String.valueOf(a);
        String _b=String.valueOf(b);
        String _c=String.valueOf(c);
        String _t=String.valueOf(bestTime);
        pref.edit().putString("a", _a).apply();
        pref.edit().putString("b", _b).apply();
        pref.edit().putString("c", _c).apply();
        pref.edit().putString("t", _t).apply();
    }
    public void loadPreferences() {
        SharedPreferences pref = getSharedPreferences("TicTac", MODE_PRIVATE);
        a=Integer.parseInt(pref.getString("a", "0"));
        b=Integer.parseInt(pref.getString("b", "0"));
        c=Integer.parseInt(pref.getString("c", "0"));
        bestTime=Integer.parseInt(pref.getString("t", "-1"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPreferences(); }
}
