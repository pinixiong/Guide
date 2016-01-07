package com.rj.guide;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Guide extends LinearLayout {
	private int isme;
	private String[] columnNames;
	private OnColumnClickListener mListener;

	public Guide(Context context,String[] columnNames,int me) {
		super(context);
		this.setColumnNames(columnNames);
		this.createView();
		this.isme=me;
	}
	
	public interface OnColumnClickListener{
		public void OnColumnClick(int position);
	}
	
	public void setOnColumnClickListener(OnColumnClickListener l){
		this.mListener = l;
	}
	
	private void createView(){
		if(getColumnNames()==null||getColumnNames().length==0)return;
		TextView columnTxv,lineTxv;
		LinearLayout linearLayout;
		final ArrayList<LinearLayout> ll = new ArrayList<>();
		final ArrayList<TextView> tv = new ArrayList<>();

		final String TEXT_COLOR_NORMAL = "#000000";//未选中文本颜色:
		final String chocolate = "#D2691E";//选中文本颜色:巧可力色
		int length = getColumnNames().length;

		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//抛出监听
				mListener.OnColumnClick((Integer)v.getTag());
				int length1 = getColumnNames().length;
				for (int i = 0; i < length1; i++) {
					ll.get(i).setBackgroundColor(Color.CYAN);
					tv.get(i).setTextColor(Color.parseColor(TEXT_COLOR_NORMAL));
				}
				ll.get((Integer) v.getTag()).setBackgroundColor(Color.BLUE);
				tv.get((Integer) v.getTag()).setTextColor(Color.parseColor(chocolate));
			}
		};

		for (int i = 0; i < length; i++) {
			linearLayout = new LinearLayout(getContext());
			ll.add(linearLayout);
			linearLayout.setOrientation(VERTICAL);
			linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 85, 1.0f));
			linearLayout.setGravity(Gravity.CENTER);
			if(i==isme){
			linearLayout.setBackgroundColor(Color.BLUE);}
			else{
				linearLayout.setBackgroundColor(Color.CYAN);
			}

			columnTxv = new TextView(getContext());
			tv.add(columnTxv);
			columnTxv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 80));
			columnTxv.setOnClickListener(listener);
			columnTxv.setBackgroundColor(Color.CYAN);
			columnTxv.setTextColor(Color.parseColor(TEXT_COLOR_NORMAL));
			columnTxv.setText(getColumnNames()[i]);
			columnTxv.setGravity(Gravity.CENTER);
			columnTxv.setTag(i);//设置标号

			lineTxv = new TextView(getContext());
			lineTxv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			linearLayout.addView(columnTxv);
			linearLayout.addView(lineTxv);
			this.addView(linearLayout);	//添加TextView
		}

	}

	private String[] getColumnNames() {
		return columnNames;
	}

	private void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void setIsme(int isme) {
		this.isme = isme;
	}
}