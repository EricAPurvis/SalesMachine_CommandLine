package loader;

import java.io.File;
import java.util.Scanner;

public class LoadMap {
	
	private String path="res/Maps/";
	private String ext=".map";

/*	public void LoadIt(String name) {
		
		try {
			File file = new File(path+name+ext);
			Scanner input = new Scanner(file);
			
			String mode ="";
			
			while(input.hasNext()) {
				
				String temp = input.nextLine();
				
				if(temp.equals("dimensions")) {
					temp=input.nextLine();
					world.width = Integer.parseInt(temp.split(",")[0]);
					world.height = Integer.parseInt(temp.split(",")[1]);
					world.tiles = new Tiles(world.width, world.height, camera);
					world.objects = new Objects(world.width, world.height, camera);
					continue;
				}
				
				if(temp.equals("tiles")) {
					mode="tiles";
					continue;
				}
				if(temp.equals("objects")) {
					mode="objects";
					continue;
				}
				
				if(!temp.equals("end")) {
					String[] nums = temp.split(",");
					if(mode=="tiles") {
						world.tiles.tiles[Integer.parseInt(nums[0])][Integer.parseInt(nums[1])]=Integer.parseInt(nums[2]);
					}
					if(mode=="objects") {
						world.objects.objects[Integer.parseInt(nums[0])][Integer.parseInt(nums[1])]=Integer.parseInt(nums[2]);
					}
				}
				
			}
			
		}catch(Exception ex) {
			System.err.println("Load Error: \n"+ex);
		}
		
	}
	*/
	
}
