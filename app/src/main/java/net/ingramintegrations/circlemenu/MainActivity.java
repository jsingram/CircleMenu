package net.ingramintegrations.circlemenu;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create our Floating Action Button
        final ImageView fabIconNew = new ImageView(this);
        /*  Set the icon in the center of the Floating Action Button
            Old Deprecated way to get drawable -> getResources().getDrawable(R.drawable.ic_action_new_light)
            Instead, use -> getApplicationContext(), R.drawable.ic_action_new_light)
         */
        fabIconNew.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_new_light));

        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                //.setPosition(FloatingActionButton.POSITION_BOTTOM_LEFT)
                .build();

        // Create your menu items which are also Floating Action Buttons
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        // Create an image view for each menu item
        ImageView menuOption1 = new ImageView(this);
        ImageView menuOption2 = new ImageView(this);
        ImageView menuOption3 = new ImageView(this);
        ImageView menuOption4 = new ImageView(this);

        // Set the icon for each menu item
        menuOption1.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_chat_light));
        menuOption2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_camera_light));
        menuOption3.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_video_light));
        menuOption4.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_place_light));

        // Build the menu with default options: 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(menuOption1).build())
                .addSubActionView(rLSubBuilder.setContentView(menuOption2).build())
                .addSubActionView(rLSubBuilder.setContentView(menuOption3).build())
                .addSubActionView(rLSubBuilder.setContentView(menuOption4).build())
                .attachTo(rightLowerButton)
                //.setStartAngle(360)
                .build();

        // Listen for menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });

        // OnClickListeners for each menu item
        menuOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Option 1", Toast.LENGTH_SHORT).show();
                /*
                    Could also go to an Intent or perform whatever action you want.
                    To go to another Activity, you would use something like:

                    Intent goToActivity = new Intent(getApplicationContext(), Name_Of_Activity.class)
                    startActivity(goToActivity);
                 */
            }
        });

        menuOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Option 2", Toast.LENGTH_SHORT).show();
            }
        });

        menuOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Option 3", Toast.LENGTH_SHORT).show();
            }
        });

        menuOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Option 4", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
