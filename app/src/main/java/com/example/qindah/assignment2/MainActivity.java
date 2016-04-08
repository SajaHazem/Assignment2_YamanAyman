package com.example.qindah.assignment2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public double Memoryvalue = 0;
    public double num = 0;
    public char operation = '_';
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        result.setText("0");
        Button MR = (Button) findViewById(R.id.MR);
        Button M = (Button) findViewById(R.id.M);
        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = result.getText().toString();
                Memoryvalue = Integer.parseInt(value);
                result.setText("0");

            }

        });
        Button C = (Button) findViewById(R.id.C);
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = 0;
                result.setText(num + "");
            }

        });
        Button MC = (Button) findViewById(R.id.MC);
        MC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Memoryvalue = 0;
            }

        });
        MR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = result.getText().toString();
                if(isOperant(res.charAt(res.length()-1)))
                    result.append(Memoryvalue + "");
                else if(num==0){
                    result.setText(Memoryvalue + "");
                }


            }

        });
        Button N = (Button) findViewById(R.id.N);
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                num *=-1;
                result.setText(num+"");
            }

        });
        Button Back = (Button) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String temp = result.getText().toString();
                char lastchar = temp.charAt(temp.length()-1);
                if(isOperant(lastchar))
                    operation='_';
                temp = temp.substring(0,temp.length()-1);
                result.setText(temp);
            }

        });
        Button zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(0);
            }

        });
        Button one = (Button) findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(1);
            }

        });
        Button two = (Button) findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(2);
            }

        });
        Button three = (Button) findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(3);
            }

        });
        Button four = (Button) findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(4);
            }

        });
        Button five = (Button) findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(5);
            }

        });
        Button six = (Button) findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(6);
            }

        });
        Button seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(7);

            }

        });
        Button eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(8);
            }

        });
        Button nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                find(9);
            }

        });
        Button div = (Button) findViewById(R.id.divid);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkOpporation('/');
            }

        });
        Button mul = (Button) findViewById(R.id.multiple);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOpporation('*');
            }

        });
        Button plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkOpporation('+');
            }

        });
        Button minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOpporation('-');
            }

        });
        Button equal = (Button) findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {

                    num = sum(result.getText().toString());
                    result.setText(num + "");

                }catch (myError myerr){ }
                catch (Exception e){
                    result.setText(num + "");

                }
                finally {
                    operation = '_';
                }
            }

        });

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



    public void find(int input){
        if(operation!='_' && num ==0)
            result.append(input+"");
        else if( num==0) {
            num = input;
            result.setText(input+"");

        }
        else {
            result.append(input+"");
        }


    }

    protected void checkOpporation(char opp){
        if(result.getText().toString().equals("infinity"))
            return;
        if(operation!='_'){
            if(isOperant(result.getText().toString().charAt(result.getText().toString().length()-1)))
            {
                result.setText(result.getText().toString().replace(operation,opp));
                operation=opp;
                return;
            }
            String res = result.getText().toString();
            try {
                num = sum(res);

                operation=opp;
                result.setText(num+""+operation);
            }catch (myError er){}
        }
        else{
            result.append((operation=opp)+"");

        }
    }

    protected boolean isOperant(char input){
        return input=='+' || input=='-' ||input=='/' ||input=='*' ;
    }

    protected double sum(String result) throws myError{
        char[] arr = result.toCharArray();
        double num=0;
        String n1="",n2="";
        int flag=0;
        for(char x:arr){
            if(x==operation)
                flag = 1;
            else if(flag==0){
                n1+=x+"";
            }
            else{
                n2+=x+"";
            }
        }

        double num1= Double.parseDouble(n1);
        double num2= Double.parseDouble(n2);
        if(operation == '+')
            num = num1 +num2;
        else if(operation == '-')
            num = num1 -num2;
        else if(operation == '*')
            num = num1*num2;
        else if(operation == '/') {
            if(num2==0){
                this.result.setText("infinity");
                this.num=0;
                this.operation='_';
                throw new myError();
            }
            else
                num = num1 / num2;
        }
        return num;
    }

    protected void onResume(){super.onResume();}
    protected void onStop(){super.onStop();}
    protected void onPause(){super.onPause();}

}

class myError extends Exception{

}