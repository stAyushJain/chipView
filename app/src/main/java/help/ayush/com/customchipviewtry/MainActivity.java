package help.ayush.com.customchipviewtry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout mainView;
    int countParent = 0, countChild = 0, checkWidth=0;
    boolean isFirstTime = true;
    LinearLayout subLayout;
    EditText myData;
    int widthParent, widthChild;
    Button add,refreshAndDelete;
    View textViewChild;
    ScrollView myScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = (LinearLayout)findViewById(R.id.mainView);
        add = (Button)findViewById(R.id.add);
        myData = (EditText)findViewById(R.id.myData);
        myScrollView = (ScrollView)findViewById(R.id.myScrollView);
        refreshAndDelete = (Button)findViewById(R.id.refreshAndDelete);



        mainView.post(new Runnable() {
            @Override
            public void run() {
//                height = mainView.getHeight();
                widthParent = mainView.getWidth();
                Log.d("calendercall: ",  "" + widthParent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myData.getText().toString().trim().equals(""))
                {
                    return;
                }
                addToCustomGrid(myData.getText().toString());
            }
        });

    }

    public void addNewLayOut()
    {
        subLayout = new LinearLayout(this);
        subLayout.setOrientation(LinearLayout.HORIZONTAL);
        subLayout.setId(countParent);
        subLayout.setPadding(2,2,2,2);
        countParent++;
        mainView.addView(subLayout);
    }

    public void addToCustomGrid(String string)
    {
        if(isFirstTime)
        {
            isFirstTime = false;
            addNewLayOut();
            View customView = ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.text_lable,null);
            customView.setId(countChild);
//                    customText  string
            ((TextView)customView.findViewById(R.id.customText)).setText(string);
            customView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int localWidth = customView.getMeasuredWidth();
            widthChild = localWidth;
//                    int height = customView.getMeasuredHeight();
            countChild++;
            checkWidth++;
            subLayout.addView(customView);
            customView.requestFocus();
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   onGridItemSelected(view);
                }
            });
            myScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            return;
        }
        Log.d("onClick: checkWidth", ""+checkWidth);

        View customView1 = ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.text_lable,null);
        ((TextView)customView1.findViewById(R.id.customText)).setText(string);
//                    customText  string

        customView1.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int localWidth = customView1.getMeasuredWidth();
        widthChild = widthChild +localWidth;



        if(widthChild <= widthParent)
        {
            View customView = getLayoutInflater().inflate(R.layout.text_lable,null,false);
            ((TextView)customView.findViewById(R.id.customText)).setText(string);
            customView.setId(countChild);
            countChild++;
            checkWidth++;
            subLayout.addView(customView);
            customView.requestFocus();
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   onGridItemSelected(view);
                }
            });
        }
        else
        {
            checkWidth = 0;
            widthChild = 0;
            addNewLayOut();
            View customView = ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.text_lable,null);
            ((TextView)customView.findViewById(R.id.customText)).setText(string);
            customView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int locallWidth = customView.getMeasuredWidth();
            widthChild = widthChild +locallWidth;
            customView.setId(countChild);
            countChild++;
            checkWidth++;
            subLayout.addView(customView);

            customView.requestFocus();
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGridItemSelected(view);
                }
            });
        }

        myScrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void onGridItemSelected(View view) {

//        for shikha

        int id = view.getId();
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        String value = ((TextView)view.findViewById(R.id.customText)).getText().toString();
        Log.d("Clicked: ", "id is "+id+" text is "+value);

//        for PuspUUU

//        refreshAndDelete.setVisibility(View.VISIBLE);

    }
}
