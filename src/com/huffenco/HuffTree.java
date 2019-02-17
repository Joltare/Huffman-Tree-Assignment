/**********************************************************
* @author  Richard Connolly
*          Dillon Feehan
* @version 1.0
* @since   2018-12
* Notes in README document in src folder of this project.
**********************************************************/

package com.huffenco;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffTree {
    private String toEncode;
    private HuffTreeNode root;

    HuffTree(String toEncode) {
        this.toEncode = toEncode;
    }

    
/****************************create all the leaf nodes and adds them to the min heap***********************************************/
    private PriorityQueue < HuffTreeNode > buildHeap() {
        PriorityQueue < HuffTreeNode > minHeap = new PriorityQueue < > (toEncode.length(), new HuffNodeCompar());

        
        
/****************************getting the frequency of each character (max array size 2'16 or 65536)********************************/
        int[] frequencies = new int[65536];
        for (int i = 0; i < toEncode.length(); i++)
            frequencies[(int) toEncode.charAt(i)]++;
        
/****************************create leaf nodes and inserting them in priority queue************************************************/
        for (int i = 0; i < 65536; i++) {
            if (frequencies[i] > 0) {
                HuffTreeNode leafNode = new HuffTreeNode((char) i, frequencies[i]);
                minHeap.add(leafNode);
            }
        }
        return minHeap;
    }
    
/****************************inserting them in priority queue**********************************************************************/
    private void buildHuffmanTree() {
        PriorityQueue < HuffTreeNode > minHeap = buildHeap();

        while (minHeap.size() > 1) {
            HuffTreeNode firstMin = minHeap.remove();
            HuffTreeNode secondMin = minHeap.remove();
            HuffTreeNode internalNode = new HuffTreeNode(firstMin.frequency + secondMin.frequency, firstMin, secondMin);

            minHeap.add(internalNode);
        }
        
/****************************remaining node is the root node of the tree***********************************************************/
        root = minHeap.remove();
    }
    HashMap < Character, String > getHuffmanEncoding() {
        buildHuffmanTree();
        HashMap < Character, String > map = new HashMap < > ();
        getHuffmanEncoding(root, "", map);
        return map;
    }
    
/**********************************************************************************************************************************/
    private void getHuffmanEncoding(HuffTreeNode root, String encoding, HashMap < Character, String > map) {
        if (root.nodeToLeft == null && root.nodeToRight == null) {
            map.put(root.character, encoding);
            return;
        }
        getHuffmanEncoding(root.nodeToLeft, encoding + "0", map);
        getHuffmanEncoding(root.nodeToRight, encoding + "1", map);
    }
   
/*****************************Calculate Entropy Value*******************************************************************************/
    double computeEntrpy(HashMap < Character, String > map, String inputText) {
        double entropyValue = 0;
        for (Map.Entry < Character, String > entry: map.entrySet()) {

            int count = 0;
            for (char j: inputText.toCharArray()) {
                if (j == entry.getKey()) count++;
            }
            double p = (double) count / (double) inputText.length();
            entropyValue += p * log2(1 / p);
        }
        return entropyValue;
    }
    
/****************************Calculate average codeword Length*************************************************************************/
    double computeAverageCodewordLength(HashMap < Character, String > map, String inputText) {
        double average = 0;
        for (Map.Entry < Character, String > entry: map.entrySet()) {

            int count = 0;
            for (char j: inputText.toCharArray()) {
                if (j == entry.getKey()) count++;
            }

            double p = (double) count / (double) inputText.length();
            average += entry.getValue().length() * p;
        }
        return average;
    }

    private double log2(double v) {
        return Math.log(v) / Math.log(2);
    }
    
/************************Calculate tree height ****************************************************************************************/
    private int getHeight(HuffTreeNode root) {
        if (root == null)
            return 0;

        int heightLeft = getHeight(root.nodeToLeft);
        int heightRight = getHeight(root.nodeToRight);

        return 1 + Math.max(heightLeft, heightRight);
    }
    
/***************************************************************************************************************************************/
    int getHeight() {
        return getHeight(root);
    }

    HuffTreeNode getRoot() {
        return root;
    }
}
