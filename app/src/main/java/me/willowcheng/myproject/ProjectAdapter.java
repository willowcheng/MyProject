package me.willowcheng.myproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by willowcheng on 4/18/2015.
 */
public class ProjectAdapter extends BaseAdapter{

    private List<ProjectItem> projectItemList;
    private Activity context;

    public ProjectAdapter(Activity context, List<ProjectItem> projectItemList) {
        this.context = context;
        this.projectItemList = projectItemList;
    }

    @Override
    public int getCount() {
        return projectItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return projectItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_row_project, null);
        final TextView nameText = (TextView) view.findViewById(R.id.project_name);
        final TextView descriptionText = (TextView) view.findViewById(R.id.project_description);
        final TextView dueText = (TextView) view.findViewById(R.id.project_due);

        ProjectItem projectItem = projectItemList.get(i);
        nameText.setText(projectItem.getName());
        descriptionText.setText(projectItem.getDescription());
        dueText.setText(projectItem.getDue());

        return view;
    }

    public void removeAll() {
        projectItemList.clear();
        this.notifyDataSetChanged();
    }


}
