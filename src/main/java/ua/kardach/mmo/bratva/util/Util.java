package ua.kardach.mmo.bratva.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ua.kardach.mmo.bratva.model.Inventory;

public class Util {
	
	private static Random random = new Random(System.currentTimeMillis());
	private static Map<String, String> imagePrefix = initImagePrefix();
	
	private static Map<String, String> initImagePrefix(){
		Map<String, String> imagePrefix = new HashMap<String, String>();
		imagePrefix.put("weapon", "in/wp/wp");
		imagePrefix.put("armor", "in/ar/ar");
		imagePrefix.put("auto", "in/au/au");
		imagePrefix.put("tr", "in/tr/tr");
		imagePrefix.put("podgon", "po/po");
		/*case 'gift':
			return 'gi/gi';
		case 'phone':
			return 'lf/in/tf/tf';
		case 'head':
			return 'lf/in/hd/hd';
		case 'breast':
			return 'lf/in/br/br';
		case 'neck':
			return 'lf/in/nk/nk';
		case 'finger':
			return 'lf/in/fg/fg';
		case 'arm':
			return 'lf/in/am/am';
		case 'belt':
			return 'lf/in/bl/bl';
		case 'leg':
			return 'lf/in/lg/lg';
		case 'shoes':
			return 'lf/in/sh/sh';
		case 'banda':
			return 'lf/lb/lb';
		case 'respect':
			return 'lf/rl/rl';
		case 'business':
			return 'bu/bu';
		case 'achieve':
			return 'ac/ac';
		case 'military':
			return 'mi';
		case 'cash':
		case 'money':
		case 'health':
		case 'energy':
		case 'power':
		case 'authority':
		case 'level':
		case 'attack':
		case 'protect':
		case 'ic_gift':
		case 'band':
		case 'bandam':
		case 'rank':
		case 'star':
		case 'win':
		case 'loose':
		case 'clock':
		case 'online':
		case 'pahan':
		case 'from':
		case 'to':
		case 'delete':
			return 'ic/'.$name;
		case 'avatar':
			return 'av';*/

		return imagePrefix;
	}
	
	public static String imagePrefix(String imageName){
		return imagePrefix.get(imageName);
	}
	
	public static String imageInventory(Inventory inventory){
		return imagePrefix(inventory.getCategory().getImageName());
	}
	
	public static String imageIndex(long index){
		 return String.format("%03d", index);
	}
	
	public static int rand(int start, int end){
		end++;
		return start + random.nextInt(end - start);
	}
}
