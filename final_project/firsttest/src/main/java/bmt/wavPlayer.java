package bmt;

import java.io.*;
import javax.sound.sampled.*;

public class wavPlayer implements Runnable{
    private String path;
    private AudioFormat format = null;
    private AudioInputStream aistream = null;
    private float sampleRate = 0;
    private float framelength = 0;
    private DataLine.Info datalineinfo = null;
    private SourceDataLine dataline = null;
    private boolean pause = false;
    private boolean stop = false;
    private int played = 0;
    private int play_from = 0;
    private boolean pass = false;
    private boolean playing = false;

    public wavPlayer(){}

    public void setPath(String p){
        path = p;
    }

    public String getPath(){
        return path;
    }

    public void stopplaying(){
        stop = true;
    }

    public boolean isStop(){
        return stop;
    }

    public int getPlayed(){
        return played;
    }

    public void playFrom(int t){
        play_from = t;
    }
    
    public float getSecLength(){
        return framelength / sampleRate;
    }

    public void setPause(boolean p){
        pause = p;
    }

    public boolean isPaused(){
        return pause;
    }

    public boolean isPlaying(){
        return playing;
    }

    public void set(){
        try{
            aistream = AudioSystem.getAudioInputStream(new File(path));
            format = aistream.getFormat();
            sampleRate = format.getSampleRate();
            framelength = aistream.getFrameLength();
            datalineinfo = new DataLine.Info(SourceDataLine.class, format);
            dataline = (SourceDataLine)AudioSystem.getLine(datalineinfo);
        }
        catch(LineUnavailableException err){
            System.out.println(err);
        }
        catch(UnsupportedAudioFileException err){
            System.out.println(err);
        }
        catch(IOException err){
            System.out.println(err);
        }
    }

    public void run(){
        try{
            byte[] bytes = new byte[512];
            int length = 0;
            dataline.open(format);
            dataline.start();
            played = 0;
            playing = true;
            stop = false;
            while(stop == false){
                if(pause == true){
                    Thread.sleep(1500);
                    continue;
                }
                if(pass == true){
                    if((length = aistream.read(bytes)) <= 0){
                        break;
                    }
                    played += 1;
                    continue;
                }
                if(played < play_from){
                    if((length = aistream.read(bytes)) > 0){
                        played += 1;
                        continue;
                    }
                    else{
                        break;
                    }
                }
                if((length = aistream.read(bytes)) > 0){
                    dataline.write(bytes, 0, length);
                    played += 1;
                }
                else{
                    break;
                }
            }
            stop = true;
            aistream.close();
            dataline.drain();
            dataline.close();

            aistream = null;
            format = null;
            datalineinfo = null;
            dataline = null;
        }
        catch(Exception err){
            System.out.println("Error");
            err.printStackTrace();
        }
        catch(Error err){
            System.out.println("Error: can not play the audio!");
            err.printStackTrace();
        }

    }
}