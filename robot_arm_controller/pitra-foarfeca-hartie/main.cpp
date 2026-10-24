#include <iostream>
#include <ctime>
#include <stdlib.h>
#include <windows.h>
using namespace std;
char p='p', f='f',h='h',a,c;
int i;
void delay(unsigned int mseconds)
{
    clock_t goal = mseconds + clock();
    while (goal > clock());
}
void computerChoice()
{
    srand(time(0));
    int o=rand()%3+1;
    if(o==1)
        c='p';
    if(o==2)
        c='f';
    if(o==3)
        c='h';
}
void playerChoice()
{
    cout<<"Pentru piatra: p;"<<endl;
    cout<<"Pentru foarfece: f;"<<endl;
    cout<<"Pentru hartie: h."<<endl;
    cout<<"Alegerea ta este: "; cin>>a;
}
bool checkWin()
{
    if(a=='p'&&c=='h')
        cout<<"Ai pierdut!";
    if(a=='p'&&c=='f')
        cout<<"Ai castigat!";
    if(a=='f'&&c=='p')
        cout<<"Ai pierdut!";
    if(a=='f'&&c=='h')
        cout<<"Ai castigat!";
    if(a=='h'&&c=='f')
        cout<<"Ai pierdut!";
    if(a=='h'&&c=='p')
        cout<<"Ai castigat!";
    if(a==c)
        cout<<"Egalitate!";
}
void piatra()
{
    cout<<"  _____";
    cout<<endl;
    cout<<" -     -";
    cout<<endl;
    cout<<"|       |";
    cout<<endl;
    cout<<"|       |";
    cout<<endl;
    cout<<" -     -";
    cout<<endl;
    cout<<"  -----";
    cout<<endl;
}
void foarfeca()
{
    cout<<"O   /";
    cout<<endl;
    cout<<" \\ /";
    cout<<endl;
    cout<<"  X";
    cout<<endl;
    cout<<" / \\";
    cout<<endl;
    cout<<"O   \\";
    cout<<endl;
}
void hartie()
{
    cout<<"   _____";
    cout<<endl;
    cout<<"  /    /";
    cout<<endl;
    cout<<" /    /";
    cout<<endl;
    cout<<"/    /";
    cout<<endl;
    cout<<"-----";
    cout<<endl;
}
void vs()
{
    cout<<"\\    /  __ ";
    cout<<endl;
    cout<<" \\  /  |__ ";
    cout<<endl;
    cout<<"  \\/  O __| O";
    cout<<endl;
}
int main()
{
    playerChoice();
    computerChoice();
    system("cls");
    for(i=1;i<=5;i++)
    {piatra();
    delay(100);
    system("cls");
    foarfeca();
    delay(100);
    system("cls");
    hartie();
    delay(100);
    system("cls");}
    if(a=='p')
        piatra();
    else if(a=='f')
        foarfeca();
    else
        hartie();
    vs();
    cout<<endl;
    if(c=='p')
        piatra();
    else if(c=='f')
        foarfeca();
    else
        hartie();
    cout<<endl;
    cout<<endl;
    if(a=='p')
        cout<<"piatra";
    else if(a=='f')
        cout<<"foarfeca";
    else
        cout<<"hartie";
    cout<<"VS";
    if(c=='p')
        cout<<"piatra";
    else if(c=='f')
        cout<<"foarfeca";
    else
        cout<<"hartie";
    cout<<endl;
    checkWin();
}
