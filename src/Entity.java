
public class Entity {
	String name;
	float vxpos;
	float vypos;
	float sxpos;
	float sypos;
	int gridXpos;
	int gridYpos;
	
	Entity(String n){
		name = n;
	}
	
	
	float get_vxpos(){
		return vxpos;
	}
	float get_vypos(){
		return vypos;
	}
	float get_sxpos(){
		return sxpos;
	}
	float get_sypos(){
		return sypos;
	}
	int get_gridXpos(){
		return gridXpos;
	}
	int get_gridYpos(){
		return gridYpos;
	}
	void set_vxpos(float vxp){
		vxpos = vxp;
	}
	void set_vypos(float vyp){
		vypos = vyp;
	}
	void set_sxpos(float sxp){
		sxpos = sxp;
	}
	void set_sypos(float syp){
		sypos = syp;
	}
	void set_gridXpos(int gxp){
		gridXpos = gxp;
	}
	void set_gridYpos(int gyp){
		gridYpos = gyp;
	}
	
	
	
	

}
