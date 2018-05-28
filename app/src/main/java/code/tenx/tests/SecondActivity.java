package code.tenx.tests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "new_word_sql";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText etTask = findViewById(R.id.etTask);
        final EditText etStart = findViewById(R.id.etStartTime);
        final EditText etEnd = findViewById(R.id.etEndTime);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                String start = etStart.getText().toString();
                String end = etEnd.getText().toString();
                ToDo toDo = new ToDo(task, start, end);
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(etTask.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    replyIntent.putExtra(EXTRA_REPLY,toDo);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

            }
        });

    }
}
