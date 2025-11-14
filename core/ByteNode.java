package com.example.File_compression_tool.huffman;

//This is like a container for(Byte,Freq) that Priority Queue can sort to build Huffman Tree
public class ByteNode implements Comparable<ByteNode>{
    Byte data;
    int frequency;
    ByteNode left;
    ByteNode right;

    public ByteNode(Byte data, int weight){
        this.data = data;
        this.frequency = weight;    //weight of node
    }
    @Override
    public int compareTo(ByteNode o) {
        return this.frequency - o.frequency;
    }
}
