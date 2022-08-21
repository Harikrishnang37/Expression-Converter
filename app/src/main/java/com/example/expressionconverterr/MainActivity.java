package com.example.expressionconverterr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final  Expression_Converter obj= new Expression_Converter();
        final EditText expEntered = (EditText) findViewById(R.id.ExpEntered);
        final TextView Resultview1 = (TextView) findViewById(R.id.Result1view);
        final TextView Result2view = (TextView) findViewById(R.id.Result2view);
        final TextView Result2header = (TextView) findViewById(R.id.Result2header);
        final TextView Result1header = (TextView) findViewById(R.id.Result1heading);
        final TextView Detectedexp= (TextView) findViewById(R.id.detectedexp);
        final TextView mainheader= (TextView) findViewById(R.id.header);


        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredexp = expEntered.getText()+"";

                int fc=0; int ec=enteredexp.length()-1;

                while(enteredexp.charAt(fc)=='(')
                {fc++;}
                while(enteredexp.charAt(ec)==')')
                {ec--;}

                if(!(Character.isAlphabetic(enteredexp.charAt(fc))))
                {
                    //Prefix entered
                    Detectedexp.setText("Detected Input: Prefix");
                    Result1header.setText("Infix Expression:");
                    Result2header.setText("Postfix Expression:");

                    String infi= obj.Pre_toInfi(enteredexp);
                    String post= obj.convert_inToPost(infi);

                    Resultview1.setText(infi);
                    Result2view.setText(post);
                }
                else if (!(Character.isAlphabetic(enteredexp.charAt(ec))))
                {
                    //Postfix entered
                    Detectedexp.setText("Detected Input: Postfix");
                    Result1header.setText("Infix Expression:");
                    Result2header.setText("Prefix Expression:");

                    String infi= obj.Post_toInfi(enteredexp);
                    String pre= obj.convert_inToPre(infi);

                    Resultview1.setText(infi);
                    Result2view.setText(pre);
                }
                else
                {
                    //Infix entered
                    Detectedexp.setText("Detected Input: Infix");
                    Result1header.setText("Postfix Expression:");
                    Result2header.setText("Prefix Expression:");

                    String post= obj.convert_inToPost(enteredexp);
                    String pre= obj.convert_inToPre(enteredexp);

                    Resultview1.setText(post);
                    Result2view.setText(pre);
                }


            }
        });





    }
}

 class Expression_Converter {

    char A[] =new char[100];
    int sp=-1; int i,j;

    String convert_inToPost(String S)
    {
        emptystack();
        int len=S.length();
        String ans="";

        for(i=0;i<len;i++)
        {
            char tempchar= S.charAt(i);

            if(Character.isAlphabetic(tempchar))
                ans+=tempchar;
            else if (tempchar=='+' || tempchar=='-' || tempchar=='*' || tempchar=='/' || tempchar=='(' || tempchar==')' || tempchar=='^')
            {
                if( tempchar=='('|| peek()=='(')
                    if(tempchar==')'){}
                    else{ push(tempchar); continue;}

                if(tempchar==')')
                {
                    while(peek()!='(')
                    {
                        ans+=pop();
                    }
                    char garbage=pop();
                }

                else if(tempchar=='^')
                {
                    while(peek()=='^')
                    { ans+=pop();
                    }
                    push(tempchar);
                }

                else if(tempchar=='*'|| tempchar=='/')
                { while( peek()=='*' || peek()=='/'|| peek()=='^')
                { ans+=pop();
                }
                    push(tempchar);
                }


                else if(tempchar=='+' || tempchar=='-')
                {
                    while(peek()=='-' || peek()=='*' || peek()=='/' || peek()=='+'|| peek()=='^')
                    {

                        ans+=pop();
                    }
                    push(tempchar);


                }



            }
        }
        while(sp!=-1)
        {
            ans+=pop();
        }

        return ans;

    }

    void emptystack()
    { for(i=0;i<100;i++)
    {A[i]=' '; sp=-1;}
    }

    String convert_inToPre(String ST)
    {
        String STR="";
        for (i=ST.length()-1; i>=0; i--)
        {
            if (ST.charAt(i)=='(')
                STR+=')';
            else if (ST.charAt(i)==')')
                STR+='(';
            else
                STR+=ST.charAt(i);
        }
        String S1= convert_inToPost(STR);
        String Ans="";
        for (i=S1.length()-1; i>=0; i--)
        {
            Ans+=S1.charAt(i);
        }
        return Ans;

    }

    String D[]= new String[100];
    int po=-1;

    String Post_toInfi(String S)
    {
        emptystack2();
        for (i=0;i<S.length();i++)
        { char ch= S.charAt(i);

            if(Character.isAlphabetic(ch))
            { push2(ch+"");
            }
            else
            { String T1=pop2();
                String T2=pop2();
                String T3= "("+T2+")"+ch+"("+T1+")";
                push2(T3);

            }
        }
        String Ans=pop2();
        return Ans;
    }

    String Pre_toInfi(String S)
    {
        emptystack2();
        S=reverse(S);
        for(i=0;i<S.length();i++)
        {char ch=S.charAt(i);

            if(Character.isAlphabetic(ch))
            {push2(ch+"");
            }
            else
            { String T1=pop2();
                String T2=pop2();
                String T3= "("+T1+")"+ch+"("+T2+")";
                push2(T3);

            }
        }
        String Ans=pop2();
        return Ans;
    }











    String reverse(String S)
    { String Ans="";
        for(i=S.length()-1;i>=0;i--)
        { Ans+=S.charAt(i);
        }
        return Ans;
    }

    void emptystack2()
    { for(i=0;i<100;i++)
    {D[i]=""; po=-1;}
    }

    void push2(String in)
    {
        po++;
        D[po]=in;
    }
    String pop2()
    {
        String val;
        if(po!=-1)
        { val=D[po];
            po--;
            return val;
        }
        else
        {return "$";}
    }
    String peek2()
    {
        if(po!=-1)return D[po];
        else return "$";
    }

    void push(char in)
    {
        sp++;
        A[sp]=in;
    }
    char pop()
    {
        char val;
        if(sp!=-1)
        { val=A[sp];
            sp--;
            return val;
        }
        else
        {return '$';}
    }
    char peek()
    {
        if(sp!=-1)return A[sp];
        else return '$';
    }



}





