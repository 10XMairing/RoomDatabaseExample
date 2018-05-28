package code.tenx.tests;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

     AppViewModel appViewModel;
    private static String LOG_TAG = "MainActivity";
    public static final int NEW_WORD_REQUEST_CODE = 1;

    private ToDoAdapter mToDoAdapter;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_WORD_REQUEST_CODE && resultCode == RESULT_OK){
                ToDo todo = data.getParcelableExtra(SecondActivity.EXTRA_REPLY);
                appViewModel.insertTodo(todo);
            Toast.makeText(getApplicationContext(), "Task added to database", Toast.LENGTH_SHORT).show();
        }else{
                Toast.makeText(getApplicationContext(), "Task empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mToDoAdapter = new ToDoAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mToDoAdapter);



        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllToDos().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(@Nullable List<ToDo> toDos) {
                mToDoAdapter.setToDos(toDos);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabDelete = findViewById(R.id.fabdelete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent,NEW_WORD_REQUEST_CODE);


            }
        });
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appViewModel.deleteAll();
            }
        });







    }
}
