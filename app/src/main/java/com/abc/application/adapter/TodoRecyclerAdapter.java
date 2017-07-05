package com.abc.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.abc.application.R;
import com.abc.application.models.Todo;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>{

    Context context;
    ArrayList<Todo> todoArrayList;


    public TodoRecyclerAdapter(Context context, ArrayList<Todo> todoArrayList) {
        this.context = context;
        this.todoArrayList = todoArrayList;
    }

    public void updateTodos(ArrayList<Todo> newTodoList) {
        this.todoArrayList = newTodoList;
        notifyDataSetChanged();
    }


    /*@Override
    public int getItemViewType(int position) {
        Todo thistodo = todoArrayList.get(position);
        if (thistodo.getCompleted().equals("False")){
            return 0;
        }
        else
            return 1;
    }*/

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        /*if(viewType == 0){
            itemView = li.inflate(R.layout.todo_false_layout , parent , false);
        }
        else
            itemView = li.inflate(R.layout.todo_layout , parent , false);*/
        itemView = li.inflate(R.layout.todo_false_layout , parent , false);

        TodoViewHolder todoViewHolder = new TodoViewHolder(itemView);
        return todoViewHolder;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {

        final Todo thistodo = todoArrayList.get(position);
        holder.title.setText(thistodo.getTitle());
        if(thistodo.getCompleted().equals("false")){
            holder.checkbox.setChecked(false);
        }
        else{
            holder.checkbox.setChecked(true);
        }

    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        CheckBox checkbox;
        public TodoViewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            checkbox = (CheckBox)itemView.findViewById(R.id.checkbox);
        }
    }
}
