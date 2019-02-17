/**********************************************************
* @author  Richard Connolly
*          Dillon Feehan
* @version 1.0
* @since   2018-12
* Notes in README document in src folder of this project.
**********************************************************/


package com.huffenco;

public class HuffTreeNode {
    char character;
    int frequency;
/****************************Left & Right nodes on the tree ***********************************************************/
    HuffTreeNode nodeToLeft;
    HuffTreeNode nodeToRight;

/****************************Character & frequency to populate the tree ***********************************************************/
   HuffTreeNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
    HuffTreeNode(int frequency, HuffTreeNode nodeToLeft, HuffTreeNode nodeToRight) {
        this.character = '~';
        this.frequency = frequency;
        this.nodeToLeft = nodeToLeft;
        this.nodeToRight = nodeToRight;
    }
}
