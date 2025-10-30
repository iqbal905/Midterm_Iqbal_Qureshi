package ca.gbc.midterm_iqbal_qureshi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText numberInput;
    Button generateBtn, historyBtn;
    ListView listView;
    ArrayList<String> tableList;
    ArrayAdapter<String> adapter;
    int currentNumber = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.numberInput);
        generateBtn = findViewById(R.id.generateBtn);
        historyBtn = findViewById(R.id.historyBtn);
        listView = findViewById(R.id.listView);

        tableList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableList);
        listView.setAdapter(adapter);

        generateBtn.setOnClickListener(v -> generateTable());
        historyBtn.setOnClickListener(v -> openHistory());
        listView.setOnItemClickListener((parent, view, position, id) -> confirmDelete(position));
    }

    private void generateTable() {
        String input = numberInput.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        currentNumber = Integer.parseInt(input);
        tableList.clear();

        for (int i = 1; i <= 10; i++) {
            tableList.add(currentNumber + " × " + i + " = " + (currentNumber * i));
        }
        adapter.notifyDataSetChanged();

        if (!DataStore.historyNumbers.contains(currentNumber)) {
            DataStore.historyNumbers.add(currentNumber);
        }
    }

    private void confirmDelete(int position) {
        String item = tableList.get(position);

        new AlertDialog.Builder(this)
                .setTitle("Delete Row")
                .setMessage("Delete " + item + "?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    tableList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Deleted: " + item, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void openHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
