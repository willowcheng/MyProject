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
    private final DatabaseHandler databaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);
        Log.d(TAG, "Set content");

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

    @Override
    protected void onResume() {
        super.onResume();
        ListView projectListView = (ListView) findViewById(R.id.project_list);
        final List<ProjectItem> projectItemList = databaseHandler.getAllProjectItems();
        final ProjectAdapter adapter = new ProjectAdapter(this, projectItemList);
        projectListView.setAdapter(adapter);
        projectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditActivity.class);
                intent.putExtra("id", projectItemList.get(i).getId());
                intent.putExtra("Course Title", projectItemList.get(i).getName());
                intent.putExtra("Course Number", projectItemList.get(i). getCourseNumber());
                intent.putExtra("Instructor", projectItemList.get(i).getInstructor());
                intent.putExtra("Project Number", projectItemList.get(i).getProjectNumber());
                intent.putExtra("Project Description", projectItemList.get(i).getProjectNumber());
                intent.putExtra("Due Date", projectItemList.get(i).getDue());
                startActivity(intent);
            }
        });
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

}
