package activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vnexpress.R;

public class ActionButton_Calculator extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bcham, bcong, btru, bnhan, bchia, bthuchien, bxoaall, bxoa ;
    TextView textView;
    double giatri1, giatri2;
    boolean cong, tru, nhan, chia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbutton__calculator);
        b1 = (Button) findViewById(R.id.one);
        b2 = (Button) findViewById(R.id.two);
        b3 = (Button) findViewById(R.id.three);
        b4 = (Button) findViewById(R.id.four);
        b5 = (Button) findViewById(R.id.five);
        b6 = (Button) findViewById(R.id.six);
        b7 = (Button) findViewById(R.id.seven);
        b8 = (Button) findViewById(R.id.eight);
        b9 = (Button) findViewById(R.id.nine);
        b0 = (Button) findViewById(R.id.zero);
        bcham = (Button) findViewById(R.id.cham);
        bcong = (Button) findViewById(R.id.cong);
        btru = (Button) findViewById(R.id.tru);
        bnhan = (Button) findViewById(R.id.nhan);
        bchia = (Button) findViewById(R.id.chia);
        bxoa = (Button) findViewById(R.id.xoatungso);
        bxoaall = (Button) findViewById(R.id.xoatatca);
        bthuchien = (Button) findViewById(R.id.thuchien);
        textView = (TextView) findViewById(R.id.hienthi);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "1");
                }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                textView.setText(textView.getText() + "0");
            }
        });
        bcham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().length()==0) {
                    textView.setText(textView.getText() + "0.");
                }
                else if(textView.getText().length()>0)
                {
                    textView.setText(textView.getText() + ".");
                }
            }
        });
        bxoaall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(null);
            }
        });
        bxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textView.getText().toString();
                if(s.length()>0) {
                    StringBuilder sb = new StringBuilder(s);
                    textView.setText(sb.deleteCharAt(sb.length() - 1));
                }
                else {
                    textView.setText(null);
                }
            }
        });
        bcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().length()>0) {

                    giatri1 = Double.parseDouble(textView.getText() + "");
                    cong = true;
                    textView.setText(null);
                }
            }
        });
        btru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().length()>0) {
                    giatri1 = Double.parseDouble(textView.getText() + "");
                    tru = true;
                    textView.setText(null);
                }
            }
        });

        bnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().length()>0) {
                    giatri1 = Double.parseDouble(textView.getText() + "");
                    nhan = true;
                    textView.setText(null);
                }
            }
        });

        bchia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().length()>0) {
                    giatri1 = Double.parseDouble(textView.getText() + "");
                    chia = true;
                    textView.setText(null);
                }
            }
        });
        bthuchien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().length() > 0) {
                    giatri2 = Double.parseDouble(textView.getText() + "");
                    if (cong == true) {
                        textView.setText(giatri1 + giatri2 + "");
                        cong = false;
                    }
                    if (tru == true) {
                        textView.setText(giatri1 - giatri2 + "");
                        tru = false;
                    }
                    if (nhan == true) {
                        textView.setText(giatri1 * giatri2 + "");
                        nhan = false;
                    }
                    if (chia == true) {
                        textView.setText(giatri1 / giatri2 + "");
                        chia = false;
                    }
                }
            }
        });


    }
}
