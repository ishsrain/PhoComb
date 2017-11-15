package com.example.ishsrain.phocomb;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConfigFragment extends Fragment {

  // Listener
  private OnListFragmentInteractionListener mListener;

  // View
  ConfigListAdapter mAdapter;

  // Resource
  int mResource;

  // Config
  ConfigObject mConfig;

  public ConfigFragment() {
  }

  // For Recreate
  public static ConfigFragment newInstance(int resource, ConfigObject config) {
    ConfigFragment fragment = new ConfigFragment();

    fragment.mResource = resource;
    fragment.mConfig = config;

    Bundle args = new Bundle();
    args.putInt("resource", resource);
    //args.putSerializable("config", config);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      mResource = getArguments().getInt("resource");
      //mConfig = getArguments().getSerializable("config");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_config, container, false);
    View view = rootView.findViewById(R.id.RecyclerView);

    if(view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;

      // Grid
//      recyclerView.setLayoutManager(new LinearLayoutManager(context));
      recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

//      mView.setHasFixedSize(true);

      // Adapter
      mAdapter = new ConfigListAdapter(mResource, mConfig, mListener);
      recyclerView.setAdapter(mAdapter);
    }

    return rootView;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnListFragmentInteractionListener) {
      mListener = (OnListFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnListFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onResume() {
    super.onResume();
    if(mConfig != null) {
      mListener.onListFragmentInteraction(mConfig.getType());
    }
  }

  public interface OnListFragmentInteractionListener {

    // TODO: Update argument type and name
    void onListFragmentInteraction(ConfigObject config, int position);

    void onListFragmentInteraction(int type);
  }
}
