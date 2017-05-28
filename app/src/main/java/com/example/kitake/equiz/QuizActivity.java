package com.example.kitake.equiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    List<Pytania> quesList;
    int score=0;
    int qid=0;
    int gotowka = 500;
    int nrPytania = 1;
    Pytania currentQ;
    TextView txtQuestion, pieniadze;
    RadioButton rda, rdb, rdc;
    Button butNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseHelper db=new DatabaseHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView);
        rda=(RadioButton)findViewById(R.id.radioButton);
        rdb=(RadioButton)findViewById(R.id.radioButton2);
        rdc=(RadioButton)findViewById(R.id.radioButton3);
        butNext=(Button)findViewById(R.id.button5);
        pieniadze=(TextView)findViewById(R.id.gotowka);
        setQuestionView();
        pieniadze.setText("Wynik:" +gotowka +" zł");
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

                nrPytania++;
                if (gotowka<=0) qid=50;
                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    if (nrPytania<35){
                        gotowka+=100;
                    }
                    if (nrPytania>=35){
                        gotowka+=200;
                    }
                    score++;


                }else {
                    if (nrPytania < 15) gotowka -= 100;
                    if (nrPytania >= 15 && nrPytania <= 35) gotowka -= 200;
                    if (nrPytania > 35) gotowka -= 500;
                }
                if(qid<50){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{

                    Intent intent = new Intent(QuizActivity.this, Wynik.class);
                    Intent intentwyniki = new Intent(QuizActivity.this, Wyniki.class);
                    Bundle a= new Bundle();
                    Bundle b = new Bundle();
                    Bundle c = new Bundle();
                    a.putInt("nrPytania",nrPytania);
                    b.putInt("score", score);
                    c.putInt("score", score);
                    intent.putExtras(b);
                    intentwyniki.putExtras(c);
                    startActivity(intent);
                    finish();
                }
                pieniadze.setText("Wynik:" +gotowka +" zł");
                if (nrPytania==15)  Toast.makeText(QuizActivity.this, "Śreni poziom pytań", Toast.LENGTH_LONG).show();
                if (nrPytania==35)  Toast.makeText(QuizActivity.this, "Trudny poziom pytań", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }


}