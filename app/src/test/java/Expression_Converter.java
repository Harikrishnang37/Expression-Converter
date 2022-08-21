
public class Expression_Converter {

    char A[] =new char[100];
    int sp=-1; int i,j;

    String convert_inToPost(String S)
    {
        int len=S.length();
        String ans="";

        for(i=0;i<len;i++)
        {
            char tempchar= S.charAt(i);

            if(Character.isAlphabetic(tempchar))
                ans+=tempchar;
            else if (tempchar=='+' || tempchar=='-' || tempchar=='*' || tempchar=='/' || tempchar=='(' || tempchar==')')
            {
                if( tempchar=='('|| peek()=='(')
                    push(tempchar);

                else if(tempchar==')')
                {
                    while(peek()!='(')
                    {
                        ans+=pop();
                    }
                    char garbage=pop();
                }

                else if(tempchar=='/' && peek()!='*' && peek()!='/')
                {
                    push(tempchar);
                }
                else if(tempchar=='/' && peek()=='*')
                {
                    ans+=pop();
                    push(tempchar);
                }
                else if(tempchar=='/' && peek()=='/')
                {
                    ans+=pop();
                    push(tempchar);
                }

                else if(tempchar=='*' && peek()!='/' && peek()!='*')
                {
                    push(tempchar);
                }
                else if(tempchar=='*' && peek()=='/')
                {
                    ans+=pop();
                    push(tempchar);
                }
                else if(tempchar=='*' && peek()=='*')
                {
                    ans+=pop();
                    push(tempchar);
                }

                else if(tempchar=='+')
                {
                    if(peek()=='-' || peek()=='*' || peek()=='/' || peek()=='+')
                    {
                        while(peek()=='-' || peek()=='*' || peek()=='/' || peek()=='+')
                        {
                            if (peek()=='$')
                                break;
                            ans+=pop();
                        }
                        push(tempchar);
                    }
                    else push(tempchar);
                }


                else if(tempchar=='-')
                {
                    if(peek()=='-' || peek()=='*' || peek()=='/' || peek()=='+')
                    {
                        while(peek()=='-' || peek()=='*' || peek()=='/' || peek()=='+')
                        {
                            ans+=pop();
                        }
                        push(tempchar);
                    }
                    else push(tempchar);
                }
            }
        }
        while(sp!=-1)
        {
            ans+=pop();
        }

        return ans;

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
