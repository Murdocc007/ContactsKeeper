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


//Name: Aditya Mahajan
//NetId: axm156630
//Date: 11/2/2015
public class FileWriter {

    Context fileContext;//filecontext used to write the contents

    //passing the file context from the activity page
    FileWriter(Context fileContext){
        this.fileContext=fileContext;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //this function takes an arraylist of ContactDataModel Object and  writes it to the txt file
    public void setContactObject(ArrayList<ContactDataModel> inf){
        clearContents();
        try {

            final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" );
            File foo=new File(dir,"foo2.txt");//txtfilename

            PrintStream pr = new PrintStream(fileContext.openFileOutput(foo.getName(), Context.MODE_PRIVATE));

            for(ContactDataModel temp:inf){
                String str;

                //string is stored in the format->(KeyLabel:Keyvalue)
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


                str=temp.getId();
                str="(id:"+str+")";
                pr.print(str);

                pr.println("");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //Gets the ContactDataModel Objects as
    // arraylist from the txt file
    public ArrayList<ContactDataModel> getContactObject(){
        ArrayList<ContactDataModel> inf=new ArrayList<ContactDataModel>();
        String str;

        final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/Download" );
        File foo=new File(dir,"foo2.txt");

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

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //this function takes the string s read from the txt file
    // and converts it to a ContactDatModel Object
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

            if(e.getKey().equals("id")){
                inf.setId(e.getValue());
            }

        }
        return inf;

    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //this function takes a string s of the txt file
    // and converts that string s into a key value pair
    private HashMap<String,String> convertStringtoMap(String s){
        HashMap<String,String> temp=new HashMap<String,String>();
        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(s);
        while(m.find()) {
            String [] str=m.group(1).split(":",2);
            temp.put(str[0],str[1]);
        }
        return temp;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //Clears the contents of the txt file
    private void clearContents(){
        final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" );
        File foo=new File(dir,"foo2.txt");
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

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //records written in the txt file have a unique id associated with them,
    // this function gets the maximum of all the ids present in the txt file
    public String getMaxId() {
        ContactDataModel temp;
        String str;
        int maxid=0;
        final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/Download" );
        File foo=new File(dir,"foo2.txt");
        try{
            FileInputStream fis=fileContext.openFileInput(foo.getName());
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            while((str=br.readLine())!=null)
            {
                temp=convertStringtoObject(str);
                if(Integer.parseInt(temp.getId())>maxid){
                    maxid=Integer.parseInt(temp.getId());
                }
            }
            //temp=convertStringtoObject(str);
        }catch(IOException e){
            e.printStackTrace();
        }

        return String.valueOf(maxid);
    }


}
