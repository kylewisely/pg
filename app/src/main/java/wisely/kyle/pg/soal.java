package wisely.kyle.pg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class soal extends AppCompatActivity {
    TextView a;
    RadioGroup b;
    pg[] soal = new pg[10];
    int[] answer = new int[10];
    int[] trueAnswer = {3,2,2,2,1};
    int noSoal, ans, result;
    RadioButton pilgan1,pilgan2, pilgan3;
    Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        a = findViewById(R.id.soal);
        b = findViewById(R.id.pg);
        c = findViewById(R.id.next);
        pilgan1 = findViewById(R.id.pilgan1);
        pilgan2 = findViewById(R.id.pilgan2);
        pilgan3 = findViewById(R.id.pilgan3);

        //untuk selalu menyimpan jawaban yang dipilih ke dalam variable "ans"
        b.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.pilgan1:
                        ans = 1;
                        break;
                    case R.id.pilgan2:
                        ans = 2;
                        break;
                    case R.id.pilgan3:
                        ans = 3;
                        break;
                }
            }
        });

        for ( int i=0; i<5; i++) {
            soal[i]=new pg();
        }

        soal[0].setSoal("Burung adalah hewan yang bisa?","1.Terbang","2.Berenang","3.Tidak" );
        soal[1].setSoal("ular adalah hewan yang bisa?","1.melata","2.iya","3.mati" );
        soal[2].setSoal("sayuran berwarna merah?","1.tidak","2.bawang merah","3.kol" );
        soal[3].setSoal("(1+4*0) 0?","1.iya","2.tidak","3.nol");
        soal[4].setSoal("gigi nenek yang sudah tua","1.tidak tau","2.dua","3.nol" );


        Soal();

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer[noSoal] = ans;
                //bersihkan semua radiobutton
                b.clearCheck();
                ans = 0;

                if((noSoal+1) < 5){
                    noSoal++;
                    //ubah button dari next mmenjadi finish
                    if(noSoal == 4){
                        c.setText("finish");
                    }
                    Soal();
                }
                else
                {
                    for(int i = 0 ; i < 5 ; i++){
                        if(answer[i] == trueAnswer[i]){
                            result += 20;
                        }
                    }

                    Intent intent = new Intent(wisely.kyle.pg.soal.this, Result.class);
                    intent.putExtra("hasil",String.valueOf(result));
                    startActivity(intent);
                    finish();
                    //result;
                }
            }
        });
    }

    public void Soal(){
        if(noSoal < 5){
            a.setText(soal[noSoal].q);
            pilgan1.setText(soal[noSoal].pg1);
            pilgan2.setText(soal[noSoal].pg2);
            pilgan3.setText(soal[noSoal].pg3);
        }
    }
}
