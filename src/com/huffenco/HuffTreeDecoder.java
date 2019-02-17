/**********************************************************
* @author  Richard Connolly
*          Dillon Feehan
* @version 1.0
* @since   2018-12
* Notes in README document in src folder of this project.
**********************************************************/


package com.huffenco;

public class HuffTreeDecoder {
    HuffTree tree;
    String toDecode;

    HuffTreeDecoder(HuffTree tree, String toDecode) {
        this.tree = tree;
        this.toDecode = toDecode;
    }

    String decode() {
        int len = toDecode.length();
        StringBuilder decoded = new StringBuilder();

        
        //contains current node of the tree which is being traversed
        HuffTreeNode current = tree.getRoot();

        for(int i = 0; i < len; i++) {
            //if the leaf node is reached
            if(current.nodeToLeft == null && current.nodeToRight == null) {
                decoded.append(current.character);
                current = tree.getRoot();
                i--;
            }
            else  {
                //if encoding is zero go left else go right
                if(toDecode.charAt(i) == '0')
                    current = current.nodeToLeft;
                else
                    current = current.nodeToRight;

                //invalid sequence condition
                if(i == len - 1) {
                    if(current.nodeToLeft == null && current.nodeToRight == null)
                        return (decoded.toString() + current.character);
                    else
                        return null;
                }
            }
        }

        return decoded.toString();
    }
}

