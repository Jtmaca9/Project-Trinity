
public class PathFinder {
	Node nodes[][];
	Node closedList[];
	Node parent, lastFound;
	Node path[];
	int pathLength;
	private String target;
	int width;
	int height;
	int targetX, targetY, curX, curY;
	boolean found, go, complete;
	
	PathFinder(int w, int h, int cX, int cY, String t){
		target = t;
		width = w;
		height = h;
		curX = cX;
		curY = cY;
		
		path = new Node[width *height];
		for (int c  = 0; c < width *height; c++){
			path[c] = new Node(0, 0,0,  false, true);
		}
		
		init();
		
		
	}
	
	void init(){
		pathLength = 0;
		found = false;
		go = false;
		if(target == "player1"){
			targetX = ProjectTrinity.currentMap.player1.gridXpos;
			targetY = ProjectTrinity.currentMap.player1.gridYpos;
		}else if(target == "player2"){
			targetX = ProjectTrinity.currentMap.player2.gridXpos;
			targetY = ProjectTrinity.currentMap.player2.gridYpos;
		}
		
		nodes = new Node[width][height];
		closedList = new Node[width *height];
		for (int l  = 0; l < width *height; l++){
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
		nodes[curX][curY].parentx = curX;
		nodes[curX][curY].parenty = curY;
		
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
		boolean foundu = false;
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
	
		
		for(int m = 0; m < width*height; m++){
			if(closedList[m].value < lowest && closedList[m].block == false){
				lowest = closedList[m].value;
			}
		}
		step:
		for(int c = 0; c < 200; c++){
			if(foundu == false){
		for(int b = 0; b < width*height; b++){
			if(lowest + c  == closedList[b].value  && closedList[b].block == false){
				for(int z = -1; z < 2; z++){
					for(int x = -1; x < 2; x++){
						if(z+x == 1 || z+x == -1){
							if(((closedList[b].xpos + z) >= 0) && ((closedList[b].ypos + x) >= 0) && ((closedList[b].xpos + z) < (width -2)) && (closedList[b].ypos + x) < (height -2)){
								if( nodes[closedList[b].xpos + z][closedList[b].ypos + x].block == false && nodes[closedList[b].xpos + z][closedList[b].ypos + x].closed == false){
									temp = closedList[b]; 
									foundu = true;
									break step;
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
		nodes[parent.xpos][parent.ypos].parent = true;
		
	}
	
	public void gridSearch(){
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				if(i+j == 1 || i+j == -1){
					if(((parent.xpos + i) >= 0) && ((parent.ypos + j) >= 0) && ((parent.xpos + i) <= (width -2)) && ((parent.ypos + j) <= (height -2))){
						if( nodes[parent.xpos + i][parent.ypos + j].block == false && nodes[parent.xpos + i][parent.ypos + j].closed == false){
							if(parent.xpos == (parent.xpos + i) && parent.ypos == (parent.ypos + j) -1){
								nodes[parent.xpos + i][parent.ypos + j].dir ="down";
					
//								if(parent.xpos == curX && parent.ypos == curY){
//									nodes[parent.xpos][parent.ypos].dir ="down";
//								}
							}else if(parent.xpos == (parent.xpos + i) +1 && parent.ypos == parent.ypos + j){
								nodes[parent.xpos + i][parent.ypos + j].dir ="left";
						
//								if(parent.xpos == curX && parent.ypos == curY){
//									nodes[parent.xpos][parent.ypos].dir ="left";
//								}
							}else if(parent.xpos == parent.xpos + i && parent.ypos == (parent.ypos + j) + 1){
								nodes[parent.xpos + i][parent.ypos + j].dir ="up";
							
//								if(parent.xpos == curX && parent.ypos == curY){
//									nodes[parent.xpos][parent.ypos].dir ="up";
//								}
							}else if (parent.xpos == (parent.xpos + i) -1 && parent.ypos == parent.ypos + j){
								nodes[parent.xpos + i][parent.ypos + j].dir ="right";
							
//								if(parent.xpos == curX && parent.ypos == curY){
//									nodes[parent.xpos][parent.ypos].dir ="right";
//								}
							}
							nodes[parent.xpos + i][parent.ypos + j].value = nodeValue(nodes[parent.xpos + i][parent.ypos + j]);
							nodes[parent.xpos + i][parent.ypos + j].closed = true;
							lastFound = nodes[parent.xpos + i][parent.ypos + j];
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
	
	
	public void createPath(){
		complete = false;
	path:
		for(int u = 0; u < 1000; u++){
			if(go == false){
				if( u == 0){
					path[u] = nodes[targetX][targetY];
					//pathLength++;
					System.out.println(nodes[targetX][targetY].dir);
					}else if(path[u - 1].dir == "up"){
						path[u] = nodes[path[u-1].xpos][path[u-1].ypos +1];
					//	pathLength++;
					}else if(path[u - 1].dir == "right"){
						path[u] = nodes[path[u-1].xpos - 1][path[u-1].ypos];
					//	pathLength++;
					}else if(path[u - 1].dir == "down"){
						path[u] = nodes[path[u-1].xpos][path[u-1].ypos - 1];
						//pathLength++;
					}else if(path[u - 1].dir == "left"){
						path[u] = nodes[path[u-1].xpos + 1][path[u-1].ypos];
					//	pathLength++;
				}
				
				if(path[u].xpos == curX && path[u].ypos == curY){
					if(!((curX == targetX) && (curY == targetY))){
					path[u].dir = nodes[path[u-1].xpos][path[u-1].ypos].dir;
				pathLength = u;
				go = true;
				complete = true;
				System.out.println("path found");	
				break path;
					}
			}
		}
			
			
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
