package apps.niksman.jobinterviw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText input_et;
    TextView output_tv;
    Button convertButton;
    ImageButton refreshImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pronalazenje widget-a sa layout-a
        fieldsInitialization();


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // char[] koji pomocu metode getInput() preuzima unos korisnika i razdvaja String u char-ove i pritom izbacuje nezeljene karaktere
                final char[] charray = getInput();

                // Lista objekata u koju se smesta konvertovani char[] pomocu metode convertChanges()
                ArrayList<Character> charlist = convertChanges(charray);

                // Gradjenje String-a iz ArrayList<Character> pomocu metode getToString()
                String output = getToString(charlist);

                // Metoda setOutput() salje Ispis na textView, a metoda stringTrimer() iseca poslednji zarez iz String-a
                setOutput(stringTrimer(output));
            }
        });

        // Brz refresh editText i textView polja radi mogucnosti novog unosa
        refreshImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_et.setText("");
                output_tv.setText(R.string.output);
            }
        });


    }

    private String stringTrimer(String s) {
        if (s!=null && s.length()>0 && s.charAt(s.length()-1)==',') {
            s = s.substring(0, s.length()-1);
        }
        return s;
    }

    private String getToString(ArrayList<Character> c) {
        StringBuilder builder = new StringBuilder(c.size());
        for (Character ch : c) {
            builder.append(ch + ",");
        }
        return builder.toString();
    }

    private void setOutput(String s) {
        output_tv.setText(s);
    }

    private ArrayList<Character> convertChanges(char [] c) {
        ArrayList<Character> charlista = new ArrayList<Character>();
        int counter = 1;
        for (int i = 0; i<c.length; i++){
            for (int j = 0; j<counter; j++){
                System.out.print(c[i] + ",");
                charlista.add(c[i]);
            }
            counter++;
        }
        return charlista;
    }

    private char[] getInput() {
        String input = input_et.getText().toString();
        String replaced = input.replaceAll(getString(R.string.unwanted_characters), "");
        char[] charray = replaced.toCharArray();
        return charray;
    }

    private void fieldsInitialization(){
        input_et = (EditText) findViewById(R.id.editText);
        output_tv = (TextView) findViewById(R.id.textView);
        convertButton = (Button) findViewById(R.id.button);
        refreshImageButton = (ImageButton) findViewById(R.id.imageButton);
    }
}
