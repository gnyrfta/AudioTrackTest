package com.example.david.audiotracktest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.provider.DocumentFile;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by david on 2016-08-29.
 */
public class MainActivity extends Activity {
    private String TAG = "MainActivity";
    //These have to be at least accessible outside the scope of their methods, cause gonna have to mix them with each other.
    public byte[] data1;
    public byte[] data2;
    public byte[] data3;
    public byte[] data4;
    public byte[] data5;
    public byte[] data6;
    public byte[] data7;//buffer for button seven.
    public byte[] data8;//buffer for button eight.
    public byte[] data9;//buffer for button nine.
    public byte[] data10;
    public byte[] data11;
    public byte[] data12;
    public int dataSize1;
    public int dataSize2;
    public int dataSize3;
    public int dataSize4;
    public int dataSize5;
    public int dataSize6;
    public int dataSize7;
    public int dataSize8;
    public int dataSize9;
    public int dataSize10;
    public int dataSize11;
    public int dataSize12;

    public static volatile byte[] outputBuffer;
    public static volatile byte[] tempBuffer;
    public volatile boolean changedBuffer=false;
    //
    public volatile boolean addBuffer1=false;
    public volatile boolean addBuffer2=false;
    public volatile boolean addBuffer3=false;
    public volatile boolean addBuffer4=false;
    public volatile boolean addBuffer5=false;
    public volatile boolean addBuffer6=false;
    public volatile boolean addBuffer7=false;
    public volatile boolean addBuffer8=false;
    public volatile boolean addBuffer9=false;
    public volatile boolean addBuffer10=false;
    public volatile boolean addBuffer11=false;
    public volatile boolean addBuffer12=false;
    public volatile boolean addBuffer13=false;
    public volatile boolean addBuffer14=false;
    public volatile boolean addBuffer15=false;
    public volatile boolean addBuffer16=false;
    public volatile boolean addBuffer17=false;
    public volatile boolean addBuffer18=false;
    public volatile boolean addBuffer19=false;
    public volatile boolean addBuffer20=false;
    //
    public volatile boolean letIn1=false;
    public volatile boolean letIn2=false;
    public volatile boolean letIn3=false;
    public volatile boolean letIn4=false;
    public volatile boolean letIn5=false;
    public volatile boolean letIn6=false;
    public volatile boolean letIn7=false;
    public volatile boolean letIn8=false;
    public volatile boolean letIn9=false;
    public volatile boolean letIn10=false;
    public volatile boolean letIn11=false;
    public volatile boolean letIn12=false;

    public volatile int addCounter1=0;
    public volatile int addCounter2=0;
    public volatile int addCounter3=0;
    public volatile int addCounter4=0;
    public volatile int addCounter5=0;
    public volatile int addCounter6=0;
    public volatile int addCounter7=0;
    public volatile int addCounter8=0;
    public volatile int addCounter9=0;
    public volatile int addCounter10=0;
    public volatile int addCounter11=0;
    public volatile int addCounter12=0;
    public volatile int addCounter13=0;
    public volatile int addCounter14=0;
    public volatile int addCounter15=0;
    public volatile int addCounter16=0;
    //
    public volatile boolean removeBuffer1=false;
    public volatile boolean removeBuffer2=false;
    public volatile boolean removeBuffer3=false;
    public volatile boolean removeBuffer4=false;
    public volatile boolean removeBuffer5=false;
    public volatile boolean removeBuffer6=false;
    public volatile boolean removeBuffer7=false;
    public volatile boolean removeBuffer8=false;
    public volatile boolean removeBuffer9=false;
    public volatile boolean removeBuffer10=false;
    public volatile boolean removeBuffer11=false;
    public volatile boolean removeBuffer12=false;
    public volatile boolean removeBuffer13=false;
    public volatile boolean removeBuffer14=false;
    public volatile boolean removeBuffer15=false;
    public volatile boolean removeBuffer16=false;
    //
    public volatile int removeCounter1=0;
    public volatile int removeCounter2=0;
    public volatile int removeCounter3=0;
    public volatile int removeCounter4=0;
    public volatile int removeCounter5=0;
    public volatile int removeCounter6=0;
    public volatile int removeCounter7=0;
    public volatile int removeCounter8=0;
    public volatile int removeCounter9=0;
    public volatile int removeCounter10=0;
    public volatile int removeCounter11=0;
    public volatile int removeCounter12=0;
    public volatile int removeCounter13=0;
    public volatile int removeCounter14=0;
    public volatile int removeCounter15=0;
    public volatile int removeCounter16=0;

    public volatile int bufferLength=200;

    public volatile float numberOfStreams=0;
//Get Drawables:
    private static Drawable greenButtonLightOn = MyApp.context().getResources().getDrawable(R.drawable.green_square_button_with_light);
    private static Drawable greenButtonLightOff = MyApp.context().getResources().getDrawable(R.drawable.green_square_button);
    private static Drawable yellowButtonLightOn = MyApp.context().getResources().getDrawable(R.drawable.yellow_square_button_with_light);
    private static Drawable yellowButtonLightOff = MyApp.context().getResources().getDrawable(R.drawable.yellow_square_button);
    private static Drawable purpleButtonLightOn = MyApp.context().getResources().getDrawable(R.drawable.purple_square_button_with_light);
    private static Drawable purpleButtonLightOff = MyApp.context().getResources().getDrawable(R.drawable.purple_square_button);
    private static Drawable redButtonLightOn = MyApp.context().getResources().getDrawable(R.drawable.red_square_button_with_light);
    private static Drawable redButtonLightOff = MyApp.context().getResources().getDrawable(R.drawable.red_square_button);    //
    private static Drawable blueButtonLightOff = MyApp.context().getResources().getDrawable(R.drawable.blue_square_button3);
    private static Drawable blueButtonLightOn = MyApp.context().getResources().getDrawable(R.drawable.blue_square_button3_light_on);

