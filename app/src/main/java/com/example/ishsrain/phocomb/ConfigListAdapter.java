package com.example.ishsrain.phocomb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.ishsrain.phocomb.ConfigFragment.OnListFragmentInteractionListener;

public class ConfigListAdapter extends RecyclerView.Adapter<ConfigListAdapter.Holder> {

  // Config Object
  ConfigObject mConfig;

  // Listener
  private final OnListFragmentInteractionListener mListener;

  // xml Resource
  int mResource;

  public ConfigListAdapter(int resource, ConfigObject config,
      OnListFragmentInteractionListener Listener) {

    // XML
    mResource = resource;

    // Listener
    mListener = Listener;

    // List
    mConfig = config;

  }

  @Override
  public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    // View
    View view = LayoutInflater.from(parent.getContext()).inflate(mResource, parent, false);

    // Holder
    Holder holder = new Holder(view);

    return holder;
  }

  @Override
  public void onBindViewHolder(Holder holder, int position) {

    // Set
    holder.mTextView.setText(mConfig.getText(position));
    if (mResource == R.layout.config_list_check) {
//    if(holder.mCheckBox != null) {
        holder.mCheckBox.setChecked(mConfig.getCheck(position));
        holder.mCheckBox.setClickable(false);
    }
  }

  @Override
  public int getItemCount() {
    if(mConfig != null) {
      return mConfig.getSize();
    }
    else {
      return 0;
    }
  }

  // ViewHolder is one of ListView.
  public class Holder extends RecyclerView.ViewHolder {

    public final TextView mTextView;
    public final CheckBox mCheckBox;

    public Holder(View view) {
      super(view);

      mTextView = (TextView) view.findViewById(R.id.textView);
      mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);

      view.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {

          // Get Position
          int position = getAdapterPosition();

          // Get Item
          if (!mConfig.getCheck(position)) {
            mConfig.setCheck(position, true);
          } else {
            mConfig.setCheck(position, false);
          }
          notifyDataSetChanged();

          // Call Listener
          mListener.onListFragmentInteraction(mConfig, position);
        }
      });
    }
  }

}
