# Binary-to-ATE
Created a Perl program to convert firmware code from binary to ATE (automatic test equipment) code format to streamline test code loading flow.

I have code for both C++ and Perl implementation

Task Description: 
DFT function pattern conversion Flow

In this task, you will implement a script or program for converting a binary file into code loading
commands used for dft test. Your script/program will input content of a binary file, extract the data,
added some fixed commands, and do output. For each line of input file, it includes the input offset in
hexadecimal, followed by sixteen space-separated, two-column, hexadecimal bytes, followed by the
same sixteen bytes in %_p format enclosed in &#39;|&#39; characters. Only Hex data are needed and other
information need be discarded. For output file, the first two lines always constant. For remaining lines,
every 4 lines will contain the hex data of 1 line in input file. 
NOTE: the data order changes in the output. Please refer the example below for the format and order.
Your script/program can be done by any program language.

Example:

Input: IN.bin

00000000  01 00 00 00 00 00 00 c0  5c 00 00 00 afb0 8f 1d  |........\.......|

00000010  12 48 00 21 12 4d 13 4a  15 e0 13 68 43 f0 10 03  |.H.!.M.J...hC...|

00000020  13 60 0f 4b 1b 1f 1b 68  01 2b 10 d0 2b 68 1b 68  |.`.K...h.+..+h.h|

00000030  10 29 01 da 08 c0 49 1c  13 68 1b 07 fcd4 13 68  |.)....I..h.....h|

Output: OUT.sv

jtag.drload(`CPU1LOAD_CMD_SIZE, (16&#39;h0000 &lt;&lt; 5) | (16&#39;h0 &lt;&lt; 4) | (16&#39;h1 &lt;&lt; 3) | 16&#39;h2, 16&#39;h0,
16&#39;h0,rdata);

jtag.serial_access(`CPU1LOAD_DATA);

jtag.drload(32, 32&#39;h00000001, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;hc0000000, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h0000005c, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h1d8fb0af, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h21004812, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h4a134d12, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h6813e015, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h0310f043, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h4b0f6013, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h681b1f1b, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;hd0102b01, 8&#39;h0, 8&#39;h0, rdata);

jtag.drload(32, 32&#39;h681b682b, 8&#39;h0, 8&#39;h0, rdata);
