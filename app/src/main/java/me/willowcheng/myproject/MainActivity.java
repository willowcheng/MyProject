package me.willowcheng.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.iconics.typeface.FontAwesome;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<ProjectItem> projectItemList = new ArrayList<>();
        for (int dummy_i = 0; dummy_i < 7; dummy_i++) {
            projectItemList.add(new ProjectItem("Name" + String.valueOf(dummy_i),
                    "Description" + String.valueOf(dummy_i),
                    "Due Date" + String.valueOf(dummy_i)));
        }
        ListView projectListView = (ListView) findViewById(R.id.project_list);
        ProjectAdapter adapter = new ProjectAdapter(this, projectItemList);
        projectListView.setAdapter(adapter);
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
