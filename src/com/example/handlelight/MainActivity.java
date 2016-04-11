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
	//�����������ȡһ�����������
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
        //������Ļ����������
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
        //�����ֵ�ر�ʱ���ͼƬ
        imageset.setImageResource(R.drawable.light_off);
        //������Ļ  
    }  
  
    @Override  
    public void onClick(View v) {  
        ToggleButton tb = (ToggleButton) v;  
        //����getPatameters���������Parameters���������ò���
        Camera.Parameters param = camera.getParameters();  
        if(!tb.isChecked()){  
        	//���������������FLASH_MODE_TORCH  ���������ƣ�FLASH_MODE_ON ֻ��һ��       
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
