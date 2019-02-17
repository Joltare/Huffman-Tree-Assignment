/**********************************************************
* @author  Richard Connolly
*          Dillon Feehan
* @version 1.0
* @since   2018-12
* Notes in README document in src folder of this project.
**********************************************************/


package com.huffenco;


/*****************************Node Comparator  Referenced in the Readme**********************************************/
import java.util.Comparator;
public class HuffNodeCompar 
implements Comparator<HuffTreeNode> {
    @Override
    public int compare(HuffTreeNode arg0, 
    		           HuffTreeNode arg1) {
              return (arg0.frequency - arg1.frequency);
    }
}




