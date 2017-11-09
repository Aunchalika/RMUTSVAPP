package app.rmutsv.sampuriwat.rmutsvservice.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.rmutsv.sampuriwat.rmutsvservice.R;

/**
 * Created by lenovo on 9/11/2560.
 */

public class ListViewAdepter extends BaseAdapter{
    private Context context;
    private String[] nameString, categoryString, userString, passString;


    public ListViewAdepter(Context context,
                           String[] nameString,
                           String[] categoryString,
                           String[] userString,
                           String[] passString) {
        this.context = context;
        this.nameString = nameString;
        this.categoryString = categoryString;
        this.userString = userString;
        this.passString = passString;
    }

    @Override
    public int getCount() {
        return nameString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.layout_listview, viewGroup, false);

        TextView nameTextView = view1.findViewById(R.id.txtName);
        TextView categoryTextView = view1.findViewById(R.id.txtCategory);
        TextView userTextView = view1.findViewById(R.id.txtUser);
        TextView passTextView = view1.findViewById(R.id.txtPassword);

        nameTextView.setText(nameString[i]);
        categoryTextView.setText(categoryString[i]);
        userTextView.setText(userString[i]);
        passTextView.setText(passString[i]);


        return view1;
    }
}// main class
