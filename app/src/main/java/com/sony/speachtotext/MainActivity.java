package com.sony.speachtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Region;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView result_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_txt= (TextView)findViewById(R.id.txtview);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.mic)
        {
            PromptSpeachInput();
        }
    }

    public void PromptSpeachInput()
    {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT , "Say Something");
        try {
            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(MainActivity.this , "Sorry Your Device Doesn't Support Speach language!", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int request_code , int result_code , Intent i)
    {
        super.onActivityResult(request_code,result_code,i);
        switch (request_code)
        {
            case 100: if(result_code==RESULT_OK &&  i !=null)
            {
                ArrayList<String> result  = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                result_txt.setText(result.get(0));
            }
                break;
        }
    }
}

