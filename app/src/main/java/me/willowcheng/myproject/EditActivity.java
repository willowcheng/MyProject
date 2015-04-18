package me.willowcheng.myproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditActivity extends ActionBarActivity {

    private final String TAG = "EditActivity";
    private final DatabaseHandler databaseHandler = new DatabaseHandler(this);
    private TextView showdate;
    private Button setdate;
    private int year;
    private int month;
    private int day;
    private int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        final EditText courseTitle = (EditText) findViewById(R.id.CourseTitle);
        final EditText courseNumber = (EditText) findViewById(R.id.CourseNumber);
        final EditText instructorName = (EditText) findViewById(R.id.InstructorName);
        final EditText projectNumber = (EditText) findViewById(R.id.ProjectNumber);
        final EditText projectDescription = (EditText) findViewById(R.id.ProjectDescription);
        final Button confirmButton = (Button) findViewById(R.id.confirm);

        showdate = (TextView) this.findViewById(R.id.DueDate);
        setdate = (Button) this.findViewById(R.id.DueDate);
        //初始化Calendar日历对象
        Calendar mycalendar = Calendar.getInstance(Locale.CANADA);
        Date mydate = new Date(); //获取当前日期Date对象
        mycalendar.setTime(mydate);////为Calendar对象设置时间为当前日期

        year = mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        month = mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        day = mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
        showdate.setText(year + "-" + (month + 1) + "-" + day);
        showdate.setTextColor(0xff888888);

        //添加单击事件--设置日期
        setdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * 构造函数原型：
                 * public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack,
                 * int year, int monthOfYear, int dayOfMonth)
                 * content组件运行Activity，
                 * DatePickerDialog.OnDateSetListener：选择日期事件
                 * year：当前组件上显示的年，monthOfYear：当前组件上显示的月，dayOfMonth：当前组件上显示的第几天
                 *
                 */
                //创建DatePickerDialog对象
                DatePickerDialog dpd = new DatePickerDialog(EditActivity.this, Datelistener, year, month, day);
                dpd.show();//显示DatePickerDialog组件
            }
        });

        final Intent intent = getIntent();
        if (intent.getExtras() != null) {

            projectId = intent.getIntExtra("id", 0);
            courseTitle.setText(intent.getStringExtra("Course Title"));
            courseNumber.setText(intent.getStringExtra("Course Number"));
            instructorName.setText(intent.getStringExtra("Instructor"));
            projectNumber.setText(intent.getStringExtra("Project Number"));
            projectDescription.setText(intent.getStringExtra("Project Description"));
            showdate.setText(intent.getStringExtra("Due Date"));
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent.getExtras() == null) {
                    databaseHandler.addProject(new ProjectItem(courseTitle.getText().toString(),
                            courseNumber.getText().toString(),
                            instructorName.getText().toString(),
                            projectNumber.getText().toString(),
                            projectDescription.getText().toString(),
                            showdate.getText().toString()));
                } else {
                    Log.d(TAG, courseTitle.getText().toString());
                    databaseHandler.updateProject(new ProjectItem(projectId, courseTitle.getText().toString(),
                            courseNumber.getText().toString(),
                            instructorName.getText().toString(),
                            projectNumber.getText().toString(),
                            projectDescription.getText().toString(),
                            showdate.getText().toString()));
                    Log.d(TAG, databaseHandler.getProject(projectId).getName());
                }

                databaseHandler.close();
                finish();
            }
        });

    }

    private DatePickerDialog.OnDateSetListener Datelistener = new DatePickerDialog.OnDateSetListener() {
        /**params：view：该事件关联的组件
         * params：myyear：当前选择的年
         * params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */
        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {


            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
            year = myyear;
            month = monthOfYear;
            day = dayOfMonth;
            //更新日期
            updateDate();

        }

        private void updateDate() {
            showdate.setText(year + "-" + (month + 1) + "-" + day);
            showdate.setTextColor(0xff0000ff);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);

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
                intent.setClass(this, TaskActivity.class);
                startActivity(intent);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
