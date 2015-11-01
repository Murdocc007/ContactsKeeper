package contactskeeper.achaturvedi.com.contactskeeper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by aditya on 11/1/15.
 */
public class FileWriter {

    Context fileContext;

    FileWriter(Context fileContext){
        this.fileContext=fileContext;
    }


    public void setContactObject(ArrayList<ContactDataModel> inf){
        clearContents();
        try {

            final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" );
            File foo=new File(dir,"foo.txt");

            PrintStream pr = new PrintStream(fileContext.openFileOutput(foo.getName(), Context.MODE_PRIVATE));

            for(ContactDataModel temp:inf){
                String str;

                str=temp.getFname();
                str="(firstName:"+str+")";
                pr.print(str);

                str=temp.getLname();
                str="(lastName:"+str+")";
                pr.print(str);

                str=String.valueOf(temp.getPhone());
                str="(phoneNumber:"+str+")";
                pr.print(str);

                str=temp.getEmail();
                str="(emailAddress:"+str+")";
                pr.print(str);

                pr.println("");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ContactDataModel> getContactObject(){
        ArrayList<ContactDataModel> inf=new ArrayList<ContactDataModel>();
        String str;

        final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" );
        File foo=new File(dir,"foo.txt");

        if(!foo.exists()) {
            try {
                foo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            FileInputStream fis=fileContext.openFileInput(foo.getName());
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            while((str=br.readLine())!=null)
            {
                inf.add(convertStringtoObject(str));
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return inf;
    }


    private ContactDataModel convertStringtoObject(String s){
        HashMap<String,String> temp=convertStringtoMap(s);
        ContactDataModel inf=new ContactDataModel();
        for(Map.Entry<String, String> e : temp.entrySet()){


            if(e.getKey().equals("firstName")){
                inf.setFname(e.getValue());
            }

            if(e.getKey().equals("lastName")){
                inf.setLname(e.getValue());
            }

            if(e.getKey().equals("phoneNumber")){
                inf.setPhone(e.getValue());
            }

            if(e.getKey().equals("emailAddress")){
                inf.setEmail(e.getValue());
            }

        }
        return inf;

    }


    private HashMap<String,String> convertStringtoMap(String s){
        HashMap<String,String> temp=new HashMap<String,String>();
        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(s);
        while(m.find()) {
            String [] str=m.group(1).split(":",2);
            temp.put(str[0],str[1]);
        }
        return temp;
    }


    private void clearContents(){
        final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" );
        File foo=new File(dir,"foo.txt");
        try{
            FileOutputStream writer = fileContext.openFileOutput(foo.getName(),Context.MODE_PRIVATE);

            writer.write(("").getBytes());

            writer.close();
        }catch(FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
