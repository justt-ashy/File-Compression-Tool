package com.example.File_compression_tool.huffman;

public class Node implements Comparable<Node>{      //comparable means that the node is comparable to another nodes
    Node left;        // left child of Huffman Tree
    Node right;       // right child of Huffman Tree
    char character;   //character that will be stored
    int frequency;    //frequency of the character

    //Constructor
    Node(final Node left, final Node right, final char character, final int frequency){
        this.character = character;
        if(frequency<0) throw new IllegalArgumentException("Error : Frequency must be >= 0");
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    //To check if node is leaf node
    public boolean isLeaf(){
        return this.left == null && this.right==null;
    }

    //Will decide how two nodes will be compared
    public int compareTo(final Node that) {     //here this -> current object and that -> other object
        final int compareFreq = Integer.compare(this.frequency, that.frequency);
        //to ensure nodes with less freq comes first in queue
        if(compareFreq != 0){
            return compareFreq;
        }
        //to compare charactes to keep order stable
        return Integer.compare(this.character,that.character);
    }

    //converts node to a string - mainly for debugging
    public String toString(){
        String str = "" + character;
        return str;
    }
}
