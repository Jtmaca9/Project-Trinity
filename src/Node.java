
public class Node {
	int xpos, ypos, value;
	boolean closed, block;
	
	
	Node(int x, int y, int v, boolean c, boolean b){
		xpos = x;
		ypos = y;
		value = v;
		closed = c;
		block = b;
	}
	
	int getNodeValue(){
		return value;
	}
	int getNodeXpos(){
		return xpos;
	}
	int getNodepos(){
		return ypos;
	}
	void setNodeValue(int v){
		value = v;
	}
	void setNodeXpos(int x){
		 xpos = x;
	}
	void gstNodepos(int y){
		ypos = y;
	}


}
