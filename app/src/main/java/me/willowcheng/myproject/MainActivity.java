package me.willowcheng.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.iconics.typeface.FontAwesome;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);
        Log.d(TAG, "Set content");

        final DatabaseHandler databaseHandler = new DatabaseHandler(this);
        for (int dummy_i = 0; dummy_i < 3; dummy_i++) {
            databaseHandler.addProject(new ProjectItem(dummy_i , "Name" + String.valueOf(dummy_i),
                    "Description" + String.valueOf(dummy_i),
                    "Due Date" + String.valueOf(dummy_i)));
        }
        Log.d(TAG, "Add items to database");

        ListView projectListView = (ListView) findViewById(R.id.project_list);
        final List<ProjectItem> projectItemList = databaseHandler.getAllProjectItems();
        final ProjectAdapter adapter = new ProjectAdapter(this, projectItemList);
        projectListView.setAdapter(adapter);
        projectListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                databaseHandler.deleteProject(projectItemList.get(i));
                projectItemList.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        if (itemCount > 0) {
        ActionItemBadge.update(this, menu.findItem(R.id.action_warning_list), FontAwesome.Icon.faw_android, ActionItemBadge.BadgeStyle.DARKGREY, 1);
//        } else {
//            ActionItemBadge.update(this, menu.findItem(R.id.action_shopping), FontAwesome.Icon.faw_shopping_cart, ActionItemBadge.BadgeStyle.DARKGREY, -1);
//        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add: {
                Intent intent = new Intent();
                intent.setClass(this, EditActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_about: {
                Toast.makeText(this, "Clicked about", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.action_warning_list: {
                Toast.makeText(this, "Clicked warning list", Toast.LENGTH_LONG).show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
