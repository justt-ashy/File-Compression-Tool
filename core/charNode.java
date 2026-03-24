package com.example.File_compression_tool.huffman;

//To store a character, its Huffman code (bit string) and its frequency.
public class charNode {
    charNode next;
    char ch;
    String bit_size;
    int frequency = 0;

    //next is not as parameter, as next of every node is null.
    charNode(final char ch, final String code, final int frequency){
        this.ch = ch;
        this.frequency = frequency;
        this.bit_size  = code;
        next = null;
    }
}