    //

    //constants needed for the streaming:
    boolean m_stop = false; //Keep feeding data.
    AudioTrack m_audioTrack; //Our audiotrack that we write to.
    Thread audioTrackThread; //Our thread where we write to the AudioTrack.
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /*This is already set in Manifest - might not be necessary. */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    //testAudio -> testAudio4 will not be used when trying to get STREAM to work.
    public void testAudio() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();
            AudioTrack at = new AudioTrack(STREAM_MUSIC, 44100, CHANNEL_OUT_MONO, ENCODING_PCM_16BIT, dataSize, MODE_STATIC);
            at.write(data, 0, data.length);
            int frames = data.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            at.setLoopPoints(0, frames, 3);
            at.play();
        } catch (IOException e) {
            throw e;
        }
    }

    public void testAudio2() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        //TO USE BELOW CODE, ADD AUDIO TRACK.
        /*
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat2_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();

            AudioTrack at = new AudioTrack(STREAM_MUSIC, 44100, CHANNEL_OUT_MONO, ENCODING_PCM_16BIT, dataSize, MODE_STATIC);
            at.write(data, 0, data.length);
            int frames = data.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            at.setLoopPoints(0, frames, 3);
            at.play();
        } catch (IOException e) {
            throw e;
        }*/
    }

    public void testAudio3() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        //TO USE BELOW CODE, ADD AUDIO TRACK:
        /*
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat2_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();
            InputStream is2 = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize2 = wi.readHeader(is2);
            byte[] data2 = new byte[dataSize];
            is2.read(data2, 0, data2.length);
            is2.close();
            byte[] data3 = new byte[dataSize];
            Log.d(TAG, "Entering for-loop.");
            short resMax = 0;
            short resPrevious=0;
            for (int i = 0; i < data2.length; i += 2) {

                short buf1a = data[i + 1];
                short buf2a = data[i];
                buf1a = (short) ((buf1a & 0xff) << 8);
                buf2a = (short) (buf2a & 0xff);
                short buf1b = data2[i + 1];
                short buf2b = data2[i];
                buf1b = (short) ((buf1b & 0xff) << 8);
                buf2b = (short) (buf2b & 0xff);

                short buf1c = (short) (buf1a + buf1b);
                short buf2c = (short) (buf2a + buf2b);

                short res = (short) (buf1c + buf2c);

                if(res>10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
                {
                    if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                    {
                        Log.d(TAG,"res:"+res+"");
                        res = resPrevious;
                    }
                }
                if(res<-10000)
                {
                    if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                    {
                        res = resPrevious;
                    }
                }
                resPrevious=res;
                data3[i] = (byte) res;
                data3[i + 1] = (byte) (res >> 8);
            }
            WavWriter ww = new WavWriter();
            ww.setDataSize((long) dataSize);
            ww.setDataChunk(data3);
            ww.writeToWav("awesome.wav");
            Log.d(TAG, "Exiting for-loop");
            Log.d(TAG, "Resmax = " + resMax + "");
            // AudioTrack at = new AudioTrack(STREAM_MUSIC,44100,CHANNEL_OUT_MONO,ENCODING_PCM_16BIT,dataSize,MODE_STATIC);
            // at.write(data3,0,data3.length);
            int frames = data3.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data3.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            // at.setLoopPoints(0,frames,3);
            // at.play();
        } catch (IOException e) {
            throw e;
        }*/
    }

    public void testAudio4() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        //TO USE BELOW CODE, ADD AUDIO FILE:
        /*
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.sound1_plus_sound2);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();
            ////
            for (int i = 0; i < data.length; i += 2) {

                short buf1a = data[i + 1];
                short buf2a = data[i];
                buf1a = (short) ((buf1a & 0xff) << 8);//? Converting to decimal somehow...
                buf2a = (short) (buf2a & 0xff);
                //  short buf3 = (short) buf1a+buf2a;
            }
            ////
            AudioTrack at = new AudioTrack(STREAM_MUSIC, 44100, CHANNEL_OUT_MONO, ENCODING_PCM_16BIT, dataSize, MODE_STATIC);
            at.write(data, 0, data.length);
            int frames = data.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            at.setLoopPoints(0, frames, 3);
            at.play();
        } catch (IOException e) {
            throw e;
        }*/
    }

     volatile Runnable feedToBuffer = new Runnable()
    {
        @Override
        public synchronized void run() {
            int wavCounter=0;
            int wavFileNumber=0;
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);//Don't know what this does. But I guess its good.
            //While we have not stopped the audio, feed the buffer data2 to output.
            //All the buffers should be the same length, using data6 here but hopefully could have used any array:
            byte[] testBuffer=new byte[data6.length];//Should create an array of zeros, although there seems to be debate about whether the default value is
            //zero for local variables.
            int testBufferDataSize=dataSize6;
           // byte[] resultingBuffer;
            //Load testBuffer:
           /* try {
                Log.d(TAG + "loading", "Starting to load testbuffer");
                WavInfo wi = new WavInfo();
                Log.d(TAG + "loading", "Attempting to get resource");
                InputStream is = getResources().openRawResource(R.raw.beat1_mono);
                Log.d(TAG + "loading", "Got resource, attempting header");
                testBufferDataSize = wi.readHeader(is);
                Log.d(TAG + "loading", "Read header, attempting to read resource to byte buffer.");
                testBuffer = new byte[testBufferDataSize];
                is.read(testBuffer, 0,testBufferDataSize);
                is.close();
                Log.d(TAG + "loading", "Finished loading testbuffer");
            }
                catch(IOException e ){throw new RuntimeException(e);}*/
            //
            int i = 0;
           // byte[] temp;
            int startWritingPoint=0;
            int counter=0;
            while(!m_stop)
            {
                Log.d(TAG,"Letting tracks in.");
                //Should store a files about twice per second.
                if(wavCounter>2000)
                {
                    WavWriter ww = new WavWriter();
                    ww.setDataSize((long) testBuffer.length);
                    ww.setDataChunk(testBuffer);
                    ww.writeToWav("noiseDebug"+wavFileNumber+".wav");
                    wavFileNumber++;
                    wavCounter=0;
                }
                //temp = Arrays.copyOfRange(testBuffer,i,i+bufferLength);
                byte[] temp = new byte[bufferLength];
                byte[] resultingBuffer = new byte[temp.length];

                //For going in here:
                    //changedBuffer = true
                    // Then check for value 1 -16 - each number is one byte array.
                    //Load correct byte array.

                if(addBuffer1)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn1=true;
                    }
                    if(letIn1)
                    {
                        byte[] subBuffer = Arrays.copyOfRange(data1, addCounter1, addCounter1 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter1 += 2;
                            if (addCounter1 >= (data1.length - bufferLength)) {
                                addCounter1 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }

                if(addBuffer2)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn2=true;
                    }
                    if(letIn2) {
                        byte[] subBuffer = Arrays.copyOfRange(data2, addCounter2, addCounter2 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter2 += 2;
                            if (addCounter2 >= (data2.length - bufferLength)) {
                                addCounter2 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }

                if(addBuffer3)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn3=true;
                    }
                    if(letIn3) {
                        byte[] subBuffer = Arrays.copyOfRange(data3, addCounter3, addCounter3 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter3 += 2;
                            if (addCounter3 >= (data3.length - bufferLength)) {
                                addCounter3 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }

                if(addBuffer4)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn4=true;
                    }
                    if(letIn4) {
                        byte[] subBuffer = Arrays.copyOfRange(data4, addCounter4, addCounter4 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter4 += 2;
                            if (addCounter4 >= (data4.length - bufferLength)) {
                                addCounter4 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }

                if(addBuffer5)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn5=true;
                    }
                    if(letIn5) {
                        byte[] subBuffer = Arrays.copyOfRange(data5, addCounter5, addCounter5 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter5 += 2;
                            if (addCounter5 >= (data5.length - bufferLength)) {
                                addCounter5 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }
                //
                if(addBuffer6)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn6=true;
                    }
                    if(letIn6) {
                        byte[] subBuffer = Arrays.copyOfRange(data6, addCounter6, addCounter6 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            //Comment away to reapply start at current position in array:
                            addCounter6 += 2;
                            if (addCounter6 >= (data6.length - bufferLength)) {
                                addCounter6 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }

                if(addBuffer7)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn7=true;
                    }
                    if(letIn7) {
                        byte[] subBuffer = Arrays.copyOfRange(data7, addCounter7, addCounter7 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter7 += 2;
                            if (addCounter7 >= (data7.length - bufferLength)) {
                                addCounter7 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }
                if(addBuffer8)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn8=true;
                    }
                    if(letIn8) {
                        byte[] subBuffer = Arrays.copyOfRange(data8, addCounter8, addCounter8 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter8 += 2;
                            if (addCounter8 >= (data8.length - bufferLength)) {
                                addCounter8 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }
                if(addBuffer9)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn9=true;
                    }
                    if(letIn9) {
                        byte[] subBuffer = Arrays.copyOfRange(data9, addCounter9, addCounter9 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter9 += 2;
                            if (addCounter9 >= (data9.length - bufferLength)) {
                                addCounter9 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }
                //
                if(addBuffer10)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn10=true;
                    }
                    if(letIn10) {
                        byte[] subBuffer = Arrays.copyOfRange(data10, addCounter10, addCounter10 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);

                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter10 += 2;
                            if (addCounter10 >= (data10.length - bufferLength)) {
                                addCounter10 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }
                //
                if(addBuffer11)
                {
                    if(i%37200==0)//actually should be 37220, but i is always a multiple of bufferlength, which is 100.Fun fact : there is an asteroid called 18610 ArthurDent.
                    {
                        Log.d(TAG,"Letting tracks in.");
                        letIn11=true;
                    }
                    if(letIn11) {
                        byte[] subBuffer = Arrays.copyOfRange(data11, addCounter11, addCounter11 + bufferLength);
                        byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                        for (int m = 0; m < subBuffer.length; m += 2) {
                            short buf1a = temp[m + 1];
                            short buf2a = temp[m];
                            buf1a = (short) ((buf1a & 0xff) << 8);
                            buf2a = (short) (buf2a & 0xff);
                            short buf1b = subBuffer[m + 1];
                            short buf2b = subBuffer[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Dividing amplitude by half and writing to temporary buffer.

                            //Dividing amplitude by half and writing to temporary other buffer.
                            short dudette = (short) (buf1b + buf2b);
                            float fdudette = (float) (dudette / numberOfStreams);
                            dudette = (short) fdudette;
                            temporaryBufferWithAdjustedAmplitude[m] = (byte) dudette;
                            temporaryBufferWithAdjustedAmplitude[m + 1] = (byte) (dudette >> 8);
                            //Getting values from temporary buffer.

                            //Getting values from temporary other buffer.
                            buf1b = temporaryBufferWithAdjustedAmplitude[m + 1];
                            buf2b = temporaryBufferWithAdjustedAmplitude[m];
                            buf1b = (short) ((buf1b & 0xff) << 8);
                            buf2b = (short) (buf2b & 0xff);
                            //Adding buffers.
                            short buf1c = (short) (buf1a + buf1b);
                            short buf2c = (short) (buf2a + buf2b);
                            short res = (short) (buf1c + buf2c);
                            resultingBuffer[m] = (byte) res;
                            resultingBuffer[m + 1] = (byte) (res >> 8);
                            addCounter11 += 2;
                            if (addCounter11 >= (data11.length - bufferLength))
                            {
                                addCounter11 = 0;
                            }
                        }
                        temp = resultingBuffer;
                    }
                }

                Log.d(TAG,"writing to audioTrack");
                Log.d(TAG,"this is i: "+i+"");
                try {
                    m_audioTrack.write(temp, 0, temp.length);//To register changes faster, try writing shorter parts at a time.
                }
                catch(Exception e){throw e;}
                i=i+bufferLength;
                wavCounter++;
                if(i>outputBuffer.length-bufferLength)
                {
                    i=0;
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main4);
        try
        {
            fillAllBuffers();//Load data7, data8, data9.
           // bufferLength=data7.length;
        }
        catch (Exception e){}
        //Start: applies to activit_main3.xml

        ImageButton b1 = (ImageButton) findViewById(R.id.buttonOne);
        ImageButton b2 = (ImageButton) findViewById(R.id.buttonTwo);
        ImageButton b3 = (ImageButton) findViewById(R.id.buttonThree);
        ImageButton b4 = (ImageButton) findViewById(R.id.buttonFour);
        ImageButton b5 = (ImageButton) findViewById(R.id.buttonFive);
        ImageButton b6 = (ImageButton) findViewById(R.id.buttonSix);
        ImageButton b7 = (ImageButton) findViewById(R.id.buttonSeven);
        ImageButton b8 = (ImageButton) findViewById(R.id.buttonEight);
        ImageButton b9 = (ImageButton) findViewById(R.id.buttonNine);
        ImageButton b10 = (ImageButton) findViewById(R.id.buttonTen);
        ImageButton b11 = (ImageButton) findViewById(R.id.buttonEleven);
        ImageButton b12 = (ImageButton) findViewById(R.id.buttonTwelve);
        ImageButton b13 = (ImageButton) findViewById(R.id.buttonThirteen);
        ImageButton b14 = (ImageButton) findViewById(R.id.buttonFourteen);
        ImageButton b15 = (ImageButton) findViewById(R.id.buttonFifteen);
        ImageButton b16 = (ImageButton) findViewById(R.id.buttonSixteen);
        ImageButton b17 = (ImageButton) findViewById(R.id.buttonSeventeen);
        ImageButton b18 = (ImageButton) findViewById(R.id.buttonEighteen);
        ImageButton b19 = (ImageButton) findViewById(R.id.buttonNineteen);
        ImageButton b20 = (ImageButton) findViewById(R.id.buttonTwenty);


        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer1)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer1 = true;
                            startStreaming();
                            ((ImageButton) findViewById(R.id.buttonOne)).setImageDrawable(greenButtonLightOn);
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer1 = true;
                            ((ImageButton) findViewById(R.id.buttonOne)).setImageDrawable(greenButtonLightOn);
                        }

                    }
                    else if(addBuffer1)
                    {
                        addBuffer1=false;
                        numberOfStreams-=1.0;
                        addCounter1=0;
                        letIn1=false;
                        ((ImageButton) findViewById(R.id.buttonOne)).setImageDrawable(greenButtonLightOff);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer2)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer2 = true;
                            ((ImageButton) findViewById(R.id.buttonTwo)).setImageDrawable(greenButtonLightOn);
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer2 = true;
                            ((ImageButton) findViewById(R.id.buttonTwo)).setImageDrawable(greenButtonLightOn);
                        }

                    }
                    else if(addBuffer2)
                    {
                        addBuffer2=false;
                        numberOfStreams-=1.0;
                        addCounter2=0;
                        letIn2=false;
                        ((ImageButton) findViewById(R.id.buttonTwo)).setImageDrawable(greenButtonLightOff);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer3)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer3 = true;
                            startStreaming();
                            ((ImageButton) findViewById(R.id.buttonThree)).setImageDrawable(greenButtonLightOn);
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer3 = true;
                            ((ImageButton) findViewById(R.id.buttonThree)).setImageDrawable(greenButtonLightOn);
                        }

                    }
                    else if(addBuffer3)
                    {
                        addBuffer3=false;
                        numberOfStreams-=1.0;
                        addCounter3=0;
                        letIn3=false;
                        ((ImageButton) findViewById(R.id.buttonThree)).setImageDrawable(greenButtonLightOff);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer4)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer4 = true;

                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer4 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonFour)).setImageDrawable(yellowButtonLightOn);
                    }
                    else if(addBuffer4)
                    {
                        addBuffer4=false;
                        numberOfStreams-=1.0;
                        addCounter4=0;
                        letIn4=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonFour)).setImageDrawable(yellowButtonLightOff);
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer5)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer5 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer5 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonFive)).setImageDrawable(yellowButtonLightOn);
                    }
                    else if(addBuffer5)
                    {
                        addBuffer5=false;
                        numberOfStreams-=1.0;
                        addCounter5=0;
                        letIn5=false;
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonFive)).setImageDrawable(yellowButtonLightOff);
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer6)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer6 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer6 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonSix)).setImageDrawable(yellowButtonLightOn);
                    }
                    else if(addBuffer6)
                    {
                        addBuffer6=false;
                        numberOfStreams-=1.0;
                        addCounter6=0;
                        letIn6=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonSix)).setImageDrawable(yellowButtonLightOff);
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer7)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer7 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer7 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonSeven)).setImageDrawable(redButtonLightOn);
                    }
                    else if(addBuffer7)
                    {
                        addBuffer7=false;
                        numberOfStreams-=1.0;
                        addCounter7=0;
                        letIn7=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonSeven)).setImageDrawable(redButtonLightOff);
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer8)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer8 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer8 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonEight)).setImageDrawable(redButtonLightOn);
                    }
                    else if(addBuffer8)
                    {
                        addBuffer8=false;
                        numberOfStreams-=1.0;
                        addCounter8=0;
                        letIn8=false;
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonEight)).setImageDrawable(redButtonLightOff);
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer9)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer9 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer9 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonNine)).setImageDrawable(redButtonLightOn);
                    }
                    else if(addBuffer9)
                    {
                        addBuffer9=false;
                        numberOfStreams-=1.0;
                        addCounter9=0;
                        letIn9=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonNine)).setImageDrawable(redButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer10)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer10 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer10 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonTen)).setImageDrawable(redButtonLightOn);
                    }
                    else if(addBuffer10)
                    {
                        addBuffer10=false;
                        numberOfStreams-=1.0;
                        addCounter10=0;
                        letIn10=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonTen)).setImageDrawable(redButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer11)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer11 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer11 = true;
                        }
                        ((ImageButton) findViewById(R.id.buttonEleven)).setImageDrawable(redButtonLightOn);
                    }
                    else if(addBuffer11)
                    {
                        addBuffer11=false;
                        numberOfStreams-=1.0;
                        addCounter11=0;
                        letIn11=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                        ((ImageButton) findViewById(R.id.buttonEleven)).setImageDrawable(redButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer12)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer12=true;
                        ((ImageButton) findViewById(R.id.buttonTwelve)).setImageDrawable(purpleButtonLightOn);
                    }
                    else if(addBuffer12)
                    {
                        addBuffer12=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonTwelve)).setImageDrawable(purpleButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer13)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer13=true;
                        ((ImageButton) findViewById(R.id.buttonThirteen)).setImageDrawable(purpleButtonLightOn);
                    }
                    else if(addBuffer13)
                    {
                        addBuffer13=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonThirteen)).setImageDrawable(purpleButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer14)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer14=true;
                        ((ImageButton) findViewById(R.id.buttonFourteen)).setImageDrawable(purpleButtonLightOn);
                    }
                    else if(addBuffer14)
                    {
                        addBuffer14=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonFourteen)).setImageDrawable(purpleButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer15)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer15=true;
                        ((ImageButton) findViewById(R.id.buttonFifteen)).setImageDrawable(purpleButtonLightOn);
                    }
                    else if(addBuffer15)
                    {
                        addBuffer15=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonFifteen)).setImageDrawable(purpleButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer16)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer16=true;
                        ((ImageButton) findViewById(R.id.buttonSixteen)).setImageDrawable(purpleButtonLightOn);
                    }
                    else if(addBuffer16)
                    {
                        addBuffer16=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonSixteen)).setImageDrawable(purpleButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer17)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer17=true;
                        ((ImageButton) findViewById(R.id.buttonSeventeen)).setImageDrawable(blueButtonLightOn);
                    }
                    else if(addBuffer12)
                    {
                        addBuffer12=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonEighteen)).setImageDrawable(blueButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer18)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer18=true;
                        ((ImageButton) findViewById(R.id.buttonEighteen)).setImageDrawable(blueButtonLightOn);
                    }
                    else if(addBuffer18)
                    {
                        addBuffer18=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonEighteen)).setImageDrawable(blueButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer19)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer19=true;
                        ((ImageButton) findViewById(R.id.buttonNineteen)).setImageDrawable(blueButtonLightOn);
                    }
                    else if(addBuffer19)
                    {
                        addBuffer19=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonNineteen)).setImageDrawable(blueButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer20)
                    {
                        /*
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                        }*/
                        addBuffer20=true;
                        ((ImageButton) findViewById(R.id.buttonTwenty)).setImageDrawable(blueButtonLightOn);
                    }
                    else if(addBuffer20)
                    {
                        addBuffer20=false;
                        /*numberOfStreams-=1.0;
                        addCounter12=0;
                        letIn12=false;

                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }*/
                        ((ImageButton) findViewById(R.id.buttonTwenty)).setImageDrawable(blueButtonLightOff);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //End: applies to activity_main3.xml
        //START: applies to activity_main2.xml
        /*
        Button b1 = (Button) findViewById(R.id.buttonOne);
        Button b2 = (Button) findViewById(R.id.buttonTwo);
        Button b3 = (Button) findViewById(R.id.buttonThree);
        Button b4 = (Button) findViewById(R.id.buttonFour);
        Button b5 = (Button) findViewById(R.id.buttonFive);
        Button b6 = (Button) findViewById(R.id.buttonSix);
        Button b7 = (Button) findViewById(R.id.buttonSeven);
        Button b8 = (Button) findViewById(R.id.buttonEight);
        Button b9 = (Button) findViewById(R.id.buttonNine);
        Button b10 = (Button) findViewById(R.id.buttonTen);
        Button b11 = (Button) findViewById(R.id.buttonEleven);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer1)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer1 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer1 = true;
                        }
                        CharSequence playing = "Beat 1 on. : )";
                        ((Button) findViewById(R.id.buttonOne)).setText(playing);
                    }
                    else if(addBuffer1)
                    {
                        addBuffer1=false;
                        numberOfStreams-=1.0;
                        addCounter1=0;
                        letIn1=false;
                        CharSequence notPlaying = "Beat 1";
                        ((Button) findViewById(R.id.buttonOne)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer2)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer2 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer2 = true;

                        }
                        CharSequence playing = "Beat 3 on. : )";
                        ((Button) findViewById(R.id.buttonTwo)).setText(playing);
                    }
                    else if(addBuffer2)
                    {
                        addBuffer2=false;
                        numberOfStreams-=1.0;
                        addCounter2=0;
                        letIn2=false;
                        CharSequence notPlaying = "Beat 3";
                        ((Button) findViewById(R.id.buttonTwo)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer3)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer3 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer3 = true;
                        }
                        CharSequence playing = "Hihat on. : )";
                        ((Button) findViewById(R.id.buttonThree)).setText(playing);
                    }
                    else if(addBuffer3)
                    {
                        addBuffer3=false;
                        numberOfStreams-=1.0;
                        addCounter3=0;
                        letIn3=false;
                        CharSequence notPlaying = "Hihat";
                        ((Button) findViewById(R.id.buttonThree)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer4)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer4 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer4 = true;
                        }
                        CharSequence playing = "Sub bass 1 on. : )";
                        ((Button) findViewById(R.id.buttonFour)).setText(playing);
                    }
                    else if(addBuffer4)
                    {
                        addBuffer4=false;
                        numberOfStreams-=1.0;
                        addCounter4=0;
                        letIn4=false;
                        CharSequence notPlaying = "Sub Bass 1";
                        ((Button) findViewById(R.id.buttonFour)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer5)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer5 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer5 = true;
                        }
                        CharSequence playing = "Sub bass 2 on. : )";
                        ((Button) findViewById(R.id.buttonFive)).setText(playing);
                    }
                    else if(addBuffer5)
                    {
                        addBuffer5=false;
                        numberOfStreams-=1.0;
                        addCounter5=0;
                        letIn5=false;
                        CharSequence notPlaying = "Sub bass";
                        ((Button) findViewById(R.id.buttonFive)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer6)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer6 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer6 = true;
                        }
                        CharSequence playing = "Synth bass on. : )";
                        ((Button) findViewById(R.id.buttonSix)).setText(playing);
                    }
                    else if(addBuffer6)
                    {
                        addBuffer6=false;
                        numberOfStreams-=1.0;
                        addCounter6=0;
                        letIn6=false;
                        CharSequence notPlaying = "Synth bass";
                        ((Button) findViewById(R.id.buttonSix)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer7)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer7 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer7 = true;
                        }
                        CharSequence playing = "Boxy Synth on. : )";
                        ((Button) findViewById(R.id.buttonSeven)).setText(playing);
                    }
                    else if(addBuffer7)
                    {
                        addBuffer7=false;
                        numberOfStreams-=1.0;
                        addCounter7=0;
                        letIn7=false;
                        CharSequence notPlaying = "Boxy Synth";
                        ((Button) findViewById(R.id.buttonSeven)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer8)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer8 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer8 = true;
                        }
                        CharSequence playing = "Bright Strings on. : )";
                        ((Button) findViewById(R.id.buttonEight)).setText(playing);
                    }
                    else if(addBuffer8)
                    {
                        addBuffer8=false;
                        numberOfStreams-=1.0;
                        addCounter8=0;
                        letIn8=false;
                        CharSequence playing = "Bright Strings";
                        ((Button) findViewById(R.id.buttonEight)).setText(playing);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer9)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer9 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer9 = true;
                        }
                        CharSequence playing = "Chords on. : )";
                        ((Button) findViewById(R.id.buttonNine)).setText(playing);
                    }
                    else if(addBuffer9)
                    {
                        addBuffer9=false;
                        numberOfStreams-=1.0;
                        addCounter9=0;
                        letIn9=false;
                        CharSequence notPlaying = "Chords";
                        ((Button) findViewById(R.id.buttonNine)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer10)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer10 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer10 = true;
                        }
                        CharSequence playing = "Epic Brass on.:)";
                        ((Button) findViewById(R.id.buttonTen )).setText(playing);
                    }
                    else if(addBuffer10)
                    {
                        addBuffer10=false;
                        numberOfStreams-=1.0;
                        addCounter10=0;
                        letIn10=false;
                        CharSequence notPlaying = "Epic brass";
                        ((Button) findViewById(R.id.buttonTen)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer11)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer11 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer11 = true;
                        }
                        CharSequence playing = "Lead 1 on. : )";
                        ((Button) findViewById(R.id.buttonEleven)).setText(playing);
                    }
                    else if(addBuffer11)
                    {
                        addBuffer11=false;
                        numberOfStreams-=1.0;
                        addCounter11=0;
                        letIn11=false;
                        CharSequence notPlaying = "Lead 1";
                        ((Button) findViewById(R.id.buttonEleven)).setText(notPlaying);
                        if(numberOfStreams==0)
                        {
                            stopStreaming();
                        }                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        */

        //END APPLIES TO activity_main2.xml

       //START APPLIES TO activity_main.xml:
       /* b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio3();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio4();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    startStreaming();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    stopStreaming();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                  //  outputBuffer = addBufferToMix(data7,dataSize7,outputBuffer,"plusbuttonseven.wav");
                   // tempBuffer=outputBuffer;
                    addBuffer7=true;
                    Log.d(TAG,"set addBuffer7 to true");
                   // m_audioTrack.write(outputBuffer,0,outputBuffer.length);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    removeBuffer7=true;
                    Log.d(TAG,"set removeBuffer7 to true");
                   // outputBuffer=removeBufferFromMix(data9,dataSize9,outputBuffer,"minusbuttonnine.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    outputBuffer = addBufferToMix(data9,dataSize9,outputBuffer,"plusbuttonnine.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //END APPLIES TO activity_main.xml
        */
        //Strange automatically added google stuff:
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.david.audiotracktest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.david.audiotracktest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
        //
    }
    void startStreaming()
    {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;

        try
        {
            //Just doing all this to get the dataSize really (and feed to outputBuffer).
          /* WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize = wi.readHeader(is);
            data2 = new byte[dataSize];
            is.read(data2, 0, data2.length);
            is.close();*/
            outputBuffer = data2;
            int frames = data2.length / 2; //2 bytes per frame.*/
            m_stop = false;
            m_audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, bufferLength,
                    AudioTrack.MODE_STREAM);//100 is hardcoded buffer size in bytes. 'datasize' is size of sample.
            m_audioTrack.play();
            audioTrackThread = new Thread(feedToBuffer);
            audioTrackThread.start();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    void stopStreaming()
    {
        m_stop = true;
        m_audioTrack.stop();
        m_audioTrack.release();
    }
    //This should be called in onCreate.
    boolean fillAllBuffers() throws IOException
    {
        Log.d(TAG+"fill","Entering fill all buffers");
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            Log.d(TAG+"loading","Starting to load #1");
            WavInfo wi1 = new WavInfo();
            Log.d(TAG+"loading","Attempting to get resource");
            InputStream is1 = getResources().openRawResource(R.raw.beat1_mono);
            Log.d(TAG+"loading","Got resource, attempting header");
            dataSize1 = wi1.readHeader(is1);
            Log.d(TAG+"loading","Read header, attempting to read resource to byte buffer.");
            data1 = new byte[dataSize1];
            is1.read(data1, 0, data1.length);
            is1.close();
            Log.d(TAG+"loading","Finished loading #1");

            Log.d(TAG+"loading","Starting to load #2");
            WavInfo wi2 = new WavInfo();
            Log.d(TAG+"loading","Attempting to get resource");
            InputStream is2 = getResources().openRawResource(R.raw.beat3_mono);
            Log.d(TAG+"loading","Got resource, attempting header");
            dataSize2 = wi2.readHeader(is2);
            Log.d(TAG+"loading","Read header, attempting to read resource to byte buffer.");
            data2 = new byte[dataSize2];
            is2.read(data2, 0, data2.length);
            is2.close();
            Log.d(TAG+"loading","Finished loading #2");
            Log.d(TAG+"loading","Starting loading #3");
            //
            WavInfo wi3 = new WavInfo();
            InputStream is3 = getResources().openRawResource(R.raw.hihat);
            dataSize3 = wi3.readHeader(is3);
            data3 = new byte[dataSize3];
            is3.read(data3, 0, data3.length);
            is3.close();
            Log.d(TAG+"loading","Starting loading #4");
            //
            WavInfo wi4 = new WavInfo();
            InputStream is4 = getResources().openRawResource(R.raw.sub_bass1);
            dataSize4 = wi4.readHeader(is4);
            data4 = new byte[dataSize4];
            is4.read(data4, 0, data4.length);
            is4.close();

            WavInfo wi5 = new WavInfo();
            InputStream is5 = getResources().openRawResource(R.raw.sub_bass2);
            dataSize5 = wi5.readHeader(is5);
            data5 = new byte[dataSize5];
            is5.read(data5, 0, data5.length);
            is5.close();

            WavInfo wi6 = new WavInfo();
            InputStream is6 = getResources().openRawResource(R.raw.synth_bass);
            dataSize6 = wi6.readHeader(is6);
            data6 = new byte[dataSize6];
            is6.read(data6, 0, data6.length);
            is6.close();

            WavInfo wi7 = new WavInfo();
            InputStream is7 = getResources().openRawResource(R.raw.boxy_synth);
            dataSize7 = wi7.readHeader(is7);
            data7 = new byte[dataSize7];
            is7.read(data7, 0, data7.length);
            is7.close();

            WavInfo wi8 = new WavInfo();
            InputStream is8 = getResources().openRawResource(R.raw.bright_strings);
            dataSize8 = wi8.readHeader(is8);
            data8 = new byte[dataSize8];
            is8.read(data8, 0, data8.length);
            is8.close();

            WavInfo wi9 = new WavInfo();
            InputStream is9 = getResources().openRawResource(R.raw.chords);
            dataSize9 = wi9.readHeader(is9);
            data9 = new byte[dataSize9];
            is9.read(data9, 0, data9.length);
            is9.close();

            WavInfo wi10 = new WavInfo();
            InputStream is10 = getResources().openRawResource(R.raw.epic_brass);
            dataSize10 = wi10.readHeader(is10);
            data10 = new byte[dataSize10];
            is10.read(data10, 0, data10.length);
            is10.close();

            WavInfo wi11 = new WavInfo();
            InputStream is11 = getResources().openRawResource(R.raw.lead1);
            dataSize11 = wi11.readHeader(is11);
            data11 = new byte[dataSize11];
            is11.read(data11, 0, data11.length);
            is11.close();

            Log.d(TAG+"loading","Finished loading all 11 files.");
            //
        } catch (IOException e) {
            throw e;
        }
        Log.d(TAG+"fill","Exiting fill all buffers");
        return true;
    }
    synchronized byte[] addBufferToMix(byte[] buffer,int bufferSize,byte[] currentBuffer,String fileName)
    {
        Log.d(TAG+"add","Entering addBufferToMix");
        short resPrevious=0;
        byte[] resultingBuffer = new byte[bufferSize];

        for (int i = 0; i < buffer.length; i += 2) {

            short buf1a = currentBuffer[i + 1];
            short buf2a = currentBuffer[i];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = buffer[i + 1];
            short buf2b = buffer[i];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);

            short buf1c = (short) (buf1a + buf1b);
            short buf2c = (short) (buf2a + buf2b);

            short res = (short) (buf1c + buf2c);
            float temp = (float)res;
            float temp2 = temp/2;
            res = (short)temp2;

            if(res>10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    Log.d(TAG,"res:"+res+"");
                    res = resPrevious;
                }
            }
            if(res<-10000)
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    res = resPrevious;
                }
            }
            resPrevious=res;
            resultingBuffer[i] = (byte) res;
            resultingBuffer[i + 1] = (byte) (res >> 8);
        }
        WavWriter ww = new WavWriter();
        ww.setDataSize((long) bufferSize);
        ww.setDataChunk(resultingBuffer);
        ww.writeToWav(fileName);
        Log.d(TAG+"add","Exiting addBufferToMix");
        return resultingBuffer;
    }
    byte[] removeBufferFromMix(byte[] buffer,int bufferSize,byte[] currentBuffer,String fileName)
    {
        short resPrevious=0;
        byte[] resultingBuffer = new byte[bufferSize];
        for (int i = 0; i < buffer.length; i += 2) {

            short buf1a = currentBuffer[i + 1];
            short buf2a = currentBuffer[i];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = buffer[i + 1];
            short buf2b = buffer[i];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);

            short buf1c = (short) (buf1a - buf1b);
            short buf2c = (short) (buf2a - buf2b);

            short res = (short) (buf1c + buf2c);

            if(res>10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    Log.d(TAG,"res:"+res+"");
                    res = resPrevious;
                }
            }
            if(res<-10000)
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    res = resPrevious;
                }
            }
            resPrevious=res;
            resultingBuffer[i] = (byte) res;
            resultingBuffer[i + 1] = (byte) (res >> 8);
        }
        WavWriter ww = new WavWriter();
        ww.setDataSize((long) bufferSize);
        ww.setDataChunk(resultingBuffer);
        ww.writeToWav(fileName);
        byte[] returnBuffer = data2;//
        return returnBuffer;
    }
    public synchronized byte[] getBufferUpdate()
    {
        return tempBuffer;
    }
    //Adding array data7 at the correct place
    //increment until you have changed everything back to where
    //you started. Then set false.
    public synchronized byte[] addBufferToStream(byte[] newBuffer,byte[] currentBuffer,int i) {
        //i = position in the array that is next to be fed to the outputbuffer.
        //i + the 99 next elements will be written to the Audiobuffer.
        Log.d(TAG, "Entered addBufferToStream.");
        short resPrevious = 0;
        byte[] subBuffer = Arrays.copyOfRange(newBuffer, i, i + 100);
        byte[] resultingBuffer = new byte[subBuffer.length];

        for (int m = 0; m < subBuffer.length; m += 2) {
            //  Log.d(TAG,"this is index: "+m);
            short buf1a = currentBuffer[m + 1];
            short buf2a = currentBuffer[m];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = subBuffer[m + 1];
            short buf2b = subBuffer[m];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);

            short buf1c = (short) (buf1a + buf1b);
            short buf2c = (short) (buf2a + buf2b);

            short res = (short) (buf1c + buf2c);
            float temporary = (float) res;
            float temp2 = temporary / 2;
            res = (short) temp2;

            if (res > 10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
            {
                if ((res * resPrevious) < 0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    Log.d(TAG, "res:" + res + "");
                    res = resPrevious;
                }
            }
            if (res < -10000) {
                if ((res * resPrevious) < 0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    res = resPrevious;
                }
            }
            resPrevious = res;
            resultingBuffer[m] = (byte) res;
            resultingBuffer[m + 1] = (byte) (res >> 8);
            //counter += 2;
        }
        // temp=resultingBuffer;
        //Log.d(TAG, "temp just got changed.");
        //
        //outputBuffer=addBufferToMix(data7,dataSize7,outputBuffer,"thisFile");

        Log.d(TAG,"Exiting addBufferToStream. ");
        return resultingBuffer;
    }
    //Test for using a method outside the thread.
    public synchronized byte[] addByteArray(byte[] dataBuffer, byte[] temp,int i)
    {
        short resPrevious=0;
        byte[] subBuffer2 = Arrays.copyOfRange(data7,i,i+bufferLength);
        //Log.d(TAG,"this is testBuffer.length: "+testBuffer.length);
        byte[] temporaryBufferWithHalfAmplitude = new byte[bufferLength];
        byte[] temporaryOtherBufferWithHalfAmplitude = new byte[bufferLength];
        byte[] resultingBuffer = new byte[bufferLength];
        for (int m = 0; m < subBuffer2.length; m += 2)
        {
            short buf1a = temp[m + 1];
            short buf2a = temp[m];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = subBuffer2[m + 1];
            short buf2b = subBuffer2[m];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);
            //Dividing amplitude by half and writing to temporary buffer.
            short dude = (short) (buf1a + buf2a);
            float fdude = (float) (dude / 2.0);
            dude = (short) fdude;
            temporaryBufferWithHalfAmplitude[m] = (byte) dude;
            temporaryBufferWithHalfAmplitude[m + 1] = (byte) (dude >> 8);
            //Dividing amplitude by half and writing to temporary other buffer.
            short dudette = (short) (buf1b + buf2b);
            float fdudette = (float) (dudette / 2.0);
            dudette = (short) fdudette;
            temporaryOtherBufferWithHalfAmplitude[m] = (byte) dudette;
            temporaryOtherBufferWithHalfAmplitude[m + 1] = (byte) (dudette >> 8);
            //Getting values from temporary buffer.
            buf1a = temporaryBufferWithHalfAmplitude[m + 1];
            buf2a = temporaryBufferWithHalfAmplitude[m];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            //Getting values from temporary other buffer.
            buf1b = temporaryOtherBufferWithHalfAmplitude[m + 1];
            buf2b = temporaryOtherBufferWithHalfAmplitude[m];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);
            //Adding buffers.
            short buf1c = (short) (buf1a + buf1b);
            short buf2c = (short) (buf2a + buf2b);
            short res = (short) (buf1c + buf2c);
            //write to return buffer.
            resultingBuffer[m] = (byte) res;
            resultingBuffer[m + 1] = (byte) (res >> 8);
            addCounter7 += 2;
        }
            return resultingBuffer;
    }
}
