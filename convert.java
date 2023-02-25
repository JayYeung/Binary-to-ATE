import java.io.*;

public class convert {
    public static void main(String[] args) {
        /*
        //creating the test input
        try {
            // create a writer
            FileOutputStream fos = new FileOutputStream(new File("IN.bin"));
        
            // write data to file (this is test data)
            fos.write("00000000 01 00 00 00 00 00 00 c0 5c 00 00 00 afb0 8f 1d |........ .......|".getBytes()); //missing '\' beacuse my compiler does not know how to deal with it
            fos.write("\n".getBytes());
            fos.write("00000010 12 48 00 21 12 4d 13 4a 15 e0 13 68 43 f0 10 03 |.H.!.M.J...hC...|".getBytes());
            fos.write("\n".getBytes());
            fos.write("00000020 13 60 0f 4b 1b 1f 1b 68 01 2b 10 d0 2b 68 1b 68 |.`.K...h.+..+h.h|".getBytes());
            fos.write("\n".getBytes());
            fos.write("00000030 10 29 01 da 08 c0 49 1c 13 68 1b 07 fcd4 13 68 |.)....I..h.....h|".getBytes());
            fos.write("\n".getBytes());        
            // close the writer
            fos.close();
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

        //actual program
        try {
            // create a reader
            FileInputStream fis = new FileInputStream(new File("IN.bin"));

            FileWriter writer = new FileWriter("OUT.txt");
        
            writer.write("jtag.drload(`CPU1LOAD_CMD_SIZE, (16'h0000 << 5) | (16'h0 << 4) | (16'h1 << 3) | 16'h2, 16'h0, 16'h0,rdata);\n");
            writer.write("jtag.serial_access(`CPU1LOAD_DATA);\n");

            String str="", str2;
            int ch;
            boolean useless=false;
            int location[]={6, 7, 4, 5, 2, 3, 0, 1};

            // read one byte at a time
            while ((ch = fis.read()) != -1) {
                if((char) ch == '|'){
                    if(!(str.length()==0)){
                        if(str.charAt(0)=='|'){
                            str=str.substring(2);
                        }
                        str = str.replaceAll("\\s", "");
                        for(int i=8;i<str.length();i+=8){
                            str2="";
                            for(int j=0;j<8;j++){
                                str2+=str.charAt(i+location[j]);
                            }
                            writer.write("jtag.drload(32, 32'h"+str2+", 8'h0, 8'h0, rdata);\n");
                        }
                    }
                    str="";
                    useless=!useless;
                }
                if(!useless){
                    str+=(char) ch;
                }
            }
        
            // close the reader
            fis.close();
            writer.close();
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}