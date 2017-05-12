package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private RavensObject source;
    private RavensObject target;


    public RavensObject getSource() {
        return source;
    }

    public void setSource(RavensObject source) {
        this.source = source;
    }

    public RavensObject getTarget() {
        return target;
    }

    public void setTarget(RavensObject target) {
        this.target = target;
        
    }
    
    public void init()
    {

    }

    public ArrayList<RavImageAttribute> GetArrayFromObject(RavensObject obj) {
        ArrayList<RavImageAttribute> attrList = new ArrayList<RavImageAttribute>();

      	  for (String name : obj.getAttributes().keySet())
      	  {
      		  String value = obj.getAttributes().get(name);
      		  RavImageAttribute att = new RavImageAttribute();
      		  att.name = name;
      		  att.value = value;
      		  attrList.add(att);
      	  }
      	return attrList;
      
    }
    
    public RavImageAttribute getAttributeFromObject(RavensObject object, String character) {
    	ArrayList<RavImageAttribute> list = GetArrayFromObject(object);
    	for (RavImageAttribute attribute : list) {
            if (attribute.getName().equals(character))
                return attribute;
        }

        return null;
    }
    

    public ArrayList<RavImageAttribute> getDifferences() {
        ArrayList<RavImageAttribute> Differences = new ArrayList<RavImageAttribute>();

        for(RavImageAttribute tarAttr: GetArrayFromObject(target)) {
            boolean found = false;
            for(RavImageAttribute sourAttr: GetArrayFromObject(source)) {
                if(tarAttr.getName().equals(sourAttr.getName())) {
                    found = true;
                    if(!tarAttr.getValue().equals(sourAttr.getValue())) {
                    	Differences.add(tarAttr);
                    }
                }

            }
            if(!found) Differences.add(tarAttr);
        }

        return Differences;
    }

	public int GetWeight(ArrayList<RavImageAttribute> RavImageAttribute) {
		//To do - for future - use weight to give score. 
		boolean found;
		int score = RavImageAttribute.size();
		found = false;

/*		
		// if it is not a shape difference, if it is, score + 10
		for(RavImageAttribute attr :RavImageAttribute)
		{
			if (attr.getName().equals("shape"))
			{
				found = true;
			}
		}
		if (!found)
		{
			score = Math.round( score / 2 );
		}
		
		
		// if it is not a shape difference, if it is, score + 10
		for(RavImageAttribute attr :RavImageAttribute)
		{
			if (attr.getName().equals("angle"))
			{
				found = true;
			}
		}
		if (!found)
		{
			score = Math.round( score / 2 );
		}*/
		
		
		
		
		return score;
	}
    
}

