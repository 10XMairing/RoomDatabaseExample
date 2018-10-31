package code.tenx.tests;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private static String LOG_TAG = "Adapter";
    private Context mCtx;
    private List<ToDo> mList;

    public ToDoAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    public void setToDos(List<ToDo> list){

        this.mList = list;
        notifyDataSetChanged();
        Log.d(LOG_TAG, "notifyDataSetChanged called");
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyvlerview_item_layout, parent, false);

        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDo currentToDo = mList.get(position);

        holder.tvtask.setText(currentToDo.getmTask());
    }



    @Override
    public int getItemCount() {
       if(mList == null){
           return 0;
       }else{
           return mList.size();
       }
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{
        TextView tvtask;
         ToDoViewHolder(View itemView) {
            super(itemView);
            tvtask = itemView.findViewById(R.id.tvTaskOutput);

        }
    }
}
