package org.Giraffe;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;


public class GameCall extends Activity{

	GameView view;
	SurfaceView surface;
	SurfaceHolder holder;
	GameController controller;
	GameModel model;
	Context context;
	private static boolean mPaused=false;
	static final int DIALOG_PAUSED_ID = 0;
	static final int DIALOG_GAMEOVER_ID = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game);
        
        surface=(SurfaceView)findViewById(R.id.gSurface);
        context=surface.getContext();
        model=new GameModel(context);
        
        holder=surface.getHolder();
        
        controller= new GameController(model);
        surface.setOnTouchListener(controller);
        
        view=new GameView(controller, holder, model, context);
        surface.getHolder().addCallback(view);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
       
    }
    //overrides the back button
    @Override
    public void onBackPressed() {
    // do something on back.
    	//setContentView(R.layout.pausescreen);
    	
    	//gameThread.setRunning(false);
    	//gameThread.run();
    	mPaused=true;
    	showDialog(0);
    	//gameThread.suspend();
    return;
    }
    @Override
    protected void onPause() {
        super.onPause();
        //mPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mPaused = false;
    }
 
    
    
    
    
    @Override
	protected Dialog onCreateDialog(int id) {
    	Dialog dialog;
    	
		if (id == DIALOG_PAUSED_ID) {
			//Log.d("OnDialog", "Dialog is totally called right now.");
			dialog = new AlertDialog.Builder(this)
            .setMessage("Would you like to quit?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	mPaused=false;
                    GameCall.this.finish();
               }
           }).setNegativeButton("No", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
            	   mPaused=false;
            	  // Log.d("WTF", "It's totally never gettign here.");
            	   dialog.dismiss();
            	   
              }
          })
            .create();
		} 
		
		else 
		{
			dialog = super.onCreateDialog(id);
		}
		return dialog;
    }
    
    public static boolean getmPaused()
    {
    	return mPaused;
    }

}