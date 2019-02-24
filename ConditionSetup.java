package com.example.peeyushrai.healthscanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class ConditionSetup extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    private String[] moviewList;
    private Button conditionsFinishButton;
    public static ArrayList<ConditionNames> movieNamesArrayList = new ArrayList<ConditionNames>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_setup);

        // Generate sample data

        moviewList = new String[]{"Vegan", "Vegetarian", "Diabetes type 1", "Diabetes type 2", "Lactose Intolerance",
                "High Blood Pressure", "Low Blood Pressure", "Alzheimers", "ADD/ADHD", "Peanut Allergy", "Celiac Disease", "Low Protein Diet", "Wheat Allergy"};

        // Locate the ListView in listview_main.xml
        list =  findViewById(R.id.listview);

        movieNamesArrayList = new ArrayList<>();

        for (int i = 0; i < moviewList.length; i++) {
            ConditionNames movieNames = new ConditionNames(moviewList[i]);
            // Binds all strings into an array
            movieNamesArrayList.add(movieNames);
        }

        // Pass results to com.example.peeyushrai.healthscanner.ListViewAdapter Class
        adapter = new ListViewAdapter(this);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ConditionSetup.this, movieNamesArrayList.get(position).getConditionName(), Toast.LENGTH_SHORT).show();
            }
        });

        conditionsFinishButton = findViewById(R.id.ConditionToHome);
        conditionsFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}

//    THIRD METHOD:
//private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_condition_setup);
//        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);
//    }


//    SECOND METHOD:
//    private EditText filterText;
//
//    Button finishButton;
//    private ArrayAdapter<String> listAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        filterText = (EditText)findViewById(R.id.editText);
//
//        ListView itemList = (ListView)findViewById(R.id.listView);
//
//        String [] listViewAdapterContent = {"School", "House", "Building", "Food", "Sports", "Dress", "Ring"};
//
//        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listViewAdapterContent);
//
//        itemList.setAdapter(listAdapter);
//
//        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//// make Toast when click
//                Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
//            }
//        });
//        filterText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                MainActivity.this.listAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//// Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//// Handle action bar item clicks here. The action bar will
//// automatically handle clicks on the Home/Up button, so long
//// as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
////noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    finishButton = findViewById(R.id.finishAcc);
//        finishButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent conditionToLogin = new Intent(ConditionSetup.this, UserLogin.class);
//            startActivity(conditionToLogin);
//        }
//    });


//    FIRST METHOD:
//    Button finishButton;
//    EditText textBox;
//    TextView text;
//    ListView list;
//    String conditions_list[]={"Alzheimers","Diabetes type 1","Diabetes type 2","Lactose Intolerance","High Blood Pressure","Low Blood Pressure","Nepal","Canada","Sri-Lanka","Russia"};
//    ArrayAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textBox=(EditText)findViewById(R.id.textBox);
//        text=(TextView)findViewById(R.id.text);
//        list=(ListView)findViewById(R.id.list);
//
//        adapter=new ArrayAdapter(this,R.layout.list_item,R.id.text,conditions_list);
//        list.setAdapter(adapter);
//
//
//        textBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                adapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });



