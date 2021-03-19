package pollub.ism.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isXTurn = false;

    //tablica wartosci:
    //0 - puste pole
    //1 - zaznaczono X
    //2 - zaznaczono Y
    private int[][] tab = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void kliknieciePrzycisku(View view){
        String nazwaPrzycisku = view.getResources().getResourceEntryName(view.getId());
        Button button = (Button) findViewById(view.getId());
        if(button.getText().equals(" ") && isXTurn){
            System.out.println("This happens");
            button.setText("X");
            tab[Character.valueOf(nazwaPrzycisku.charAt(7))-49][Character.valueOf(nazwaPrzycisku.charAt(9))-49]=1;
            sprawdzenieWarunku();
            isXTurn=false;
        }
        else if(button.getText().equals(" ") && !isXTurn){
            button.setText("O");
            tab[Character.valueOf(nazwaPrzycisku.charAt(7))-49][Character.valueOf(nazwaPrzycisku.charAt(9))-49]=2;
            sprawdzenieWarunku();
            isXTurn=true;
        }

    }

    private void sprawdzenieWarunku(){
        int X = 1, Y = 2;
        for(int i=0;i<3;i++){
            if(tab[0][i] == X && tab[1][i] == X && tab[2][i] == X) {
                win(X);
                return;
            }
            if(tab[i][0] == X && tab[i][1] == X && tab[i][2] == X) {
                win(X);
                return;
            }
        }
        for(int i=0;i<3;i++){
            if(tab[0][i] == Y && tab[1][i] == Y && tab[2][i] == Y) {
                win(Y);
                return;
            }
            if(tab[i][0] == Y && tab[i][1] == Y && tab[i][2] == Y) {
                win(Y);
                return;
            }
        }

        if(tab[0][0]==X && tab[1][1]==X && tab[2][2]==X) win(X);
        if(tab[0][2]==X && tab[1][1]==X && tab[2][0]==X) win(X);

        if(tab[0][0]==Y && tab[1][1]==Y && tab[2][2]==Y) win(Y);
        if(tab[0][2]==Y && tab[1][1]==Y && tab[2][0]==Y) win(Y);

    }

    private void win(int n) {
        if(n==1)
            Toast.makeText(this,"Wygrały X", Toast.LENGTH_LONG).show();
        else if (n==2)
            Toast.makeText(this,"Wygrały O", Toast.LENGTH_LONG).show();
    }
}