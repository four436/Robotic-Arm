#include <iostream>
#include <stdlib.h>
#include <string>
using namespace std;
char a[3][3]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
int i,j,q,e;
char token='X';
string N;
string n;
bool tie=false;
void table()
{
    cout<<"   |   |   ";
    cout<<endl;
    cout<<" "<<a[0][0] <<" | "<<a[0][1]<<" | "<<a[0][2]<<" ";
    cout<<endl;
    cout<<"   |   |   ";
    cout<<endl;
    cout<<"---|---|---";
    cout<<endl;
    cout<<"   |   |   ";
    cout<<endl;
    cout<<" "<<a[1][0] <<" | "<<a[1][1]<<" | "<<a[1][2]<<" ";
    cout<<endl;
    cout<<"   |   |   ";
    cout<<endl;
    cout<<"---|---|---";
    cout<<endl;
    cout<<"   |   |   ";
    cout<<endl;
    cout<<" "<<a[2][0] <<" | "<<a[2][1]<<" | "<<a[2][2]<<" ";
    cout<<endl;
    cout<<"   |   |   ";
    cout<<endl;
}
void play()
{
    int s;
    if(token=='X')
    { cout<<N<<" selecteaza pozitia: "; cin>>s;}
    if(token=='O')
    { cout<<n<<" selecteaza pozitia: "; cin>>s;}
    if(s==1)
        {i=0; j=0;}
    if(s==2)
        {i=0; j=1;}
    if(s==3)
        {i=0; j=2;}
    if(s==4)
        {i=1; j=0;}
    if(s==5)
        {i=1; j=1;}
    if(s==6)
        {i=1; j=2;}
    if(s==7)
        {i=2; j=0;}
    if(s==8)
        {i=2; j=1;}
    if(s==9)
        {i=2; j=2;}
    else if(s<1||s>9) cout<<"Invalid!";
    if(token=='X'&&a[i][j]!='X'&&a[i][j]!='O')
    { a[i][j]='X'; token='O';}
    else if(token=='O'&&a[i][j]!='X'&&a[i][j]!='O')
    { a[i][j]='O'; token='X';}
    else {cout<<"Spatiul este ocupat!"<<endl; play();}

}
bool CheckWin()
{
    if(a[0][0]==a[0][1]&&a[0][0]==a[0][2])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[1][0]==a[1][1]&&a[1][0]==a[1][2])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[2][0]==a[2][1]&&a[2][0]==a[2][2])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[0][0]==a[1][0]&&a[0][0]==a[2][0])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[0][1]==a[1][1]&&a[0][1]==a[2][1])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[0][2]==a[1][2]&&a[0][2]==a[2][2])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[0][0]==a[1][1]&&a[1][1]==a[2][2])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
        return true;
    }
    if(a[0][2]==a[1][1]&&a[1][1]==a[2][0])
    {
        if(a[0][0]=='O')
            {table(); cout<<n<<" a castigat!";}
        if(a[0][0]=='X')
            {table(); cout<<N<<" a castigat!";}
            return true;
    }
    for(q=0;q<3;q++)
        for(e=0;e<3;e++)
            if(a[q][e]!='X'&&a[q][e]!='O')
                return false;
}
int main()
{
    cout<<"Primul jucator este: "; cin>>N;
    cout<<"Al doilea jucator este: "; cin>>n;
    while(CheckWin()==0)
    {
        table();
        play();
        CheckWin();
        system("CLS");
    }
}
