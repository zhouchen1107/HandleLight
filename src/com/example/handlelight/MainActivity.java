package com.example.handlelight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ToggleButton;


public class MainActivity extends Activity implements OnClickListener
{

	private ToggleButton toggleButton;  
	private ImageView imageset;
	//打开照相机，获取一个照相机对象
    private Camera camera = Camera.open();  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) 
    {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);  
        toggleButton = (ToggleButton) this.findViewById(R.id.toggleButton1); 
        imageset=(ImageView)findViewById(R.id.image_set);
        toggleButton.setOnClickListener(this); 
        //控制屏幕常亮不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
        //加载手电关闭时候的图片
        imageset.setImageResource(R.drawable.light_off);
        //保持屏幕  
    }  
  
    @Override  
    public void onClick(View v) {  
        ToggleButton tb = (ToggleButton) v;  
        //调用getPatameters方法，获得Parameters对象来设置参数
        Camera.Parameters param = camera.getParameters();  
        if(!tb.isChecked()){  
        	//设置照相机参数，FLASH_MODE_TORCH  持续的亮灯，FLASH_MODE_ON 只闪一下       
            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);  
            //toggleButton.setBackgroundColor(0x330000); 
            imageset.setImageResource(R.drawable.light_on);
        }else{  
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);  
           // toggleButton.setBackgroundColor(0x330000);  
            imageset.setImageResource(R.drawable.light_off);
        }  
        camera.setParameters(param);  
    }  
  
    @Override  
    protected void onDestroy() {  
//      camera.release();  
//      Process.killProcess(Process.myPid());  
       if(camera!=null)
       {
    	   camera.stopPreview(); 
    	   camera.release();
       }
    }  
}
