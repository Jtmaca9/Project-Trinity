
public class PathFinder {
	Node nodes[][];
	Node closedList[];
	Node parent;
	Node path[];
	private String target;
	int width;
	int height;
	int targetX, targetY, curX, curY;
	boolean found;
	
	PathFinder(int w, int h, int cX, int cY, String t){
		target = t;
		width = w;
		height = h;
		curX = cX;
		curY = cY;
		init();
		
		
	}
	
	void init(){
		found = false;
		if(target == "player1"){
			targetX = ProjectTrinity.currentMap.player1.gridXpos;
			targetY = ProjectTrinity.currentMap.player1.gridYpos;
		}
		
		nodes = new Node[width][height];
		for (int k  = 0; k < width; k++){
			closedList = new Node[400];
		}
		for (int l  = 0; l < 400; l++){
			closedList[l] = new Node(0, 0,0,  false, true);
		}
		
		for (int i  = 0; i < width; i++){
			for (int j  = 0; j < height; j++){
				if(ProjectTrinity.currentMap.tiles[i][j].block){
					nodes[i][j] = new Node(i, j, 1000, false, true);
				}else{
					nodes[i][j] = new Node(i, j, 1000, false, false);
				}
			}
			
		}
		nodes[curX][curY] = new Node(curX, curY, 1000, true, false);
		parent = new Node(0, 0, 10000000, true, false);
		parent  = nodes[curX][curY];

		
			
	}
	

	public void updateTargetLocation(int tx, int ty) {
		targetX = tx;
		targetY = ty;
	
		
	}
	public void updateCurrenttLocation(int cx, int cy) {
		curX = cx;
		curY = cy;
	
		
	}
	
	public void parentSearch(){
		int lowest = 1000;
		int closed = 0;
		boolean found = false;
		int f = 0;
		Node temp;
		temp = new Node(0,0,0,false, true);
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				if(nodes[i][j].closed){
					closedList[closed] = nodes[i][j];
					closed++;
					}	
				}
			}
	
		
		for(int m = 0; m < 400; m++){
			if(closedList[m].value < lowest && closedList[m].block == false){
				lowest = closedList[m].value;
			}
		}
		for(int c = 0; c < 20; c++){
			if(found == false){
		for(int b = 0; b < 400; b++){
			if(lowest + c  == closedList[b].value  && closedList[b].block == false){
				for(int z = -1; z < 2; z++){
					for(int x = -1; x < 2; x++){
						if(z+x == 1 || z+x == -1){
							if(((closedList[b].xpos + z) >= 0) && ((closedList[b].ypos + x) >= 0) && ((closedList[b].xpos + z) < (width -1)) && (closedList[b].ypos + x) < (height -1)){
								if( nodes[closedList[b].xpos + z][closedList[b].ypos + x].block == false && nodes[closedList[b].xpos + z][closedList[b].ypos + x].closed == false){
									temp = closedList[b]; 
									found = true;
									break;
								}
							}
							}
						}
				}
			}
			}
		}
		}
		parent = temp;
		System.out.println("Parent " + parent.xpos + "," + parent.ypos + "," + lowest);
		nodes[parent.xpos][parent.ypos] = parent;
		
	}
	
	public void gridSearch(){
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				if(i+j == 1 || i+j == -1){
					if(((parent.xpos + i) > 0) && ((parent.ypos + j) > 0) && ((parent.xpos + i) < (width -1)) && ((parent.ypos + j) < (height -1))){
						if( nodes[parent.xpos + i][parent.ypos + j].block == false && nodes[parent.xpos + i][parent.ypos + j].closed == false){
							nodes[parent.xpos + i][parent.ypos + j].value = nodeValue(nodes[parent.xpos + i][parent.ypos + j]);
							nodes[parent.xpos + i][parent.ypos + j].closed = true;
							System.out.println(nodes[parent.xpos + i][parent.ypos + j].xpos + "," + nodes[parent.xpos + i][parent.ypos + j].ypos);
						}
					}
				}
				
			}
		}
	}
	
	public void findTarget(){
		if(nodes[targetX][targetY].closed == false && found == false){
			parentSearch();
			gridSearch();
		}else{
			found = true;
		}
		
	}
	
	
	public float getTrueDistance(Node n) {
		float trueDistance = (float)(Math.sqrt((Math.pow(n.xpos - targetX, 2) + Math.pow(n.ypos - targetY, 2))));
		return trueDistance;
	}
	
	
	public int get_nodeValue(int i, int j){
		return nodes[i][j].getNodeValue();
	}
	
	public int nodeValue(Node n){
		int p = (Math.abs(n.xpos - parent.xpos) + Math.abs(n.ypos - parent.ypos));
		int v = (Math.abs(n.xpos - targetX) + Math.abs(n.ypos - targetY));
		int nv = p + v;
		return nv;
		
	}

}
