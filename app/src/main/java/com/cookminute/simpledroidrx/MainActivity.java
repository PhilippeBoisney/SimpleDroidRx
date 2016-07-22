package com.cookminute.simpledroidrx;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cookminute.simpledroidrx.Fragments.Android_REST_Sample_Fragment;
import com.cookminute.simpledroidrx.Fragments.Android_Simple_Sample_Fragment;
import com.cookminute.simpledroidrx.Fragments.Background_Tasks_Fragment;
import com.cookminute.simpledroidrx.Fragments.Error_Handling_Fragment;
import com.cookminute.simpledroidrx.Fragments.Famous_Operators_Fragment;
import com.cookminute.simpledroidrx.Fragments.Hello_World_Fragment;
import com.cookminute.simpledroidrx.Utils.Navigation.FragmentDrawer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.activity_navigation_cordinator) CoordinatorLayout coordinator;

    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        this.configureData();
        this.configureDesign();
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.updateDesign();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    // ****************************************
    // CONFIGURATIONS
    // ****************************************

    private void configureData(){ }

    private void configureDesign() {
        this.configureToolBar();
        this.configureDrawer();
        this.displayView(0);
    }

    private void updateDesign(){

    }

    // ****************************************
    // OTHERS CONFIGURATIONS
    // ****************************************

    private void configureDrawer(){
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }

    private void configureToolBar(){
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    // ****************************************
    // OTHERS
    // ****************************************



    private void displayView(int position) {
        Fragment fragment = null;
        String title ="";
        switch (position) {
            case 0:
                fragment = new Hello_World_Fragment();
                title = "Hello World";
                break;

            case 1:
                fragment = new Famous_Operators_Fragment();
                title = "Famous Operators";
                break;

            case 2:
                fragment = new Error_Handling_Fragment();
                title = "Error Handling";
                break;

            case 3:
                fragment = new Background_Tasks_Fragment();
                title = "Background Tasks";
                break;

            case 4:
                fragment = new Android_Simple_Sample_Fragment();
                title = "Android Simple Sample";
                break;

            case 5:
                fragment = new Android_REST_Sample_Fragment();
                title = "Android REST Sample";
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            mTitle.setText(title);
        }
    }
}
